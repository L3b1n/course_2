#include <queue>
#include <vector>
#include <numeric>
#include <iostream>
#include <algorithm>

class MaximalFlow
{
public:
    MaximalFlow() : count_summit(0), count_edge(0) {}

    friend std::istream& operator >> (std::istream& in, MaximalFlow& a)
    {
        in >> a.count_summit >> a.count_edge;
        a.network.resize(a.count_summit + 1);
        a.adjacency_list.resize(a.count_summit + 1);
        for(int i = 0; i < a.count_edge; i++)
        {
            int begin, end, capacity;
            in >> begin >> end >> capacity;
            a.adjacency_list[begin].push_back(std::make_pair(end, capacity));
        }
        return in;
    }

    inline long long maximalFlow(){ return maximalFlow(1, count_summit);}

private:
    struct Node
    {
        Node() : edge(std::make_pair(0, 0)), capacity(0), flow(0) {}
        Node(std::pair<int, int> edge, int capacity, int flow) : edge(edge), capacity(capacity), flow(flow) {}

        std::pair<int, int> edge;
        int capacity;
        int flow;
    };

private:
    int count_edge, count_summit;
    std::vector<int> priority;
    std::vector<Node> work_list;
    std::vector<bool> is_visited;
    std::vector<std::vector<int>> network;
    std::vector<std::vector<std::pair<int, int>>> adjacency_list;

    long long maximalFlow(int begin, int end)
    {
        if(begin == end){ return 0;}
        BuildNetwork();
        long long flow = 0ll;
        while(true)
        {
            priority.assign(count_summit + 1, INT_MAX);
            is_visited.assign(count_summit + 1, false);
            BFS(begin);
            if(!is_visited[end]){ break;}
            std::vector<int> temp_path = NewPath(end);
            int temp_flow = work_list[temp_path[0]].capacity - work_list[temp_path[0]].flow;
            std::for_each(++temp_path.begin(), temp_path.end(), 
                [this, &temp_flow, &temp_path](const int& summit){
                    temp_flow = std::min(temp_flow, work_list[summit].capacity - work_list[summit].flow);
                }
            );
            FlowIncrease(temp_path, temp_flow);
            flow += temp_flow;
        }
        return flow;
    }

    void BFS(int summit)
    {
        is_visited[summit] = true;
        std::for_each(network[summit].begin(), network[summit].end(), 
            [this](int temp_summit){
                if(!is_visited[work_list[temp_summit].edge.second] && (work_list[temp_summit].capacity - work_list[temp_summit].flow) > 0)
                {
                    priority[work_list[temp_summit].edge.second] = temp_summit;
                    BFS(work_list[temp_summit].edge.second);
                }
            }
        );
    }

    void BuildNetwork()
    {
        int i = 1;
        std::for_each(++network.begin(), network.end(), 
            [this, &i](std::vector<int>& vec){
                std::for_each(adjacency_list[i].begin(), adjacency_list[i].end(), 
                    [this, &i, &vec](const std::pair<int, int>& temp_vec){
                        vec.push_back(work_list.size());
                        work_list.emplace_back(std::make_pair(i, temp_vec.first), temp_vec.second, 0);
                        network[temp_vec.first].push_back(work_list.size());
                        work_list.emplace_back(std::make_pair(temp_vec.first, i), 0, 0);
                    }
                );
                i++;
            }
        );
    }

    std::vector<int> NewPath(int summit)
    {
        std::vector<int> temp_path;
        while(priority[summit] != INT_MAX)
        {
            temp_path.push_back(priority[summit]);
            summit = work_list[priority[summit]].edge.first;
        }
        return temp_path;
    }

    void FlowIncrease(const std::vector<int>& temp_path, int temp_flow)
    {
        std::for_each(temp_path.begin(), temp_path.end(), 
            [this, &temp_flow](int summit){
                work_list[summit].flow += temp_flow; 
                work_list[summit ^ 1].flow -= temp_flow;
            }
        );
    }
};

int main()
{
    std::freopen("input.txt", "r", stdin);
    // std::freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    MaximalFlow maximalFlow;
    std::cin >> maximalFlow;
    std::cout << maximalFlow.maximalFlow() << "\n";
    // std::for_each(work_list.begin(), work_list.end(), [](const Node& temp){ std::cout << temp.edge.first << " " << temp.edge.second << " " << temp.capacity << " " << temp.flow << "\n";});
    // std::for_each(network.begin(), network.end(), [](const std::vector<int>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";});
    return 0;
}
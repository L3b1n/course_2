#include <vector>
#include <numeric>
#include <iostream>
#include <algorithm>

class MaximalFlow
{
public:
    MaximalFlow() : count_edge(0), count_summit(0) {}

    friend std::istream& operator >> (std::istream& in, MaximalFlow& a)
    {
        in >> a.count_summit >> a.count_edge;
        a.network.resize(a.count_summit);
        for(int i = 0; i < a.count_edge; i++)
        {
            int begin, end, capacity;
            in >> begin >> end >> capacity; begin--; end--;
            a.network[begin].push_back(a.work_list.size());
            a.work_list.emplace_back(std::make_pair(begin, end), capacity, 0);
            a.network[end].push_back(a.work_list.size());
            a.work_list.emplace_back(std::make_pair(end, begin), 0, 0);
        } 
        return in;
    }

    inline long long maximalFlow(){ return maximalFlow(0, count_summit - 1);}

private:
    struct Node
    {
        Node() : edge(std::make_pair(0, 0)), capacity(0), flow(0) {}
        Node(std::pair<int, int> edge, int capacity, int flow) : edge(edge), capacity(capacity), flow(flow) {}

        std::pair<int, int> edge;
        int capacity;
        long long flow;
    };

private:
    int count_edge, count_summit;
    std::vector<int> counter;
    std::vector<int> priority;
    std::vector<Node> work_list;
    std::vector<std::vector<int>> network;

    long long maximalFlow(int begin, int end)
    {
        if(begin == end){ return 0;}
        long long flow = 0ll;
        for(; BFS(begin, end);)
        {
            counter.assign(count_summit, 0);
            while(long long temp_flow = DFS(begin, end, LLONG_MAX)){ flow += temp_flow;}
        }
        return flow;
    }

    int BFS(int begin, int end)
    {
        priority.assign(count_summit, -1);
        int first_index = 0, second_index = 0;
        std::vector<int> temp_vec(count_summit, 0);
        temp_vec[second_index++] = begin;
        priority[begin] = 0;
        while(first_index < second_index && priority[end] == -1)
        {
            std::for_each(network[temp_vec[first_index]].begin(), network[temp_vec[first_index]].end(), 
                [this, &temp_vec, &second_index, &first_index](const int& temp){
                    if(priority[work_list[temp].edge.second] == -1 && work_list[temp].capacity > work_list[temp].flow)
                    {
                        temp_vec[second_index++] = work_list[temp].edge.second;
                        priority[work_list[temp].edge.second] = priority[temp_vec[first_index]] + 1;
                    }
                }
            );
            ++first_index;
        }
        return priority[end] != -1;
    }

    long long DFS(int summit, int end, long long flow)
    {
        if(!flow){ return 0;}
        if(summit == end){ return flow;}
        for(; counter[summit] < network[summit].size();)
        {
            if(priority[work_list[network[summit][counter[summit]]].edge.second] == priority[summit] + 1)
            {
                long long temp_flow = DFS(work_list[network[summit][counter[summit]]].edge.second, end, std::min(work_list[network[summit][counter[summit]]].capacity - work_list[network[summit][counter[summit]]].flow, flow));
                if(temp_flow)
                {
                    work_list[network[summit][counter[summit]]].flow += temp_flow;
                    work_list[network[summit][counter[summit]] ^ 1].flow -= temp_flow;
                    return temp_flow;
                }
            }
            counter[summit]++;
        }
        return 0;
    }
};

int main()
{
    std::ios_base::sync_with_stdio(0);
    std::freopen("input.txt", "r", stdin);
    // std::freopen("output.txt", "w", stdout);
    std::cin.tie(0);
    std::cout.tie(0);
    MaximalFlow maximalFlow;
    std::cin >> maximalFlow;
    std::cout << maximalFlow.maximalFlow() << "\n";
    // std::for_each(work_list.begin(), work_list.end(), [](const Node& temp){ std::cout << temp.edge.first << " " << temp.capacity << " " << temp.flow << " " << temp.edge.second << "\n";});
    // std::for_each(network.begin(), network.end(), [](const std::vector<int>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";});
    return 0;
}
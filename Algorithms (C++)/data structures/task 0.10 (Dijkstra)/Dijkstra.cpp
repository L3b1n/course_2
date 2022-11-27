#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::cin.tie(0);
    std::cout.tie(0);
    int count_edge, count_summit;
    std::cin >> count_summit >> count_edge;
    std::vector<std::vector<std::pair<int, int>>> edge(count_summit);
    std::vector<long long> answer(count_summit, LLONG_MAX);
    std::for_each(edge.begin(), (edge.begin() + count_edge), 
        [&edge](std::vector<std::pair<int, int>>& vec){
            int begin, end, value;
            std::cin >> begin >> end >> value;
            edge[--begin].push_back(std::make_pair(--end, value));
            edge[end].push_back(std::make_pair(begin, value));
        }
    );
    std::set<std::pair<long long, long long>> set;
    set.insert(std::make_pair(answer[0] = 0ll, 0));
    while(!set.empty())
    {
        int end = set.begin()->second; set.erase(set.begin());
        std::for_each(edge[end].begin(), edge[end].end(), 
            [&end, &answer, &set](std::pair<int, int>& temp){
                int begin = temp.first;
                long long value = temp.second;
                if(answer[end] + value < answer[begin])
                { 
                    set.erase(std::make_pair(answer[begin], begin)); 
                    answer[begin] = answer[end] + value; 
                    set.insert(std::make_pair(answer[begin], begin));
                }
            }
        );
    }
    // std::for_each(edge.begin(), edge.end(), [](const std::vector<std::pair<int, int>>& vec){ std::for_each(vec.begin(), vec.end(), [](const std::pair<int, int>& temp){ std::cout << temp.second << " ";}); std::cout << "\n";});
    // std::copy(answer.begin(), answer.end(), std::ostream_iterator<long long>(std::cout, " "));
    std::cout << answer[count_summit - 1];
    return 0;
}
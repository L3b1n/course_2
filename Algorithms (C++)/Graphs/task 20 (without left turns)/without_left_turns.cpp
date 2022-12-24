#include <cmath>
#include <queue>
#include <vector>
#include <iostream>
#include <algorithm>

namespace std
{
    template<typename T1, typename T2>
    std::istream& operator >> (std::istream& in, std::pair<T1, T2>& temp)
    {
        in >> temp.first >> temp.second;
        return in;
    }
}

int count_intersection, count_road;
std::queue<long long> temp_queue;
std::vector<std::vector<std::pair<long long, bool>>> matrix;
std::vector<std::pair<long long, long long>> intersection_coodrinates;

void DFS(long long start, long long start_intersection, long long finish_intersection)
{
    if(start_intersection == finish_intersection)
    {
        std::cout << "Yes\n";
        temp_queue.push(finish_intersection);
        for(; !temp_queue.empty();)
        {
            std::cout << temp_queue.front() << " ";
            temp_queue.pop();
        }
        exit(0);
    }
    int i = 1;
    std::for_each(++intersection_coodrinates.begin(), intersection_coodrinates.end(), 
        [&i, &start, start_intersection, &finish_intersection](std::pair<long long, long long>& temp_intersection){
            if(i != start_intersection && matrix[start_intersection][i].first == 1 && matrix[start_intersection][i].second)
            {
                long long temp = (temp_intersection.first - intersection_coodrinates[start].first) * (intersection_coodrinates[start_intersection].second - intersection_coodrinates[start].second) - 
                                 (temp_intersection.second - intersection_coodrinates[start].second) * (intersection_coodrinates[start_intersection].first - intersection_coodrinates[start].first);
                if(temp >= 0)
                {
                    bool condition = true;
                    if(temp == 0)
                    {
                        long long temp1 = std::sqrt(std::pow(std::abs(temp_intersection.first - intersection_coodrinates[start_intersection].first), 2) +
                                                    std::pow(std::abs(temp_intersection.second - intersection_coodrinates[start_intersection].second), 2));
                        long long temp2 = std::sqrt(std::pow(std::abs(intersection_coodrinates[start].first - intersection_coodrinates[start_intersection].first), 2) +
                                                    std::pow(std::abs(intersection_coodrinates[start].second - intersection_coodrinates[start_intersection].second), 2)) + 
                                          std::sqrt(std::pow(std::abs(temp_intersection.first - intersection_coodrinates[start].first), 2) + 
                                                    std::pow(std::abs(temp_intersection.second - intersection_coodrinates[start].second), 2));               
                        if(temp1 == temp2){ condition = false;}
                    }
                    if(condition)
                    {
                        temp_queue.push(start_intersection);
                        matrix[start_intersection][i].second = false;
                        DFS(start_intersection, i, finish_intersection);
                        temp_queue.pop();
                    }
                }
            }
            i++;
        }
    );
}

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::cin.tie(0);
    std::cout.tie(0);
    std::cin >> count_intersection >> count_road;
    intersection_coodrinates.resize(count_intersection + 1);
    matrix.assign(count_intersection + 1, std::vector<std::pair<long long, bool>>(count_intersection + 1));
    for(int i = 0; i < count_road; i++)
    {
        long long number_of_first_intersection, number_of_second_intersection;
        std::pair<long long, long long> first_intersection, second_intersection;
        std::cin >> first_intersection >> second_intersection >> number_of_first_intersection >> number_of_second_intersection;
        intersection_coodrinates[number_of_first_intersection] = first_intersection;
        intersection_coodrinates[number_of_second_intersection] = second_intersection;
        matrix[number_of_first_intersection][number_of_second_intersection] = std::make_pair(1, true);
        matrix[number_of_second_intersection][number_of_first_intersection] = std::make_pair(1, true);
    }
    long long start_intersection, finish_intersection;
    std::cin >> start_intersection >> finish_intersection;
    intersection_coodrinates[0] = std::make_pair(intersection_coodrinates[start_intersection].first, intersection_coodrinates[start_intersection].second - 1);
    matrix[0][start_intersection].second = false;
    DFS(0, start_intersection, finish_intersection);
    std::cout << "No\n";
    return 0;
}
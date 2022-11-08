#include <iostream>
#include <vector>
#include <string>
#include <numeric>
#include <fstream>
#include <cmath>

std::ofstream out("output.txt");

int main()
{
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    std::string info;
    long long size, n, right, left;
    std::cin >> size;
    int temp_size = floor(sqrt(size));
    std::vector<long long> arr;
    long long temp_sum = 0ll;
    std::vector<long long> answer;
    for(int i = 0, temp; i < size; i++){ std::cin >> temp; arr.push_back(temp); if(!((i + 1) % temp_size)){ temp_sum += temp; answer.push_back(temp_sum); temp_sum = 0;}else{ temp_sum += temp;}}
    std::cin >> n;
    for(int i = 0; i < n; i++)
    {
        std::cin >> info >> left >> right;
        int temp_left = left / temp_size;
        if(info == "Add"){ answer[temp_left] += right; arr[left] += right;}
        if(info == "FindSum")
        {
            long long sum = 0ll;
            int temp_right = right / temp_size;
            out << (temp_right == temp_left ? std::accumulate((arr.begin() + left), (arr.begin() + right), sum, std::plus<long long>()) :
                    std::accumulate((arr.begin() + left), (arr.begin() + (temp_left + 1) * temp_size), sum, std::plus<long long>()) +
                    std::accumulate((answer.begin() + temp_left + 1), (answer.begin() + temp_right), sum, std::plus<long long>()) +
                    std::accumulate((arr.begin() + temp_right * temp_size), (arr.begin() + right), sum, std::plus<long long>())) << std::endl;
        }
    }
    // std::copy(arr.begin(), arr.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << std::endl;
    // std::copy(answer.begin(), answer.end(), std::ostream_iterator<long long>(std::cout, " "));
    return 0;
}
#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

int counter = 1;
std::vector<int> answer;
std::vector<bool> priority;
std::vector<std::vector<int>> matrix;

void Function(const std::vector<int>& vec, int edge)
{
    int i = 0;
    priority[edge] = true;
    answer[edge] = counter++;
    std::for_each(vec.begin(), vec.end(), 
        [&i](const int& temp){
            if(!priority[i] && temp){ Function(matrix[i], i);} i++;
        }
    );
}

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n, j = 0;
    std::cin >> n;
    answer.assign(n, 0);
    priority.assign(n, false);
    matrix.assign(n, std::vector<int>(n, 0));
    std::for_each(matrix.begin(), matrix.end(), 
        [](std::vector<int>& vec){
            std::for_each(vec.begin(), vec.end(), [](int& temp){ std::cin >> temp;});
        }
    );
    std::for_each(matrix.begin(), matrix.end(), 
        [&j](const std::vector<int>& temp){
            if(!priority[j]){ Function(temp, j);} j++;
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<int>(std::cout, " "));
}
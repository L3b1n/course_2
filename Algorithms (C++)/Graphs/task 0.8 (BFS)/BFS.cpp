#include <iostream>
#include <queue>
#include <vector>
#include <fstream>
#include <algorithm>

std::ifstream in("input.txt", std::ios::out);
std::ofstream out("output.txt");

int main()
{
    std::ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    int n, k = 1;
    in >> n;
    std::vector<int> priority(n, 0); 
    std::vector<std::vector<int>> matrix(n, std::vector<int>(n, 0));
    std::for_each(matrix.begin(), matrix.end(), 
        [](std::vector<int>& vec){
            std::for_each(vec.begin(), vec.end(), [](int& temp){ ::in >> temp;});
        }
    );
    for(int j = 0; j < n; j++)
    {
        if(!priority[j])
        {
            std::queue<int> temp;
            temp.push(j);
            priority[j] = k++;
            while(!temp.empty())
            {
                int i = 0;
                std::for_each(matrix[temp.front()].begin(), matrix[temp.front()].end(), 
                    [&i, &temp, &k, &priority](int& num){
                        if(num){ if(!priority[i]){ priority[i] = k++; temp.push(i);}} i++;
                    }
                );
                temp.pop();
            }
        }
    }
    std::copy(priority.begin(), priority.end(), std::ostream_iterator<int>(out, " "));
}
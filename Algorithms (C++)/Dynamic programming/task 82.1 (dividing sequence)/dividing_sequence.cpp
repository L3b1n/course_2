#include <cmath>
#include <vector>
#include <fstream>
#include <numeric>
#include <iostream>
#include <algorithm>

int i = 1;

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int size;
    std::cin >> size;
    std::vector<int> vec = { std::istream_iterator<int>(std::cin), std::istream_iterator<int>()};
    std::vector<int> temp_vec(size); temp_vec[0] = 1;
    std::for_each(++vec.begin(), vec.end(), 
        [&vec, &temp_vec](int& tempI){
            int j = 0;
            std::for_each(vec.begin(), (vec.begin() + i), 
                [&temp_vec, &tempI, &j](int& tempJ){
                    if(i && tempJ && !(tempI % tempJ) && temp_vec[j] > temp_vec[i]){ temp_vec[i] = temp_vec[j];}
                    j++;
                }
            );
            temp_vec[i++]++;
        }
    );
    std::make_heap(temp_vec.rbegin(), temp_vec.rend());
    std::sort_heap(temp_vec.rbegin(), temp_vec.rend());
    std::cout << size - temp_vec[0];
    return 0;
}
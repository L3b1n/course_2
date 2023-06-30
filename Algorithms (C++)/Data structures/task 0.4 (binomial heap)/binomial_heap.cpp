#include <string>
#include <vector>
#include <fstream>
#include <iostream>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int i = 0;
    long long n;
    std::cin >> n;
    std::vector<int> arr;
    for(;n;){ arr.push_back(n & 1); n >>= 1;}
    std::for_each(arr.begin(), arr.end(), 
        [&i](int temp){ 
            if(temp == 1){ std::cout << i << "\n";}
            i++;
        }
    );
    return 0;
}
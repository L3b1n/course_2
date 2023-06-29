#include <vector>
#include <fstream>
#include <numeric>
#include <iostream>
#include <algorithm>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int size;
    std::cin >> size;
    std::vector<long long> num = { std::istream_iterator<long long>(std::cin), std::istream_iterator<long long>()};
    num.push_back(std::numeric_limits<long long>::max());
    int i = 0;
    if(size == 1){ std::cout << "Yes"; return 0;}
    std::vector<long long>::iterator it = num.begin() + size / 2;
    std::string temp = std::accumulate(num.begin(), it, std::string(),
        [&i, &size, &num](std::string p, long long temp) -> std::string {
            if(num[2 * i + 1] < temp || num[2 * i++ + 2] < temp || p == "No"){ return std::string("No");}
            return std::string("Yes");
        }
    );
    std::cout << temp << "\n";
    return 0;
}
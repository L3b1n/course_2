#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    long long n, answer = 0;
    std::cin >> n;
    std::vector<long long> num = { std::istream_iterator<long long>(std::cin), std::istream_iterator<long long>()};
    if(n == 1){ std::cout << 1; return 0;}
    std::vector<long long> arr(n + 1, LLONG_MAX);
    arr[0] = LLONG_MIN;
    std::for_each(num.begin(), num.end(), 
        [&answer, &arr](long long temp){
            long long j = std::distance(arr.begin(), std::upper_bound(arr.begin(), arr.end(), temp)); 
            if(arr[j - 1] < temp && temp < arr[j])
            { 
                arr[j] = temp; 
                answer = std::max(j, answer);
            }
        }
    );
    std::cout << answer << "\n";
    return 0;
}
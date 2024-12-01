#include <vector>
#include <fstream>
#include <numeric>
#include <iostream>
#include <algorithm>

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout); 
    std::cin.tie(0);
    std::cout.tie(0);
    int k, n, l, i = 0;
    std::cin >> k >> n;
    long long sum = 0ll;
    std::vector<int> answer(n);
    answer[0] = k; answer[1] = std::pow(k, 2);
    std::cout << std::accumulate(answer.begin(), answer.end(), sum,
        [&i, &k, &answer](long long& sum, int& temp) -> long long {
            if(i >= 2){ temp = (answer[i - 2] + answer[i - 1]) * k;} i++;
            return sum += temp;
        }
    );
    return 0;
}
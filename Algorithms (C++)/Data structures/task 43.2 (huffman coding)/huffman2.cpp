#include <queue>
#include <numeric>
#include <iostream>
#include <algorithm>

int main()
{
    freopen("huffman.in", "r", stdin);
    // freopen("huffman.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    long long n;
    std::cin >> n;
    std::priority_queue<long long, std::vector<long long>, std::greater<long long>> request = { std::istream_iterator<long long>(std::cin), std::istream_iterator<long long>()};
    long long sum = 0ll;
    for(int i = 0; i < n - 1; i++)
    {
        long long temp_sum = request.top(); request.pop();
        temp_sum += request.top(); request.pop();
        sum += temp_sum;
        request.push(temp_sum);
    } 
    std::cout << sum << "\n";
    // std::copy(request.begin(), request.end(), std::ostream_iterator<value_type>(std::cout, "\n")); std::cout << std::endl;
    return 0;
}
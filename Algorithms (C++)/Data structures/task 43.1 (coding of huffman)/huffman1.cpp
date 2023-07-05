#include <set>
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
    int n;
    std::cin >> n;
    std::multiset<long long> request = { std::istream_iterator<long long>(std::cin), std::istream_iterator<long long>()};
    long long sum = 0ll;
    for(int i = 0; i < n - 1; i++)
    {
        std::multiset<long long>::iterator it_begin = request.begin();
        std::multiset<long long>::iterator it_end = ++request.begin();
        long long temp_sum = *it_begin + *it_end;
        sum += temp_sum;
        request.erase(it_begin, ++it_end);
        request.insert(temp_sum);
    } 
    std::cout << sum << "\n";
    // std::copy(request.begin(), request.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << std::endl;
    return 0;
}
#include <fstream>
#include <iostream>
#include <set>
#include <numeric>
#include <algorithm>

std::ifstream in("huffman.in", std::ios::out);
std::ofstream out("huffman.out");
 
int main()
{
    std::ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    int n;
    in >> n;
    std::multiset<long long> request = { std::istream_iterator<long long>(in), std::istream_iterator<long long>()};
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
    out << sum;
    // std::copy(request.begin(), request.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << std::endl;
    return 0;
}
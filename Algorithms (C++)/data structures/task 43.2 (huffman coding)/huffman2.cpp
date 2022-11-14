#include <fstream>
#include <iostream>
#include <queue>
#include <numeric>
#include <algorithm>

std::ifstream in("huffman.in", std::ios::out);
std::ofstream out("huffman.out");
 
int main()
{
    std::ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    long long n;
    in >> n;
    std::priority_queue<long long, std::vector<long long>, std::greater<long long>> request = { std::istream_iterator<long long>(in), std::istream_iterator<long long>()};
    long long sum = 0ll;
    for(int i = 0; i < n - 1; i++)
    {
        long long temp_sum = request.top(); request.pop();
        temp_sum += request.top(); request.pop();
        sum += temp_sum;
        request.push(temp_sum);
    } 
    out << sum;
    // std::copy(request.begin(), request.end(), std::ostream_iterator<value_type>(std::cout, "\n")); std::cout << std::endl;
    return 0;
}
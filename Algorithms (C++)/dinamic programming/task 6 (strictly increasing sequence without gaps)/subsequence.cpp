#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

int main()
{
    long long n, answer = 0;
    in >> n;
    std::vector<long long> num = { std::istream_iterator<long long>(in), std::istream_iterator<long long>()};
    if(n == 1){ out << 1; return 0;}
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
    out << answer << endl;
    in.close();
    out.close();
    return 0;
}
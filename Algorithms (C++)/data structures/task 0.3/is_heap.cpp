#include <iostream>
#include <fstream>
#include <numeric>
#include <algorithm>
#include <vector>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

int main()
{
    int size;
    in >> size;
    std::vector<long long> num = { std::istream_iterator<long long>(in), std::istream_iterator<long long>()};
    num.push_back(LLONG_MAX);
    int i = 0;
    if(size == 1){ out << "Yes"; return 0;}
    std::vector<long long>::iterator it = num.begin() + size / 2;
    std::string temp = std::accumulate(num.begin(), it, std::string(),
        [&i, &size, &num](std::string p, long long temp) -> string {
            if(num[2 * i + 1] < temp || num[2 * i++ + 2] < temp || p == "No"){ return std::string("No");}
            return std::string("Yes");
        }
    );
    out << temp;
    return 0;
}
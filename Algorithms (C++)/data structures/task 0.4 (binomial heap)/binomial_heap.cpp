#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

int main()
{
    int i = 0;
    long long n;
    in >> n;
    std::vector<int> arr;
    for(;n;){ arr.push_back(n & 1); n >>= 1;}
    std::copy(arr.rbegin(), arr.rend(), std::ostream_iterator<int>(cout)); out << endl;
    std::for_each(arr.begin(), arr.end(), 
        [&i](int temp){ 
            if(temp == 1){ out << i << endl;}
            i++;
        }
    );
    return 0;
}
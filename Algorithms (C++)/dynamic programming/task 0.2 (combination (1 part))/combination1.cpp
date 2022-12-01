#include <iostream>
#include <vector>

using namespace std;

int main()
{
    const unsigned modulus = 1000000007;
    unsigned long long m, n;

    cin >> m >> n;
    if(n > m / 2){ n = m - n;}

    std::vector<unsigned long long> vec(m + 1, 1);

    for(unsigned long long cm = 0; cm <= m; cm++)
    {
        for(unsigned long long i = m + 1 - cm; i < m; i++)
        {    
            vec[i] = (vec[i] + vec[i + 1]) % modulus;
        }
    }

    cout << vec[n] << endl;
}
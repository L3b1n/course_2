#include <iostream>
#include <vector>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    const unsigned modulus = 1000000007;
    unsigned long long m, n;

    std::cin >> m >> n;
    if(n > m / 2){ n = m - n;}

    std::vector<unsigned long long> vec(m + 1, 1);

    for(unsigned long long cm = 0; cm <= m; cm++)
    {
        for(unsigned long long i = m + 1 - cm; i < m; i++)
        {    
            vec[i] = (vec[i] + vec[i + 1]) % modulus;
        }
    }

    std::cout << vec[n] << "\n";
}
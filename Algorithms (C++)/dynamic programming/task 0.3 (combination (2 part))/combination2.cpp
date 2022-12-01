#include <iostream>
#include <vector>

using namespace std;

const unsigned mod = 1000000007;

unsigned long long power(unsigned long long& x, unsigned long long& pow, unsigned long long& res)
{
    if(pow <= 0){ return res;}
    if(pow % 2 == 1){ pow >>= 1; res = (res * x) % mod; x = (x * x) % mod; return power(x, pow, res);}
    else{ x = (x * x) % mod; pow >>= 1; return power(x, pow, res);}
}


int main()
{
    unsigned long long m, n, numerator = 1, denominator = 1, pow = 1000000005, res = 1;

    cin >> m >> n;
    for(int i = m - n + 1; i <= m; i++){ numerator = (numerator * i) % mod;}
    for(int i = 1; i <= n; i++){ denominator = (denominator * i) % mod;}

    cout << (numerator * power(denominator, pow, res)) % mod;
}
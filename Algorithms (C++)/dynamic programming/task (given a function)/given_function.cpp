#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>
#include <iterator>
#include <algorithm>
#include <unordered_map>

namespace dma
{
    static const int modulus = 1000000007;
}

class Function
{
public:
    Function() : argument(0), length(0), startValue(0) {}

    friend std::istream& operator >> (std::istream& fi, Function& a)
    {
        fi >> a.argument >> a.length >> a.startValue;
        for(int i = 0; i < a.length; i++){ int number; fi >> number; a.vec.push_back(number);}
        return fi;
    }

    long long Solution(){ return Solution(argument, startValue);}

private:
    std::vector<int> vec;
    int argument, length, startValue;
    std::unordered_map<int, long long> temp;

    int Solution(int n, int a)
    {
        if(n < 0){ return 0ll;}
        else if(n == 0){ return a;}
        else if(n % 2 == 1 && n >= 1)
        {
            if(temp[n] != 0){ return ((temp[n] == -10 ? 0 : temp[n]) % dma::modulus + n % dma::modulus) % dma::modulus;}
            else{ temp[n] = (Solution(n - 1, a) % dma::modulus == 0 ? -10 : Solution(n - 1, a) % dma::modulus); return ((temp[n] == -10 ? 0 : temp[n]) % dma::modulus + n % dma::modulus) % dma::modulus;}
        }
        else if(n % 2 == 0 && n >= 2)
        {
            int sum = 0, i = 0;
            std::for_each(vec.begin(), vec.end(),
                [this, &i, &n, &a, &sum](const int& number){
                    if(temp[n / 2 - i] != 0){ sum = (sum % dma::modulus + ((number % dma::modulus) * ((temp[n / 2 - i] == -10 ? 0 : temp[n / 2 - i]) % dma::modulus)) % dma::modulus) % dma::modulus;}
                    else{ temp[n / 2 - i] = (Solution(n / 2 - i, a) % dma::modulus == 0 ? -10 : Solution(n / 2 - i, a) % dma::modulus); sum = (sum % dma::modulus + ((number % dma::modulus) * ((temp[n / 2 - i] == -10 ? 0 : temp[n / 2 - i]) % dma::modulus)) % dma::modulus) % dma::modulus;} 
                    ++i;
                }
            );
            return sum;
        }
    }
};

int main() 
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Function function;
    std::cin >> function;
    std::cout << function.Solution() % dma::modulus << "\n";
    return 0;
}
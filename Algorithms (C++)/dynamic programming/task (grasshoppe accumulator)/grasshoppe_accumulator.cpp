#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

class Frog
{
private:
    int size = 0;
    std::vector<long long> num;
    std::vector<std::vector<long long>> sum;

public:
    Frog(int _size = 0) : size(_size) { num.reserve(size); sum.resize(3, std::vector<long long>(size, LLONG_MIN));}

    friend std::istream& operator >> (std::istream& fi, Frog& a)
    {
        for(int i = 0, x; i < a.size; i++){ fi >> x; a.num.push_back(x);}
        return fi;
    }

    long long Solution()
    {
        int i = 0;
        sum[(num[0] % 3 + 3) % 3][0] = num[0];
        std::for_each(num.begin(), num.end(), 
            [this, &i](long long& temp){
                if(i + 2 < sum[0].size())
                {
                    std::for_each(sum.begin(), sum.end(), 
                        [this, &i](const std::vector<long long>& vec){
                            if(vec[i] != LLONG_MIN){ sum[((num[i + 2] + vec[i]) % 3 + 3) % 3][i + 2]= std::max(num[i + 2] + vec[i], sum[((num[i + 2] + vec[i]) % 3 + 3) % 3][i + 2]);}
                        }
                    );
                }
                if(i + 5 < sum[0].size())
                {
                    std::for_each(sum.begin(), sum.end(), 
                        [this, &i](const std::vector<long long>& vec){
                            if(vec[i] != LLONG_MIN){ sum[((num[i + 5] + vec[i]) % 3 + 3) % 3][i + 5] = std::max(num[i + 5] + vec[i], sum[((num[i + 5] + vec[i]) % 3 + 3) % 3][i + 5]);}
                        }
                    );
                }
                i++;
            }
        );
        return sum[0][sum[0].size() - 1] == LLONG_MIN ? -2023 : sum[0][sum[0].size() - 1];
    }
};

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    std::cin >> n;
    Frog temp(n);
    std::cin >> temp;
    std::cout << temp.Solution() << "\n";
    return 0;
}
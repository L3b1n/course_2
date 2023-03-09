#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

namespace dma
{
    static const int modulus = 1000000007;
}

class Store
{
public:
    Store() : length(0) {}

    friend std::istream& operator >> (std::istream& fi, Store& a)
    {
        int i = 1;
        fi >> a.length;
        a.number.resize(a.length + 1);
        a.sum.assign(a.length + 1, 0ll);
        a.tempArray.assign(a.length + 1, std::vector<std::pair<int, long long>>(a.length + 1, std::make_pair(-1, LLONG_MAX)));
        std::for_each(++a.number.begin(), a.number.end(), [&a, &fi, &i](long long& temp){ fi >> temp; a.sum[i] = temp + a.sum[i - 1]; a.tempArray[i][i] = std::make_pair(-1, 0ll); i++;});
        return fi;
    }
    
    long long Solution()
    {
        int k = 2;
        std::for_each(++++sum.begin(), sum.end(), 
            [this, &k](const long long& x){
                int i = 1;
                std::for_each(++tempArray.begin(), tempArray.begin() + length - k + 2, 
                    [this, &i, &k](std::vector<std::pair<int, long long>>& vec){
                        int j = i + k - 1;
                        long long tempResult;
                        if(vec[j - 1].second != LLONG_MAX){ tempResult = vec[j - 1].second + ((20 * (sum[j - 1] - sum[i - 1]) - 23 * number[j]) % dma::modulus + dma::modulus) % dma::modulus;}
                        if(vec[j].second > tempResult){ vec[j] = std::make_pair(0, tempResult);}
                        if(tempArray[i + 1][j].second != LLONG_MAX){ tempResult = tempArray[i + 1][j].second + ((20 * (sum[j] - sum[i]) - 23 * number[i]) % dma::modulus + dma::modulus) % dma::modulus;}
                        if(vec[j].second > tempResult){ vec[j] = std::make_pair(1, tempResult);}
                        i++;
                    }
                );
                k++;
            }
        );
        return tempArray[1][length].second;
    }

    void RestoreSolution()
    {
        int i = 1, j = length;
        std::vector<int> way;
        while(tempArray[i][j].first != -1)
        {
            if(tempArray[i][j].first == 0){ way.push_back(j); j--;}
            else{ way.push_back(i); i++;}
        }
        way.push_back(i);
        std::copy(way.rbegin(), way.rend(), std::ostream_iterator<int>(std::cout, " "));
    }
private:
    int length;
    std::vector<long long> sum;
    std::vector<long long> number;
    std::vector<std::vector<std::pair<int, long long>>> tempArray;
};

int main() 
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Store store;
    std::cin >> store;
    std::cout << store.Solution() << "\n";
    store.RestoreSolution();
    return 0;
}
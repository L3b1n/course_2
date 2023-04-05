#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

class Shift
{
public:
    Shift() : length(0) {}

    friend std::istream& operator >> (std::istream& fi, Shift& a)
    {
        fi >> a.length >> a.firstString >> a.secondString;
        return fi;
    }

    int Solution()
    {
        int i = 1;
        int answer = -1;
        int l = 0, r = 0;
        firstString += firstString;
        std::vector<int> tempArray(3 * length + 1);
        std::string string = secondString + "1" + firstString;
        std::for_each(++tempArray.begin(), tempArray.end(),
            [this, &i, &l, &r, &tempArray, &string](int& temp){
                if(i <= r){ temp = std::min(r - i + 1, tempArray[i - l]);}
                while(i + temp < 3 * length + 1 && string[temp] == string[i + temp]){ ++temp;}
                if(i + temp - 1 > r){ l = i; r = i + temp - 1;} i++;
            }
        ); i = 0;
        return std::accumulate(firstString.begin(), firstString.end(), int(),
            [this, tempArray, &i, &answer](int tempAnswer, const char& temp){
                if(tempArray[length + i + 1] == length && answer == -1){ answer = i;} i++;
                return answer;
            }
        );
    }

private:
    int length;
    std::string firstString;
    std::string secondString;
};

int main() 
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Shift shift;
    std::cin >> shift;
    std::cout << shift.Solution() << "\n";
    return 0;
}
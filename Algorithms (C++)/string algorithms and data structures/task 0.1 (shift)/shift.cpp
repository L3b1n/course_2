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
        firstString += firstString;
        std::vector<int> tempArray(3 * length + 1);
        std::string string = (secondString + "1" + firstString);
        std::for_each(++string.begin(), string.end(), 
            [this, &i, &string, &tempArray](const int& temp){
                int j = tempArray[i - 1];
                while(j > 0 && temp != string[j]){ j = tempArray[j - 1];}
                if(temp == string[j]){ j++;}
                tempArray[i++] = j;
            }
        ); i = 1;
        return std::accumulate(firstString.begin(), firstString.end(), int(),
            [this, tempArray, &i, &answer](int tempAnswer, const char& temp){
                if(tempArray[secondString.length() + i] == secondString.length() && answer == -1){ answer = i - secondString.length();} i++;
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
#include <map>
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <cstring>

class SuffixArray
{
public:
    SuffixArray() : length(0) {}

    friend std::istream& operator >> (std::istream& fi, SuffixArray& a)
    {
        int i = 0;
        fi >> a.string;
        a.length = a.string.length();
        a.tempArray.resize(++a.length);
        a.permutation.resize(a.length);
        a.equivalenceClass.resize(a.length);
        std::for_each(a.tempArray.begin(), a.tempArray.begin() + a.length,
            [&i, &a](int& temp){
                temp = int(a.string[i++]);
            }
        );
        return fi;
    }

    void Solution()
    {
        int i = 0;
        int counter = 0;
        int classCounter = 0;
        std::for_each(tempArray.begin(), tempArray.end(), [this, &i](const int& temp){ tempMap[temp].push_back(i++);});
        std::for_each(tempMap.begin(), tempMap.end(),
            [this, &counter, &classCounter](const std::pair<int, std::vector<int>>& pair){
                std::for_each(pair.second.begin(), pair.second.end(),
                    [this, &counter, &classCounter](const int& temp){
                        equivalenceClass[temp] = classCounter;
                        permutation[counter++] = temp;
                    }
                ); classCounter++;
            }
        );
        for(int len = 1; classCounter < length; len++)
        {
            counter = 0;
            int tempClassCounter = 0;
            int distance = (1 << len) / 2;
            std::vector<int> tempEquivalenceClass(length);
            std::vector<std::vector<int>> matrix(classCounter);
            std::for_each(permutation.begin(), permutation.end(),
                [this, &distance, &matrix](const int& temp){
                    int Class = (temp - distance + length) % length;
                    matrix[equivalenceClass[Class]].push_back(Class);
                }
            );
            std::for_each(matrix.begin(), matrix.end(),
                [this, &distance, &tempClassCounter, &tempEquivalenceClass, &counter](const std::vector<int>& vec){
                    int i = 0;
                    std::for_each(vec.begin(), vec.end(),
                        [this, &i, &distance, &vec, &tempClassCounter, &tempEquivalenceClass, &counter](const int& temp){
                            if(i == 0 || equivalenceClass[(temp + distance) % length] != equivalenceClass[(vec[i - 1] + distance) % length]){ tempClassCounter++;}
                            tempEquivalenceClass[temp] = tempClassCounter - 1;
                            permutation[counter++] = temp; i++;
                        }
                    );
                }
            );
            classCounter = tempClassCounter;
            equivalenceClass = tempEquivalenceClass;
        }
        std::cout << permutation.size() - 1 << "\n";
        std::for_each(++permutation.begin(), permutation.end(), [](const int& temp){ std::cout << temp + 1 << " ";}); std::cout << "\n";
    }

private:
    int length;
    std::string string;
    std::vector<int> tempArray;
    std::vector<int> permutation;
    std::vector<int> equivalenceClass;
    std::map<int, std::vector<int>> tempMap;
};

int main() 
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    SuffixArray suffixArray;
    std::cin >> suffixArray;
    suffixArray.Solution();
    return 0;
}
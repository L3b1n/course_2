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
        a.tempArray.resize(a.length);
        a.permutation.resize(a.length);
        std::for_each(a.tempArray.begin(), a.tempArray.begin() + a.length,
            [&i, &a](int& temp){
                temp = int(a.string[i++]);
            }
        );
        a.tempArray.push_back(0); a.tempArray.push_back(0); a.tempArray.push_back(0);
        return fi;
    }

    void Solution()
    {
        suffixArray(tempArray, permutation, length, SuffixArray::alphabet);
        std::cout << permutation.size() << "\n";
        std::for_each(permutation.begin(), permutation.end(), [](const int& temp){ std::cout << temp + 1 << " ";}); std::cout << "\n";
    }

private:
    int length;
    std::string string;
    std::vector<int> tempArray;
    std::vector<int> permutation;
    static const int alphabet = 256;

    inline bool lexicographic(const int& a1, const int& a2, const int& b1, const int& b2){ return a1 < b1 || a1 == b1 && a2 <= b2;}
    inline bool lexicographic(const int& a1, const int& a2, const int& a3, const int& b1, const int& b2, const int& b3){ return a1 < b1 || a1 == b1 && lexicographic(a2, a3, b2, b3);}

    void radixPass(std::vector<int>& a, std::vector<int>& b, const std::vector<int>& r, const int& length, const int& alphabet)
    {
        int sum = 0;
        std::vector<int> tempVec(alphabet + 1, 0);
        std::for_each(a.begin(), a.begin() + length, [&tempVec, &r](const int& temp){ tempVec[r[temp]]++;});
        std::for_each(tempVec.begin(), tempVec.end(), [&sum](int& temp){ int tmp = temp; temp = sum; sum += tmp;});
        std::for_each(a.begin(), a.begin() + length, [&tempVec, &b, &r](const int& temp){ b[tempVec[r[temp]]++] = temp;});
    }

    void suffixArray(std::vector<int>& tempArray, std::vector<int>& permutation, const int& length, int alphabet)
    {
        int i = 0, j = 0;
        int deuces = length / 3;
        int zero = (length + 2) / 3;
        int units = (length + 1) / 3;
        int zeroDeuces = zero + deuces;
        std::vector<int> zeroVec(zero);
        std::vector<int> zeroPermutation(zero);
        std::vector<int> unitsDeuces(zeroDeuces + 3, 0);
        std::vector<int> tempPermutation(zeroDeuces + 3, 0);
        int className = 0, class0 = -1, class1 = -1, class2 = -1;
        for(int i = 0, j = 0; i < length + (zero - units); i++){ if(i % 3 != 0){ unitsDeuces[j++] = i;}}
        radixPass(unitsDeuces, tempPermutation, std::vector<int>(tempArray.begin() + 2, tempArray.end()), zeroDeuces, alphabet);
        radixPass(tempPermutation, unitsDeuces, std::vector<int>(tempArray.begin() + 1, tempArray.end()), zeroDeuces, alphabet);
        radixPass(unitsDeuces, tempPermutation, tempArray, zeroDeuces, alphabet);
        std::for_each(tempPermutation.begin(), tempPermutation.end() - 3,
            [&class0, &class1, &class2, &className, &unitsDeuces, &zero, &tempArray](const int& temp){
                if(tempArray[temp] != class0 || tempArray[temp + 1] != class1 || tempArray[temp + 2] != class2){ className++; class0 = tempArray[temp]; class1 = tempArray[temp + 1]; class2 = tempArray[temp + 2];}
                if(temp % 3 == 1){ unitsDeuces[temp / 3] = className;}
                else{ unitsDeuces[temp / 3 + zero] = className;}
            }
        );
        if(className < zeroDeuces)
        {
            suffixArray(unitsDeuces, tempPermutation, zeroDeuces, className); 
            std::for_each(tempPermutation.begin(), tempPermutation.end() - 3, [&i, &unitsDeuces](const int& temp){ unitsDeuces[temp] = ++i;});
        }
        else{ std::for_each(unitsDeuces.begin(), unitsDeuces.end() - 3, [&i, &tempPermutation](const int& temp){ tempPermutation[temp - 1] = i++;});}
        std::for_each(tempPermutation.begin(), tempPermutation.end() - 3, [&j, &zero, &zeroVec](const int& temp){ if(temp < zero){ zeroVec[j++] = 3 * temp;}});
        radixPass(zeroVec, zeroPermutation, tempArray, zero, alphabet);
        for(int p = 0, k = 0, t = zero - units; k < length; k++)
        {
            int i = tempPermutation[t] < zero ? tempPermutation[t] * 3 + 1 : (tempPermutation[t] - zero) * 3 + 2;
            int j = zeroPermutation[p];
            if(tempPermutation[t] < zero ? 
                lexicographic(tempArray[i], unitsDeuces[tempPermutation[t] + zero], tempArray[j], unitsDeuces[j / 3]) :
                lexicographic(tempArray[i], tempArray[i + 1], unitsDeuces[tempPermutation[t] - zero + 1], tempArray[j], tempArray[j + 1], unitsDeuces[j / 3 + zero]))
            {
                permutation[k] = i; t++;
                if(t == zeroDeuces){ k++; std::for_each(zeroPermutation.begin() + p, zeroPermutation.begin() + zero, [&p, &k, &permutation](const int& temp){ permutation[k++] = temp; p++;});}
            }
            else
            {
                permutation[k] = j; p++;
                if(p == zero){ k++; std::for_each(tempPermutation.begin() + t, tempPermutation.begin() + zeroDeuces, [&t, &k, &zero, &permutation](const int& temp){ permutation[k++] = temp < zero ? temp * 3 + 1 : (temp - zero) * 3 + 2; t++;});}
            }
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
    SuffixArray suffixArray;
    std::cin >> suffixArray;
    suffixArray.Solution();
    return 0;
}
#include <iostream>
#include <vector>
#include <fstream>
#include <string>

namespace dma
{
    int min(int a, int b, int c)
    {
        return std::min(std::min(a, b), c);
    }
}

using namespace std;

ifstream in("in.txt", ios::out);
ofstream out("out.txt");

class Levenshtein
{
public:
    Levenshtein() : del(0), insert(0), replace(0) {}

    friend std::istream& operator >> (std::istream& fi, Levenshtein& a)
    {
        int i = 0;
        fi >> a.del >> a.insert >> a.replace >> a.str1 >> a.str2;
        a.num.resize(a.str2.length() + 1); for_each(a.num.begin(), a.num.end(), [&a, &i](pair<int, int>& temp){ if(i == 0){temp.second = a.del;} temp.first = (i++) * a.insert;});
        return fi;
    }

    friend ostream& operator << (ostream& fo, Levenshtein& a)
    {
        fo << a.del << "  " << a.insert << "  " << a.replace << endl;
        fo << a.str1 << "  " << a.str2 << "\n" << endl;
        for_each(a.num.begin(), a.num.end(), [&fo](pair<int, int>& temp){ fo << temp.first << "  ";}); fo << endl;
        for_each(a.num.begin(), a.num.end(), [&fo](pair<int, int>& temp){ fo << temp.second << "  ";});
        return fo;
    }

    void Solution()
    {
        for(int i = 1; i <= str1.length(); i++)
        {
            for(int j = 1; j <= str2.length(); j++)
            {
                num[j].second = str1[i - 1] == str2[j - 1] ? num[j - 1].first : dma::min((num[j].first + del), (num[j - 1].second + insert), (num[j - 1].first + replace));
            }
            for(int j = 0; j <= str2.length(); j++)
            {
                num[j].first = num[j].second;
                if(j == 0){ num[0].second = (i + 1) * del;}
                else num[j].second = 0;
            }
        }
        out << num[str2.length()].first;
    }

private:
    int del;
    int insert;
    int replace;
    string str1;
    string str2;
    vector<pair<int, int>> num;
};

int main()
{
    Levenshtein a;
    in >> a;
    a.Solution();
    return 0;
}
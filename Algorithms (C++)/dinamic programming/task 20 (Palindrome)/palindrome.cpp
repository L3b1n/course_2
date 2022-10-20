#include <iostream>
#include <algorithm>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

class Polindrome
{
public:
    Polindrome(string _str) : size(_str.length()), str(_str) { int i = 0; arr.resize(size); for_each(arr.begin(), arr.end(), 
    [this, &i](vector<int>& temp){ temp.resize(this->size - i); temp[0] = 1; temp[1] = this->str[i] == this->str[i + 1] ? 2 : 1;  i++;});}

    friend ostream& operator << (ostream& fo, Polindrome& a)
    {
        for_each(a.arr.begin(), a.arr.end(), 
            [&fo](vector<int>& vec){
                for_each(vec.begin(), vec.end(),
                    [&fo](int x){ fo << x << "  ";}
                );
                fo << endl;
            }
        );
        return fo;
    }

    void Solution()
    {
        if(size == 1){ cout << 1 << endl; cout << str; return;}
        else if(size == 2){ cout << (str[0] == str[1] ? 2 : 1) << endl; if(str[0] == str[1]){ cout << str;} else{ cout << str[0];} return;}
        for(int i = 2; i < size; i++)
        {
            for(int h = i - 2, j = i, k = 0; h >= 0; h--, k++){ arr[h][j - h] = str[h] == str[j] ? arr[h + 1][j - h - 2] + 2 : max(arr[h + 1][j - h - 1], arr[h][j - h - 1]);}
        }
        for(int i = 0, j = size - 1, k = 1; j >= 0; i++, j -= 2)
        {
            int temp = arr[i][j]; k = 1;
            for(; i < size - 1 && j - k >= 0 && arr[i + 1][j - k] == temp; i++, k++){} j -= (k - 1);
            for(; j > 0 && arr[i][j - 1] == temp; j--){}
            result << str[i];
        }
        out << arr[0][size - 1] << endl; 
        string a = result.str();
        out << a;
        std::copy(a.rbegin() + arr[0][size - 1] % 2, a.rend(), std::ostream_iterator<char>(out));
    }

private:
    int size;
    string str = "";
    ostringstream result;
    vector<vector<int>> arr;
};

int main()
{
    string str;
    in >> str;
    Polindrome a(str); 
    a.Solution();
    return 0;
}
#include <vector>
#include <algorithm>
#include <stack>
#include <limits>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

struct KData
{
    explicit KData(long long _info = 0, int _str = 0, string _side = "") : info(_info), str(_str), side(_side) {}

    friend std::istream& operator >> (std::istream& fi, KData& a)
    {
        string s;
        if(getline(fi, s))
        {
            istringstream ss(s); string sf;
            getline(ss, sf, ' '); a.info = stoll(sf);
            getline(ss, sf, ' '); a.str = stoi(sf);
            getline(ss, sf, ' '); a.side = sf;
        }
        return fi;
    }

    KData& operator = (KData const &a)
    {
        info = a.info;
        side = a.side;
        str = a.str;
        return *this;
    }

    friend std::ostream& operator << (std::ostream& os, const KData& a)
    {
        os << a.info << "  " << a.str << "  " << a.side;
        return os;
    }

    long long info;
    int str;
    string side;
};

int main()
{
    //0.3----------------------------------
    string s;
    KData temp;
    ifstream fi("bst.in", ios::out);
    ofstream out("bst.out");
    getline(fi, s); int n = stoi(s);
    KData* pos = new KData[n + 1];
    getline(fi, s); pos[1].info = stoll(s);
    pair<long long, long long>* interval = new pair<long long, long long>[n + 1]; interval[1].first = LLONG_MIN; interval[1].second = LLONG_MAX;
    for(int i = 1; i < n; ++i)
    {
        fi >> temp; 
        pos[i + 1] = temp;
        if(temp.side == "R")
        {
            interval[i + 1].first = pos[temp.str].info - 1; interval[i + 1].second = interval[temp.str].second;  
            if(temp.info <= interval[i + 1].first || temp.info >= interval[i + 1].second){ out << "NO"; return 0;}
        }
        if(temp.side == "L")
        {
            interval[i + 1].first = interval[temp.str].first; interval[i + 1].second = pos[temp.str].info;  
            if(temp.info <= interval[i + 1].first || temp.info >= interval[i + 1].second){ out << "NO"; return 0;}
        }
    }
    out << "YES";
}
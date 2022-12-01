#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

class Frog
{
private:
    int size = 0;
    vector<int> num;
    vector<int> sum;

public:
    Frog(int _size = 0) : size(_size) { num.reserve(size); sum.resize(size);}

    friend std::istream& operator >> (std::istream& fi, Frog& a)
    {
        for(int i = 0, x; i < a.size; i++){ fi >> x; a.num.push_back(x);}
        return fi;
    }

    int Way_of_frog()
    {
        int i = 0;
        return Way_of_frog(i);
    }

    int Way_of_frog(int i)
    {
        if(i == 0){ sum[i] = num[i];}
        else if(i == 1){ sum[i] = -10;}
        else if(i == 2){ sum[i] = sum[0] + num[i];}
        else{ sum[i] = max(sum[i - 2], sum[i - 3]) + num[i];}
        if(i == size - 1){ return sum[i] >= 0 ? sum[i] : -1;}
        return Way_of_frog(++i);
    }
};

int main()
{
    int n;
    ifstream fi("input.txt", ios::out);
    ofstream out("output.txt");
    fi >> n; 
    Frog temp(n);
    fi >> temp;
    out << temp.Way_of_frog() << endl;
    return 0;
}
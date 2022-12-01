#include <fstream>
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

class KForest
{
public:
    KForest() : n(0), q(0), counter(0) {}

    friend std::istream& operator >> (std::istream& in, KForest& temp)
    {
        in >> temp.n >> temp.q; temp.Node.resize(temp.n + 1); 
        int i = 1; temp.counter = temp.n;
        for_each(++temp.Node.begin(), temp.Node.end(), [&i](std::vector<int>& x){ x.push_back(i); x.push_back(i++);});
        return in;
    }

    void Solution()
    {
        std::for_each(Node.begin(), (Node.begin() + q), 
            [this](const std::vector<int>& temp){
                int begin, end;
                ::in >> begin >> end;
                ::out << (this->Merge(begin, end) == true ? --this->counter : this->counter) << "\n";
            }
        );
        // std::for_each(Node.begin(), Node.end(), [](const std::vector<int>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, " ")); cout << endl;});
    }

    bool Merge(int first, int second)
    {
        int i = 1;
        first = Node[first][0];
        second = Node[second][0];
        if(first == second){ return false;}
        if(Node[first].size() > Node[second].size()){ std::swap(first, second);}
        std::for_each(++Node[first].begin(), Node[first].end(), 
            [this, &first, &second, &i](int& temp){  
                this->Node[second].push_back(this->Node[first][i]);
                this->Node[this->Node[first][i++]][0] = second;
            }
        );
        return true;
    }

private:
    int n, q;
    int counter;
    std::vector<std::vector<int>> Node;
};

int main()
{
    ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    KForest test;
    in >> test;
    test.Solution();
    return 0;
}
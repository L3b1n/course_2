#include <vector>
#include <iostream>
#include <fstream>
#include <iterator>

class Frog
{
private:
    int size = 0;
    std::vector<int> num;
    std::vector<int> sum;

public:
    Frog(int _size = 0) : size(_size) { num.reserve(size); sum.resize(size);}

    friend std::istream& operator >> (std::istream& fi, Frog& a)
    {
        for(int i = 0, x; i < a.size; i++){ fi >> x; a.num.push_back(x);}
        return fi;
    }

    int Number()
    {
        int i = 0;
        return Number(i);
    }

    int Number(int i)
    {
        if(i == 0){ sum[i] = num[i];}
        else if(i == 1){ sum[i] = -10;}
        else if(i == 2){ sum[i] = sum[0] + num[i];}
        else{ sum[i] = std::max(sum[i - 2], sum[i - 3]) + num[i];}
        if(i == size - 1){ return sum[i] >= 0 ? sum[i] : -1;}
        return Number(++i);
    }

    void Way_of_frog()
    {
        std::vector<int> vec;
        Way_of_frog(size - 1, vec);
        std::copy(vec.rbegin(), vec.rend(), std::ostream_iterator<int>(std::cout, " "));
    }

    void Way_of_frog(int i, std::vector<int> &vec)
    {
        if(i < 0){ return;}
        else if(sum[i - 2] == sum[i] - num[i]){ vec.push_back(i + 1); Way_of_frog(i - 2, vec);}
        else if(sum[i - 3] == sum[i] - num[i]){ vec.push_back(i + 1); Way_of_frog(i - 3, vec);}
        else{ return;}
    }
};

int main()
{
    // freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    std::cin >> n; 
    Frog temp(n);
    std::cin >> temp;
    std::cout << temp.Number() << std::endl;
    temp.Way_of_frog();
    return 0;
}
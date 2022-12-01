#include <fstream>
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <deque>
#include <unordered_map>

typedef std::unordered_map<int, std::pair<int, int>>::value_type value_type_1;
typedef std::unordered_map<int, int>::value_type value_type_2;

namespace std
{
    std::ostream& operator << (std::ostream& out, const value_type_2& temp)
    {
        out << temp.second;
        return out;
    }

    std::ostream& operator << (std::ostream& out, const value_type_1& temp)
    {
        out << temp.second.first << " " << temp.second.second;
        return out;
    }
}

std::ifstream in("input.txt", std::ios::out);
std::ofstream out("output.txt");

class KForest
{
public:
    KForest() : count_cities(0), count_roads(0), count_earthquakes(0), counter(0) {}

    friend std::istream& operator >> (std::istream& in, KForest& temp)
    {
        in >> temp.count_cities >> temp.count_roads >> temp.count_earthquakes; 
        temp.counter = temp.count_cities; temp.num.resize(temp.count_earthquakes); temp.erase.resize(temp.count_roads + 1);
        for(int i = 1; i <= temp.count_cities;)
        {
            temp.Node[i] = i;
            temp.rank[i++] = 0;
        }
        return in;
    }

    friend std::ostream& operator << (std::ostream& out, KForest& temp)
    {
        for(int i = 1; i <= temp.count_cities; i++){ out << temp.Node[i] << " ";}
        return out;
    }

    void Solution()
    {
        for(int i = 0; i < count_roads;)
        {
            int begin, end;
            ::in >> begin >> end; request[++i] = std::make_pair(begin, end);
        }
        for(int i = 0; i < count_earthquakes;)
        {
            int count;
            ::in >> count; this->erase[count] = true;
            num[i++] = count;
        }
        std::copy(erase.begin(), erase.end(), std::ostream_iterator<bool>(std::cout, " ")); std::cout << std::endl;
        // std::cout << erase[] << std::endl;
        for(int i = 1; i <= count_roads; i++){ if(!erase[i]){ num.push_back(i);}}
        std::copy(num.begin(), num.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << std::endl;
        // if(count_earthquakes < count_roads)
        // {
        //     std::for_each(num.begin(), num.end(), 
        //         [this](const int& temp){
        //             if(!this->erase[temp]){ this->Merge(this->request[temp].first, this->request[temp].second) == true ? --this->counter : this->counter;}
        //         }
        //     );
        // }
        std::vector<int> vec = std::accumulate(num.rbegin(), num.rend(), std::vector<int>(),
            [this](std::vector<int> p, const int& temp) -> std::vector<int> {
                if(this->erase[temp]){ std::cout << temp << std::endl; p.push_back(this->counter > 1 ? 0 : 1); this->Merge(this->request[temp].first, this->request[temp].second) == true ? --this->counter : this->counter;}
                else{ std::cout << "2) " << temp << std::endl; this->Merge(this->request[temp].first, this->request[temp].second) == true ? --this->counter : this->counter;}
                return p;
            }
        );
        std::copy(vec.rbegin(), vec.rend(), std::ostream_iterator<int>(out));
    }

    int FindNode(int data)
    {
        return Node[data] == data ? Node[data] : Node[data] = FindNode(Node[data]);
    }
 
    bool Merge(int first, int second)
    {
        first = FindNode(first);
        second = FindNode(second);
        if(first == second){ return false;}
        if(rank[first] > rank[second]){ Node[second] = first;}
        else if(rank[first] < rank[second]){ Node[first] = second;}
        else
        {
            Node[first] = second;
            rank[second]++;
        }
        return true;
    }

private:
    int counter;
    int count_cities, count_roads, count_earthquakes;
    std::vector<bool> erase;
    std::vector<int> num;
    std::unordered_map<int, std::pair<int, int>> request;
    std::unordered_map<int, int> Node;
    std::unordered_map<int, int> rank;
};
 
int main()
{
    std::ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    KForest temp;
    in >> temp;
    temp.Solution();
    return 0;
}

// #include <fstream>
// #include <iostream>
// #include <vector>
// #include <numeric>
// #include <algorithm>
// #include <unordered_map>

// namespace std
// {
//     std::ostream& operator << (std::ostream& out, const std::pair<int, int>& temp)
//     {
//         out << temp.second;
//         return out;
//     }
// }

// using namespace std;

// ifstream in("input.txt", ios::out);
// ofstream out("output.txt");

 
// class KForest
// {
// public:
//     KForest() : count_cities(0), count_roads(0), count_earthquakes(0) {}

//     friend std::istream& operator >> (std::istream& in, KForest& temp)
//     {
//         in >> temp.count_cities >> temp.count_roads >> temp.count_earthquakes; 
//         temp.counter.resize(temp.count_cities + 1);
//         return in;
//     }

//     friend std::ostream& operator << (std::ostream& out, const KForest& temp)
//     {
//         // for(int i = 1; i <= temp.count_cities; i++){ out << temp.Node[i] << " ";}
//         // std::for_each(temp.Node.begin(), temp.Node.end(), 
//         //     [](const std::vector<int>& vec){
//         //         std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(::out, " ")); ::out << std::endl;
//         //     }
//         // );
//         return out;
//     }

//     void Solution()
//     {
//         for(int i = 0; i < count_roads; i++)
//         {
//             int begin, end;
//             ::in >> begin >> end; request[i].first.first = begin; request[i].first.second = counter[begin]++; request[i].second.first = end; request[i].second.second = counter[end]++;
//             Node[begin].push_back(end); Node[end].push_back(begin);
//         }
//         std::copy(counter.begin(), counter.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << std::endl;
//         for(int i = 0; i < count_earthquakes; i++)
//         {
//             int count;
//             ::in >> count; 
//             Node[request[count - 1].first.first].erase(Node[request[count - 1].first.first].begin() + request[count - 1].first.second - 1);
//             Node[request[count - 1].second.first].erase(Node[request[count - 1].second.first].begin() + request[count - 1].second.second - 1);
//             ::out << (Node.size() == count_cities ? 1 : 0);
//         }
//     }

// private:
//     int count_cities, count_roads, count_earthquakes;
//     std::vector<int> counter;
//     std::unordered_map<int, std::pair<std::pair<int, int>, std::pair<int, int>>> request;
//     std::unordered_map<int, std::vector<int>> Node;
// };
 
// int main()
// {
//     KForest temp;
//     in >> temp;
//     temp.Solution();
//     return 0;
// }
 
// int main()
// {
//     std::unordered_map<int, std::vector<int>> temp;
//     temp[1].push_back(2);
//     temp[2].push_back(1);

//     temp[2].push_back(3);
//     temp[3].push_back(2);

//     temp[3].push_back(4);
//     temp[4].push_back(3);

//     temp[4].push_back(1);
//     temp[1].push_back(4);

//     temp[1].push_back(3);
//     temp[3].push_back(1);

//     temp[2].push_back(4);
//     temp[4].push_back(2);
    
//     temp[1].erase(temp[1].begin() + 2);
//     temp[3].erase(temp[3].begin() + 2);

//     temp[2].erase(temp[2].begin() + 2);
//     temp[4].erase(temp[4].begin() + 2);

//     temp[1].erase(temp[1].begin() + 0);
//     temp[2].erase(temp[2].begin() + 0);
//     std::cout << temp.size() << std::endl;
//     for(auto vec : temp){ for(auto x : vec.second){ std::cout << x << " ";} std::cout << std::endl;} 
//     return 0;
// }

// 1   2
// | /
// 3 - 4

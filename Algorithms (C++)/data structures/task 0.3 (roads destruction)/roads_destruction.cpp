#include <fstream>
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <unordered_map>

std::ifstream in("input.txt", std::ios::out);
std::ofstream out("output.txt");

class KForest
{
public:
    KForest() : count_cities(0), count_roads(0), count_earthquakes(0), counter(0) {}

    friend std::istream& operator >> (std::istream& in, KForest& temp)
    {
        in >> temp.count_cities >> temp.count_roads >> temp.count_earthquakes; 
        temp.counter = temp.count_cities; temp.request.resize(temp.count_roads);
        temp.num.resize(temp.count_earthquakes); temp.erase.resize(temp.count_roads + 1);
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
        std::for_each(request.begin(), request.end(), 
            [](std::pair<int, int>& temp){
                int begin, end;
                ::in >> begin >> end; temp = std::make_pair(begin, end);
            }
        );
        std::for_each(num.rbegin(), num.rend(), 
            [this](int& temp){
                int count;
                ::in >> count; temp = count;
                this->erase[count] = true;
            }
        );
        // std::copy(erase.begin(), erase.end(), std::ostream_iterator<bool>(std::cout, " ")); std::cout << std::endl;
        // std::copy(num.begin(), num.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << std::endl;
        int i = 0;
        if(count_earthquakes < count_roads)
        {
            std::for_each(++erase.begin(), erase.end(), 
                [this, &i](const bool& temp){
                    if(!temp){ this->Merge(this->request[i].first, this->request[i].second) == true ? --this->counter : this->counter;} i++;
                }
            );
        }
        std::vector<bool> vec = std::accumulate(num.begin(), num.end(), std::vector<bool>(),
            [this](std::vector<bool> p, const int& temp) -> std::vector<bool> {
                p.push_back(this->counter > 1 ? 0 : 1); this->Merge(this->request[temp - 1].first, this->request[temp - 1].second) == true ? --this->counter : this->counter;
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
    std::vector<std::pair<int, int>> request;
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
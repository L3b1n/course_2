#include <set>
#include <queue>  
#include <vector>
#include <iostream>
#include <algorithm>

class TrucksAndStones
{
public:
    TrucksAndStones() : n(0), m(0) {}

    friend std::istream& operator >> (std::istream& in, TrucksAndStones& a)
    {
        in >> a.n;
        a.answer.resize(a.n, 0);
        for(int i = 0; i < a.n; i++) 
        {
            long long temp;
            std::cin >> temp;
            a.answer[i] = Truck(temp);
        }
        std::sort(a.answer.rbegin(), a.answer.rend());
        in >> a.m;
        a.tempVector.resize(a.m, 0);
        for(int i = 0; i < a.m; i++){ std::cin >> a.tempVector[i];}
        std::sort(a.tempVector.rbegin(), a.tempVector.rend());
        return in;
    }

	void Solution()
	{
        int i = 0;
        int index = 0;
        tempQueue.push(answer[i]);
        while(index != m) 
        {
            if(tempQueue.top().stones >= tempVector[index])
            {
                Truck temp = tempQueue.top(); tempQueue.pop();
                temp.stones -= tempVector[index];
                (*temp.matrix).push_back(tempVector[index++]);
                tempQueue.push(temp);
            }
            else 
            {
                if(++i == n && index != m){ std::cout << "no solution\n"; return;}
                tempQueue.push(answer[i]);
            }
        }
        std::cout << n << "\n";
        for(int i = 0; i < n; ++i) 
        {
            std::cout << answer[i].stones << "\n";
            for(int h = 0; h < answer[i].matrix->size(); ++h){ std::cout << (*answer[i].matrix)[h] << " ";}
            std::cout << "\n";
        }
	}

private:
    class Truck
    {
    public:
        Truck() : stones(0) {}

        Truck(long long stones) : stones(stones) { matrix = new std::vector<long long>();}

        inline bool operator > (const Truck& temp) const{ return (stones > temp.stones);}

        inline bool operator < (const Truck& temp) const{ return (stones < temp.stones);}

        inline bool operator == (const Truck& temp) const{ return (stones == temp.stones);}

    public:
        long long stones;
        std::vector<long long>* matrix;
    };

private:
    int n, m;
    std::vector<Truck> answer;
    std::vector<long long> tempVector;
    std::priority_queue<Truck> tempQueue;
};

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    TrucksAndStones trucksAndStones;
    std::cin >> trucksAndStones;
    trucksAndStones.Solution();
    return 0;
}
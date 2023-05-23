// паросочетание (скорее всего это решение)
// максимальный поток
// Запускать два куна -- сначала для однозначных ребер, типа Е1 -> L1 и E1 -> L0 ... E1, а потом достроив ребра E0 -> L0 и 
// E1 ->... L0 ... E0 ->... L1 запустить второй раз куна
#include <set>
#include <vector>
#include <numeric>
#include <iostream>
#include <algorithm>

namespace std
{
    template<typename T1, typename T2>
    std::ostream& operator << (std::ostream& in, const std::pair<T1, T2>& temp)
    {
        in << temp.first << " " << temp.second;
        return in;
    }
}

class CrimeHouse
{
public:
    CrimeHouse() : countSummit(0) {}
 
	friend std::istream& operator >> (std::istream& in, CrimeHouse& a)
    {
        int counter = 0;
        in >> a.countSummit;
        bool isEntered = false;
        a.sortedSummit.resize(2002);
        a.conected.resize(2002, true);
        a.graph.resize(a.countSummit + 1);
        a.inputSummit.push_back(std::make_pair(-20, "E"));
        for(int i = 0; i < a.countSummit; i++)
        {
            int number;
            std::string type;
            std::cin >> type >> number;
            if(type == "E"){ isEntered = true; a.enter.push_back(std::make_pair(++number, ++counter)); a.inputSummit.push_back(std::make_pair(number, type)); a.sortedSummit[number].push_back(counter);}
            else if(!isEntered && number != 0 && !a.leftSet.emplace(number + 1).second){ std::cout << "CRIME TIME\n"; exit(0);}
            else if(isEntered){ a.left.push_back(std::make_pair(++number, ++counter)); a.inputSummit.push_back(std::make_pair(number, type)); a.sortedSummit[number].push_back(counter);}
        }
        return in;
    }

	int Solution()
	{
        int i = 1;
        std::for_each(++inputSummit.begin(), inputSummit.end(),
            [this, &i](const std::pair<int, std::string>& temp){
                if(temp.first != 1 && temp.second == "E")
                {
                    std::vector<int>::iterator itSorted = std::lower_bound(sortedSummit[temp.first].begin(), sortedSummit[temp.first].end(), i);
                    if(itSorted != sortedSummit[temp.first].end() && ++itSorted != sortedSummit[temp.first].end()){
                        if(temp == inputSummit[*itSorted] && conected[*itSorted]){
                            bool condition = true;
                            for(int j = i; j < *itSorted; j++){ if(inputSummit[j].first == 1 && inputSummit[j].second == "L" && conected[j]){ graph[i].push_back(j); conected[i] = false; conected[j] = false; condition = false; break;}}
                            if(condition){ std::cout << "CRIME TIME\n"; exit(0);}
                        }
                        else if(inputSummit[*itSorted].second == "L" && conected[*itSorted]){ graph[i].push_back(*itSorted); conected[i] = false; conected[*itSorted] = false;}
                    }
                } i++;
            }
        ); i = 1;
        std::for_each(++inputSummit.begin(), inputSummit.end(),
            [this, &i](const std::pair<int, std::string>& temp){
                if(temp.first != 1 && temp.second == "L")
                {
                    std::vector<int>::iterator itSorted = std::lower_bound(sortedSummit[temp.first].begin(), sortedSummit[temp.first].end(), i);
                    if(itSorted-- != sortedSummit[temp.first].begin()){
                        if(temp == inputSummit[*itSorted]){
                            bool condition = true;
                            for(int j = *itSorted; j < i; j++){ if(inputSummit[j].first == 1 && inputSummit[j].second == "E"){ condition = false; break;}}
                            if(condition){ std::cout << "CRIME TIME\n"; exit(0);}
                        }
                    }
                } i++;
            }
        ); i = 1;
        std::for_each(++inputSummit.begin(), inputSummit.end(),
            [this, &i](const std::pair<int, std::string>& temp){
                if(temp.first == 1 && temp.second == "E" && conected[i]){
                    for(int j = i; j < inputSummit.size(); j++){ if(inputSummit[j].first != 1 && inputSummit[j].second == "L" && conected[j]){ graph[i].push_back(j); conected[i] = false; conected[j] = false; break;}}
                } i++;
            }
        );
        summit.resize(countSummit + 1, -1);
        tempUsed.resize(countSummit + 1, false);
        for(int tempSummit = 1; tempSummit <= countSummit; tempSummit++)
        {
            isUsed.assign(countSummit + 1, false);
            kuhn(tempSummit);
        } i = 1;
        std::for_each(++summit.begin(), summit.end(),
            [this, &i](const int& temp){
                if(temp != -1){ tempUsed[temp] = true; tempUsed[i] = true;} i++;
            }
        );
        graph.clear(); graph.resize(countSummit + 1);
        std::for_each(left.begin(), left.end(), 
            [this](const std::pair<int, int>& temp){
                std::vector<std::pair<int, int>>::iterator itEnter = enter.begin();
                while(itEnter != enter.end() && temp.second > itEnter->second)
                {
                    if(temp.first == itEnter->first){ graph[itEnter->second].push_back(temp.second);}
                    else if(temp.first == 1){ graph[itEnter->second].push_back(temp.second);}
                    else if(itEnter->first == 1){ graph[itEnter->second].push_back(temp.second);}
                    itEnter++;
                }
            }
        );
        for(int tempSummit = 1; tempSummit <= countSummit; tempSummit++)
        {
            if(tempUsed[tempSummit]){ continue;}
            isUsed.assign(countSummit + 1, false);
            kuhn(tempSummit);
        } i = 1;
        std::for_each(++summit.begin(), summit.end(), [this, &i](int& temp){ if(temp != -1){ summit[temp] = i;} i++;}); i = 1;
        return enter.size() - std::accumulate(++inputSummit.begin(), inputSummit.end(), int(), 
            [this, &i](int tempCount, std::pair<int, std::string>& temp){
                if(summit[i] != -1){ tempCount++;}
                else
                {
                    if(temp.first != 1 && (temp.second == "E" ? !enterSet.emplace(temp.first).second : !leftSet.emplace(temp.first).second))
                    {
                        std::cout << "CRIME TIME\n"; exit(0);
                    }
                } i++;
                return tempCount;
            }
        ) / 2;
        // std::copy(sortedSummit.begin(), sortedSummit.end(), std::ostream_iterator<std::pair<int, int>>(std::cout, "\n")); std::cout << "\n\n";
        // std::copy(inputSummit.begin(), inputSummit.end(), std::ostream_iterator<std::pair<int, std::string>>(std::cout, " ")); std::cout << "\n\n";
        // for(int i = 0; i < countSummit + 1; ++i){ if(summit[i] != -1){ printf("%d %d\n", summit[i], i);}}
	}

private:
    int countSummit;
    std::set<int> leftSet;
    std::set<int> enterSet;
    std::vector<int> summit;
    std::vector<bool> isUsed;
    std::vector<bool> tempUsed;
    std::vector<bool> conected;
    std::vector<std::vector<int>> graph;
    std::vector<std::pair<int, int>> left;
    std::vector<std::pair<int, int>> enter;
    std::vector<std::vector<int>> sortedSummit;
    std::vector<std::pair<int, std::string>> inputSummit;

    bool kuhn(int tempSummit) 
    {
        if(isUsed[tempSummit]){ return false;}
        isUsed[tempSummit] = true;
        return std::any_of(graph[tempSummit].begin(), graph[tempSummit].end(),
            [&](const int& to){
                bool condition = false; 
                if(summit[to] == -1 || kuhn(summit[to])){ summit[to] = tempSummit; condition = true;} return condition;
            }
        );
    }
};

int main()
{
    freopen("crime.in", "r", stdin);
    // freopen("crime.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    CrimeHouse crimeHouse;
    std::cin >> crimeHouse;
    std::cout << crimeHouse.Solution() << "\n";
    return 0;
}
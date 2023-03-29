#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <cmath>
#include <queue>
#include <vector>
#include <numeric>
#include <algorithm>
#include <unordered_map>

class Cylinder
{
public:
    Cylinder() : hight(0), length(0) {}

    friend std::istream& operator >> (std::istream& fi, Cylinder& a)
    {
        fi >> a.hight >> a.length;
        a.sheet.resize(a.hight, std::vector<std::pair<int, bool>>(a.length, std::make_pair(0, false)));
        std::for_each(a.sheet.begin(), a.sheet.end(), 
            [&a, &fi](std::vector<std::pair<int, bool>>& vec){
                std::for_each(vec.begin(), vec.end(), [&fi](std::pair<int, bool>& temp){ int tempNode; fi >> tempNode; temp = std::make_pair(tempNode, false);});
            }
        );
        return fi;
    }

    std::vector<std::pair<int, int>> Get(int tempI, int tempJ)
    {
        std::vector<std::pair<int, int>> answer;
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(i * j != 0 || (i == 0 && j == 0) || tempI + i < -1 || tempI + i > hight || tempJ + j < 0 || tempJ + j >= length){ continue;}
                if(tempI + i == -1){ tempI = hight - 1 - i;}
                else if(tempI + i == hight){ tempI = 0 - i;}
                if(sheet[tempI + i][tempJ + j].first == 0){ answer.push_back(std::make_pair(tempI + i, tempJ + j));}
            }
        }
        return answer;
    }

    void BFS(int i, int j)
    {
        std::queue<std::pair<int, int>> tempQueue;
        tempQueue.push(std::make_pair(i, j));
        sheet[i][j].second = true;
        while(!tempQueue.empty())
        {
            std::pair<int, int> temp = tempQueue.front(); tempQueue.pop();
            std::vector<std::pair<int, int>> vec = Get(temp.first, temp.second);
            std::for_each(vec.begin(), vec.end(),
                [this, &tempQueue](const std::pair<int, int>& tempGet){
                    if(!sheet[tempGet.first][tempGet.second].second)
                    {
                        sheet[tempGet.first][tempGet.second].second = true;
                        tempQueue.push(tempGet);
                    }
                }
            );
        }
    }

    int Solution()
    {
        int i = 0;
        std::for_each(sheet.begin(), sheet.end(), 
            [this, &i](const std::vector<std::pair<int, bool>>& vec){
                int j = 0;
                std::for_each(vec.begin(), vec.end(), 
                    [this, &i, &j](const std::pair<int, bool>& temp){
                        if(!temp.second && temp.first == 0)
                        {
                            BFS(i, j);
                            countOfElements++;
                        }
                        j++;
                    }
                );
                i++;
            }
        );
        return countOfElements;
    }

private:
    int hight, length, countOfElements = 0;
    std::vector<std::vector<std::pair<int, bool>>> sheet;
};

int main()
{
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Cylinder cylinder;
    std::cin >> cylinder;
    std::cout << cylinder.Solution() << "\n";
    return 0;
}
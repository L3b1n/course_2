#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <stack>
#include <vector>
#include <numeric>
#include <algorithm>

struct Point
{
public:
    Point() : color(0), blocked(std::make_pair(false, false)) {}

    int color;
    std::pair<bool, bool> blocked;
};

class FloodIt
{
public:
    FloodIt() : height(0), width(0), variousColor(0),  stepLength(0) {}

    friend std::istream& operator >> (std::istream& fi, FloodIt& a)
    {
        fi >> a.height >> a.width >> a.variousColor >> a.stepLength;
        a.tempColors.resize(a.variousColor + 1);
        a.points.resize(a.height, std::vector<Point>(a.width + 1));
        int i = 0;
        std::for_each(a.points.begin(), a.points.begin() + a.height,
            [&a, &i, &fi](std::vector<Point>& vec){
                int j = 0;
                std::for_each(vec.begin(), vec.begin() + a.width,
                    [&i, &j, &fi](Point& temp){
                        fi >> temp.color;
                    }
                );
                i++;
            }
        );
        return fi;
    }

    void Solution()
    {
        int i = 0;
        int counter = 0;
        bool condition = false;
        int color = points[0][0].color;
        int previousColor = points[0][0].color;
        tempColors[points[0][0].color].push(std::make_pair(0, 0));
        pointRepaint(color, counter);
        while(i++ < stepLength)
        {
            std::cin >> color;
            if(counter - height * width){ if(color != previousColor){ pointRepaint(color, counter);} previousColor = color;}
            else{ condition = true; break;}
        }
        if(condition){ for(; std::cin >> color;){}}
        std::for_each(points.begin(), points.begin() + height,
            [this, &condition, &color](const std::vector<Point>& vec){
                std::for_each(vec.begin(), vec.begin() + width,
                    [&condition, &color](const Point& temp){
                        if(!condition && !temp.blocked.second){ std::cout << temp.color << " ";}
                        else{ std::cout << color << " ";}
                    }
                );
                std::cout << "\n";
            }
        );
    }

private:
    std::vector<std::vector<Point>> points;
    std::vector<std::stack<std::pair<int, int>>> tempColors;
    int height, width, variousColor, stepLength;
    
    inline void pointRepaint(int& color, int& counter)
    {
        std::pair<int, int> tempPoint;
        while(!tempColors[color].empty())
        {
            counter++;
            tempPoint = tempColors[color].top(); tempColors[color].pop();
            points[tempPoint.first][tempPoint.second].blocked = std::make_pair(true, true);
            if(tempPoint.first > 0 && !points[tempPoint.first - 1][tempPoint.second].blocked.first)
            {
                points[tempPoint.first - 1][tempPoint.second].blocked.first = true;
                tempColors[points[tempPoint.first - 1][tempPoint.second].color].push(std::make_pair(tempPoint.first - 1, tempPoint.second));
            }
            if(tempPoint.first > 0 && !points[tempPoint.first][tempPoint.second - 1].blocked.first)
            {
                points[tempPoint.first][tempPoint.second - 1].blocked.first = true;
                tempColors[points[tempPoint.first][tempPoint.second - 1].color].push(std::make_pair(tempPoint.first, tempPoint.second - 1));
            }
            if(tempPoint.first < height - 1 && !points[tempPoint.first + 1][tempPoint.second].blocked.first)
            {
                points[tempPoint.first + 1][tempPoint.second].blocked.first = true;
                tempColors[points[tempPoint.first + 1][tempPoint.second].color].push(std::make_pair(tempPoint.first + 1, tempPoint.second));
            }
            if(tempPoint.first < width - 1 && !points[tempPoint.first][tempPoint.second + 1].blocked.first)
            {
                points[tempPoint.first][tempPoint.second + 1].blocked.first = true;
                tempColors[points[tempPoint.first][tempPoint.second + 1].color].push(std::make_pair(tempPoint.first, tempPoint.second + 1));
            }
        }
    }
};

int main()
{
    freopen("floodit.in", "r", stdin);
    // freopen("floodit.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    FloodIt floodIt;
    std::cin >> floodIt;
    floodIt.Solution();
    return 0;
}
#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <unordered_map>

struct Point
{
public:
    Point() : color(0), point(std::make_pair(0, 0)), blocked(std::make_pair(false, false)) {}

    int color;
    std::pair<int, int> point;
    std::pair<bool, bool> blocked;
};

class FloodIt
{
public:
    FloodIt() : height(0), width(0), variousColor(0),  stepLength(0) {}

    friend std::istream& operator >> (std::istream& fi, FloodIt& a)
    {
        fi >> a.height >> a.width >> a.variousColor >> a.stepLength;
        a.position.resize(a.variousColor + 1, 0);
        a.points.resize(a.height + 10, std::vector<Point>(a.width + 10));
        int i = 0;
        std::for_each(a.points.begin(), a.points.begin() + a.height,
            [&a, &i, &fi](std::vector<Point>& vec){
                int j = 0;
                std::for_each(vec.begin(), vec.begin() + a.width,
                    [&i, &j, &fi](Point& temp){
                        fi >> temp.color;
                        temp.point = std::make_pair(i, j++);
                    }
                );
                i++;
            }
        );
        return fi;
    }

    void Solution()
    {
        int i = 0, color;
        points[0][0].blocked.second = true;
        tempMap.insert(std::make_pair(points[0][0].color, 0));
        position[points[0][0].color]++;
        createPoint(points[0][0]);
        while(i++ < stepLength)
        {
            std::cin >> color;
            if(int tempPos = position[color])
            {
                int j = 0;
                std::unordered_multimap<int, int>::const_iterator itTemp = tempMap.find(color);
                std::unordered_multimap<int, int>::const_iterator itStart = tempMap.find(color);
                while(j++ < tempPos){ createPoint(points[itTemp->second >> 10][itTemp->second & 1023]); itTemp++;}
                tempMap.erase(itStart, itTemp);
                position[color] = 0;
            }
        }
        std::for_each(points.begin(), points.begin() + height,
            [this, &color](const std::vector<Point>& vec){
                std::for_each(vec.begin(), vec.begin() + width,
                    [&color](const Point& temp){
                        if(temp.blocked.first){ std::cout << color << " ";}
                        else{ std::cout << temp.color << " ";}
                    }
                );
                std::cout << "\n";
            }
        );
    }

private:
    std::vector<int> position;
    std::vector<std::vector<Point>> points;
    std::unordered_multimap<int, int> tempMap;
    int height, width, variousColor, stepLength;
    
    inline void createPoint(Point& tempPoint)
    {
        tempPoint.blocked.first = true;
        if(tempPoint.point.first > 0){ insertPoint(points[tempPoint.point.first - 1][tempPoint.point.second], tempPoint.color);}
        if(tempPoint.point.first < height - 1){ insertPoint(points[tempPoint.point.first + 1][tempPoint.point.second], tempPoint.color);}
        if(tempPoint.point.second > 0){ insertPoint(points[tempPoint.point.first][tempPoint.point.second - 1], tempPoint.color);}
        if(tempPoint.point.second < width - 1){ insertPoint(points[tempPoint.point.first][tempPoint.point.second + 1], tempPoint.color);}
    }

    inline void insertPoint(Point& tempPoint, int tempColor)
    {
        if(tempPoint.blocked.first){ return;}
        if(tempPoint.color == tempColor){ createPoint(tempPoint);}
        else if(!tempPoint.blocked.second)
        {
            tempPoint.blocked.second = true;
            position[tempPoint.color]++;
            tempMap.insert(std::make_pair(tempPoint.color, (tempPoint.point.first << 10) + tempPoint.point.second));
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
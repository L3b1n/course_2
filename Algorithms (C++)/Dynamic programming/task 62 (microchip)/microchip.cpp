#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>

namespace std
{
    template<typename T1, typename T2>
    std::istream& operator >> (std::istream& in, std::pair<T1, T2>& temp)
    {
        in >> temp.first >> temp.second;
        return in;
    }
}

class Microchip
{
public:
    Microchip(long long length, long long numberOfContacts) : length(length), numberOfContacts(numberOfContacts)
    {
        points.resize(numberOfContacts);
        vertical.resize(39, std::vector<long long>());
        horizontal.resize(39, std::vector<long long>());
        answerArray.resize(31, std::vector<long long>(31, 0ll));
        isExist.resize(31, std::vector<std::vector<std::vector<std::vector<bool>>>>(31, std::vector<std::vector<std::vector<bool>>>(31, std::vector<std::vector<bool>>(31, std::vector<bool>(16, false)))));
        tempArray.resize(31, std::vector<std::vector<std::vector<std::vector<long long>>>>(31, std::vector<std::vector<std::vector<long long>>>(31, std::vector<std::vector<long long>>(31, std::vector<long long>(16, 0ll)))));
    }

    friend std::istream& operator >> (std::istream& fi, Microchip& a)
    {
        std::for_each(a.points.begin(), a.points.end(), 
            [&a, &fi](std::pair<long long, long long>& tempPoint){
                fi >> tempPoint;
                a.horizontal[tempPoint.first].push_back(tempPoint.second);
                a.vertical[tempPoint.second].push_back(tempPoint.first);
            }
        );
        long long i = 0;
        std::for_each(a.vertical.begin(), a.vertical.end(), 
            [&i, &a](std::vector<long long>& vec){
                std::make_heap(vec.begin(), vec.end());
                std::make_heap(a.horizontal[i].begin(), a.horizontal[i].end());
                std::sort_heap(vec.begin(), vec.end());
                std::sort_heap(a.horizontal[i].begin(), a.horizontal[i].end());
                ++i;
            }
        );
        // std::cout << a.horizontal[0][0] << "\n";
        // std::for_each(a.points.begin(), a.points.end(), [](const std::pair<long long, long long>& temp){ std::cout << temp.first << " " << temp.second << "\n";});
        // std::for_each(a.vertical.begin(), a.vertical.end(), [](const std::vector<long long>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << "\n";});
        // std::for_each(a.horizontal.begin(), a.horizontal.end(), [](const std::vector<long long>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << "\n";});
        return fi;
    }

    long long minimalLength(long long left, long long top, long long right, long long bottom, long long direction)
    {
        if(isExist[left][top][right][bottom][direction]){ return tempArray[left][top][right][bottom][direction];}
        tempArray[left][top][right][bottom][direction] = INT_MAX;
        if(right < left || bottom < top){ return 0;}
        if(direction == 15){ return tempArray[left][top][right][bottom][direction];}
        long long leftTop = greaterOrEqualNumber(horizontal[left], top);
        long long leftBottom = greaterNumber(horizontal[left], bottom);
        if(leftTop == leftBottom)
        {
            tempArray[left][top][right][bottom][direction] = minimalLength(left + 1, top, right, bottom, direction);
            isExist[left][top][right][bottom][direction] = true;
            return tempArray[left][top][right][bottom][direction];
        }
        long long rightTop = greaterOrEqualNumber(horizontal[right], top);
        long long rightBottom = greaterNumber(horizontal[right], bottom);
        if(rightTop == rightBottom)
        {
            tempArray[left][top][right][bottom][direction] = minimalLength(left, top, right - 1, bottom, direction);
            isExist[left][top][right][bottom][direction] = true;
            return tempArray[left][top][right][bottom][direction];
        }
        long long topLeft = greaterOrEqualNumber(vertical[top], left);
        long long topRight = greaterNumber(vertical[top], right);
        if(topLeft == topRight)
        {
            tempArray[left][top][right][bottom][direction] = minimalLength(left, top + 1, right, bottom, direction);
            isExist[left][top][right][bottom][direction] = true;
            return tempArray[left][top][right][bottom][direction];
        }
        long long bottomLeft = greaterOrEqualNumber(vertical[bottom], left);
        long long bottomRight = greaterNumber(vertical[bottom], right);
        if(bottomLeft == bottomRight)
        {
            tempArray[left][top][right][bottom][direction] = minimalLength(left, top, right, bottom - 1, direction);
            isExist[left][top][right][bottom][direction] = true;
            return tempArray[left][top][right][bottom][direction];
        }
        if(!Closed(direction, 0) || !Closed(direction, 2))
        {
            int i = leftTop, counter = 0;
            bool isUpAvailable = true;
            for(int i = leftTop; i != leftBottom && i < horizontal[left].size(); i++)
            {
                long long temp = horizontal[left][i];
                if(isLineExist(left, temp, direction, 2))
                {
                    tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, right, temp - 1, directionClose(direction, 1)) 
                                                                                + minimalLength(left, temp + 1, right, bottom, directionClose(direction, 3)) + this->length - left);
                }
                counter++;
                isUpAvailable &= isLineExist(left, temp, direction, 0);
            }
            if(isUpAvailable){ tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left + 1, top, right, bottom, direction) + left * counter);}
            i = rightTop; counter = 0;
            bool isDownAvailable = true;
            for(int i = rightTop; i != rightBottom && i < horizontal[right].size(); i++)
            {
                long long temp = horizontal[right][i];
                if(isLineExist(right, temp, direction, 0))
                {
                    tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, right, temp - 1, directionClose(direction, 1)) 
                                                                                + minimalLength(left, temp + 1, right, bottom, directionClose(direction, 3)) + right);
                }
                counter++;
                isDownAvailable &= isLineExist(right, temp, direction, 2);
            }
            if(isDownAvailable){ tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, right - 1, bottom, direction) + (length - right) * counter);}
        }
        if(!Closed(direction, 1) || !Closed(direction, 3))
        {
            int i = topLeft, counter = 0;
            bool isLeftAvailable = true;
            for(int i = topLeft; i != topRight && i < vertical[top].size(); i++)
            {
                long long temp = vertical[top][i];
                if(isLineExist(temp, top, direction, 1))
                {
                    tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, temp - 1, bottom, directionClose(direction, 2)) 
                                                                                + minimalLength(temp + 1, top, right, bottom, directionClose(direction, 0)) + this->length - top);
                }
                counter++;
                isLeftAvailable &= isLineExist(temp, top, direction, 3);
            }
            if(isLeftAvailable){ tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top + 1, right, bottom, direction) + top * counter);}
            i = bottomLeft; counter = 0;
            bool isRightAvailable = true;
            for(int i = bottomLeft; i != bottomRight && i < vertical[bottom].size(); i++)
            {
                long long temp = vertical[bottom][i];
                if(isLineExist(temp, bottom, direction, 3))
                {
                    tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, temp - 1, bottom, directionClose(direction, 2)) 
                                                                                + minimalLength(temp + 1, top, right, bottom, directionClose(direction, 0)) + bottom);
                }
                counter++;
                isRightAvailable &= isLineExist(temp, bottom, direction, 1);
            }
            if(isRightAvailable){ tempArray[left][top][right][bottom][direction] = std::min(tempArray[left][top][right][bottom][direction], minimalLength(left, top, right, bottom - 1, direction) + (length - bottom) * counter);}
        }
        isExist[left][top][right][bottom][direction] = true;
        return tempArray[left][top][right][bottom][direction];
    }

    void createPath(long long left, long long top, long long right, long long bottom, long long direction)
    {
        if(right < left || bottom < top){ return;}
        long long tempNumber = tempArray[left][top][right][bottom][direction];
        long long leftTop = greaterOrEqualNumber(horizontal[left], top);
        long long leftBottom = greaterNumber(horizontal[left], bottom);
        if(leftTop == leftBottom){ createPath(left + 1, top, right, bottom, direction); return;}
        long long rightTop = greaterOrEqualNumber(horizontal[right], top);
        long long rightBottom = greaterNumber(horizontal[right], bottom);
        if(rightTop == rightBottom){ createPath(left, top, right - 1, bottom, direction); return;}
        long long topLeft = greaterOrEqualNumber(vertical[top], left);
        long long topRight = greaterNumber(vertical[top], right);
        if(topLeft == topRight){ createPath(left, top + 1, right, bottom, direction); return;}
        long long bottomLeft = greaterOrEqualNumber(vertical[bottom], left);
        long long bottomRight = greaterNumber(vertical[bottom], right);
        if(bottomLeft == bottomRight){ createPath(left, top, right, bottom - 1, direction); return;}
        if(!Closed(direction, 0) || !Closed(direction, 2))
        {
            bool isUpAvailable = true;
            int i = leftTop, counter = 0;
            for(int i = leftTop; i != leftBottom && i < horizontal[left].size(); i++)
            {
                long long temp = horizontal[left][i];
                if(isLineExist(left, temp, direction, 2) && tempNumber == minimalLength(left, top, right, temp - 1, directionClose(direction, 1)) + minimalLength(left, temp + 1, right, bottom, directionClose(direction, 3)) + this->length - left)
                {
                    answerArray[left][temp] = 2;
                    createPath(left, top, right, temp - 1, directionClose(direction, 1));
                    createPath(left, temp + 1, right, bottom, directionClose(direction, 3));
                    return;
                }
                counter++;
                isUpAvailable &= isLineExist(left, temp, direction, 0);
            }
            if(isUpAvailable && tempNumber == minimalLength(left + 1, top, right, bottom, direction) + left * counter)
            {
                int i = leftTop;
                while(i != leftBottom && i < horizontal[left].size()){ answerArray[left][horizontal[left][i++]] = 0;}
                createPath(left + 1, top, right, bottom, direction); return;
            }
            i = rightTop; counter = 0;
            bool isDownAvailable = true;
            for(int i = rightTop; i != rightBottom && i < horizontal[right].size(); i++)
            {
                long long temp = horizontal[right][i];
                if(isLineExist(right, temp, direction, 0) && tempNumber == minimalLength(left, top, right, temp - 1, directionClose(direction, 1)) + minimalLength(left, temp + 1, right, bottom, directionClose(direction, 3)) + right)
                {
                    answerArray[right][temp] = 0;
                    createPath(left, top, right, temp - 1, directionClose(direction, 1));
                    createPath(left, temp + 1, right, bottom, directionClose(direction,3)); 
                    return;
                }
                counter++;
                isUpAvailable &= isLineExist(right, temp, direction, 2);
            }
            if(isDownAvailable && tempNumber == minimalLength(left, top, right - 1, bottom, direction) + (this->length - right) * counter)
            {
                int i = rightTop;
                while(i != rightBottom && i < horizontal[right].size()){ answerArray[right][horizontal[right][i++]] = 2;}
                createPath(left, top, right - 1, bottom, direction); return;
            }
        }

        if(!Closed(direction, 1) || !Closed(direction, 3))
        {
            bool isLeftAvailable = true;
            int i = topLeft, counter = 0;
            for(int i = topLeft; i != topRight && i < vertical[top].size(); i++)
            {
                long long temp = vertical[top][i];
                if(isLineExist(temp, top, direction, 1) && tempNumber == minimalLength(left, top, temp - 1, bottom, directionClose(direction, 2)) + minimalLength(temp + 1, top, right, bottom, directionClose(direction, 0)) + this->length - top)
                {
                    answerArray[temp][top] = 1;
                    createPath(left, top, temp - 1, bottom, directionClose(direction, 2));
                    createPath(temp + 1, top, right, bottom, directionClose(direction, 0));
                    return;
                }
                counter++;
                isLeftAvailable &= isLineExist(temp, top, direction, 3);
            }
            if(isLeftAvailable && tempNumber == minimalLength(left, top + 1, right, bottom, direction) + top * counter)
            { 
                int i = topLeft;
                while(i != topRight && i < vertical[top].size()){ answerArray[vertical[top][i++]][top] = 3;}
                createPath(left, top + 1, right, bottom, direction); return;
            }
            i = bottomLeft; counter = 0;
            bool isRightAvailable = true;
            for(int i = bottomLeft; i != bottomRight && i < vertical[bottom].size(); i++)
            {
                long long temp = vertical[bottom][i];
                if(isLineExist(temp, bottom, direction, 3) && tempNumber == minimalLength(left, top, temp - 1, bottom, directionClose(direction, 2)) + minimalLength(temp + 1, top, right, bottom, directionClose(direction, 0)) + bottom)
                {
                    answerArray[temp][bottom] = 3;
                    createPath(left, top, temp - 1, bottom, directionClose(direction, 2));
                    createPath(temp + 1, top, right, bottom, directionClose(direction, 0));
                    return;
                }
                counter++;
                isLeftAvailable &= isLineExist(temp, top, direction, 1);
            }
            if(isRightAvailable && tempNumber == minimalLength(left, top, right, bottom - 1, direction) + (this->length - bottom) * counter)
            { 
                int i = bottomLeft;
                while(i != bottomRight && i < vertical[bottom].size()){ answerArray[vertical[bottom][i++]][bottom] = 1;}
                createPath(left, top, right, bottom - 1, direction); return;
            }
        }
    }

    void Solution()
    {
        std::cout << this->minimalLength(1, 1, length - 1, length - 1, 0ll) << "\n";
        this->createPath(1, 1, length - 1, length - 1, 0ll);
        std::for_each(points.begin(), points.end(),
            [this](const std::pair<long long, long long>& temp){
                if(this->interpretsDirection(this->answerArray[temp.first][temp.second]) == 0){ std::cout << "UP\n";}
                else if(this->interpretsDirection(this->answerArray[temp.first][temp.second]) == 1){ std::cout << "RIGHT\n";}
                else if(this->interpretsDirection(this->answerArray[temp.first][temp.second]) == 2){ std::cout << "DOWN\n";}
                else if(this->interpretsDirection(this->answerArray[temp.first][temp.second]) == 3){ std::cout << "LEFT\n";}
            }
        );
    }

private:
    long long length;
    long long numberOfContacts;
    std::vector<std::vector<long long>> vertical;
    std::vector<std::vector<long long>> horizontal;
    std::vector<std::vector<long long>> answerArray;
    std::vector<std::pair<long long, long long>> points;
    std::vector<std::vector<std::vector<std::vector<std::vector<bool>>>>> isExist;
    std::vector<std::vector<std::vector<std::vector<std::vector<long long>>>>> tempArray;

    long long interpretsDirection(long long direction){ return (direction + 3) % 4;}
    long long directionClose(long long direction, long long constDirection){ return direction | (1 << constDirection);}
    bool Closed(long long direction, long long constDirection){ return ((direction >> constDirection) & 1) != 0;}
    bool isLineExist(long long horizontalTemp, long long verticalTemp, long long direction, long long constDirection)
    {
        if(Closed(direction, constDirection)){ return false;}
        else if(constDirection == 0){ return vertical[verticalTemp][0] == horizontalTemp;}
        else if(constDirection == 2){ return vertical[verticalTemp][vertical[verticalTemp].size() - 1] == horizontalTemp;}
        else if(constDirection == 3){ return horizontal[horizontalTemp][0] == verticalTemp;}
        else{ return horizontal[horizontalTemp][horizontal[horizontalTemp].size() - 1] == verticalTemp;}
    }
    long long greaterOrEqualNumber(const std::vector<long long>& vec, long long number)
    {
        long long i = 0;
        return std::accumulate(vec.begin(), vec.end(), long(), 
            [&i, &number](long long p, const long long temp) -> long long {
                if(temp < number){ i++;}
                return i;
            }
        );
    }
    long long greaterNumber(const std::vector<long long>& vec, long long number)
    {
        long long i = 0;
        return std::accumulate(vec.begin(), vec.end(), long(),
            [&i, &number](long long p, const long long temp) -> long long {
                if(temp <= number){ i++;}
                return i;
            }
        );
    }
};
// UP = 0
// RIGHT = 1
// DOWN = 2
// LEFT = 3

int main() 
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    long long length, numberOfContacts;
    std::cin >> length >> numberOfContacts;
    Microchip microchip(length, numberOfContacts);
    std::cin >> microchip;
    microchip.Solution();
    return 0;
}
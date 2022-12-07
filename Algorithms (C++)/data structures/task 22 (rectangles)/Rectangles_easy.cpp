#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <fstream>
#include <vector>
#include <stack>
#include <set>
#include <algorithm>

struct Rectangle
{
    Rectangle(int _color, bool _checked, int _counter) : color(_color), checked(_checked), counter(_counter) {}

    friend bool operator == (const Rectangle& temp1, const Rectangle& temp2)
    {
        return temp1.color == temp2.color;
    }
    
    friend bool operator == (const Rectangle&& temp1, Rectangle& temp2)
    {
        return (temp1.color == temp2.color || temp1.counter >= temp2.counter) ? true : false;
    }

    int color;
    int counter;
    bool checked;
};

namespace std
{
    bool operator > (const std::pair<int, int>& temp1, const int& temp2){ return temp1.first > temp2 && temp1.second > temp2;}

    std::ostream& operator << (std::ostream& out, const Rectangle& temp)
    {
        out << temp.color;// << "|" << temp.checked << "|" << temp.counter;
        return out;
    }
    
    std::ostream& operator << (std::ostream& out, const std::pair<int, int>& temp)
    {
        out << temp.first << " " << temp.second << "\n";
        return out;
    }

    std::istream& operator >> (std::istream& in, std::pair<int, int>& temp)
    {
        in >> temp.first >> temp.second;
        return in;
    }
}
    
int hight, width, size;
std::multiset<std::pair<int, int>> answer;

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen("in.txt", "r", stdin);
    // freopen("out.txt", "w", stdout);
    std::cin.tie(0);
    std::cout.tie(0);
    std::cin >> hight >> width >> size;
    std::vector<std::vector<Rectangle>> sheet(hight + 2, std::vector<Rectangle>(width + 2, Rectangle(1, false, 0)));
    for(int i = 0; i < size; i++)
    {
        int color;
        std::pair<int, int> bottom_left, upper_right;
        std::cin >> bottom_left >> upper_right >> color;
        std::for_each((sheet.begin() + hight / 2 + bottom_left.second + 1), (sheet.begin() + hight / 2 + upper_right.second + 1), 
            [&bottom_left, &upper_right, &color, &i](std::vector<Rectangle>& vec){
                std::for_each((vec.begin() + width / 2 + bottom_left.first + 1), (vec.begin() + width / 2 + upper_right.first + 1), 
                    [&color, &i](Rectangle& temp){
                        temp.color = color;
                        temp.counter = i + 1;
                    }
                );
            }
        );
    }
    int i = 1;
    std::for_each(++sheet.begin(), --sheet.end(), 
        [&i, &sheet](std::vector<Rectangle>& vec){
            int j = 1;
            std::for_each(++vec.begin(), --vec.end(), 
                [&i, &j, &sheet](Rectangle& temp){
                    if(temp.checked){ j++; return;}
                    temp.checked = true;
                    int temp_color = temp.color, square = 0;
                    std::stack<std::pair<int, int>> stack;
                    stack.emplace(i, j);
                    while(!stack.empty())
                    {
                        std::pair<int, int> x_y = std::make_pair(stack.top().first, stack.top().second); stack.pop();
                        if(x_y.first > 1 && !sheet[x_y.first - 1][x_y.second].checked && sheet[x_y.first - 1][x_y.second] == sheet[x_y.first][x_y.second])
                        { 
                            stack.emplace(x_y.first - 1, x_y.second); sheet[x_y.first - 1][x_y.second].checked = true;
                        }

                        if(x_y.second > 1 && !sheet[x_y.first][x_y.second - 1].checked && sheet[x_y.first][x_y.second - 1] == sheet[x_y.first][x_y.second])
                        {
                            stack.emplace(x_y.first, x_y.second - 1); sheet[x_y.first][x_y.second - 1].checked = true;
                        }
                        
                        if(x_y.first < hight && !sheet[x_y.first + 1][x_y.second].checked && sheet[x_y.first + 1][x_y.second] == sheet[x_y.first][x_y.second])
                        {
                            stack.emplace(x_y.first + 1, x_y.second); sheet[x_y.first + 1][x_y.second].checked = true;
                        }

                        if(x_y.second < width && !sheet[x_y.first][x_y.second + 1].checked && sheet[x_y.first][x_y.second + 1] == sheet[x_y.first][x_y.second])
                        {
                            stack.emplace(x_y.first, x_y.second + 1); sheet[x_y.first][x_y.second + 1].checked = true;
                        }

                        if(x_y.first > 1 && x_y.second > 1 && !sheet[x_y.first - 1][x_y.second - 1].checked && sheet[x_y.first - 1][x_y.second - 1].color == sheet[x_y.first][x_y.second].color && 
                        Rectangle(sheet[x_y.first - 1][x_y.second - 1].color, 0, sheet[x_y.first - 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first - 1][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second - 1].color, 0, sheet[x_y.first - 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second - 1])
                        {
                            stack.emplace(x_y.first - 1, x_y.second - 1); sheet[x_y.first - 1][x_y.second - 1].checked = true;
                        }

                        if(x_y.second < width && x_y.first > 1 && !sheet[x_y.first - 1][x_y.second + 1].checked && sheet[x_y.first - 1][x_y.second + 1].color == sheet[x_y.first][x_y.second].color && 
                        Rectangle(sheet[x_y.first - 1][x_y.second + 1].color, 0, sheet[x_y.first - 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first - 1][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second + 1].color, 0, sheet[x_y.first - 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second + 1])
                        {
                            stack.emplace(x_y.first - 1, x_y.second + 1); sheet[x_y.first - 1][x_y.second + 1].checked = true;
                        }

                        if(x_y.first < hight && x_y.second < width && !sheet[x_y.first + 1][x_y.second + 1].checked && sheet[x_y.first + 1][x_y.second + 1].color == sheet[x_y.first][x_y.second].color && 
                        Rectangle(sheet[x_y.first + 1][x_y.second + 1].color, 0, sheet[x_y.first + 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first + 1][x_y.second] && 
                        Rectangle(sheet[x_y.first + 1][x_y.second + 1].color, 0, sheet[x_y.first + 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second + 1])
                        {
                            stack.emplace(x_y.first + 1, x_y.second + 1); sheet[x_y.first + 1][x_y.second + 1].checked = true;
                        }

                        if(x_y.second > 1 && x_y.first < hight && !sheet[x_y.first + 1][x_y.second - 1].checked && sheet[x_y.first + 1][x_y.second - 1].color == sheet[x_y.first][x_y.second].color && 
                        Rectangle(sheet[x_y.first + 1][x_y.second - 1].color, 0, sheet[x_y.first + 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first + 1][x_y.second]&& 
                        Rectangle(sheet[x_y.first + 1][x_y.second - 1].color, 0, sheet[x_y.first + 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second - 1])
                        {
                            stack.emplace(x_y.first + 1, x_y.second - 1); sheet[x_y.first + 1][x_y.second - 1].checked = true;
                        }
                        ++square;
                    }
                    answer.emplace(temp_color, square); j++;
                }
            );
            i++;
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<std::pair<int, int>>(std::cout));
    // std::for_each(sheet.begin(), sheet.end(), [](const std::vector<Rectangle>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<Rectangle>(std::cout, "   ")); std::cout << "\n";});
    return 0;
}
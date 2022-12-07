#pragma comment(linker, "/STACK:10000000000")
#include <set>
#include <stack>
#include <vector>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <algorithm>

struct Node
{
    int left;
    int right;
    long long num;
    long long color;
    long long counter;
};

struct Rectangle
{
    Rectangle(int _color, bool _checked, int _counter) : color(_color), checked(_checked), counter(_counter) {}

    friend bool operator == (const Rectangle& temp1, const Rectangle& temp2) { return temp1.color == temp2.color;}
    
    friend bool operator == (const Rectangle&& temp1, Rectangle& temp2) { return (temp1.color == temp2.color || temp1.counter >= temp2.counter) ? true : false;}

    int color;
    int counter;
    bool checked;
};

namespace std
{
    std::istream& operator >> (std::istream& in, std::pair<int, int>& temp)
    {
        in >> temp.first >> temp.second;
        return in;
    }

    std::ostream& operator << (std::ostream& out, const std::pair<int, int>& temp)
    {
        out << temp.first << " " << temp.second << "\n";
        return out;
    }

    std::ostream& operator << (std::ostream& out, const Rectangle& temp)
    {
        out << temp.color;// << "|" << temp.counter;// << "|" << temp.counter;
        return out;
    }

    std::ostream& operator << (std::ostream& out, const Node& temp)
    {
        out << temp.color;
        return out;
    }
}

class Segment_tree;

int hight, width, size;
std::vector<Segment_tree> temp_sheet;
std::vector<std::vector<Rectangle>> sheet;
std::multiset<std::pair<int, int>> answer;

class Segment_tree
{
public:
    Segment_tree(int _size) : size(4 * _size) 
    { 
        vec.resize(size);
        Set(1, 1, _size);
    }

    void Push(int index)
    {
        if(vec[index].num != 0)
        {
            vec[2 * index].num += vec[index].num;
            vec[2 * index].color += vec[index].num;
            vec[2 * index + 1].num += vec[index].num;
            vec[2 * index + 1].color += vec[index].num;
            vec[index].num = 0ll;
        }
    }
    
    void Set(int index, int left, int right)
    {
        if(left > right){ return;}
        vec[index].num = 0ll;
        vec[index].color = 0ll;
        vec[index].counter = 0ll;
        vec[index].left = left;
        vec[index].right = right;
        if(left != right)
        {
            int medium = (left + right) >> 1;
            Set(index * 2, left, medium);
            Set(index * 2 + 1, medium + 1, right);
        }
    }

    void Insert(int left, int right, std::pair<int, int> data)
    {
        Insert(left, right, data, 1);
    }

    void Insert(int left, int right, std::pair<int, int> data, int index)
    {
        if(left > right){ return;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right)
        {
            vec[index].num = 0ll;
            vec[index].color = data.first;
            vec[index].counter = data.second;
            Push(index);
            return;
        }
        if(left >= vec[index].left && right <= vec[index].right && vec[index].color != 0)
        {
            Insert(vec[2 * index].left, vec[2 * index].right, std::make_pair(vec[index].color, vec[index].counter), 2 * index);
            Insert(vec[2 * index + 1].left, vec[2 * index + 1].right, std::make_pair(vec[index].color, vec[index].counter), 2 * index + 1);
            vec[index].color = 0ll;
        }
        int medium = (vec[index].right + vec[index].left) >> 1;
        if(left >= medium && right > medium && vec[index].color != 0)
        {
            Insert(vec[2 * index].left, vec[2 * index].right, std::make_pair(vec[index].color, vec[index].counter), 2 * index);
            vec[index].color = 0ll;
        }
        if(left < medium && right <= medium && vec[index].color != 0)
        {
            Insert(vec[2 * index + 1].left, vec[2 * index + 1].right, std::make_pair(vec[index].color, vec[index].counter), 2 * index + 1);
            vec[index].color = 0ll;
        }
        Insert(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), data, 2 * index);
        Insert(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), data, 2 * index + 1);
        vec[index].num = 0ll;
    }

    void TreeBuilder(int i, int k = 1)
    {
        if(k >= size){ return;}
        if(vec[k].color != 0ll){ for(int j = vec[k].left; j <= vec[k].right; j++){ sheet[i][j].color = vec[k].color; sheet[i][j].counter = vec[k].counter;} return;}
        TreeBuilder(i, 2 * k);
        TreeBuilder(i, 2 * k + 1);
    }

public:
    int size;
    std::vector<Node> vec;
};

void Builder()
{
    int i = 1;
    std::for_each(temp_sheet.begin(), temp_sheet.end(), 
        [&i](Segment_tree& vec){
            vec.TreeBuilder(i, 1); i++;
        }
    );
}

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    std::cin.tie(0);
    std::cout.tie(0);
    std::cin >> hight >> width >> size;
    temp_sheet.assign(hight, Segment_tree(width));
    sheet.assign(hight + 2, std::vector<Rectangle>(width + 2, Rectangle(1, false, 0)));
    for(int i = 0; i < size; i++)
    {
        int color;
        std::pair<int, int> bottom_left, upper_right;
        std::cin >> bottom_left >> upper_right >> color;
        std::for_each((temp_sheet.begin() + (hight >> 1) + bottom_left.second), (temp_sheet.begin() + (hight >> 1) + upper_right.second),
            [&bottom_left, &upper_right, &color, &i](Segment_tree& temp){
                // std::cout << color << " --> [" << (width >> 1) + bottom_left.first + 1 << "; " << (width >> 1) + upper_right.first << "]\n";
                temp.Insert((width >> 1) + bottom_left.first + 1, (width >> 1) + upper_right.first, std::make_pair(color, i + 1));
            }
        );
    }
    Builder();
    int i = 1;
    std::for_each(++sheet.begin(), --sheet.end(), 
        [&i](std::vector<Rectangle>& vec){
            int j = 1;
            std::for_each(++vec.begin(), --vec.end(), 
                [&i, &j](Rectangle& temp){
                    if(temp.checked){ j++; return;}
                    temp.checked = true;
                    int temp_color = temp.color, square = 0;
                    std::stack<std::pair<int, int>> stack;
                    stack.emplace(i, j);
                    while(!stack.empty())
                    {
                        std::pair<int, int> x_y = stack.top(); stack.pop();
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

                        if(x_y.first > 1 && x_y.second > 1 && !sheet[x_y.first - 1][x_y.second - 1].checked && sheet[x_y.first - 1][x_y.second - 1] == sheet[x_y.first][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second - 1].color, 0, sheet[x_y.first - 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first - 1][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second - 1].color, 0, sheet[x_y.first - 1][x_y.second - 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second - 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second - 1])
                        {
                            stack.emplace(x_y.first - 1, x_y.second - 1); sheet[x_y.first - 1][x_y.second - 1].checked = true;
                        }

                        if(x_y.second < width && x_y.first > 1 && !sheet[x_y.first - 1][x_y.second + 1].checked && sheet[x_y.first - 1][x_y.second + 1] == sheet[x_y.first][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second + 1].color, 0, sheet[x_y.first - 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first - 1][x_y.second] && 
                        Rectangle(sheet[x_y.first - 1][x_y.second + 1].color, 0, sheet[x_y.first - 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first - 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second + 1])
                        {
                            stack.emplace(x_y.first - 1, x_y.second + 1); sheet[x_y.first - 1][x_y.second + 1].checked = true;
                        }

                        if(x_y.first < hight && x_y.second < width && !sheet[x_y.first + 1][x_y.second + 1].checked && sheet[x_y.first + 1][x_y.second + 1] == sheet[x_y.first][x_y.second] && 
                        Rectangle(sheet[x_y.first + 1][x_y.second + 1].color, 0, sheet[x_y.first + 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first + 1][x_y.second] && 
                        Rectangle(sheet[x_y.first + 1][x_y.second + 1].color, 0, sheet[x_y.first + 1][x_y.second + 1].counter > sheet[x_y.first][x_y.second].counter ? sheet[x_y.first + 1][x_y.second + 1].counter : sheet[x_y.first][x_y.second].counter) == sheet[x_y.first][x_y.second + 1])
                        {
                            stack.emplace(x_y.first + 1, x_y.second + 1); sheet[x_y.first + 1][x_y.second + 1].checked = true;
                        }

                        if(x_y.second > 1 && x_y.first < hight && !sheet[x_y.first + 1][x_y.second - 1].checked && sheet[x_y.first + 1][x_y.second - 1] == sheet[x_y.first][x_y.second] && 
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
    // std::for_each(temp_sheet.begin(), temp_sheet.end(), [](const Segment_tree& vec){ std::copy(++vec.vec.begin(), vec.vec.end(), std::ostream_iterator<Node>(std::cout, " ")); std::cout << "\n";});
    return 0;
}
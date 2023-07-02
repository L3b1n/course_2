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
    long long sum;
    long long min;
    long long max;
};

namespace std
{
    std::ostream& operator << (std::ostream& out, const Node& temp)
    {
        out << temp.sum;
        return out;
    }
}

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
            vec[2 * index].sum += vec[index].num * (vec[2 * index].right - vec[2 * index].left + 1);
            vec[2 * index].min += vec[index].num;
            vec[2 * index].max += vec[index].num;
            vec[2 * index + 1].num += vec[index].num;
            vec[2 * index + 1].sum += vec[index].num * (vec[2 * index + 1].right - vec[2 * index + 1].left + 1);
            vec[2 * index + 1].min += vec[index].num;
            vec[2 * index + 1].max += vec[index].num;
            vec[index].num = 0ll;
        }
    }
    
    void Set(int index, int left, int right)
    {
        if(left > right){ return;}
        vec[index].num = 0ll;
        vec[index].sum = 0ll;
        vec[index].min = 0ll;
        vec[index].max = 0ll;
        vec[index].left = left;
        vec[index].right = right;
        if(left != right)
        {
            int medium = (left + right) >> 1;
            Set(index * 2, left, medium);
            Set(index * 2 + 1, medium + 1, right);
        }
    }

    void Add(int left, int right, int data)
    {
        Add(left + 1, right + 1, data, 1);
    }

    void Add(int left, int right, int data, int index)
    {
        if(left > right){ return;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right)
        { 
            vec[index].num = data;
            vec[index].sum += (vec[index].right - vec[index].left + 1) * data;
            vec[index].max += data;
            vec[index].min += data;
            return;
        }
        Add(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), data, index * 2);
        Add(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), data, index * 2 + 1);
        vec[index].sum = vec[index * 2].sum + vec[index * 2 + 1].sum;
        vec[index].max = std::max(vec[2 * index].max, vec[2 * index + 1].max);
        vec[index].min = std::min(vec[2 * index].min, vec[2 * index + 1].min);
    }

    void Insert(int pos, int data)
    {
        Insert(pos + 1, pos + 1, data, 1);
    }

    void Insert(int left, int right, int data, int index)
    {
        if(left > right){ return;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right)
        {
            vec[index].num = 0ll;
            vec[index].sum = (vec[index].right - vec[index].left + 1) * data;
            vec[index].min = data;
            vec[index].max = data;
            return;
        }
        Insert(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), data, 2 * index);
        Insert(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), data, 2 * index + 1);
        vec[index].max = std::max(vec[2 * index].max, vec[2 * index + 1].max);
        vec[index].min = std::min(vec[2 * index].min, vec[2 * index + 1].min);
        vec[index].sum = vec[index * 2].sum + vec[index * 2 + 1].sum;
        vec[index].num = 0ll;
    }

    long long FindSum(int left, int right)
    {
        return FindSum(left + 1, right + 1, 1);
    }

    long long FindSum(int left, int right, int index)
    {
        if(left > right){ return 0ll;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right){ return vec[index].sum;}
        return FindSum(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), index * 2) + 
               FindSum(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), index * 2 + 1);
    }

    long long FindMax(int left, int right)
    {
        return FindMax(left + 1, right + 1, 1);
    }

    long long FindMax(int left, int right, int index)
    {
        if(left > right){ return LLONG_MIN;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right){ return vec[index].max;}
        return std::max(FindMax(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), index * 2), 
                        FindMax(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), index * 2 + 1));
    }

    long long FindMin(int left, int right)
    {
        return FindMin(left + 1, right + 1, 1);
    }

    long long FindMin(int left, int right, int index)
    {
        if(left > right){ return LLONG_MAX;}
        Push(index);
        if(left == vec[index].left && right == vec[index].right){ return vec[index].min;}
        return std::min(FindMin(std::max(left, vec[2 * index].left), std::min(right, vec[2 * index].right), index * 2), 
                        FindMin(std::max(left, vec[2 * index + 1].left), std::min(right, vec[2 * index + 1].right), index * 2 + 1));
    }

public:
    int size;
    std::vector<Node> vec;
};

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    std::cin >> n;
    Segment_tree temp(n);
    int request;
    while(std::cin >> request)
    {
        if(request == 0){ break;}
        if(request == 1)
        {
            int index, data;
            std::cin >> index >> data;
            temp.Insert(index, data);
        }
        else if(request == 2)
        {
            int left, right, data;
            std::cin >> left >> right >> data;
            temp.Add(left, right, data);
        }
        else if(request == 3)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << temp.FindSum(left, right) << "\n";
        }
        else if(request == 4)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << temp.FindMin(left, right) << "\n";
        }
        else if(request == 5)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << temp.FindMax(left, right) << "\n";
        }
    }
    int i = 0;
    for(auto x : temp.vec){ std::cout << std::setw(3) << i++;} std::cout << "\n";
    for(auto x : temp.vec){ std::cout << std::setw(3) << x.sum;} std::cout << "\n";
    for(auto x : temp.vec){ std::cout << std::setw(3) << x.max;} std::cout << "\n";
    for(auto x : temp.vec){ std::cout << std::setw(3) << x.min;} std::cout << "\n";
    return 0;
}
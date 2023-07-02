#include <vector>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <algorithm>

struct Node
{
    long long num = 0ll;
    long long sum = 0ll;
    long long min = 0ll;
    long long max = 0ll;
};

class Segment_tree
{
public:
    Segment_tree(int _size) : size(4 * _size - 5), n(_size) { tree.resize(size);} 

    void Insert(int index, long long data) { Insert(index, index, data, 0, 0, n - 1);}

    void Add(int left, int right, long long data) { Add(left, right, data, 0, 0, n - 1);}

    long long FindSum(int left, int right) { return FindSum(left, right, 0, 0, n - 1, 0);}
    
    long long FindMax(int left, int right) { return FindMax(left, right, 0, 0, n - 1);}
    
    long long FindMin(int left, int right) { return FindMin(left, right, 0, 0, n - 1);}

    void Print() { Print_const();}
private:
    int n;
    int size;
    std::vector<Node> tree;

    void Print_const()
    {
        for(auto x : tree){ std::cout << x.min << " ";} std::cout << "\n";
    }

    long long Get(int index, int v, int left, int right, long long temp)
    {
        if(left == index && index == right){ return temp + tree[v].num + tree[v].sum;}
        int medium = (left + right) >> 1;
        if(medium >= index){ return Get(index, 2 * v + 1, left, medium, temp + tree[v].num);}
        return Get(index, 2 * (v + 1), medium + 1, right, temp + tree[v].num);
    }

    void Insert(int left, int right, long long data, int v, int temp_left, int temp_right)
    {
        long long temp = -1 * Get(left, 0, temp_left, temp_right, 0);
        Add(left, right, temp, 0, temp_left, temp_right);
        Add(left, right, data, 0, temp_left, temp_right);
    }

    void Add(int left, int right, long long data, int v, int temp_left, int temp_right)
    {
        if(left > right){ return;}
        if(left == temp_left && temp_right == right){ tree[v].num += data; tree[v].min += data; tree[v].max += data; return;}
        int medium = (temp_left + temp_right) >> 1;
        Add(left, std::min(right, medium), data, 2 * v + 1, temp_left, medium);
        Add(std::max(medium + 1, left), right, data, 2 * (v + 1), medium + 1, temp_right);
        tree[v].max = tree[v].num + std::max(tree[2 * v + 1].max, tree[2 * (v + 1)].max);
        tree[v].min = tree[v].num + std::min(tree[2 * v + 1].min, tree[2 * (v + 1)].min);
        tree[v].sum += (right - left + 1) * data;
    }

    long long FindSum(int left, int right, int v, int temp_left, int temp_right, long long temp)
    {
        if(left > right){ return 0ll;}
        if(left == temp_left && temp_right == right){ return (tree[v].num + temp) * (right - left + 1) + tree[v].sum;}
        int medium = (temp_left + temp_right) >> 1;
        return FindSum(left, std::min(medium, right), 2 * v + 1, temp_left, medium, tree[v].num + temp) + 
               FindSum(std::max(medium + 1, left), right, 2 * (v + 1), medium + 1, temp_right, tree[v].num + temp);
    }
    
    long long FindMax(int left, int right, int v, int temp_left, int temp_right)
    {
        if(left > right){ return LLONG_MIN;}
        if(left == temp_left && temp_right == right){ return tree[v].max;}
        int medium = (temp_left + temp_right) >> 1;
        return tree[v].num + 
               std::max(FindMax(left, std::min(medium, right), 2 * v + 1, temp_left, medium),
                        FindMax(std::max(medium + 1, left), right, 2 * (v + 1), medium + 1, temp_right));
    }
    
    long long FindMin(int left, int right, int v, int temp_left, int temp_right)
    {
        if(left > right){ return LLONG_MAX;}
        if(left == temp_left && temp_right == right){ return tree[v].min;}
        int medium = (temp_left + temp_right) >> 1;
        return tree[v].num + 
               std::min(FindMin(left, std::min(medium, right), 2 * v + 1, temp_left, medium),
                        FindMin(std::max(medium + 1, left), right, 2 * (v + 1), medium + 1, temp_right));
    }
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
            long long index, data;
            std::cin >> index >> data;
            temp.Insert(index, data);
        }
        else if(request == 2)
        {
            long long left, right, data;
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
    // temp.Print();
    return 0;
}
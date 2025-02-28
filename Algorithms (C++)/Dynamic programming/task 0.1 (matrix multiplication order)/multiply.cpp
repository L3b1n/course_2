#include <iostream>
#include <fstream>
#include <algorithm>
#include <iterator>
#include <vector>

struct Matr
{
    Matr(int _size = 0) : size(_size) { num.resize(size);}

    void Resize()
    { 
        int i = 0;
        arr.resize(size); 
        for(int i = 0; i < size; i++)
        {
            arr[i].resize(size - i);
            arr[i][1] = num[i].first * num[i].second * num[i + 1].second;
        }
    }

    friend std::istream& operator >> (std::istream& fi, Matr& a)
    {
        for(int i = 0; i < a.size; i++){ fi >> a.num[i].first >> a.num[i].second;}
        return fi;
    }

    int Solution()
    {
        for(int i = 2; i < size; i++)
        {
            int j = i;
            for(int h = i - 2; h >= 0; h--)
            {
                int x = j - h;
                arr[h][x] = INT_MAX;
                for(int k = 0; k < j - h; k++)
                {
                    int temp = arr[h][k] + arr[h + k + 1][--x] + num[h].first * num[h + k].second * num[j].second;
                    if(temp < arr[h][j - h]){ arr[h][j - h] = temp;} 
                }
            }
        }
        return arr[0][size - 1];
    }

private:
    int size;
    std::vector<std::vector<int>> arr;
    std::vector<std::pair<int, int>> num;
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
    Matr a(n);
    std::cin >> a; 
    a.Resize();
    std::cout << a.Solution();
    return 0;
}
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    std::cin >> n;
    std::vector<int> arr1;
    for(int i = 0, x; i < n; i++){ std::cin >> x; arr1.push_back(x);}
    std::vector<int> arr2 = { std::istream_iterator<int>(std::cin), std::istream_iterator<int>()};
    // for(int i = 0, x; i < n; i++){ std::cin >> x; arr2.push_back(x);}
    std::vector<std::vector<int>> arr(n + 1, std::vector<int>(n + 1)); 
    for(int j = 1; j < n + 1; j++)
    {
        for(int i = 1; i < n + 1; i++)
        { 
            arr[i][j] = arr1[i - 1] == arr2[j - 1] ? arr[i - 1][j - 1] + 1 : std::max(arr[i - 1][j], arr[i][j - 1]);
        }
    }
    std::vector<int> result1;
    std::vector<int> result2;
    for(int i = n, j = n; result1.size() != arr[n][n]; i--, j--)
    {
        int temp = arr[i][j];
        for(; i > 0 && arr[i - 1][j] == temp; i--){}
        for(; j > 0 && arr[i][j - 1] == temp; j--){}
        result1.push_back(i - 1);
        result2.push_back(j - 1);
    }
    std::cout << arr[n][n] << "\n";
    if(result1.size() != 0){ std::copy(result1.rbegin(), --result1.rend(), std::ostream_iterator<int>(std::cout, " ")); std::cout << result1[0] << "\n";}
    if(result1.size() != 0){ std::copy(result2.rbegin(), --result2.rend(), std::ostream_iterator<int>(std::cout, " ")); std::cout << result2[0];}
    return 0;
}
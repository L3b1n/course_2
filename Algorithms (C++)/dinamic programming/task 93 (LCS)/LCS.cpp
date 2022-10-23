#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> arr1;
    for(int i = 0, x; i < n; i++){ cin >> x; arr1.push_back(x);}
    vector<int> arr2 = { std::istream_iterator<int>(cin), std::istream_iterator<int>()};
    vector<vector<int>> arr(n + 1, vector<int>(n + 1)); 
    for(int j = 1; j < n + 1; j++)
    {
        for(int i = 1; i < n + 1; i++)
        { 
            arr[i][j] = arr1[i - 1] == arr2[j - 1] ? arr[i - 1][j - 1] + 1 : max(arr[i - 1][j], arr[i][j - 1]);
        }
    }
    vector<int> result1;
    vector<int> result2;
    for(int i = n, j = n; result1.size() != arr[n][n]; i--, j--)
    {
        int temp = arr[i][j];
        for(; i > 0 && arr[i - 1][j] == temp; i--){}
        for(; j > 0 && arr[i][j - 1] == temp; j--){}
        result1.push_back(i - 1);
        result2.push_back(j - 1);
    }
    cout << arr[n][n] << endl;
    if(result1[0] != -1){ std::copy(result1.rbegin(), --result1.rend(), std::ostream_iterator<int>(cout, " ")); cout << result1[0] << endl;}
    if(result2[0] != -1){ std::copy(result2.rbegin(), --result2.rend(), std::ostream_iterator<int>(cout, " ")); cout << result2[0];}
    return 0;
}
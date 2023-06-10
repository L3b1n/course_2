#include <vector>
#include <numeric>
#include <iostream>
#include <algorithm>

struct Stage
{
    int machine;
    int duration;
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
    std::vector<std::vector<Stage>> jobs(2);
    for(int i = 0; i < 2; i++)
    {
        int s;
        std::cin >> s;
        jobs[i].resize(s);
        for(int j = 0; j < s; j++){ std::cin >> jobs[i][j].machine >> jobs[i][j].duration;}
    }
    std::vector<std::vector<int>> matrix(2, std::vector<int>(n, 0));
    matrix[0][0] = jobs[0][0].duration;
    matrix[1][0] = matrix[0][0] + jobs[1][0].duration;
    for(int i = 1; i < n; i++)
    {
        matrix[0][i] = matrix[0][i - 1] + jobs[0][i].duration;
        matrix[1][i] = std::max(matrix[1][i - 1], matrix[0][i]) + jobs[1][i].duration;
    }
    std::cout << matrix[1][n - 1] << "\n";
    return 0;
}
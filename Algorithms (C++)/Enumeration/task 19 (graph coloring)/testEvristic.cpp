#include <set>
#include <vector>
#include <iostream>
#include <algorithm>

int main() {
    freopen("colgraph.in", "r", stdin);
    // freopen("colgraph.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    std::cin >> n;
    int begin, end;
    std::vector<std::set<int>> graph(n);
    while(std::cin >> begin >> end) 
    {
        graph[begin - 1].insert(end - 1);
        graph[end - 1].insert(begin - 1);
    }
    int numColors = 0;
    std::vector<int> colors(n, 0);
    for(int i = 0; i < n; i++) 
    {
        int j = 1;
        std::set<int> usedColors;
        for(int adj : graph[i]){ if(colors[adj] != 0){ usedColors.insert(colors[adj]);}}
        while(usedColors.count(j) > 0){ j++;} colors[i] = j;
        numColors = std::max(numColors, j);
    }
    std::cout << numColors << "\n";
    std::copy(colors.begin(), colors.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
    return 0;
}

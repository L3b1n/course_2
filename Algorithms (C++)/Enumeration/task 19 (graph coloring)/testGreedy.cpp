#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

vector<vector<int>> graph;
vector<int> colors;

int getMinUnusedColor(const set<int>& usedColors) {
    int color = 1;
    while (usedColors.count(color) > 0) {
        color++;
    }
    return color;
}

void greedyColoring(int n) {
    set<int> usedColors;
    for (int vertex = 0; vertex < n; vertex++) {
        set<int> neighborColors;
        for (int neighbor : graph[vertex]) {
            if (colors[neighbor] != 0) {
                neighborColors.insert(colors[neighbor]);
            }
        }
        int color = getMinUnusedColor(neighborColors);
        colors[vertex] = color;
        usedColors.insert(color);
    }
}

int main() {
    freopen("colgraph.in", "r", stdin);
    // freopen("colgraph.out", "w", stdout);

    int n;
    cin >> n;

    graph.resize(n);
    colors.resize(n);

    int u, v;
    while (cin >> u >> v) {
        graph[u - 1].push_back(v - 1);
        graph[v - 1].push_back(u - 1);
    }

    greedyColoring(n);

    int numColors = *max_element(colors.begin(), colors.end());

    cout << numColors << endl;
    for (int i = 0; i < n; i++) {
        cout << colors[i] << " ";
    }
    cout << endl;

    return 0;
}

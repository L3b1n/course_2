// #include <iostream>
// #include <vector>
// #include <set>
// #include <unordered_set>

// using namespace std;

// void findMaxIndependentSets(const vector<set<int>>& graph, int k, set<int>& currentSet,
//                             set<int>& usedVertices, set<int>& remainingVertices,
//                             vector<set<int>>& resultSets) {
//     // Шаг вперед (расширение текущего множества)
//     for (int v : remainingVertices) {
//         currentSet.insert(v); // Добавляем вершину v в текущее множество
//         usedVertices.insert(v); // Помечаем вершину v как использованную

//         // Обновляем множество оставшихся вершин, исключая соседей вершины v
//         for (int u : graph[v]) {
//             std::cout << u << " - ";
//             remainingVertices.erase(u);
//         }
//         std::cout << "\n";

//         k++; // Увеличиваем количество элементов в текущем независимом множестве

//         // Проверка
//         for(auto x : usedVertices){ std::cout << x << " | ";} std::cout << "\n";
//         bool canProceed = true;
//         for (int u : usedVertices) {
//             bool hasNeighborInRemaining = false;
//             for (int neighbor : graph[u]) {
//                 std::cout << neighbor << "++\n";
//                 if (remainingVertices.count(neighbor) > 0) {
//                     hasNeighborInRemaining = true;
//                     break;
//                 }
//             }
//             std::cout << hasNeighborInRemaining << "--\n";
//             if (!hasNeighborInRemaining) {
//                 canProceed = false;
//                 break;
//             }
//         }

//         std::cout << canProceed << "\n";
//         if (canProceed) {
//             if (remainingVertices.empty()) {
//                 if (usedVertices.empty()) {
//                     resultSets.push_back(currentSet); // Добавляем текущее множество в результат
//                 }
//             } else {
//                 findMaxIndependentSets(graph, k, currentSet, usedVertices, remainingVertices, resultSets);
//             }
//         }

//         // Шаг назад
//         currentSet.erase(v); // Удаляем последний добавленный элемент из текущего множества
//         k--; // Уменьшаем количество элементов в текущем независимом множестве
//         usedVertices.insert(v); // Помечаем вершину v как уже добавленную
//         remainingVertices.insert(v); // Восстанавливаем вершину v в множество оставшихся вершин
//     }
// }

// set<int> maximumIndependentSet(const vector<vector<int>>& graph) {
//     set<int> independentSet;
//     vector<bool> visited(graph.size(), false);

//     for (int vertex = 0; vertex < graph.size(); ++vertex) {
//         if (!visited[vertex]) {
//             independentSet.insert(vertex);
//             visited[vertex] = true;

//             for (int neighbor : graph[vertex]) {
//                 visited[neighbor] = true;
//             }
//         }
//     }

//     return independentSet;
// }

// void Coloring(const vector<vector<int>>& G, int i, vector<int>& C) {
//     if (G.empty()) {
//         // Раскраска закончена
//         return;
//     }

//     set<int> S = maximumIndependentSet(G);
//     std::cout << "1\n";
//     for (int v : S) {
//         C[v] = i;
//     }

//     Coloring(G, i + 1, C);
// }

// int main() {
//     // Пример использования
//     // int num_vertices = 6;
//     freopen("colgraph.in", "r", stdin);
//     // freopen("colgraph.out", "w", stdout);
//     std::ios_base::sync_with_stdio(0);
//     std::cin.tie(0);
//     std::cout.tie(0);
//     int n;
//     std::cin >> n;
//     vector<vector<int>> graph(n + 1);
//     int u, v;
//     while(std::cin >> u >> v)
//     {
//         graph[u].push_back(v);
//         graph[v].push_back(u);
//     }
//     // graph[0] = {1, 2};
//     // graph[1] = {0, 2, 3, 4};
//     // graph[2] = {0, 1, 4};
//     // graph[3] = {1, 4, 5};
//     // graph[4] = {1, 2, 3};
//     // graph[5] = {3};
    
//     // int k = 0; // Количество элементов в текущем независимом множестве
//     // set<int> currentSet; // Текущее независимое множество
//     // set<int> usedVertices; // Множество вершин, уже использованных для расширения текущего множества
//     // set<int> remainingVertices; // Множество вершин, которые можно использовать для расширения текущего множества

//     // // Инициализация множеств вершин
//     // for (int i = 1; i < n + 1; i++) {
//     //     remainingVertices.insert(i);
//     // }

//     // vector<set<int>> test;
//     // findMaxIndependentSets(graph, k, currentSet, usedVertices, remainingVertices, test);
//     // std::cout << "size: " << test.size() << "\n";
 
//     // for(auto vec : test)
//     // {
//     //     std::cout << "{ ";
//     //     for(auto x : vec)
//     //     {
//     //         std::cout << x << " ";
//     //     }
//     //     std::cout << "}\n";
//     // }

//     vector<int> colors(n + 1, -1);
//     Coloring(graph, 1, colors);

//     cout << "Coloring: ";
//     for (int color : colors) {
//         cout << color << " ";
//     }
//     cout << endl;

//     return 0;
// }
#include <vector>
#include <numeric>
#include <iostream>
#include <algorithm>
#include <unordered_set>

std::unordered_set<int> SelectMaxIndependentSet(const std::vector<std::unordered_set<int>>& graph) 
{
    std::unordered_set<int> independentSet;
    for(int v = 1; v < graph.size(); v++)
    {
        bool isIndependent = true;
        for(int u : independentSet){ if(graph[v].count(u) > 0 || graph[u].count(v) > 0){ isIndependent = false; break;}}
        if(isIndependent){ independentSet.insert(v);}
    }
    return independentSet;
}

std::vector<std::unordered_set<int>> subtractGraph(const std::vector<std::unordered_set<int>>& graph, const std::unordered_set<int>& Set)
{
    std::vector<std::unordered_set<int>> subtractedGraph(graph.size());
    for(int v = 0; v < graph.size(); v++){
        if(Set.count(v) == 0){
            for(int u : graph[v]){ if(Set.count(u) == 0){ subtractedGraph[v].insert(u);}}
        }
    }
    return subtractedGraph;
}

void colorGraph(std::vector<std::unordered_set<int>>& graph, std::vector<int>& color, const std::unordered_set<int>& tempSet, int i)
{
    if(!std::any_of(graph.begin(), graph.end(), [](const std::unordered_set<int>& vec){ return vec.size();})){ return;}
    std::unordered_set<int> S = SelectMaxIndependentSet(graph);
    for(auto x : tempSet){ S.erase(x);}
    // for(auto x : S){ std::cout << x << " ";} std::cout << "\n";
    for(int v : S){ color[v] = i;}
    std::vector<std::unordered_set<int>> tempGraph = subtractGraph(graph, S);
    colorGraph(tempGraph, color, S, i + 1);
}

int main() 
{
    freopen("colgraph.in", "r", stdin);
    // freopen("colgraph.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int n;
    int u, v;
    std::cin >> n;
    std::vector<std::unordered_set<int>> graph(n + 1);
    while(std::cin >> u >> v)
    {
        graph[u].emplace(v);
        graph[v].emplace(u);
    }
    // for(auto vec : G.adjacencyList)
    // {
    //     for(auto x : vec){ std::cout << x << " ";}
    //     std::cout << "\n";
    // }
    // std::cout << "\n";

    // Создаем массив для хранения раскраски вершин
    std::unordered_set<int> temp;
    std::vector<int> color(graph.size(), -1);

    // Запускаем раскраску с цветом 0
    bool condition = true;
    colorGraph(graph, color, temp, 1);
    int maxColor = *std::max_element(color.begin(), color.end());
    for(auto& x : color){ if(x == -1){ x = condition ? ++maxColor : maxColor; condition = condition ? false : true;}}
    std::cout << maxColor << "\n";
    std::copy(++color.begin(), color.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
    // std::unordered_set<int> S = SelectMaxIndependentSet(G);
    // for(auto x : S)
    // {
    //     std::cout << x << " ";
    // }
    // std::cout << "\n";
    // Graph GMinusS = subtractGraph(G, S);
    // for(auto vec : GMinusS.adjacencyList)
    // {
    //     for(auto x : vec){ std::cout << x << " ";}
    //     std::cout << "\n";
    // }
    // S = SelectMaxIndependentSet(GMinusS);
    // for(auto x : S)
    // {
    //     std::cout << x << " ";
    // }
    // std::cout << "\n";
    // GMinusS = subtractGraph(G, S);
    // for(auto vec : GMinusS.adjacencyList)
    // {
    //     for(auto x : vec){ std::cout << x << " ";}
    //     std::cout << "\n";
    // }

    // Выводим раскраску
    std::cout << "Раскраска:" << std::endl;
    for (int v = 1; v < color.size(); ++v) {
        std::cout << "Вершина " << v << ": Цвет " << color[v] << std::endl;
    }

    return 0;
}

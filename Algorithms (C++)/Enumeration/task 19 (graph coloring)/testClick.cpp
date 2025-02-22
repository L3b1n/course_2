#include <vector>
#include <utility>
#include <numeric>
#include <iostream>
#include <algorithm>
#include <unordered_set>

class Colgraph
{
public:
	Colgraph() : countSummit(0) {}
 
	friend std::istream& operator >> (std::istream& in, Colgraph& a)
    {
        int i = 1;
        int begin, end;
        in >> a.countSummit;
        a.colors.resize(a.countSummit + 1, 0);
        a.tempGraph.resize(a.countSummit + 1);
        a.graph.resize(a.countSummit + 1, std::vector<int>(a.countSummit + 1, 0));
        while(in >> begin >> end)
        {
            a.graph[begin][end] = 1;
            a.graph[end][begin] = 1;
            a.tempGraph[begin].push_back(end);
            a.tempGraph[end].push_back(begin);
        }
        return in;
    }

	void Solution()
	{
        std::vector<int> clique;
        std::vector<int> candidates;
        std::vector<int> excluded;
        for(int i = 1; i <= countSummit; i++) {
            candidates.push_back(i);
        }
        bronKerbosch(clique, candidates, excluded);
        int numColors = maxClique.size();
        while(!graphColoring(0, numColors)){ numColors++;}
        std::cout << numColors << "\n";
        std::copy(++colors.begin(), colors.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
        // std::copy(maxClique.begin(), maxClique.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
        // colorGraph();
        // std::cout << *std::max_element(colors.begin(), colors.end()) << "\n";
        // std::copy(++colors.begin(), colors.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
	}

private:
	int countSummit;
    std::vector<int> colors;
    std::vector<int> maxClique;
    std::vector<std::vector<int>> graph;
    std::vector<std::vector<int>> tempGraph;

    inline bool isSafe(const int& summit, const int& color)
    {
        return !std::any_of(tempGraph[summit].begin(), tempGraph[summit].end(), [&](const int& temp){ return colors[temp] == color;});
    }

    bool graphColoring(const int& summit, const int& numColors)
    {
        if(summit == graph.size()){ return true;}
        for(int color = 1; color <= numColors; color++)
        {
            if(isSafe(summit, color))
            {
                colors[summit] = color;
                if(graphColoring(summit + 1, numColors)){ return true;}
                colors[summit] = 0;
            }
        }
        return false;
    }

    void bronKerbosch(std::vector<int>& clique, std::vector<int>& candidates, std::vector<int>& excluded) 
    {
        if(candidates.empty() && excluded.empty()){ if(clique.size() > maxClique.size()){ maxClique = clique;} return;}
        std::vector<int> candidatesCopy = candidates;
        for(auto summit : candidatesCopy)
        {
            std::vector<int> newExcluded;
            std::vector<int> newCandidates;
            std::vector<int> newClique = clique; newClique.push_back(summit);
            for(auto tempSummit : excluded){ if(summit != tempSummit && graph[summit][tempSummit] == 1){ newExcluded.push_back(tempSummit);}}
            for(auto tempSummit : candidates){ if(summit != tempSummit && graph[summit][tempSummit] == 1){ newCandidates.push_back(tempSummit);}}
            bronKerbosch(newClique, newCandidates, newExcluded);
            candidates.erase(find(candidates.begin(), candidates.end(), summit));
            excluded.push_back(summit);
        }
    }
};

int main()
{
    freopen("colgraph.in", "r", stdin);
    // freopen("colgraph.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Colgraph colgraph;
    std::cin >> colgraph;
    colgraph.Solution(); 
    return 0;
}

// #include <iostream>
// #include <vector>
// #include <set>

// using namespace std;

// // Функция для поиска максимальной клики
// void bronKerbosch(vector<vector<int>>& graph, set<int>& clique, set<int>& candidates, set<int>& excluded) {
//     if (candidates.empty() && excluded.empty()) {
//         // Вывод найденной клики
//         for (int vertex : clique) {
//             cout << vertex << " ";
//         }
//         cout << endl;
//         return;
//     }

//     // Выбор произвольной вершины из множества кандидатов и исключенных вершин
//     int pivot = *candidates.begin();

//     // Перебор вершин из множества кандидатов
//     for (int vertex : candidates) {
//         // Проверка, является ли вершина смежной с выбранным pivot
//         if (graph[vertex][pivot]) {
//             continue; // Пропуск несмежных вершин
//         }

//         clique.insert(vertex); // Добавление вершины в клику

//         // Создание новых множеств кандидатов и исключенных вершин
//         set<int> new_candidates, new_excluded;
//         for (int neighbor : graph[vertex]) {
//             if (candidates.count(neighbor)) {
//                 new_candidates.insert(neighbor);
//             }
//             if (excluded.count(neighbor)) {
//                 new_excluded.insert(neighbor);
//             }
//         }

//         // Рекурсивный вызов алгоритма для новых множеств
//         bronKerbosch(graph, clique, new_candidates, new_excluded);

//         // Удаление вершины из клики
//         clique.erase(vertex);

//         // Перемещение вершины из кандидатов в исключенные вершины
//         candidates.erase(vertex);
//         excluded.insert(vertex);
//     }
// }

// int main() {
//     // Пример графа, заданного списком смежности
//     vector<vector<int>> graph = {
//         {1, 2, 3}, // Вершина 0 смежна с 1, 2, 3
//         {0, 2},    // Вершина 1 смежна с 0, 2
//         {0, 1, 3}, // Вершина 2 смежна с 0, 1, 3
//         {0, 2}     // Вершина 3 смежна с 0, 2
//     };

//     set<int> clique; // Множество вершин в текущей клике
//     set<int> candidates; // Множество вершин-кандидатов
//     set<int> excluded; // Множество исключенных вершин

//     for (int i = 0; i < graph.size(); ++i) {
//         candidates.insert(i); // Изначально все вершины являются кандидатами
//     }

//     bronKerbosch(graph, clique, candidates, excluded);

//     return 0;
// }

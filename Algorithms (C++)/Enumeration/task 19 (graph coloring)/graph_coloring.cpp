#include <limits>
#include <vector>
#include <utility>
#include <numeric>
#include <iostream>
#include <algorithm>

class Colgraph
{
public:
	Colgraph() : countSummit(0) {}

	friend std::istream& operator >> (std::istream& in, Colgraph& a)
    {
        in >> a.countSummit;
        a.colors.resize(++a.countSummit, 0); a.graph.resize(a.countSummit);
        while(!in.eof())
        {
            int begin, end;
            in >> begin >> end;
            a.graph[begin].push_back(end);
            a.graph[end].push_back(begin);
        }
        return in;
    }

	void Solution()
	{
        int numColors = 1;
        while(!graphColoring(0, numColors)){ numColors++;}
        std::cout << numColors << "\n";
        std::copy(++colors.begin(), colors.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
	}

private:
	int countSummit;
    std::vector<int> colors;
    std::vector<std::vector<int>> graph;

    inline bool isSafe(const int& summit, const int& color)
    {
        return !std::any_of(graph[summit].begin(), graph[summit].end(), [&](const int& temp){ return colors[temp] == color;});
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
};

int main()
{
    freopen("colgraph.in", "r", stdin);
    freopen("colgraph.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Colgraph colgraph;
    std::cin >> colgraph;
    colgraph.Solution();
    return 0;
}
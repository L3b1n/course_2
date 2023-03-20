#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

int n, k;
std::vector<int> mt;
std::vector<bool> used;
std::vector<std::vector<int>> g;
 
bool kuhn(int v) 
{
	if(used[v]){ return false;}
	used[v] = true;
	for(size_t i = 0; i < g[v].size(); ++i) 
	{
		int to = g[v][i];
		if(mt[to] == -1 || kuhn(mt[to])){ mt[to] = v; return true;}
	}
	return false;
}
 
int main() 
{
	freopen("kuhn.in", "r", stdin);
    freopen("kuhn.out", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
	int x, y;
	std::cin >> y >> x >> n >> k;
	g.resize(n);
	for(int i = 0; i < x; i++)
	{
		int begin, end;
		std::cin >> begin >> end;
		g[begin - 1].push_back(end - 1);
	}
	mt.resize(k, -1);
	for(int v = 0; v < n; ++v) 
	{
		used.assign(n, false);
		kuhn(v);
	}
	std::cout << std::accumulate(mt.begin(), mt.end(), int(), [](int counter, int temp){ if(temp != -1){ counter++;} return counter;}) << "\n";
	return 0;
}
#include <cmath>
#include <deque>
#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>

bool answer = false;

class Graph 
{
public:
    Graph(int n) : numOfVertices(n), numOfEdges(0) { matrix.resize(n, std::vector<int>(n, 0));}

    bool processible(Graph& a, Graph& b)
    {
        if(a.numOfVertices != b.numOfVertices){ return false;}
        if(a.numOfEdges != b.numOfEdges){ return false;}
        std::vector<int> aDeg = a.getDegrees();
        std::vector<int> bDeg = b.getDegrees();
        for(int i = 0; i < a.numOfVertices; i++){ if(aDeg[i] != bDeg[i]){ return false;}}
        return true;
    }

    void add(const int& a, const int& b)
    {
        matrix[a][b] = 1;
        matrix[b][a] = 1;
        numOfEdges++;
    }

    std::vector<int> getDegrees() 
    {
        std::vector<int> vector;
        for(int i = 0; i < numOfVertices; i++){ vector.push_back(getDegree(i));}
        std::make_heap(vector.begin(), vector.end());
        std::sort_heap(vector.begin(), vector.end());
        return vector;
    }

    int getDegree(const int& v)
    {
        int counter = 0;
        for(int i = 0; i < numOfVertices; i++){ if(matrix[v][i] != 0){ counter++;}}
        return counter;
    }

public:
    int numOfEdges;
    int numOfVertices;
    std::vector<std::vector<int>> matrix;
};

bool isMatch(Graph& a, Graph& b, std::vector<int>& hm, int k, int v)
{
    if(!(a.getDegree(k) == b.getDegree(v))){ return false;}
    for(int i = 0; i < k; i++){ if(a.matrix[k][i] != b.matrix[v][hm[i]]){ return false;}}
    return true;
}

void isIs(Graph& a, Graph& b, std::vector<int>& hm, int k) 
{
    if(k == a.numOfVertices){ answer = true;}
    if(answer){ return;}
    for(int i = 0; i < b.numOfVertices; i++)
    {
        if(std::find(hm.begin(), hm.end(), i) != hm.end()){ continue;}
        else 
        {
            if(isMatch(a, b, hm, k, i)) 
            {
                hm[k] = i;
                isIs(a, b, hm, k + 1);
                if(answer){ return;}
                else{ hm[k] = -1;}
            }
        }
    }
}

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    Graph g1(0), g2(0);
    int n1, n2, m1, m2;
    std::cin >> n1 >> m1;
    g1 = Graph(n1);
    for(int i = 0; i < m1; i++) 
    {
        int a, b;
        std::cin >> a >> b; g1.add(a, b);
    }
    std::cin >> n2 >> m2;
    g2 = Graph(n2);
    for(int i = 0; i < m2; i++)
    {
        int a, b;
        std::cin >> a >> b; g2.add(a, b);
    }
    if(!g1.processible(g1, g2)){ std::cout << "NO\n";}
    else 
    {
        std::vector<int> hm(g1.numOfVertices, -1);
        isIs(g1, g2, hm, 0);
        if(answer) 
        {
            std::cout << "YES\n";
            for(int i = 0; i < g1.numOfVertices; i++){ std::cout << hm[i] << " ";}
            std::cout << "\n";
        }else{ std::cout << "NO\n";}
    }
    return 0;
}
#include <iostream>
#include <cmath>
#include <vector>
#include <chrono>
#include <random>
#include <fstream>
#include <algorithm>

struct Node 
{
    long long data;
    Node* next = nullptr;
};

double time(int n, int iter) 
{
    int i = 0;
    std::vector<Node> memory(n);
    std::vector<Node*> nodes(n);
    for(int i = 0; i < n; i++){ nodes[i] = &memory[i];}
    std::random_shuffle(nodes.begin(), nodes.end());
    for(int i = 0; i < n - 1; i++){ nodes[i]->next = nodes[i + 1];}
    Node* start = nodes[0];
    nodes.clear();
    nodes.shrink_to_fit();
    auto start_c = std::chrono::steady_clock::now();
    for(int it = 0; it < iter; ++it) 
    {
        Node* node = start;
        while(node){ node = node->next;}
    }
    double ns = std::chrono::duration_cast<std::chrono::nanoseconds>(std::chrono::steady_clock::now() - start_c).count();
    return ns / double(n * iter);
}

int main() 
{
    std::ofstream out("output.txt");
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int elemsPerMeasure = int(1) << 28;
    for(int ei = 24; ei <= 120; ++ei)
    {
        int N = (int)std::round(std::pow(2.0, double(ei) / 4));
        int reps = elemsPerMeasure / N;
        if(reps<1){ reps = 1;}
        auto ans = time(N, reps);
        // out << (N * sizeof(Node)) << " " << ans << "\n";
        std::cout << (N * sizeof(Node)) << " " << ans << "\n";
    }
    return 0;
}
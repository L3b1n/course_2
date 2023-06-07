#include <cmath>
#include <limits>
#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>

struct Vector
{
    long long index;
    std::vector<long long> coordinates;

    Vector(const std::vector<long long>& coordinates, long long index) : coordinates(coordinates), index(index) {}
};

double distance(const Vector& v1, const Vector& v2)
{
    double dist = 0.0;
    for(int i = 0; i < v1.coordinates.size(); ++i){ double diff = v1.coordinates[i] - v2.coordinates[i]; dist += diff * diff;}
    return std::sqrt(dist);
}

std::vector<long long> sumVectors(const std::vector<Vector>& vectors)
{
    Vector center(vectors[0].coordinates, 0);
    std::vector<Vector> sortedVectors = vectors;
    std::vector<long long> result; result.push_back(1);
    std::vector<bool> used(vectors.size(), false); used[0] = true;
    for(int i = 1; i < vectors.size(); ++i)
    {
        int maxIndex = -1;
        double maxDistance = -std::numeric_limits<double>::max();
        for(int j = 0; j < vectors.size(); ++j)
        {
            if(!used[j])
            {
                double minDist = distance(center, vectors[j]);
                for(int k = 0; k < result.size(); ++k){ minDist = std::min(minDist, distance(vectors[result[k] - 1], vectors[j]));}
                if(minDist > maxDistance){ maxDistance = minDist; maxIndex = j;}
            }
        }
        used[maxIndex] = true;
        result.push_back(maxIndex + 1);
        for(int j = 0; j < vectors[0].coordinates.size(); ++j){ center.coordinates[j] += vectors[maxIndex].coordinates[j];}
    }
    return result;
}

int main() 
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    long long n, m;
    std::cin >> n >> m;
    std::vector<Vector> vectors;
    for(long long i = 0; i < n; ++i) 
    {
        std::vector<long long> coordinates(m);
        for(long long j = 0; j < m; ++j){ std::cin >> coordinates[j];}
        vectors.emplace_back(coordinates, i + 1);
    }
    std::vector<long long> result = sumVectors(vectors);
    std::copy(result.begin(), result.end(), std::ostream_iterator<long long>(std::cout, "\n"));
    return 0;
}

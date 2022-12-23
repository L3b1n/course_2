#include <set>
#include <vector>
#include <fstream>
#include <iostream>

using namespace std;

int main() 
{
    ios_base::sync_with_stdio(0); 
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    cin.tie(0); 
    cout.tie(0);
    int n, m, firstIntersection, secondIntersection, q;
    cin >> n >> m;
    vector<long long> answer(n, INT_MIN);
    vector<vector<pair<int, long long>>> matrix(n);
    for (int i = 0; i < m; i++) {
        int begin, end, capacity;
        cin >> begin >> end >> capacity;
        matrix[begin - 1].push_back(make_pair(end, capacity));
        matrix[end - 1].push_back(make_pair(begin, capacity));
    }
    cin >> firstIntersection >> secondIntersection >> q;
    --firstIntersection; --secondIntersection;
    answer[firstIntersection] = q * matrix[firstIntersection].size();
    set<int> temp_set;
    temp_set.emplace(firstIntersection);
    for (; !temp_set.empty();) {
        int current_node = *temp_set.begin();
        temp_set.erase(temp_set.begin());
        for_each(matrix[current_node].begin(), matrix[current_node].end(), 
            [&current_node, &answer, &matrix, &temp_set, &q](const pair<int, long long>& temp){
                if (answer[temp.first - 1] > (answer[current_node] + temp.second + matrix[temp.first - 1].size() * q)) {
                    answer[temp.first - 1] = answer[current_node] + temp.second + matrix[temp.first - 1].size() * q;
                    temp_set.emplace(temp.first - 1);
                }
            }
        );
    }
    if (answer[secondIntersection] != INT_MIN) {
        cout << "Yes" << endl;
        cout << answer[secondIntersection] - matrix[secondIntersection].size() * q;
    } else {
        cout << "No" << endl;
    }
    return 0;
}
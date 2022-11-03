#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

int main()
{
    ios_base::sync_with_stdio(0);
    in.tie(0);
    out.tie(0);
    int n;
    in >> n;
    std::vector<int> answer(n, 0);
    for_each(answer.begin(), (answer.begin() + n - 1), 
        [&answer](const int test){ 
            pair<int, int> temp;
            ::in >> temp.first >> temp.second; answer[temp.second - 1] = temp.first;
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<int>(out, " "));
    return 0;
}
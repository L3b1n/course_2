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
    int n, m, i = 0;
    in >> n >> m;
    std::vector<std::vector<int>> answer(n, std::vector<int>());
    for_each(answer.begin(), (answer.begin() + m), 
        [&answer, &i](const std::vector<int>& temp){
            int num1, num2;
            ::in >> num1 >> num2;
            answer[num1 - 1].push_back(num2);
            answer[num2 - 1].push_back(num1);
        }
    );
    for_each(answer.begin(), answer.end(), [&i](const std::vector<int>& vec){ ::out << vec.size() << " "; std::copy(vec.rbegin(), vec.rend(), std::ostream_iterator<int>(out, " ")); ::out << endl;});
}
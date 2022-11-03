#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
    ifstream in("input.txt", ios::out);
    ofstream out("output.txt");
    ios_base::sync_with_stdio(0);
    in.tie(0);
    int n, m, i = 0;
    in >> n >> m;
    std::vector<std::vector<bool>> answer(n, std::vector<bool>(n, 0));
    for_each(answer.begin(), (answer.begin() + m), 
        [&in, &answer, &i](const std::vector<bool>& temp){
            int num1, num2;
            in >> num1 >> num2;
            answer[num1 - 1][num2 - 1] = 1;
            answer[num2 - 1][num1 - 1] = 1;
        }
    );
    for_each(answer.begin(), answer.end(), [&out](const std::vector<bool>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<bool>(out, "  ")); out << endl;});
}
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
    out.tie(0);
    int n, j = 0;
    in >> n;
    std::vector<int> answer(n, 0);
    for_each(answer.begin(), answer.end(), 
        [&in, &out, &answer, &j](int& temp){
            int i = 0; j++;
            for_each(answer.begin(), answer.end(), 
                [&in, &answer, &i, &j](int& x){ 
                    int temp; in >> temp; if(temp == 1){ answer[i] = j;} i++;
                }
            );
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<int>(out, " "));
}
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
    int size, constant, n;
    in >> size >> constant >> n;
    std::vector<int> answer(size, -1);
    for_each(answer.begin(), (answer.begin() + n), 
        [&answer, &size, &constant](const int temp){
            int key, counter = 0, index;
            ::in >> key;
            for(;;)
            {
                index = (key % size + constant * counter) % size;
                if(answer[index] == -1 || answer[index] == key){ answer[index] = key; break;}
                else{ counter++;}
            }
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<int>(out, " "));
    return 0;
}
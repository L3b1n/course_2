#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int size, constant, n;
    std::cin >> size >> constant >> n;
    std::vector<int> answer(size, -1);
    std::for_each(answer.begin(), (answer.begin() + n), 
        [&answer, &size, &constant](const int temp){
            int key, counter = 0, index;
            std::cin >> key;
            for(;;)
            {
                index = (key % size + constant * counter) % size;
                if(answer[index] == -1 || answer[index] == key){ answer[index] = key; break;}
                else{ counter++;}
            }
        }
    );
    std::copy(answer.begin(), answer.end(), std::ostream_iterator<int>(std::cout, " ")); std::cout << "\n";
    return 0;
}
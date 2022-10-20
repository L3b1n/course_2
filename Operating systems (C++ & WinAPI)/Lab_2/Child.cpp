#include <iostream>
#include <deque>
#include <vector>
#include <algorithm>
#include <stdlib.h>

using namespace std;

int main(int argc, char* argv[])
{
	cout << "I am created" << endl;
	int size = atoi(argv[1]);
	cout << "Size of array is " << size << endl;

	//double* massive = new double[size];
    vector<double> massive(size);
	if(argc > 1)
	{
		int counter = 0;
		for(int i = 2; i < argc; i++){ massive[counter++] = atof(argv[i]);}
	}
    

    deque<double> non_unique;
    for_each(
        massive.begin(), 
        massive.end(),
        [&massive, &non_unique](double el){
            int x = std::count(massive.begin(), massive.end(), el);
            if(x > 1 && std::count(non_unique.begin(), non_unique.end(), el) == 0){ for(int i = 0; i < x; i++){ non_unique.emplace_front(el);}}
            else if(x == 1){ non_unique.push_back(el);}
        }
    );
    std::copy(non_unique.begin(), non_unique.end(), ostream_iterator<double>(cout, " "));
    cout << endl;
	
	// int swapCounter = 0;
	// for(int i = 0; i < size; i++) 
    // {
	// 	if(massive[i] == numberToFind)
    //     {
	// 		massive[i] = massive[swapCounter];
	// 		massive[swapCounter] = numberToFind;
	// 		swapCounter += 1;
	// 	}
	// }

	// for(int i = 0; i < size; i++) 
    // {
	// 	cout << massive[i] << " ";
	// }

	system("pause");
	return 0;
}
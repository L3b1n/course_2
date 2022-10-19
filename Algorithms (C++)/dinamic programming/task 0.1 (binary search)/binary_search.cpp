#include <iostream>
#include <algorithm>
#include <vector>

namespace dma
{
    bool binary_search(const std::vector<int> &mas, const int &value)
    {
        int left = 0, right = mas.size() - 1;
        while(left < right)
        {
            int medium = (left + right) >> 1;
            if(mas[medium] < value){ left = medium +1;}
            else if(mas[medium] > value){ right = medium - 1;}
            else{ return true;}
        }
        return mas[left] == value;
    }

    std::vector<int>::const_iterator lower_bound(const std::vector<int> &mas, const int& value)
    {
        std::vector<int>::const_iterator it;
        std::vector<int>::const_iterator first = mas.begin();
        typename std::iterator_traits<std::vector<int>::iterator>::difference_type count, step;
        count = mas.size();
    
        while(count > 0) 
        {
            it = first; 
            step = count / 2; 
            std::advance(it, step);
            if(*it < value)
            {
                first = ++it;
                count -= step + 1;
            } 
            else{ count = step;}
        }
        return first;
    }

    std::vector<int>::const_iterator upper_bound(const std::vector<int> &mas, const int& value)
    {
        std::vector<int>::const_iterator it;
        std::vector<int>::const_iterator first = mas.begin();
        typename std::iterator_traits<std::vector<int>::iterator>::difference_type count, step;
        count = mas.size();
    
        while(count > 0) 
        {
            it = first; 
            step = count / 2; 
            std::advance(it, step);
            if(!(value < *it)) 
            {
                first = ++it;
                count -= step + 1;
            } 
            else{ count = step;}
        }
        return first;
    }
}

using namespace std;

int main()
{
    int n, k;
    cin >> n;
    vector<int> mas; mas.reserve(n);
    for(int i = 0, temp; i < n; i++){ cin >> temp; mas.push_back(temp);}
    cin >> k;
    vector<int> test = { std::istream_iterator<int>(cin), std::istream_iterator<int>()};
    vector<int>::const_iterator beg = mas.begin();
    for(int i = 0; i < k; i++)
    {
        cout << dma::binary_search(mas, test[i]) << " " << std::distance(beg, dma::lower_bound(mas, test[i])) << " " << std::distance(beg, dma::upper_bound(mas, test[i])) << endl;
    }
    return 0;
}
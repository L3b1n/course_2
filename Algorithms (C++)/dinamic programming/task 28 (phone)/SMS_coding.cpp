#include <fstream>
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <cmath>

std::ifstream in("in.txt");
std::ofstream out("out.txt");

int main()
{
	int i = 0, counter = 2;
    long long count_key, count_letter;
	in >> count_key >> count_letter;
    std::vector<long long> count(count_letter, 0ll);
    std::vector<std::vector<long long>> matrix(count_key, std::vector(count_letter, 0ll));
	std::for_each(count.begin(), count.end(), [](long long& temp){ ::in >> temp;});
	matrix[0] = std::accumulate(count.begin(), count.end(), std::vector<long long>(count_letter), 
		[&i, &counter](std::vector<long long>& p, const long long& temp) -> std::vector<long long> { 
			p[i] = (i == 0 ? temp : p[i - 1] + counter++ * temp); i++;
			return p;
		}
	);
	if(count_key == 0){ out << 0; return 0;}
	else if(count_key == 1)
	{
		int i = 0; long long sum = 0ll;
		out << std::accumulate(count.begin(), count.end(), sum, [&i](long long sum, const long long& temp) -> long long { return (++i) * temp;});
		return 0;
	}

	i = 1;
	std::for_each(++matrix.begin(), matrix.end(), 
		[&matrix, &count, &i, &count_letter](std::vector<long long>& vec){
			int counter = 2, j = i;
			std::for_each((vec.begin() + i), vec.end(), 
				[&matrix, &count, &i, &j, &counter](long long& temp){
					if(j == i){ temp = matrix[i - 1][j - 1] + count[j];}
					else{ temp = matrix[i][j - 1] + counter++ * count[j];} j++;
				}
			);

			for(int j = i + 1; j < count_letter; j++)
			{
				counter = 1;
				long long sum = matrix[i - 1][j - 1];
				std::accumulate((count.begin() + j), count.end(), sum, 
					[&matrix, &counter, &j, &i](long long& sum, const long long& temp) -> long long { 
						sum += counter * temp; 
						if(matrix[i][j + counter - 1] > sum){ matrix[i][j + counter - 1] = sum;} counter++;
						return sum;
					}
				);
			}
			i++;
		}
	);
	std::for_each(matrix.begin(), matrix.end(), [](const std::vector<long long>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<long long>(std::cout, " ")); std::cout << "\n";});
	out << matrix[count_key - 1][count_letter - 1];
	return 0;
}
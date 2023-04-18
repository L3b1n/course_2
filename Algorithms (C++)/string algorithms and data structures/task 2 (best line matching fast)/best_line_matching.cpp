#pragma comment(linker, "/STACK:10000000000")
#include <iostream>
#include <vector>
#include <numeric>
#include <complex>
#include <algorithm>

const double PI = acos(-1);

namespace std
{
    template<typename T1, typename T2>
    std::ostream& operator << (std::ostream& in, const std::pair<T1, T2>& temp)
    {
        in << temp.first << " " << temp.second;
        return in;
    }
}

class FFT
{
public:
    FFT() : length(0) {}

    friend std::istream& operator >> (std::istream& fi, FFT& a)
    {
        fi >> a.length >> a.first >> a.second;
        return fi;
    }

    std::pair<int, int> Solution()
    {
        first += first;
        std::pair<int, int> answer;
        std::vector<int> result(length, 0);
        std::vector<char> tempVec = {'A', 'B', 'C', 'D'};
        std::for_each(tempVec.begin(), tempVec.end(),
            [this, &result](const char& element){
                int i = 0; firstVec.resize(2 * length, 0); secondVec.resize(2 * length, 0);
                std::for_each(first.begin(), first.end(), [this, &i, &element](const char& temp){ firstVec[i++] = (element == temp ? 1 : 0);}); i = 0;
                std::for_each(second.begin(), second.end(), [this, &i, &element](const char& temp){ secondVec[length - i - 1] = (element == temp ? 1 : 0); i++;});
                std::vector<int> multiplyResult = multiply(firstVec, secondVec); i = length - 1;
                std::for_each(result.begin(), result.end(), [&i, &multiplyResult](int& temp){ temp += multiplyResult[i++];});
            }
        );
        std::vector<int>::iterator general = std::max_element(result.begin(), result.end());
        answer = std::make_pair(*general, std::distance(result.begin(), general));
        return answer;
    }

private:
    int length;
    std::string first;
    std::string second;
    std::vector<int> firstVec;
    std::vector<int> secondVec;

    std::vector<int> multiply(const std::vector<int>& first, const std::vector<int>& second)
    {
        int i = 0;
        std::vector<std::complex<double>> tempFirst = evaluate(first);
        std::vector<std::complex<double>> tempSecond = evaluate(second);
        return interpolate(std::accumulate(tempFirst.begin(), tempFirst.end(), std::vector<std::complex<double>>(),
            [&i, &tempFirst, &tempSecond](std::vector<std::complex<double>> tempVec, const std::complex<double>& temp){
                tempVec.push_back(temp * tempSecond[i]); i++;
                return tempVec;
            }
        ));
    }

    std::vector<std::complex<double>> fft(std::vector<std::complex<double>> vec, const bool& invert)
    {
        int i = 1, j = 0;
        int size = vec.size();
        std::for_each(++vec.begin(), vec.end(),
            [&i, &j, &size, &vec](std::complex<double>& temp){
                int bits = size >> 1;
                for(; j & bits; bits >>= 1){ j ^= bits;} j ^= bits;
                if(i < j){ std::swap(temp, vec[j]);} i++;
            }
        );
        for(int distance = 2; distance <= size; distance <<= 1)
        {
            double angle = 2 * PI / distance * (invert ? -1 : 1);
            std::complex<double> rootNumber(std::cos(angle), std::sin(angle));
            for(int i = 0; i < size; i += distance)
            {
                std::complex<double> number = 1; int j = 0;
                std::for_each(vec.begin(), vec.begin() + distance / 2,
                    [&i, &j, &vec, &number, distance, rootNumber](const std::complex<double>& temp){
                        std::complex<double> start = vec[i + j];
                        std::complex<double> end = vec[i + j + distance / 2] * number;
                        vec[i + j] = start + end;
                        vec[i + j + distance / 2] = start - end;
                        number *= rootNumber; j++;
                    }
                );
            }
        }
        return vec;
    }

    inline std::vector<std::complex<double>> evaluate(std::vector<int> tempVec)
    {
        int size = 1;
        std::vector<std::complex<double>> vec(tempVec.begin(), tempVec.end());
        while(size < vec.size()){ size <<= 1;} size <<= 1; vec.resize(size);
        return fft(vec, false);
    }

    inline std::vector<int> interpolate(std::vector<std::complex<double>> vec)
    {
        int i = 0;
        int size = vec.size();
        std::vector<std::complex<double>> tempVec = fft(vec, true);
        return std::accumulate(tempVec.begin(), tempVec.end(), std::vector<int>(),
            [&i, &tempVec, &size](std::vector<int> answer, const std::complex<double>& temp){ 
                answer.push_back(static_cast<int>(std::round(temp.real() / size)));
                return answer;
            }
        );
    } 
};

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    FFT fft;
    std::cin >> fft;
    std::cout << fft.Solution() << "\n";
    return 0;
}
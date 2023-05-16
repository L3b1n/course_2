#define _USE_MATH_DEFINES
#include <cmath>
#include <omp.h>
#include <string>
#include <iostream>

double h;
const double b =3;
const double a = -1;

inline double x(double k){ return a + h * k;}

inline double function(double x){ return std::cos(x);}

int main(int argc, char* argv[]) 
{
    h = (b - a) / N;
    const int N = std::stoi(argv[1]);
    const int num_threads = omp_get_max_threads();

    double* ideal = new double[N + 1];
    double* f_values = new double[N + 1];
#pragma omp parallel for
    for(int i = 0; i <= N; i++){ f_values[i] = function(x(i));}

    int res = 0;
    double max_f2 = 0;
#pragma omp parallel for reduction(max: max_f2) num_threads(num_threads)
    for(int i = 1; i < N; i++) 
    {
        double f2 = (f_values[i - 1] - 2 * f_values[i] + f_values[i + 1]) / (h * h);
        if(f2 > max_f2){ max_f2 = f2; res = i;}
    }
#pragma omp for
    double exact_f2 = 6 * std::max(fabs(a), fabs(b));
    std::cout << "\nmax |f''(x)| = " << max_f2 << " ideal: " << exact_f2 << " difference: " << std::abs(max_f2 - exact_f2) << "\n";
    delete[] f_values;
    return 0;
}
#define _USE_MATH_DEFINES
#include <cmath>
#include <omp.h>
#include <iostream>

inline double function(double x)
{
    if(x <= M_PI / 2){ return x * x - 2 * x;}
    else{ return x - M_PI / 2;}
}

double integration(double a, double b, int N) 
{
    double h = (b - a) / N;
    double sum = 0;
    sum += h / 2 * (function(a) + function(a + h));

#pragma omp parallel for reduction(+:sum)
    for(int i = 1; i < N; i += 2) 
    {
        double x0 = a + i * h;
        double x1 = x0 + h;
        double x2 = x1 + h;
        sum += h / 3 * (function(x0) + 4 * function(x1) + function(x2));
    }
    sum += h / 2 * (function(b - h) + function(b));
    return sum;
}

int main() 
{
    int N;
    double a = 0;
    double b = 5;
    std::cout << "Enter N: ";
    std::cin >> N;
#pragma omp parallel sections
    {
        double integral = integration(a, b, N);
        double ideal = M_PI / 2 + M_PI * M_PI / 8 - 1 + b * b / 2 - M_PI * b / 2;
        std::cout << "ideal " << ideal << "\n";
        std::cout << "Integral " << integral << "\n";
        #pragma omp section
        {
            double integral1 = integration(a, b, 4 * N);
            std::cout << "Integral " << integral1 << "\n";
        }
    }
    return 0;
}
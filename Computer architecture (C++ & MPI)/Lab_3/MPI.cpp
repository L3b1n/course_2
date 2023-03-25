#define _CRT_SECURE_NO_WARNINGS
#include <mpi.h>
#include <iostream>
#include <vector>
#include <cstdio>
#include <sys/timeb.h>

using namespace std;

int main(int argc, char* argv[]) 
{
    const int K = 1000, L = 1000, M = 1000;
    int process_rank, process_cnt;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &process_cnt);
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);
    int k1 = K / process_cnt;
    srand(time(NULL));
    float *a = (float*) malloc(K * L * sizeof(float));
    float *b = (float*) malloc(L * M * sizeof(float));
    float *c = (float*) malloc(K * M * sizeof(float));
    float *a1 = (float*) malloc(k1 * L * sizeof(float));
    float *c1 = (float*) malloc(k1 * M * sizeof(float));
    if(process_rank == 0) 
    {
        for(int i = 0; i < K * L; i++){ a[i] = (rand() % 2 == 0) ? 1 : -1;}
        for(int i = 0; i < L * M; i++){ b[i] = (rand() % 2 == 0) ? 1 : -1;}
        for(int i = 0; i < K * M; i++){ c[i] = 0;}
    }
    double time_start = MPI_Wtime();
    MPI_Scatter(a, k1 * L, MPI_FLOAT, a1, k1 * L, MPI_FLOAT, 0, MPI_COMM_WORLD);
    MPI_Bcast(b, L * M, MPI_FLOAT, 0, MPI_COMM_WORLD);
    for(int i = 0; i < k1; i++)
    {
        for(int s = 0; s < M; s++)
        {
            float sum = 0;
            for(int j = 0; j < L; j++){ sum += a1[i * L + j] * b[j * M + s];}
            c1[i * M + s] = sum;
        }
    }
    MPI_Gather(c1, k1 * M, MPI_FLOAT, c, k1 * M, MPI_FLOAT, 0, MPI_COMM_WORLD);
    double time_end = MPI_Wtime();
    double time_start1 = MPI_Wtime();
    int k2_start_index = k1 * process_cnt;
    if(process_rank == 0 && k2_start_index < K)
    {
        int k2 = K - k2_start_index;
        for(int i = k2_start_index; i < K; i++)
        {
            for(int s = 0; s < M; s++) 
            {
                float sum = 0;
                for(int j = 0; j < L; j++){ sum += a[i * L + j] * b[j * M + s];}
                c[i * M + s] = sum;
            }
        }
    }
    double time_end1 = MPI_Wtime();
    if(process_rank == 0) 
    {
        for(int i = 0; i < K; i++){ for(int j = 0; j < L; ++j){ std::cout << a[i * L + j] << "  ";} std::cout << "\n";}
        std::cout << "\n";
        for(int i = 0; i < L; i++){ for(int j = 0; j < M; ++j){ std::cout << b[i * M + j] << "  ";} std::cout << "\n";}
        std::cout << "\n";
        for(int i = 0; i < K; i++){ for(int j = 0; j < M; ++j){ std::cout << c[i * M + j] << "  ";} std::cout << "\n";}
        std::cout << "\ncalculation time 1: " << time_end - time_start;
    }
    MPI_Finalize();
    return 0;
}
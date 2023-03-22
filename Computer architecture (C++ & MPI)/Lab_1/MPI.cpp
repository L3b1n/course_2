#include <mpi.h>
#include <cstdio>
#include <iostream>

#define _CRT_SECURE_NO_WARNINGS

int main(int argc, char* argv[])
{
    int L;
    L = atoi(argv[1]);
    int process_rank, process_cnt;
    
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &process_cnt);
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);

    srand(time(NULL));
    float *arrayA = (float*) malloc(L * sizeof(float)); float *arrayA1 = (float*) malloc(L * sizeof(float));
    float *arrayB = (float*) malloc(L * sizeof(float)); float *arrayB1 = (float*) malloc(L * sizeof(float));
    float *arrayC = (float*) malloc(L * sizeof(float)); float *arrayC1 = (float*) malloc(L * sizeof(float));

    int sum = 0, rem = L % process_cnt;
    int *sendcounts = (int*) malloc(sizeof(int) * process_cnt);
    int *displs = (int*) malloc(sizeof(int) * process_cnt);
    for(int i = 0; i < process_cnt; i++) 
    {
        sendcounts[i] = L / process_cnt;
        if(rem > 0){ sendcounts[i]++; rem--;}
        displs[i] = sum; sum += sendcounts[i];
    }

    if(process_rank == 0){ for(int i = 0; i <= L; i++){ arrayA[i] = (float) rand() / RAND_MAX; arrayB[i] = (float) rand() / RAND_MAX;}}

    MPI_Scatterv(&arrayA[0], sendcounts, displs, MPI_FLOAT, &arrayA1[0], L, MPI_FLOAT, 0, MPI_COMM_WORLD);
    MPI_Scatterv(&arrayB[0], sendcounts, displs, MPI_FLOAT, &arrayB1[0], L,  MPI_FLOAT, 0, MPI_COMM_WORLD);
    for(int i = 0; i <= sendcounts[process_rank]; i++){ arrayC1[i] = arrayA1[i] * arrayB1[i];}

    MPI_Gatherv(&arrayC1[0], sendcounts[process_rank], MPI_FLOAT, &arrayC[0], sendcounts, displs, MPI_FLOAT, 0, MPI_COMM_WORLD);
    if(process_rank == 0) 
    {
        float sum = 0;
        std::cout << "       arrayA[i]   arrayB[i]   arrayC[i]   \n";
        for (int i = 0; i < L; i++){ sum += arrayC[i]; std::cout << "i = " << i << "; " << arrayA[i] << "; " << arrayB[i] << "; " << arrayC[i] << "\n";}
        std::cout << sum << "\n";
    }
    MPI_Finalize();
    return 0;
}
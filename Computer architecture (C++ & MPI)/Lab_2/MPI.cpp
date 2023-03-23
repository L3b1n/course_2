#include <mpi.h>
#include <cstdio>
#include <iostream>
#include <sys/timeb.h>

#define _CRT_SECURE_NO_WARNINGS

double realtime()
{
    struct timeb tp;
    ftime(&tp);
    return((double)(tp.time) * 1000 + (double)(tp.millitm));
}

int main(int argc, char* argv[]) 
{
    int L, M;
    L = atoi(argv[1]);
    M = atoi(argv[2]);
    int process_rank, process_cnt;
    double t1, t2, t3, t4, t5, t6;
    t1 = realtime();
    
    MPI_Init(&argc, &argv);
    t2 = realtime();
    MPI_Comm_size(MPI_COMM_WORLD, &process_cnt);
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);

    srand(time(NULL));
    double *arrayA = (double*) malloc(L * sizeof(double)); double *arrayA1 = (double*) malloc(L * sizeof(double));
    double *arrayB = (double*) malloc(L * sizeof(double)); double *arrayB1 = (double*) malloc(L * sizeof(double));
    double *arrayC = (double*) malloc(L * sizeof(double)); double *arrayC1 = (double*) malloc(L * sizeof(double));

    int sum = 0, rem = (L * M) % process_cnt; 
    int *sendcounts = (int*) malloc(sizeof(int) * process_cnt); 
    int *displs = (int*) malloc(sizeof(int) * process_cnt);

    for(int i = 0; i < process_cnt; i++) 
    {
        sendcounts[i] = (L * M) / process_cnt;
        if(rem > 0){ sendcounts[i]++; rem--;}
        displs[i] = sum; sum += sendcounts[i];
    }

    if(process_rank == 0){ for(int i = 0; i < L * M; i++){ arrayA[i] = rand() % 2; arrayB[i] = rand() % 2;}}

    double time_start = MPI_Wtime();

    MPI_Scatterv(&arrayA[0], sendcounts, displs, MPI_DOUBLE, &arrayA1[0], L * M, MPI_DOUBLE, 0, MPI_COMM_WORLD);
    MPI_Scatterv(&arrayB[0], sendcounts, displs, MPI_DOUBLE, &arrayB1[0], L * M,  MPI_DOUBLE, 0, MPI_COMM_WORLD);

    t3 = MPI_Wtime();
    for(int i = 0; i <= sendcounts[process_rank]; i++){ arrayC1[i] = arrayA1[i] + arrayB1[i];}

    t4 = MPI_Wtime();

    MPI_Gatherv(&arrayC1[0], sendcounts[process_rank], MPI_DOUBLE, &arrayC[0], sendcounts, displs, MPI_DOUBLE, 0, MPI_COMM_WORLD);
    double time_end = MPI_Wtime();
    if(process_rank == 0) 
    {
        for(int i = 0; i < L; i++){ for(int j = 0; j < M; j++) { std::cout << arrayA[i * L + j] << " ";} std::cout << "\n";}
        std::cout << "\n";
        for(int i = 0; i < L; i++){ for(int j = 0; j < M; j++){ std::cout << arrayB[i * L + j] << " ";} std::cout << "\n";}
        std::cout << "\n";
        for(int i = 0; i < L; ++i){ for(int j = 0; j < M; j++){ std::cout << arrayC[i * L + j] << " ";} std::cout << "\n";}
        std::cout << "init: " << t2 - t1 << "\n";
        std::cout << "time on one process: " << t4 - t3 << "\n";
        std::cout << "time on multiprocess: " << time_end - time_start << "\n";
    }

    MPI_Finalize();
    return 0;
}
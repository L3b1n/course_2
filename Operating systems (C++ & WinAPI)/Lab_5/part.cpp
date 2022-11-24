#include <iostream>
#include <Windows.h>
#include <conio.h>

void main(int argc, char** argv)
{

    int n, m;
    std::cout << "Part:\n Enter numbers N and M (N < M): ";
    std::cin >> n >> m;
    HANDLE hWritePipe = (HANDLE)atoi(argv[1]);
    HANDLE hReadPipe = (HANDLE)atoi(argv[2]);
    HANDLE hEvent = OpenEvent(EVENT_ALL_ACCESS, FALSE, L"Event");

    short temp;
    DWORD dwBytesRead;
    ReadFile(hReadPipe, &temp, sizeof(short), &dwBytesRead, NULL);

    DWORD dwBytesRead1;
    short *arr = new short[temp];
    ReadFile(hReadPipe, arr, sizeof(short) * temp, &dwBytesRead1, NULL);
    for(int i = 0; i < temp; i++){ std::cout << arr[i] << " ";} std::cout << "\n";

    int p = m - n;
    DWORD dwBytesWritten1;
    WriteFile(hWritePipe, &p, sizeof(int), &dwBytesWritten1, NULL);

    DWORD dwBytesWritten;
    for(int i = n; i <= m; i++){ WriteFile(hWritePipe, &arr[i], sizeof(short), &dwBytesWritten, NULL);}

    SetEvent(hEvent);
    CloseHandle(hWritePipe);
    CloseHandle(hReadPipe);
    CloseHandle(hEvent);

    std::cout << "\nEnter 0 for exit\n";
    while(_getch() != '0'){}
}
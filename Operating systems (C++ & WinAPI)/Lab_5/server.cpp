#include <iostream>
#include <Windows.h>
#include <string>
#include <conio.h>

void main(int argc, char** argv)
{
    short n;
    srand(time(0));
    std::cout << "Server:\n Enter array size: ";
    std::cin >> n;
    short *arr = new short[n];
    for(int i = 0; i < n; i++){ arr[i] = rand() % 100;}
    for(int i = 0; i < n; i++){ std::cout << arr[i] << " ";} std::cout << "\n";

    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    HANDLE hWritePipe;
    HANDLE hReadPipe;
    HANDLE hEvent;
    SECURITY_ATTRIBUTES sa;
    sa.nLength = sizeof(SECURITY_ATTRIBUTES);
    sa.lpSecurityDescriptor = nullptr;
    sa.bInheritHandle = TRUE;

    hEvent = CreateEvent(nullptr, FALSE, FALSE, L"Event");

    CreatePipe(&hReadPipe, &hWritePipe, &sa, 0);
    ZeroMemory(&si, sizeof(STARTUPINFO));
    wchar_t *wstr = new wchar_t[80];
    wsprintf(wstr, L"\"part.exe\" %d %d", (int)hWritePipe, (int)hReadPipe);
    CreateProcess(nullptr, wstr, nullptr, nullptr, TRUE, CREATE_NEW_CONSOLE, FALSE, FALSE, &si, &pi);
    
    DWORD dwBytesWritten;
    WriteFile(hWritePipe, &n, sizeof(short), &dwBytesWritten, nullptr);

    DWORD dwBytesWritten1;
    WriteFile(hWritePipe, arr, sizeof(short) * n, &dwBytesWritten1, nullptr);
    WaitForSingleObject(hEvent, INFINITE);

    int size;
    DWORD dwBytesRead1;
    ReadFile(hReadPipe, &size, sizeof(int), &dwBytesRead1, nullptr);

    short anwser;
    DWORD dwBytesRead;
    for(int i = 0; i < size; i++){ ReadFile(hReadPipe, &anwser, sizeof(short), &dwBytesRead, nullptr); std::cout << anwser << " ";}

    CloseHandle(hWritePipe);
    CloseHandle(hReadPipe);
    CloseHandle(hEvent);

    std::cout << "\nEnter 0 for exit\n";
    while(_getch() != '0'){}
}
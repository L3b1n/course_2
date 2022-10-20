#include <iostream>
#include <string>
#include <windows.h>
#include <conio.h>
#include <tchar.h>
#include <stdio.h>

using namespace std;

int main() 
{
    int size;
    cout << "Input size of array: ";
    cin >> size;

    cout << "Input array elements: ";   
    double* array = new double[size];
    for(int i = 0; i < size; i++) 
    {
        cin >> array[i];
    }

    unsigned int x;
    cout << "Input X of buffer: ";
    cin >> x;

    wstring cmd_args = L"\"child.exe\" ";
    cmd_args += to_wstring(size);
    cmd_args += L" ";
    for(int i = 0; i < size; i++) 
    {
        cmd_args += to_wstring(array[i]);
        if(i != size - 1) 
        {
            cmd_args += L" ";
        }
    }

    auto *stemp = new wchar_t[cmd_args.length()];
    wcscpy_s(stemp, cmd_args.length() + 1, cmd_args.c_str());

    STARTUPINFO si;
    PROCESS_INFORMATION piApp;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    si.cb = sizeof(STARTUPINFO);
    si.dwFlags = STARTF_USEPOSITION;
    si.dwXSize = x;

    if(!CreateProcess(NULL, stemp, NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &si, &piApp))
    {
        cout << "New process is not created" << endl;
        return 0;
    }

    cout << "New process is created" << endl;
    WaitForSingleObject(piApp.hProcess, INFINITE);

    CloseHandle(piApp.hThread);
    CloseHandle(piApp.hProcess);
    delete[] array;
    return 0;
}
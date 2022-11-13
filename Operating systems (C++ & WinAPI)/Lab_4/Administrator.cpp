#include <iostream>
#include <windows.h>

using namespace std;

HANDLE Writer[3];
HANDLE Reader[3];
HANDLE Exit[3];

wchar_t const* WriterMes[3]{ L"P_Exit", L"P_A", L"P_B"};
wchar_t const* ReaderMes[3]{ L"Ch_Exit", L"Ch_C", L"Ch_D"};
wchar_t const* ExitMes[2]{ L"1", L"0"};

int main() 
{
	for(int i = 0; i < 3; ++i) 
    {
		Writer[i] = CreateEvent(NULL, FALSE, FALSE, WriterMes[i]);
		Reader[i] = CreateEvent(NULL, FALSE, FALSE, ReaderMes[i]);
	}

	for(int i = 0; i < 2; ++i){ Exit[i] = CreateEvent(NULL, FALSE, FALSE, ExitMes[i]);}

	int numberOfProcess;
	std::cout << "Input number of process you want to start: ";
	std::cin >> numberOfProcess;

	HANDLE hSemaphore = CreateSemaphore(NULL, 3, 3, L"Semaphore");
	STARTUPINFO *Wstp = new STARTUPINFO[numberOfProcess], *Rstp = new STARTUPINFO[numberOfProcess];
	PROCESS_INFORMATION *Wpi = new PROCESS_INFORMATION[numberOfProcess], *Rpi = new PROCESS_INFORMATION[numberOfProcess];

	for(int i = 0; i < numberOfProcess; ++i) 
    {
		ZeroMemory(&Wstp[i], sizeof(STARTUPINFO));
		Wstp[i].cb = sizeof(STARTUPINFO);
		ZeroMemory(&Rstp[i], sizeof(STARTUPINFO));
		Rstp[i].cb = sizeof(STARTUPINFO);
		wstring writer = L"Writer.exe", reader = L"Reader.exe";
		CreateProcess(NULL, &writer[0], NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &Wstp[i], &Wpi[i]);
		CreateProcess(NULL, &reader[0], NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &Rstp[i], &Rpi[i]);
	}

	int counter = 1;
	for(int i = 0; i < numberOfProcess; ++i)
    {
		while(true) 
        {
			DWORD mes1 = WaitForMultipleObjects(2, Exit, FALSE, INFINITE);
			if(mes1 == WAIT_OBJECT_0) 
			{
				std::cout << "Reader " << counter << " ended his work\n";
				counter += 1;
				break;
			}
		}
		std::cout << "Writer " << i + 1 << " ended his work\n";
	}

	for(int i = 0; i < 3; i++) 
    {
		CloseHandle(Writer[i]);
		CloseHandle(Reader[i]);
	}

	for(int i = 0; i < 2; i++){ CloseHandle(Exit[i]);}

	for(int i = 0; i < numberOfProcess; i++) 
    {
		CloseHandle(Wpi[i].hThread);
		CloseHandle(Wpi[i].hProcess);
		CloseHandle(Rpi[i].hThread);
		CloseHandle(Rpi[i].hProcess);
	}

	CloseHandle(hSemaphore);
	return 0;
}
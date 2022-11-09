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

	HANDLE hSemaphore = CreateSemaphore(NULL, 5, 5, L"Semaphore");
	STARTUPINFO* Pstp = new STARTUPINFO[numberOfProcess], * Chstp = new STARTUPINFO[numberOfProcess];
	PROCESS_INFORMATION* Ppi = new PROCESS_INFORMATION[numberOfProcess], * Chpi = new PROCESS_INFORMATION[numberOfProcess];

	for(int i = 0; i < numberOfProcess; ++i) 
    {
		ZeroMemory(&Pstp[i], sizeof(STARTUPINFO));
		Pstp[i].cb = sizeof(STARTUPINFO);
		ZeroMemory(&Chstp[i], sizeof(STARTUPINFO));
		Chstp[i].cb = sizeof(STARTUPINFO);
		wstring writer = L"Writer.exe",
		reader = L"Reader.exe";
		CreateProcess(NULL, &writer[0], NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &Pstp[i], &Ppi[i]);
		CreateProcess(NULL, &reader[0], NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &Chstp[i], &Chpi[i]);
	}

	int counter = 1;
	for(int i = 0; i < numberOfProcess; ++i)
    {
		while(true) 
        {
			DWORD mes = WaitForMultipleObjects(3, Writer, FALSE, INFINITE);
			SetEvent(Reader[mes]);
			if(mes == WAIT_OBJECT_0) 
            {
				DWORD mes1 = WaitForMultipleObjects(2, Exit, FALSE, INFINITE);
				if(mes1 == WAIT_OBJECT_0) 
                {
					std::cout << "Reader " << counter << " ended his work\n";
					counter += 1;
				}
				break;
			}
			// std::wcout << L"Received : " << WriterMes[mes] + 2 << L'\n';
			DWORD mes1 = WaitForMultipleObjects(2, Exit, FALSE, INFINITE);
			if(mes1 == WAIT_OBJECT_0) 
            {
				std::cout << "Reader " << counter << " ended his work\n";
				counter += 1;
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
		CloseHandle(Ppi[i].hThread);
		CloseHandle(Ppi[i].hProcess);
		CloseHandle(Chpi[i].hThread);
		CloseHandle(Chpi[i].hProcess);
	}

	CloseHandle(hSemaphore);
	return 0;
}
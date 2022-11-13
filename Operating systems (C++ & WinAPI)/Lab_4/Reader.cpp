#include <iostream>
#include <string>
#include <windows.h>

using namespace std;

wchar_t const* mes[3]{ L"Ch_Exit", L"Ch_C", L"Ch_D"};
wchar_t const* EMes[2]{ L"1", L"0"};

int main() 
{
	std::string number;
	HANDLE Mutex = OpenMutex(MUTEX_ALL_ACCESS, FALSE, L"Mutex");
	WaitForSingleObject(Mutex, INFINITE);
	std::cout << "Process reader is started" << std::endl;
	std::cout << "Process is active" << std::endl;
	HANDLE mesEv[3];
	HANDLE mesEx[2];
	for(int i = 0; i < 3; ++i){ mesEv[i] = OpenEvent(EVENT_ALL_ACCESS, FALSE, mes[i]);}
	for(int i = 0; i < 2; ++i){ mesEx[i] = OpenEvent(EVENT_ALL_ACCESS, FALSE, EMes[i]);}
	while(true) 
    {
		DWORD m = WaitForMultipleObjects(3, mesEv, FALSE, INFINITE);
		std::wcout << mes[m] << L'\n';
		std::cout << "Input 1 to exit or 0 to continue: ";
		std::cin >> number;
		wstring wline(number.begin(), number.end());
		if(wline._Equal(EMes[0])){ SetEvent(mesEx[0]); break;}
		if(wline._Equal(EMes[1])){ SetEvent(mesEx[1]);}
	}
	ReleaseMutex(Mutex);
	return 0;
}
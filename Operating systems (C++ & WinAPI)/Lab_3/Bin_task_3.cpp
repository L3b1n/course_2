#include <iostream>
#include <cctype>
#include <deque>
#include <windows.h>

using namespace std;

CRITICAL_SECTION cs;
HANDLE hOutEvent, hContinueEvent;
int counter;
deque<char> result;

struct KData
{
    char* array;
    int size;
    int k;
};

DWORD WINAPI work(void* obj)
{
    KData* dArr = (KData*)obj;
    char* array = dArr->array;

    cout << "Thread work is started" << endl;
    int time;
    cout << "Enter time interval to sleep: ";
    cin >> time;

    for(int i = 0; i < dArr->size; i++)
    {
        if(!isdigit(array[i]) && !isalpha(array[i])){ result.push_front(array[i]);}
        else{ result.push_back(' ');}
    }

    for(int i = 0; i < dArr->size; i++){ cout << result[i] << " "; Sleep(time);}
    cout << "\nThread work is finished" << endl;
    SetEvent(hOutEvent);
    return 0;
}

DWORD WINAPI CountElement(void* obj)
{
    EnterCriticalSection(&cs);
    WaitForSingleObject(hContinueEvent, INFINITE);
    KData* dArr = (KData*)obj;
    char* array = dArr->array;
    cout << "Thread CountElement is started" << endl;

    counter = 0;
    for(int i = 0; i < dArr->size; i++)
    {
        if(result[i] == '?' || result[i] == '.' || result[i] == ',' || result[i] == ':' || result[i] == '!' || result[i] == ';'){ counter++;}
    }

    LeaveCriticalSection(&cs);
    cout << "\nThread CountElement is finished" << endl;
    return 0;
}

int main()
{
    int size, k;
    cout << "Enter size of array: ";
    cin >> size;
    char* array = new char[size];
    cout << "Enter " << size << " elements of array: ";
    for(int i = 0; i < size; i++){ cin >> array[i];}
    cout << "Size of array is: " << size << endl;
    cout << "Elements of array is: ";
    for(int i = 0; i < size; i++){ cout << array[i] << " ";} cout << endl;
    cout << "Enter number K: ";
    cin >> k;
    KData* temp = new KData();
    temp->size = size;
    temp->array = array;

    InitializeCriticalSection(&cs);
    hOutEvent = CreateEvent(NULL, FALSE, FALSE, NULL);
    hContinueEvent = CreateEvent(NULL, FALSE, FALSE, NULL);

    HANDLE hThreadWork;
    DWORD IDThreadWork;
    DWORD dwCount;
    hThreadWork = CreateThread(NULL, 0, work, (void*)temp, NULL, &IDThreadWork);
    if(hThreadWork == nullptr){ return GetLastError();}

    HANDLE hThreadCount;
    DWORD IDThreadCount;
    DWORD dwCountCount;
    hThreadCount = CreateThread(NULL, 0, CountElement, (void*)temp, NULL, &IDThreadCount);
    if(hThreadCount == nullptr){ return GetLastError();}

    WaitForSingleObject(hOutEvent, INFINITE);

    for(int i = 0; i < size; i++){ cout << result[i] << " ";}
    cout << endl;

    SetEvent(hContinueEvent);
    EnterCriticalSection(&cs);

    cout << "Number of elements: " << counter << endl;
    WaitForSingleObject(hThreadWork, INFINITE);
    WaitForSingleObject(hThreadCount, INFINITE);
    DeleteCriticalSection(&cs);
    CloseHandle(hThreadWork);
    CloseHandle(hThreadCount);
    return 0;
}
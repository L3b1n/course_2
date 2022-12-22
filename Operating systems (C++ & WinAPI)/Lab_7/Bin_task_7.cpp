#include <windows.h>
#include <stdio.h>
 
#define countOfPhilosoph 5
 
HANDLE threads[countOfPhilosoph];
CRITICAL_SECTION cs[countOfPhilosoph];
 
int LEFT(int f)
{
    if(intPhilID == 0){ return countOfPhilosoph - 1;}
    else{ return (intPhilID - 1) % countOfPhilosoph;}
}
 
int RIGHT(int intPhilID)
{
    if(intPhilID == countOfPhilosoph - 1){ return 0;}
    else{ return (intPhilID + 1) % countOfPhilosoph;}
}
 
void getForks(int intPhilID) 
{
    EnterCriticalSection(&cs[LEFT(intPhilID)]);
    EnterCriticalSection(&cs[RIGHT(intPhilID)]);
}
 
void putDown(int intPhilID) 
{
    LeaveCriticalSection(&cs[RIGHT(intPhilID)]);
    LeaveCriticalSection(&cs[LEFT(intPhilID)]);
}
 
DWORD WINAPI Start(CONST LPVOID philID) 
{
    Sleep(rand() % 10000);
    while(true) 
    {
        printf("Philosopher %d %s", int(philID) + 1, "seat\n");
        getForks(int(philID));
        printf("Philosopher %d %s", int(philID) + 1, "eating\n");
        Sleep(rand() % 3000);
        putDown(int(philID));
        printf("Philosopher %d %s", int(philID) + 1, "walking\n");
        Sleep(rand() % 5000);
    }
}
 
int main() 
{
    for(int i = 0; i < countOfPhilosoph; i++){ InitializeCriticalSection(&cs[i]);}
    for(int i = 0; i < countOfPhilosoph; i++){ threads[i] = CreateThread(NULL, 0, &Start, (LPVOID)i, 0, NULL);}
    getchar();
    return 0;
}
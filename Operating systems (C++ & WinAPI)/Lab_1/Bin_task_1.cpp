#include <iostream>
#include <windows.h>
#include <process.h>

using namespace std;

struct KData 
{
	int size;
	int* array;
};

int comp (const int *a, const int *b)
{
    return *a - *b;
}

UINT WINAPI worker(void* data) 
{
	cout << "Start of the thread" << endl;
	KData* temp = (KData*)data;
	qsort(temp->array, temp->size, sizeof(int), (int(*)(const void *, const void *)) comp);
	cout << "Min element: " << *temp->array << endl;
	cout << "Finish of the thread" << endl;
	return 0;
}



int main() 
{
	int n;
	cout << "Input size of the array: ";
	cin >> n;
	int* a = new int[n];
	cout << "Input " << n << " elements of the array: ";
	for (int i = 0; i < n; i++){ cin >> a[i];}

	int stop, start;
	cout << "Input stop time: "; cin >> stop;
	cout << "Input start time: "; cin >> start;
    cout << endl;

	HANDLE work;
	KData* arr_t = new KData();
	arr_t->size = n;
	arr_t->array = a;
	work = (HANDLE) _beginthreadex(NULL, 0, worker, (void*)arr_t, 0, NULL);
	SuspendThread(work);
	cout << "Work stop working" << endl;
	Sleep(stop);
	ResumeThread(work);
	cout << "Work start work again" << endl;

	WaitForSingleObject(work, INFINITE);
	CloseHandle(work);
	return 0;
}
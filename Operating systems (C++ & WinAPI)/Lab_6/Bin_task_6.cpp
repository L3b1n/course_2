#include <deque>
#include <mutex>
#include <thread>
#include <vector>
#include <chrono>
#include <iostream>
#include <condition_variable>

int counter;
std::mutex out_m;
std::mutex section;
std::deque<char> result;
std::condition_variable_any out;

void Work(std::vector<char>& array, const int& size)
{
    std::cout << "\nThread work is started\n|\t\t|\n";
    int time;
    std::cout << "Enter time interval to sleep: ";
    std::cin >> time;

    for(int i = 0; i < size; i++)
    {
        if(!isdigit(array[i]) && !isalpha(array[i])){ result.push_front(array[i]);}
        else{ result.push_back(' ');}
    }

    for(int i = 0; i < size; i++){ std::cout << result[i] << " "; std::this_thread::sleep_for(std::chrono::milliseconds(time));}
    out.notify_one();
    std::cout << "\nThread work is finished\n";
}

void CountElement(const int& size)
{
    std::unique_lock lock_section(section);
    std::unique_lock lock_out(out_m);
    out.wait(lock_out);
    std::cout << "\nThread CountElement is started\n|\t\t|";

    counter = 0;
    for(int i = 0; i < size; i++)
    {
        if(result[i] != ' '){ counter++;}
    }

    lock_section.unlock();
    out.notify_one();
    std::cout << "\nThread CountElement is finished\n";
}

int main()
{
    int size, k;
    std::cout << "Enter size of array: ";
    std::cin >> size;
    std::vector<char> array(size);
    std::cout << "Enter " << size << " elements of array: ";
    for(int i = 0; i < size; i++){ std::cin >> array[i];}
    std::cout << "Size of array is: " << size << "\n";
    std::cout << "Elements of array is: ";
    for(int i = 0; i < size; i++){ std::cout << array[i] << " ";} std::cout << "\n";
    std::cout << "Enter number K: ";
    std::cin >> k;

    std::thread work(Work, std::ref(array), std::ref(size));
	std::thread countElement(CountElement, std::ref(size));
	std::this_thread::sleep_for(std::chrono::milliseconds(2));

    std::unique_lock lock_out(out_m);
    out.wait(lock_out);

    for(int i = 0; i < k; i++){ std::cout << result[i] << " ";} std::cout << "\n";

    std::unique_lock lock_section(section);
    std::cout << "Number of elements: " << counter << "\n";
    lock_section.unlock();

    work.detach();
    countElement.detach();
    return 0;
}
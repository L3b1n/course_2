#include <mutex>
#include <string>
#include <thread>
#include <vector>
#include <iostream>
#include <condition_variable>

int num = 0;
int randomChoice;
bool isReady = false;
bool notification = false;
std::mutex readyMutex, giveMutex;
std::condition_variable readyEvent, giveEvent;

void AgentActions() 
{
	std::srand(std::time(nullptr));
	while (true) 
    {
		std::unique_lock<std::mutex> readyLock(readyMutex);
		while(!isReady){ readyEvent.wait(readyLock);}
		std::cout << "The mediator chooses two items\n";
		std::this_thread::sleep_for(std::chrono::milliseconds(2500));
		randomChoice = (rand() % 3) + 1;
		isReady = false;
        switch(randomChoice)
        {
            case 1:
			    std::cout << "The mediator chose paper and matches\n";
                break;
            case 2:
			    std::cout << "The mediator chose tobacco and matches\n";
                break;
            case 3:
			    std::cout << "The mediator chose tobacco and paper\n";
                break;
            default:
                break;
        }
		notification = true;
		giveEvent.notify_all();
	}
}

void SmokerActions(int id) {
	{
        std::unique_lock<std::mutex> readyLock(readyMutex);
        switch(id)
        {
            case 1:
			    std::cout << "The smoker gave tobacco\n";
                break;
            case 2:
			    std::cout << "The smoker gave paper\n";
                break;
            case 3:
			    std::cout << "The smoker gave the matches\n";
                break;
            default:
                break;
        }
		std::this_thread::sleep_for(std::chrono::milliseconds(2000));
		if(++num == 3){ isReady = true; readyEvent.notify_one();}
	}
	{
        std::unique_lock<std::mutex> giveLock(giveMutex);
		while(!notification){ giveEvent.wait(giveLock);}
		if(id == randomChoice)
		{
            switch(id)
            {
                case 1:
				    std::cout << "Man with tobacco smokes\n";
                    break;
                case 2:
				    std::cout << "Man with paper smokes\n";
                    break;
                case 3:
				    std::cout << "Man with matches smokes\n";
                    break;
                default:
                    break;
            }
		}
		num--;
	}
}

int main()
{
	int numberOfRepeats, n = 0;
	std::cout << "Enter the number of repeats: ";
	std::cin >> numberOfRepeats;
	if(numberOfRepeats <= 0){ std::cout << "Error: invalid number entered!!!"; return 0;}
	std::thread agentActions(AgentActions);
	std::vector<std::thread> smokersActions;
	for(; n < numberOfRepeats;)
	{
		smokersActions.push_back(std::thread(SmokerActions, 1));
		smokersActions.push_back(std::thread(SmokerActions, 2));
		smokersActions.push_back(std::thread(SmokerActions, 3));
		for(int i = 0; i < 3; i++){ smokersActions[i].join();}
		std::cout << "Laps done: " << ++n << "\n\n";
		smokersActions.clear();
		notification = false;
		randomChoice = 0;
		isReady = false;
	}
	agentActions.detach();
}
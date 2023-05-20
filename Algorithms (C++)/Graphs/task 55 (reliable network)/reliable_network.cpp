#include <algorithm>
#include <limits>
#include <utility>
#include <vector>
#include <iostream>

namespace std
{
    template<typename T1, typename T2>
    std::ostream& operator << (std::ostream& in, const std::pair<T1, T2>& temp)
    {
        in << temp.first << " " << temp.second;
        return in;
    }
}

namespace dma
{
    template<class T>
	class binary_heap_indexed 
	{
	public:
		binary_heap_indexed() : _size(0) {}
		binary_heap_indexed(int n) : heap(n), pos2Id(n), id2Pos(n), _size(0) {}

		inline int size(){ return _size;}

		void add(int id, T value) 
		{
			heap[_size] = value;
			pos2Id[_size] = id;
			id2Pos[id] = _size;
			up(_size++);
		}

		int remove_min() 
		{
			int removedId = pos2Id[0];
			heap[0] = heap[--_size];
			pos2Id[0] = pos2Id[_size];
			id2Pos[pos2Id[0]] = 0;
			down(0);
			return removedId;
		}

		void remove(int id) 
		{
			int pos = id2Pos[id];
			pos2Id[pos] = pos2Id[--_size];
			id2Pos[pos2Id[pos]] = pos;
			change_value(pos2Id[pos], heap[_size]);
		}

		void change_value(int id, T value) 
		{
			int pos = id2Pos[id];
			if(heap[pos] < value){ heap[pos] = value; down(pos);} 
			else if(heap[pos] > value){ heap[pos] = value; up(pos);}
		}

	private:
		int _size;
		std::vector<T> heap;
		std::vector<int> pos2Id;
		std::vector<int> id2Pos;

		void up(int pos) 
		{
			while(pos > 0) 
			{
				int parent = (pos - 1) / 2;
				if(heap[pos] >= heap[parent]){ break;}
				exchange(pos, parent);
				pos = parent;
			}
		}

		void down(int pos) 
		{
			while(true) 
			{
				int child = 2 * pos + 1;
				if(child >= _size){ break;}
				if(child + 1 < _size && heap[child + 1] < heap[child]){ ++child;}
				if(heap[pos] <= heap[child]){ break;}
				exchange(pos, child);
				pos = child;
			}
		}

		void exchange(int i, int j) 
		{
			std::swap(heap[i], heap[j]);
			std::swap(pos2Id[i], pos2Id[j]);
			id2Pos[pos2Id[i]] = i;
			id2Pos[pos2Id[j]] = j;
		}
	};
}

class Network
{
public:
	Network() : countSummit(0), countEdge(0), countOff(0), source(0), stok(0) {}
 
	friend std::istream& operator >> (std::istream& in, Network& a)
    {
		int maxNumber;
        in >> a.countSummit >> a.countEdge; maxNumber = a.countSummit++;
		a.countSummit *= 2; a.graph.resize(a.countSummit);
		std::vector<std::pair<std::pair<int, int>, int>> tempVec;
        for(int i = 0; i < a.countEdge; i++)
        {
            int begin, end, cost;
            in >> begin >> end >> cost;
			tempVec.push_back(std::make_pair(std::make_pair(begin, end), cost));
        }
		in >> a.source >> a.stok >> a.countOff;
		std::vector<bool> isConnected(a.countSummit, true);
		std::for_each(tempVec.begin(), tempVec.end(),
			[&a, &maxNumber, &isConnected](const std::pair<std::pair<int, int>, int>& temp){
				int capacity = 1;
				int cost = temp.second;
				int end = temp.first.second;
				int begin = temp.first.first;
				if(begin == a.source){
					a.addEdge(begin, end, capacity, cost);
					if(isConnected[end]){ a.addEdge(end, end + maxNumber, capacity, 0); isConnected[end] = false;}
				} else if(end == a.source){
					a.addEdge(end, begin, capacity, cost);
					if(isConnected[begin]){ a.addEdge(begin, begin + maxNumber, capacity, 0); isConnected[begin] = false;}
				} else if(begin == a.stok){
					a.addEdge(end + maxNumber, begin, capacity, cost);
					if(isConnected[end]){ a.addEdge(end, end + maxNumber, capacity, 0); isConnected[end] = false;}
				} else if(end == a.source){
					a.addEdge(begin + maxNumber, end, capacity, cost);
					if(isConnected[begin]){ a.addEdge(begin, begin + maxNumber, capacity, 0); isConnected[begin] = false;}
				} else{
					a.addEdge(begin + maxNumber, end, capacity, cost);
					a.addEdge(end + maxNumber, begin, capacity, cost);
					if(isConnected[end]){ a.addEdge(end, end + maxNumber, capacity, 0); isConnected[end] = false;}
					if(isConnected[begin]){ a.addEdge(begin, begin + maxNumber, capacity, 0); isConnected[begin] = false;}
				}
			}
		);
        return in;
    }

	void Solution()
	{
		std::pair<int, int> flow = MaxMinFlow(source, stok, countOff + 1);
		if(flow.first < countOff + 1){ std::cout << "No\n" << flow.first << "\n";}
		else{ std::cout << "Yes\n" << flow.second << "\n";}
	}

private:
	struct Node 
	{
		Node(int summit, int capacity, int cost, int flow, size_t back) : summit(summit), capacity(capacity), cost(cost), flow(flow), back(back) {}

		size_t back;
		int summit, capacity, cost, flow;
	};

private:
	std::vector<bool> isUsed;
	std::vector<int> distance;
	std::vector<int> potential;
	std::vector<bool> finished;
	std::vector<int> currentFlow;
	std::vector<int> previousEdge;
	std::vector<int> previousNode;
	std::vector<std::vector<Node>> graph;
	int countSummit, countEdge, countOff, source, stok;
	
	void addEdge(const int& begin, const int& end, const int& capacity, const int& cost) 
	{
		graph[begin].emplace_back(end, capacity, cost, 0, graph[end].size());
		graph[end].emplace_back(begin, 0, -cost, 0, graph[begin].size());
	}

	void FordBellman(int begin, std::vector<int>& dist) 
	{
        int qt = 0;
        std::vector<bool> inqueue(countSummit);
        std::vector<int> q(countSummit); q[qt++] = begin;
        std::fill(dist.begin(), dist.end(), std::numeric_limits<int>::max()); dist[begin] = 0;
        for(int qh = 0; qh != qt; qh++) 
		{
            int summit = q[qh % countSummit]; inqueue[summit] = false;
			std::for_each(graph[summit].begin(), graph[summit].end(),
				[this, &dist, &summit, &qt, &q, &inqueue](Node& temp){
					if(temp.capacity > temp.flow) 
					{
						int end = temp.summit;
						int tempDistance = dist[summit] + temp.cost;
						if(dist[end] > tempDistance) 
						{
							dist[end] = tempDistance;
							if(!inqueue[end]){ inqueue[end] = true; q[qt++ % countSummit] = end;}
						}
					}
				}
			);
        }
    }

	void Dijkstra(int begin, int end) 
	{
        dma::binary_heap_indexed<int> tempHeap(countSummit); tempHeap.add(begin, 0);
        std::fill(distance.begin(), distance.end(), std::numeric_limits<int>::max()); distance[begin] = 0;
        currentFlow[begin] = std::numeric_limits<int>::max();
        std::fill(finished.begin(), finished.end(), false);
        while(!finished[end] && tempHeap.size() != 0) 
		{
			int i = 0;
            int begin = tempHeap.remove_min(); finished[begin] = true;
			std::for_each(graph[begin].begin(), graph[begin].end(),
				[this, &i, &begin, &tempHeap](const Node& tempSummit){
					if(tempSummit.flow < tempSummit.capacity)
					{
						int end = tempSummit.summit;
						int nprio = distance[begin] + tempSummit.cost + potential[begin] - potential[end];
						if(distance[end] > nprio)
						{
							if(distance[end] == std::numeric_limits<int>::max()){ tempHeap.add(end, nprio);}
							else{ tempHeap.change_value(end, nprio);}
							distance[end] = nprio;
							previousEdge[end] = i;
							previousNode[end] = begin;
							currentFlow[end] = std::min(currentFlow[begin], tempSummit.capacity - tempSummit.flow);
						}
					} i++;
				}
			);
        }
    }

	std::pair<int, int> MaxMinFlow(const int& source, const int& stok, const int& maxFlow)
	{
		potential.resize(countSummit);
        distance.resize(countSummit); currentFlow.resize(countSummit);
        finished.resize(countSummit); isUsed.resize(countSummit, false);
        previousEdge.resize(countSummit); previousNode.resize(countSummit);
        FordBellman(source, potential);
		std::pair<int, int> flow;
        while(flow.first < maxFlow)
		{
			int i = 0;
            Dijkstra(source, stok);
            if(distance[stok] == std::numeric_limits<int>::max()){ break;}
			std::for_each(finished.begin(), finished.end(),
				[this, &i, &stok](const bool& temp){
					if(temp){ potential[i] += distance[i] - distance[stok];} i++;
				}
			);
            int tempFlow = std::min(currentFlow[stok], maxFlow - flow.first); flow.first += tempFlow;
            for(int summit = stok; summit != source; summit = previousNode[summit])
			{
                Node &temp = graph[previousNode[summit]][previousEdge[summit]];
                temp.flow += tempFlow;
                graph[summit][temp.back].flow -= tempFlow;
                flow.second += tempFlow * temp.cost;
            }
        }
		return flow;
	}
};

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
	Network network;
	std::cin >> network;
	network.Solution();
	return 0;
}
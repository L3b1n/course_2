#ifndef BinaryTreeH
#define BinaryTreeH

#include <vector>
#include <algorithm>
#include <stack>
#include <iostream>
#include <limits>
#include <fstream>
#include <string>
#include <sstream>
#include <map>
#include <set>

using namespace std;

struct KNodeTree
{ 
	long long info = 0ll;
	long long a = 0ll;
	long long b = 0ll;
	long long way = 0ll;
	long long hight = 0ll;
	long long leaf = 0ll;
	long long number_of_ways = 0ll;
	KNodeTree *left = nullptr; 
	KNodeTree *right = nullptr;

	explicit KNodeTree(long long _info = 0ll, KNodeTree* _left = 0, KNodeTree* _right = 0, long long _way = 0ll, long long _hight = 0ll,
	long long _number_of_ways = 0ll, long long _leaf = 0ll) 
	: info(_info), left(_left), right(_right), way(_way), hight(_hight), number_of_ways(_number_of_ways), leaf(_leaf) {}

	bool IsLeaf() { return left == 0 && right == 0;}

	void SetWay(long long _way) { way = _way;};

	long long GetWay() { return way;}
};


class KBinaryTree
{ 
private:
	long long max_of_ways = 0ll;
	KNodeTree *pTree = nullptr;
	std::set<long long> num;
	std::vector<long long> vec;

public:
	KBinaryTree() : pTree(nullptr), max_of_ways(0) {}
	~KBinaryTree(){ Erase(pTree);}

	int Len(){ return Len(pTree);}

	int Len(KNodeTree *t)
	{
		if(t == nullptr) return 0;
		else{ return 1 + std::max(Len(t->left), Len(t->right));}    
	}

	void Insert(long long info);
	inline bool IsTwoSons(KNodeTree* p);
	inline void DeleteLE1Sons(KNodeTree* p, KNodeTree** pp);
	bool Delete(long long info);
	long long Sum();
	long long Sum(KNodeTree* p);
	int SumBySet();
	bool Max(int& max);
	long long Get_max_way();
	long long Get_max_way(KNodeTree* p, long long &h);
	bool Get_node(long long &temp);
	void Way(KNodeTree* p);

	friend istream& operator >> (istream& fi, KBinaryTree& a)
	{
		string s;
		while(fi)
		{
			if(getline(fi, s) && a.num.emplace(stoll(s)).second){ a.Insert(stoll(s));}
		}
		return fi;
	}

	friend std::ostream& operator << (std::ostream& os, const KBinaryTree& t);

	std::ostream& PrintPreO(std::ostream& os) const; //recursive,preorder
	std::ostream& PrintPreO(std::ostream& os, KNodeTree *t) const; //recursive,preorder
	std::ostream& PrintInO(std::ostream& os, KNodeTree *t) const; //recursive,inorder
  
friend
	std::ostream& operator << (std::ostream& os, const KBinaryTree& t);

private:
	void Erase(KNodeTree *&t);
	bool Leaf(KNodeTree* t);
	bool OneEdge(KNodeTree* t);
	bool IsPreLeaf(KNodeTree* t);
	KNodeTree *GetVsLeaf(KNodeTree* t);
	bool HasOnlyOneSonAndThisLeaf(KNodeTree* t);
};
#endif// end of BinaryTreeH

//------------------------------------------------------------------------------
struct KInfo
{
	explicit KInfo(KNodeTree* p, bool b = true, int h = 0, int c = 0) : p(p), b(b), h(h), c(c) {}

	KNodeTree* p;
	bool b;
	int h;
	int c;
}; 

//------------------------------------------------------------------------------
long long KBinaryTree::Get_max_way()
{
	long long h;
	return Get_max_way(pTree, h);
}

//------------------------------------------------------------------------------
long long KBinaryTree::Get_max_way(KNodeTree* p, long long &h)
{
	long long way = 0;
	if(p == nullptr){ h = 0;}
	else if(p->IsLeaf()){ h = 0; p->leaf = 1;}
	else
	{
		long long hr, hl;
		Get_max_way(p->right, hr);
		Get_max_way(p->left, hl);
		h = 1 + max(hl, hr);
		way = (p->right == nullptr ? 0 : 1) + (p->left == nullptr ? 0 : 1) + hl + hr;
		if(way > max_of_ways){ max_of_ways = way;}
		p->SetWay(way); p->hight = h;
		if(p->left == nullptr && p->right != nullptr){ p->leaf = p->right->leaf;}
		else if(p->left != nullptr && p->right == nullptr){ p->leaf = p->left->leaf;}
		else{ p->leaf = p->right->hight == p->left->hight ? p->left->leaf + p->right->leaf : (p->right->hight > p->left->hight ? p->right->leaf : p->left->leaf);}
	}
	return way;
}

//------------------------------------------------------------------------------
bool KBinaryTree::Get_node(long long &temp)
{
	Way(pTree);
	if(!vec.empty()){ sort(vec.begin(), vec.end()); temp = vec[0]; return true;}
	return false;
}

//------------------------------------------------------------------------------
void KBinaryTree::Way(KNodeTree* p)
{
	if(p) 
    { 
        if(p->way == max_of_ways) 
        { 
            if (p->left != nullptr && p->right != nullptr) 
            { 
                p->b = p->left->leaf * p->right->leaf; 
            } 
            else if (p->left != nullptr && p->right == nullptr) 
            { 
                p->b = p->left->leaf * 1; 
            } 
            else if (p->left == nullptr && p->right != nullptr) 
            { 
                p->b = p->right->leaf * 1; 
            } 
        } 
 
        if(p->left != nullptr && p->right != nullptr) 
        { 
            if(p->left->hight > p->right->hight) 
            { 
                p->left->a = p->a + p->b; 
                p->right->a = p->b; 
            } 
            else if(p->left->hight < p->right->hight) 
            { 
                p->left->a = p->b; 
                p->right->a = p->a + p->b; 
            } 
            else 
            { 
                p->left->a = p->b + p->left->leaf * (p->a / p->leaf); 
                p->right->a = p->b + p->right->leaf * (p->a / p->leaf); 
            } 
        } 
        else if(p->left == nullptr && p->right != nullptr) 
        { 
            p->right->a = p->a + p->b; 
        } 
        else if(p->left != nullptr && p->right == nullptr) 
        { 
            p->left->a = p->a + p->b; 
        }   
		if((p->a + p->b) != 0 && (p->a + p->b) % 2 == 0) 
        { 
            vec.push_back(p->info); 
        }
        Way(p->left); 
        Way(p->right); 
    }
}

//------------------------------------------------------------------------------
void KBinaryTree::Insert(long long info)
{ 
	KNodeTree **pp = &pTree, *p = pTree;
	while(p)
	{
		if(info < p->info){ pp = &p->left; p = p->left;}
		else{ pp = &p->right; p = p->right;}
	}
	*pp = new KNodeTree (info);
	num.emplace(info);
}

//------------------------------------------------------------------------------
inline bool KBinaryTree::IsTwoSons(KNodeTree *p)
{
	return (p->left && p->right) ? true : false; 
}

//------------------------------------------------------------------------------
inline void KBinaryTree::DeleteLE1Sons(KNodeTree *p, KNodeTree **pp)
{ 
	if(p->left) *pp = p->left; 
	else *pp = p->right;
	delete p;
}

//------------------------------------------------------------------------------
bool KBinaryTree::Delete(long long info)
{
	KNodeTree **pp = &pTree, *p = pTree;
	while(p)
	{ 
		if(info < p->info){ pp = &p->left; p = p->left;}
		else if(info > p->info){ pp = &p->right; p = p->right;}
		else break;
	}
	if(p == nullptr)
		return false;
	if(!IsTwoSons(p))
	{
		DeleteLE1Sons(p, pp);
	}
	else
	{
		pp = &p->right;
		KNodeTree *p1 = p->right;
		while(p1->left){ pp = &p1->left; p1 = p1->left;}
		p->info = p1->info;
		DeleteLE1Sons(p1, pp);
	}
	return true;
}

//------------------------------------------------------------------------------
bool KBinaryTree::Leaf(KNodeTree *t)
{ 
	if(t)
		if(t->left == 0 && t->right == 0) return true;
	return false;
}

//------------------------------------------------------------------------------
bool KBinaryTree::OneEdge(KNodeTree *t)
{ 
	if(t)
		if((t->left == 0 && Leaf(t->right)) || (t->right == 0 && Leaf(t->left)))
			return true;
	return false;
}

//------------------------------------------------------------------------------
bool KBinaryTree::HasOnlyOneSonAndThisLeaf(KNodeTree *t)
{
	if(t) return (t->left == 0 && (t->right && t->right->IsLeaf())) || (t->right == 0 && (t->left && t->left->IsLeaf()));
	else return false;
}

//------------------------------------------------------------------------------
std::ostream& operator << (std::ostream& os, const KBinaryTree& t)
{ 
	return t.PrintInO(os, t.pTree); 
}

//------------------------------------------------------------------------------
std::ostream& KBinaryTree::PrintPreO(std::ostream& os) const
{ 
	return PrintPreO(os, pTree);
}

//------------------------------------------------------------------------------
std::ostream& KBinaryTree::PrintPreO(std::ostream& os, KNodeTree *t) const
{ 
	if(t){ os << t->info << endl; PrintPreO(os, t->left); PrintPreO(os, t->right);}
	return os;
}

//------------------------------------------------------------------------------
std::ostream& KBinaryTree::PrintInO(std::ostream& os, KNodeTree *t) const
{ 
	if(t){ PrintInO(os, t->left); os << t->info << " "; PrintInO(os, t->right);}
	return os;
}

//------------------------------------------------------------------------------
void KBinaryTree::Erase(KNodeTree *&t)
{ 
	if(t){ Erase (t->left); Erase(t->right); delete t;}  
}

//------------------------------------------------------------------------------
bool KBinaryTree::IsPreLeaf(KNodeTree *t)
{
	return (t->left && t->left->IsLeaf()) || (t->right && t->right->IsLeaf());
}

//------------------------------------------------------------------------------
KNodeTree* KBinaryTree::GetVsLeaf(KNodeTree *t)
{
	if(t->left && t->left->IsLeaf())
		return t->right;
	else
		return t->left;
}

//------------------------------------------------------------------------------
long long KBinaryTree::Sum()
{
	return Sum(pTree);
}

//------------------------------------------------------------------------------
long long KBinaryTree::Sum(KNodeTree *p)
{
	if(!p) return 0;
	else return p->info + Sum(p->left) + Sum(p->right);
}

//------------------------------------------------------------------------------
int KBinaryTree::SumBySet()
{
	int sum = 0;
	for(auto x : num){ sum += x;}
	return sum;
}

//------------------------------------------------------------------------------
bool KBinaryTree::Max(int& max)
{
	KNodeTree *p = pTree;
	if(p)
	{  
		while(p->right)
			p = p->right;
		max = p->info;
		return true;
	}
	else return false;
}


int main()
{
	//0.0----------------------------------
	long long temp;
	KBinaryTree t;
	ifstream fi("tst.in", ios::out);
	ofstream out("tst.out");
	fi >> t; t.Get_max_way(); 
	if(t.Get_node(temp)){ t.Delete(temp);}
	t.PrintPreO(out);
	out.close();
	fi.close();
}
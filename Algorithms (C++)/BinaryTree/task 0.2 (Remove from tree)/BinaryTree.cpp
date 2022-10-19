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
#include <set>

using namespace std;

struct KNodeTree
{ 
    explicit KNodeTree(int _info = 0, KNodeTree *_left = 0, KNodeTree *_right = 0) : info(_info), left(_left), right(_right) {}

    bool IsLeaf() { return left == 0 && right == 0;}

    int info;
    KNodeTree *left;
    KNodeTree *right;
};


class KBinaryTree
{ 
public:
    KBinaryTree() : pTree(nullptr) {}
    ~KBinaryTree() { Erase(pTree);}

    int Len() { return Len(pTree);}

    int Len(KNodeTree *t)
    {
        if(t == nullptr) return 0;
        else{ return 1 + std::max(Len(t->left), Len(t->right));}    
    }

    void Insert(int info);
    inline bool IsTwoSons(KNodeTree *p);
    inline void DeleteLE1Sons(KNodeTree *p, KNodeTree **pp);
    bool Delete(int info);
    long long Sum();
    long long Sum(KNodeTree *p);
    int SumBySet();
    bool Max(int& max);
    KNodeTree* Find(int info){ return 0;}

    friend istream& operator >> (istream& fi, KBinaryTree& a)
    {
        string s;
        while(fi)
        {
            if(getline(fi, s) && a.num.emplace(stoi(s)).second){ a.Insert(stoi(s));}
        }
        return fi;
    }

    friend std::ostream& operator << (std::ostream& os, const KBinaryTree& t);

    std::ostream& PrintPreO (std::ostream& os) const; //recursive,preorder
    std::ostream& PrintPreO (std::ostream& os, KNodeTree *t) const; //recursive,preorder
    std::ostream& PrintInO  (std::ostream& os, KNodeTree *t) const; //recursive,inorder
  
friend
    std::ostream& operator << (std::ostream& os, const KBinaryTree& t);

private:
    void Erase (KNodeTree *&t);
    bool Leaf(KNodeTree *t);
    bool OneEdge(KNodeTree *t);
    bool IsPreLeaf(KNodeTree *t);
    KNodeTree *GetVsLeaf(KNodeTree *t);
    bool HasOnlyOneSonAndThisLeaf(KNodeTree *t);

private:
    KNodeTree *pTree;
    std::set<int> num;
};
#endif// end of BinaryTreeH

//------------------------------------------------------------------------------
struct KInfo
{
    explicit KInfo(KNodeTree *p, bool b = true, int h = 0, int c = 0) : p(p), b(b), h(h), c(c) {}

    KNodeTree * p;
    bool b;
    int h;
    int c;
}; 

//------------------------------------------------------------------------------
void KBinaryTree::Insert(int info)
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
    if(p->left){ *pp = p->left;} 
    else{ *pp = p->right;}
    delete p;
}

//------------------------------------------------------------------------------
bool KBinaryTree::Delete(int info)
{
    KNodeTree **pp = &pTree, *p = pTree;
    while(p)
    { 
        if(info < p->info){ pp = &p->left; p = p->left;}
        else if(info > p->info){ pp = &p->right; p = p->right;}
        else break;
    }
    if(p == nullptr){ return false;}
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
    if(t){ if(t->left == 0 && t->right == 0){ return true;}}
    return false;
}

//------------------------------------------------------------------------------
bool KBinaryTree::OneEdge(KNodeTree *t)
{ 
    if(t)
    {  
        if((t->left == 0 && Leaf(t->right)) || (t->right == 0 && Leaf(t->left))){ return true;}
    }
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
    if(t->left && t->left->IsLeaf()){ return t->right;}
    else{ return t->left;}
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
        while(p->right){ p = p->right;}
        max = p->info;
        return true;
    }
    else return false;
}

int main()
{
    //0.2----------------------------------
    int x;
    string s;
    KBinaryTree t;
    ifstream fi("input.txt", ios::out);
    ofstream out("output.txt");
    if(getline(fi, s)){ x = stoi(s);} getline(fi, s);
    fi >> t;
    t.Delete(x);
    t.PrintPreO(out);
    out.close();
    fi.close();
}
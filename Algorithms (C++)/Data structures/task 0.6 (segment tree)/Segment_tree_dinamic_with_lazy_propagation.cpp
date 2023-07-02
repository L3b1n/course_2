#include <memory>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <algorithm>

template <typename M, typename O, typename M::T (*act)(typename M::T, typename O::T)>
class DynamicLazySegmentTree 
{
    using T = typename M::T;
    using E = typename O::T;

public:
    DynamicLazySegmentTree() = default;
    explicit DynamicLazySegmentTree(long long n) : root(std::make_unique<Node>()) 
    {
        size = 1;
        while(size < n){ size <<= 1;}
    }

    T operator[] (long long k) const { return fold(k, k + 1);}

    void update(long long l, long long r, const E& x) const { update(l, r + 1, x, root, 0, size);}
    
    void add(long long l, long long r, const E& x) const { add(l, r + 1, x, root, 0, size);}

    T fold(long long l, long long r) const { return fold(l, r + 1, root, 0, size);}

    void Print() const { Print(root);}

private:
    struct Node;
    using node_ptr = std::unique_ptr<Node>;

    struct Node 
    {
        T val;
        E lazy;
        std::unique_ptr<Node> left, right;
        Node() : val(M::id()), lazy(O::id()), left(nullptr), right(nullptr) {}
    };

    const node_ptr root;
    long long size;

    void Print(const node_ptr& n) const
    {
        std::cout << n->val << " ";
        if(n->left){ Print(n->left);}
        if(n->right){ Print(n->right);}
    }

    void push(const node_ptr& n) const 
    {
        if(n->lazy == O::id()){ return;}
        if(!n->left){ n->left = std::make_unique<Node>();}
        n->left->lazy = O::op(n->left->lazy, n->lazy);
        if(!n->right){ n->right = std::make_unique<Node>();}
        n->right->lazy = O::op(n->right->lazy, n->lazy);
        n->val = act(n->val, n->lazy);
        n->lazy = O::id();
    }

    void update(long long a, long long b, const E& x, const node_ptr& n, long long l, long long r) const 
    {
        push(n);
        if(r <= a || b <= l){ return;}
        if(a <= l && r <= b) 
        {
            n->lazy = O::op(n->lazy, x);
            push(n);
            return;
        }
        long long m = (l + r) >> 1;
        if(!n->left){ n->left = std::make_unique<Node>();}
        update(a, b, x, n->left, l, m);
        if(!n->right){ n->right = std::make_unique<Node>();}
        update(a, b, x, n->right, m, r);
        n->val = M::op(n->left->val, n->right->val);
    }
    
    void add(long long a, long long b, const E& x, const node_ptr& n, long long l, long long r) const 
    {
        push(n);
        if(r <= a || b <= l){ return;}
        if(a <= l && r <= b) 
        {
            n->lazy = O::op(n->lazy, x);
            push(n);
            return;
        }
        long long m = (l + r) >> 1;
        if(!n->left){ n->left = std::make_unique<Node>();}
        add(a, b, x, n->left, l, m);
        if(!n->right){ n->right = std::make_unique<Node>();}
        add(a, b, x, n->right, m, r);
        n->val = M::op(n->left->val, n->right->val);
    }

    T fold(long long a, long long b, const node_ptr& n, long long l, long long r) const 
    {
        push(n);
        if(r <= a || b <= l){ return M::id();}
        if(a <= l && r <= b){ return n->val;}
        long long m = (l + r) >> 1;
        T vl = n->left ? fold(a, b, n->left, l, m) : M::id();
        T vr = n->right ? fold(a, b, n->right, m, r) : M::id();
        return M::op(vl, vr);
    }
};

struct Sum 
{
    using T = long long;
    static T id() { return 0;}
    static T op(T a, T b) { return a + b;}
};

struct OSum 
{
    using T = long long;
    static T id() { return 0;}
    static T op(T a, T b) { return b;}
};

Sum::T actSum(Sum::T a, OSum::T b) { return b;}

struct Min 
{
    using T = long long;
    static T id() { return (1u << 31) - 1;}
    static T op(T a, T b) { return std::min(a, b);}
};

struct OMin 
{
    using T = long long;
    static T id() { return (1u << 31) - 1;}
    static T op(T a, T b) { return b;}
};

Min::T actMin(Min::T a, OMin::T b) { return b;}

struct Max 
{
    using T = long long;
    static T id() { return -(1 << 31) + 1;}
    static T op(T a, T b) { return std::max(a, b);}
};

struct OMax 
{
    using T = long long;
    static T id() { return -(1 << 31) + 1;}
    static T op(T a, T b) { return b;}
};

Max::T actMax(Max::T a, OMax::T b) { return b;}

int main()
{
    freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    std::ios_base::sync_with_stdio(0);
    std::cin.tie(0);
    std::cout.tie(0);
    int request;
    long long n;
    std::cin >> n;
    DynamicLazySegmentTree<Sum, OSum, actSum> sum(n);
    DynamicLazySegmentTree<Min, OMin, actMin> min(n);
    DynamicLazySegmentTree<Max, OMax, actMax> max(n);
    while(std::cin >> request)
    {
        if(request == 0){ break;}
        if(request == 1)
        {
            int index, data;
            std::cin >> index >> data;
            sum.update(index, index, data);
            min.update(index, index, data);
            max.update(index, index, data);
        }
        else if(request == 2)
        {
            int left, right, data;
            std::cin >> left >> right >> data;
            sum.add(left, right, data);
            min.add(left, right, data);
            max.add(left, right, data);
        }
        else if(request == 3)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << sum.fold(left, right) << "\n";
        }
        else if(request == 4)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << min.fold(left, right) << "\n";
        }
        else if(request == 5)
        {
            int left, right;
            std::cin >> left >> right;
            std::cout << max.fold(left, right) << "\n";
        }
    }
    sum.Print();
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
#include <sstream>

using namespace std;

typedef int int128_t __attribute__((mode(TI)));

ifstream in("input.txt", ios::out);
ofstream out("output.txt");

namespace dma
{
    unsigned int modulus()
    {
        return 10000;
    }

    template<typename T1, typename T2> 
    auto pow(const T1& x, const T2& pow)
    {
        if(pow == 0){ return x.Single();}
        if(pow % 2 == 0){ return dma::pow(x * x, pow / 2);}
        else{ return x * dma::pow(x, pow - 1);}
    }
}

class my_long 
{
public:
    my_long();
    my_long(std::string);
    my_long(signed int);
    my_long(unsigned int);
    my_long(signed long long);
    my_long(unsigned long long);
 
    friend const my_long operator + (my_long, const my_long&);
    friend const my_long operator - (my_long, const my_long&);
    friend const my_long operator / (const my_long&, const my_long&);
    friend const my_long operator * (const my_long&, const my_long&);
    friend const my_long operator % (const my_long&, const my_long&);
 
    friend bool operator == (const my_long&, const my_long&);
    friend inline bool operator < (const my_long&, const my_long&);
    friend inline bool operator <= (const my_long&, const my_long&);
 
    my_long& operator += (const my_long&);
    my_long& operator -= (const my_long&);
 
    inline const my_long operator + () const;
    inline const my_long operator - () const;

    friend std::ostream& operator << (std::ostream&, const my_long&);
    operator std::string() const;
 
private:
    static const int BASE = 1000000000; 
    std::vector<int> value;
    bool sign;
 
    void _remove_leading_zeros();
    void _shift_right();
};

//------------------------------inline-----------------------------------
inline my_long& my_long::operator += (const my_long& value) { return *this = (*this + value);}

inline my_long& my_long::operator -= (const my_long& value) { return *this = (*this - value);}

inline const my_long my_long::operator + () const { return my_long(*this);}
 
inline const my_long my_long::operator - () const { my_long copy(*this); copy.sign = !copy.sign; return copy;}
  
inline bool operator <= (const my_long& number1, const my_long& number2) { return (number1 < number2 || number1 == number2);}
//---------------------------end_of_inline-----------------------------------
//----------------------------constructor-----------------------------------
my_long::my_long() 
{
    this->sign = false;
}

my_long::operator std::string() const 
{
    std::stringstream ss;
    ss << *this;
    return ss.str();
}
 
my_long::my_long(signed int i) 
{
    if(i < 0){ this->sign = true;}
    else{ this->sign = false;}
    this->value.push_back(std::abs(i) % my_long::BASE);
    i /= my_long::BASE;
    if(i != 0){ this->value.push_back(std::abs(i));}
}
 
my_long::my_long(unsigned int i) 
{
    this->value.push_back(i % my_long::BASE);
    i /= my_long::BASE;
    if(i != 0){ this->value.push_back(i);}
}
 
my_long::my_long(signed long long l) 
{
    if(l < 0){ this->sign = true; l = -l;}
    else{ this->sign = false;}
    this->value.push_back(l % my_long::BASE);
    l /= my_long::BASE;
    while(l != 0)
    {
        this->value.push_back(l % my_long::BASE);
        l /= my_long::BASE;
    }
}
 
my_long::my_long(unsigned long long l) 
{
    this->sign = false;
    this->value.push_back(l % my_long::BASE);
    l /= my_long::BASE;
    while(l != 0)
    {
        this->value.push_back(l % my_long::BASE);
        l /= my_long::BASE;
    }
}
 
my_long::my_long(std::string str) 
{
    if (str.length() == 0){ this->sign = false;}
    else 
    {
        if(str[0] == '-'){ str = str.substr(1); this->sign = true;}
        else{ this->sign = false;}

        for(long long i = str.length(); i > 0; i -= 9) 
        {
            if(i < 9){ this->value.push_back(atoi(str.substr(0, i).c_str()));}
            else{ this->value.push_back(atoi(str.substr(i - 9, 9).c_str()));}
        }
        this->_remove_leading_zeros();
    }
}
//------------------------end_of_constructor-----------------------------------
 
void my_long::_remove_leading_zeros() 
{
    while(this->value.size() > 1 && this->value.back() == 0){ this->value.pop_back();}
    if(this->value.size() == 1 && this->value[0] == 0){ this->sign = false;}
}
 
std::ostream& operator << (std::ostream& fo, const my_long& bi) 
{
    if(bi.value.empty()){ fo << 0;}
    else 
    {
        if(bi.sign){ fo << '-';}
        fo << bi.value.back();
        char old_fill = fo.fill('0');
        for(long long i = static_cast<long long>(bi.value.size()) - 2; i >= 0; --i){ fo << setw(9) << bi.value[i];}
        fo.fill(old_fill);
    }
    return fo;
}

bool operator == (const my_long& number1, const my_long& number2) 
{
    if(number1.sign != number2.sign){ return 0;}
    if(number1.value.empty()) 
    {
        if(number2.value.empty() || (number2.value.size() == 1 && number2.value[0] == 0)){ return 1;}
        else{ return 0;}
    }
    if(number2.value.empty()) 
    {
        if(number1.value.size() == 1 && number1.value[0] == 0){ return 1;}
        else{ return 0;}
    }
    if(number1.value.size() != number2.value.size()){ return 0;}
    for(size_t i = 0; i < number1.value.size(); ++i){ if(number1.value[i] != number2.value[i]){ return 0;}}
    return 1;
}
 
bool operator < ( const my_long& number1, const my_long& number2) 
{
    if(number1 == number2){ return 0;}
    if(number1.sign) 
    {
        if(number2.sign){ return ((-number2) < (-number1));}
        else{ return 1;}
    }
    else if(number2.sign){ return 0;}
    else 
    {
        if(number1.value.size() != number2.value.size()){ return number1.value.size() < number2.value.size();}
        else 
        {
            for(long long i = number1.value.size() - 1; i >= 0; --i){ if(number1.value[i] != number2.value[i]){ return number1.value[i] < number2.value[i];}}
            return 0;
        }
    }
}
 
const my_long operator + (my_long number1, const my_long& number2) 
{
    if(number1.sign) 
    {
        if(number2.sign){ return -(-number1 + (-number2));}
        else{ return number2 - (-number1);}
    }
    else if(number2.sign){ return number1 - (-number2);}
    int carry = 0;
    for(size_t i = 0; i < std::max(number1.value.size(), number2.value.size()) || carry != 0; ++i) 
    {
        if(i == number1.value.size()){ number1.value.push_back(0);}
        number1.value[i] += carry + (i < number2.value.size() ? number2.value[i] : 0);
        carry = number1.value[i] >= my_long::BASE;
        if(carry != 0){ number1.value[i] -= my_long::BASE;}
    }
    return number1;
}
 
const my_long operator - (my_long number1, const my_long& number2) 
{
    if(number2.sign){ return number1 + (-number2);}
    else if(number1.sign){ return -(-number1 + number2);}
    else if(number1 < number2){ return -(number2 - number1);}
    int carry = 0;
    for(size_t i = 0; i < number2.value.size() || carry != 0; ++i) 
    {
        number1.value[i] -= carry + (i < number2.value.size() ? number2.value[i] : 0);
        carry = number1.value[i] < 0;
        if(carry != 0){ number1.value[i] += my_long::BASE;}
    }
    number1._remove_leading_zeros();
    return number1;
}
 
const my_long operator * (const my_long& number1, const my_long& number2) 
{
    my_long result;
    result.value.resize(number1.value.size() + number2.value.size());
    for(size_t i = 0; i < number1.value.size(); ++i) 
    {
        int carry = 0;
        for(size_t j = 0; j < number2.value.size() || carry != 0; ++j) 
        {
            long long cur = result.value[i + j] + number1.value[i] * 1LL * (j < number2.value.size() ? number2.value[j] : 0) + carry;
            result.value[i + j] = static_cast<int>(cur % my_long::BASE);
            carry = static_cast<int>(cur / my_long::BASE);
        }
    }
    result.sign = number1.sign != number2.sign;
    result._remove_leading_zeros();
    return result;
}
 
const my_long operator / (const my_long& number1, const my_long& number2) 
{
    if(number2 == 0){ return 0;}
    my_long b = number2;
    b.sign = false;
    my_long result, current;
    result.value.resize(number1.value.size());
    for(long long i = static_cast<long long>(number1.value.size()) - 1; i >= 0; --i) 
    {
        current._shift_right();
        current.value[0] = number1.value[i];
        current._remove_leading_zeros();
        int x = 0, l = 0, r = my_long::BASE;
        while(l <= r) 
        {
            int m = (l + r) / 2;
            my_long t = b * m;
            if(t <= current){ x = m; l = m + 1;}
            else{ r = m - 1;}
        }
        result.value[i] = x;
        current = current - b * x;
    }
    result.sign = number1.sign != number2.sign;
    result._remove_leading_zeros();
    return result;
}
 
const my_long operator % (const my_long& number1, const my_long& number2) 
{
    my_long result = number1 - (number1 / number2) * number2;
    if(result.sign){ result += number2;}
    return result;
}

void my_long::_shift_right() 
{
    if(this->value.size() == 0){ this->value.push_back(0); return;}
    this->value.push_back(this->value[this->value.size() - 1]);
    for(size_t i = this->value.size() - 2; i > 0; --i){ this->value[i] = this->value[i - 1];}
    this->value[0] = 0;
}


class Matrix
{
public:
    Matrix() : n(0), size(0ll), sum(0ll) {}
    Matrix(const long long _size, const long long temp = 1ll) : n(0), size(_size), sum(0ll)
    {
        num.resize(size); for(int i = 0; i < size; i++){ num[i].swap(*(new std::vector<long long>(size, 0ll))); num[i][i] = temp;}
    }
    
    Matrix Single() const
    {
        return Matrix(size);
    }

    Matrix operator * (const Matrix& a) const
    {
        long long i = 0ll;
        Matrix temp(a.size, 0ll);
        std::vector<std::vector<long long>> helper(a.size, std::vector<long long>(a.size, 0ll));
        for_each(helper.begin(), helper.end(), 
            [&i, &a](std::vector<long long>& vec){
                long long j = 0ll;
                for_each(vec.begin(), vec.end(), 
                    [&i, &j, &a](long long& x){
                        x = a.num[j++][i];
                    }
                );
                i++;
            }
        );
        i = 0ll;
        for_each(temp.num.begin(), temp.num.end(), 
            [this, &a, &temp, &i, &helper](std::vector<long long>& vec){
                long long j = 0ll;
                for_each(vec.begin(), vec.end(), 
                    [this, &a, &temp, &i, &j, &helper](long long& x){
                        for(int k = 0; k < a.size; k++)
                        {
                            x = (x % dma::modulus() + (this->num[i][k] % dma::modulus() * helper[j][k] % dma::modulus()) % dma::modulus()) % dma::modulus();
                        }
                        j++;
                    }
                );
                i++;
            }
        );
        return temp;
    }
    
    Matrix Sum()
    {
        long long temp = 0ll;
        for(int i = 1; i <= size; i++)
        {
            temp = (temp % dma::modulus() + num[i - 1][i - 1] % dma::modulus()) % dma::modulus();
            for(int j = i; j < size; j++)
            {
                sum = (sum % dma::modulus() + num[i - 1][j] % dma::modulus()) % dma::modulus();
            }
        }
        sum = ((sum % dma::modulus() * 2 % dma::modulus()) % dma::modulus() + temp % dma::modulus()) % dma::modulus();
        return *this;
    }

    void Solution()
    {
        out << dma::pow(*this, m - 1ll).Sum();
    }

    bool Bit_check(const long long& temp, const int& index)
    {
        if(index < 0){ return 0;}
        else if((temp & (1 << index)) == 0){ return 0;}
        else{ return 1;}
    }

    bool Condition(const long long& first_ind, const long long& second_ind)
    {
        for(long long i = 0; i < n - 1; i++)
        {
            if((Bit_check(first_ind, i) == 1 && Bit_check(first_ind, i + 1) == 1 && Bit_check(second_ind, i) == 1 && Bit_check(second_ind, i + 1) == 1) ||
            (Bit_check(first_ind, i) == 0 && Bit_check(first_ind, i + 1) == 0 && Bit_check(second_ind, i) == 0 && Bit_check(second_ind, i + 1) == 0)){ return 0;}
        }
        return 1;
    }

    friend std::istream& operator >> (std::istream& fi, Matrix& a)
    {
        string temp;
        long long i = 0ll;
        fi >> temp >> a.n;
        my_long m1(temp); a.m += m1;
        a.size = 1 << a.n;
        a.num.swap(*(new std::vector<std::vector<long long>>(a.size, std::vector<long long>(a.size, 0ll))));
        for_each(a.num.begin(), a.num.end(), 
            [&a, &i](std::vector<long long>& vec){ 
                long long j = 0ll;
                for_each(vec.begin(), vec.end(), 
                    [&a, &i, &j](long long& temp){
                        temp = (a.Condition(i, j++) == 1 ? 1ll : 0ll);
                    }
                );
                i++;
            }
        );
        return fi;
    }

    friend std::ostream& operator << (std::ostream& fo, Matrix a)
    {
        fo << a.sum << endl;
        for_each(a.num.begin(), a.num.end(), [&fo](const std::vector<long long>& vec){ std::copy(vec.begin(), vec.end(), std::ostream_iterator<long long>(fo, "  ")); fo << endl;});
        return fo;
    }

private:
    int n;
    my_long m;
    long long size;
    long long sum;
    std::vector<std::vector<long long>> num;
};


int main()
{
    Matrix a;
    in >> a;
    a.Solution();
    return 0;
}
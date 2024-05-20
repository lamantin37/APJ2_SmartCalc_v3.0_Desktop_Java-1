#ifndef CALCULATOR_H
#define CALCULATOR_H

#include <string>
#include <unordered_map>
#include <variant>
#include <functional>
#include <vector>

class Calculator {
 public:
    Calculator();
    Calculator(std::string str, const double x);
    Calculator(std::string str);
    ~Calculator();

    __declspec(dllexport) void setExpression(std::string str);
    __declspec(dllexport) void setVariable(double var);
    __declspec(dllexport) void clear();
    __declspec(dllexport) double eval();

 private:
    void parse_args();
    int sort_stack();
    double count_result();

    std::string expression;
    double variable{};
    std::unordered_map<std::string, int> operands_map;
    std::unordered_map<std::string, std::variant<std::function<double(double, double)>, std::function<double(double)>>> function_map;
    std::vector<std::variant<std::string, double>> tmp_mixed_lexeme_list;
};

extern "C" {
    __declspec(dllexport) void Calculator_setExpression(Calculator* calc, const char* str);
    __declspec(dllexport) void Calculator_setVariable(Calculator* calc, double var);
    __declspec(dllexport) void Calculator_clear(Calculator* calc);
    __declspec(dllexport) double Calculator_eval(Calculator* calc);
}

#endif

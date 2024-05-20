#include "smartcalcv3_core.h"
#include <cmath>
#include <stdexcept>
#include <algorithm>

Calculator::Calculator() {
    operands_map = {
        {"cos", 5}, {"sin", 5}, {"tan", 5}, {"acos", 5}, {"asin", 5},
        {"atan", 5}, {"sqrt", 5}, {"ln", 5}, {"log", 5}, {"mod", 5},
        {"+", 2}, {"-", 2}, {"*", 3}, {"/", 3}, {"^", 4},
        {"(", 1}, {")", 1}
    };

    function_map = {
        {"cos", [](double x) { return std::cos(x); }},
        {"sin", [](double x) { return std::sin(x); }},
        {"tan", [](double x) { return std::tan(x); }},
        {"acos", [](double x) { return std::acos(x); }},
        {"asin", [](double x) { return std::asin(x); }},
        {"atan", [](double x) { return std::atan(x); }},
        {"sqrt", [](double x) { return std::sqrt(x); }},
        {"ln", [](double x) { return std::log(x); }},
        {"log", [](double x) { return std::log10(x); }},
        {"mod", [](double x, double y) { return std::fmod(x, y); }},
        {"+", [](double x, double y) { return x + y; }},
        {"-", [](double x, double y) { return x - y; }},
        {"*", [](double x, double y) { return x * y; }},
        {"/", [](double x, double y) { return x / y; }},
        {"^", [](double x, double y) { return std::pow(x, y); }}
    };
}

Calculator::Calculator(std::string str, const double x) : expression(str), variable(x) {
    Calculator();
}

Calculator::Calculator(std::string str) : expression(str) {
    Calculator();
}

Calculator::~Calculator() {}

void Calculator::setExpression(std::string str) {
    expression = str;
}

void Calculator::setVariable(double var) {
    variable = var;
}

void Calculator::clear() {
    tmp_mixed_lexeme_list.clear();
}

double Calculator::eval() {
    parse_args();
    sort_stack();
    return count_result();
}

void Calculator::parse_args() {
    size_t index = 0;
    int unary_multiplayer = 1;
    while (index < expression.size()) {
        auto operand = std::find_if(
            operands_map.begin(), operands_map.end(), [&](const auto& op) {
                return expression.compare(index, op.first.size(), op.first) == 0;
            });
        if (operand != operands_map.end()) {
            if ((operand->first == "-" || operand->first == "+") &&
                (index == 0 || expression[index - 1] == '(')) {
                if (operand->first == "-") unary_multiplayer = -1;
            } else {
                tmp_mixed_lexeme_list.push_back(operand->first);
            }
            index += operand->first.size();
        } else if (isdigit(expression[index])) {
            std::size_t num_end;
            tmp_mixed_lexeme_list.push_back(
                stod(expression.substr(index), &num_end) * unary_multiplayer);
            unary_multiplayer *= unary_multiplayer == -1 ? -1 : 1;
            index += num_end;
        } else if (isalpha(expression[index])) {
            tmp_mixed_lexeme_list.push_back(unary_multiplayer == -1 ? "-x" : "x");
            unary_multiplayer *= unary_multiplayer == -1 ? -1 : 1;
            ++index;
        } else {
            ++index;
        }
    }
}

int Calculator::sort_stack() {
    std::vector<std::variant<std::string, double>> value_stack;
    std::vector<std::string> operators_stack;
    for (const auto& item : tmp_mixed_lexeme_list) {
        auto op = std::get_if<std::string>(&item);
        if (!op) {
            value_stack.push_back(std::get<double>(item));
        } else if (*op == "(") {
            operators_stack.push_back(*op);
        } else if (*op == ")") {
            while (!operators_stack.empty() && operators_stack.back() != "(")
                value_stack.push_back(operators_stack.back()),
                    operators_stack.pop_back();
            if (operators_stack.empty() || operators_stack.back() != "(")
                throw std::invalid_argument("bad ')'");
            operators_stack.pop_back();
        } else {
            if (operands_map.find(*op) == operands_map.end()) {
                value_stack.push_back(*op);
                continue;
            }
            int priority = operands_map[*op];
            while (!operators_stack.empty() &&
                operands_map[operators_stack.back()] > priority)
                value_stack.push_back(operators_stack.back()),
                    operators_stack.pop_back();
            operators_stack.push_back(*op);
        }
    }
    if (std::count(operators_stack.begin(), operators_stack.end(), "("))
        throw std::invalid_argument("bad '('");
    value_stack.insert(value_stack.end(), operators_stack.rbegin(),
        operators_stack.rend());
    tmp_mixed_lexeme_list = value_stack;
    return 0;
}

double Calculator::count_result() {
    std::vector<double> values;
    for (const auto& item : tmp_mixed_lexeme_list) {
        if (std::holds_alternative<std::string>(item)) {
            const auto& op = std::get<std::string>(item);
            if (function_map.find(op) == function_map.end()) {
                if (op == "-x") {
                    values.push_back(variable * -1);
                } else {
                    values.push_back(variable);
                }
                continue;
            }
            auto& f = function_map[op];
            if (std::holds_alternative<std::function<double(double)>>(f)) {
                if (values.empty()) throw std::invalid_argument("something wrong 1");
                values.back() =
                    std::get<std::function<double(double)>>(f)(values.back());
            } else if (std::holds_alternative<
                std::function<double(double, double)>>(f)) {
                auto& binaryFunc = std::get<std::function<double(double, double)>>(f);
                if (values.size() < 2)
                    throw std::invalid_argument("something wrong 2");
                double param2 = values.back();
                values.pop_back();
                values.back() = binaryFunc(values.back(), param2);
            }
        } else {
            values.push_back(std::get<double>(item));
        }
    }
    if (values.size() != 1) throw std::invalid_argument("something wrong 3");
    return values.front();
}

#include <iostream>
int main() {
    // Создаем экземпляр калькулятора
    Calculator calc;

    // Устанавливаем выражение для вычисления
    calc.setExpression("2 + 3 * (4 - 1)");

    // Вычисляем результат
    double result = calc.eval();

    // Выводим результат
    std::cout << "Result: " << result << std::endl;

    calc.clear();
    calc.setExpression("1 + 2");

    // Вычисляем результат
    result = calc.eval();

    // Выводим результат
    std::cout << "Result: " << result << std::endl;
    return 0;
}

extern "C" {
    Calculator* Calculator_new() { return new Calculator(); }
    void Calculator_setExpression(Calculator* calc, const char* str) { calc->setExpression(str); }
    void Calculator_setVariable(Calculator* calc, double var) { calc->setVariable(var); }
    void Calculator_clear(Calculator* calc) { calc->clear(); }
    double Calculator_eval(Calculator* calc) { return calc->eval(); }
}

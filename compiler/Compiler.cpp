//
// Created by user on 23.04.2019.
//

#include "Compiler.h"
#include "Stack.h"
#include <string.h>
#include <iostream>
#include <typeinfo>
#include <iomanip>
#include <typeindex>
#include <cmath>

using namespace std;

Compiler::Compiler() {}

Compiler::~Compiler() {}

Operator *Compiler::binary_left_assoc(char c, int precedence) {
    Operation operation;
    operation.symbol = c;
    operation.assoc = Assoc::Left;
    operation.prec = precedence;
    operation.arity = Arity::Binary;
    return new Operator(operation);
}

Operator *Compiler::unary_right_assoc(char c, int precedence) {
    Operation operation;
    operation.symbol = c;
    operation.assoc = Assoc::Right;
    operation.prec = precedence;
    operation.arity = Arity::Unary;
    return new Operator(operation);
}

bool Compiler::isBinary(char ch) {
    if (isdigit(ch)) return true;
    switch (ch) {
        case 'R':
        case 'B':
        case ']':
        case ')':
            return true;
        default:
            return false;
    }
}

bool Compiler::is_digit(char ch) {
    return ((ch) >= '0' && (ch) <= '9');
}

bool Compiler::is_letter(char ch) {
    return (ch >= 'a' && ch <= 'z');
}

void Compiler::printToken(Stack<Token *> *stack) {
    stack->toTop();
    while (stack->curr != nullptr) {
        Token *elem = stack->curr->value;
        Type type = elem->getType();
        switch (type) {
            case Type::Operation: {
                Operator *anOperator = ((Operator *) elem);
                Operation operation = anOperator->getOperation();
                if (operation.arity == Arity::Unary) {
                    if (operation.symbol == '-') {
                        cerr << " " << "m";
                    } else {
                        cerr << " " << "p";
                    }
                } else {
                    cerr << operation.symbol;
                }
            }
                break;
            case Type::Number:
                cerr << " " << ((Number *) (elem))->getValue();
                break;
            case Type::RealVar:
                cerr << " " << ((RealVar *) (elem))->getValue();
                break;
            case Type::BoolVar:
                cerr << " " << ((BoolVar *) (elem))->getValue();
                break;
            case Type::RightParenth:
                cerr << " " << ((RightParenth *) (elem))->toString();
                break;
            case Type::LeftParenth:
                cerr << " " << ((LeftParenth *) (elem))->toString();
                break;
            case Type::Function:
                cerr << " " << ((Function *) (elem))->getFunction();
                break;
        }
        stack->move();
    }

    cerr << "\n";
}

void Compiler::printValue(Stack<Value *> *stack) {
    stack->toTop();
    while (stack->curr != nullptr) {
        Value *elem = stack->curr->value;
        Type type = elem->getType();
        switch (type) {
            case Type::Number: {
                cerr << "Real(" << ((Number *) (elem))->getValue() << ")";
            }
                break;
            case Type::Bool:
                cerr << "Bool(" << ((Bool *) (elem))->value << ")";
                break;
        }
        stack->move();
    }

    cerr << endl;
}

void Compiler::printToken(vector<Token *> &expression) {
    for (auto pos_begin = expression.begin(); pos_begin != expression.end(); ++pos_begin) {
        Type type = (*pos_begin)->getType();
        switch (type) {
            case Type::Operation: {
                Operator *anOperator = ((Operator *) (*pos_begin));
                Operation operation = anOperator->getOperation();
                if (operation.arity == Arity::Unary) {
                    if (operation.symbol == '-') {
                        cerr << " " << "m";
                    } else {
                        cerr << " " << "p";
                    }
                } else {
                    cerr << operation.symbol;
                }
            }
                break;
            case Type::Number:
                cerr << " " << ((Number *) (*pos_begin))->getValue();
                break;
            case Type::RealVar:
                cerr << " " << ((RealVar *) (*pos_begin))->getValue();
                break;
            case Type::BoolVar:
                cerr << " " << ((BoolVar *) (*pos_begin))->getValue();
                break;
            case Type::RightParenth:
                cerr << " " << ((RightParenth *) (*pos_begin))->toString();
                break;
            case Type::LeftParenth:
                cerr << " " << ((LeftParenth *) (*pos_begin))->toString();
                break;
            case Type::Function:
                cerr << " " << ((Function *) (*pos_begin))->getFunction();
                break;
        }
    }
    cerr << "\n";
}

Result<vector<Token *>, Error> *Compiler::lexical_analysis(string expression) {
    Result<vector<Token *>, Error> *result = new Result<vector<Token *>, Error>();
    vector<Token *> queue;

    // set to true if some operand has been seen
    bool is_binary = false;
    // set to true, if function name seen and we expect symbol '(' only
    bool was_function = false;

    for (int it = 0; it < expression.length();) {
        // if the current char is whitespace, just skip
        if (!isspace(expression[it])) {
            char ch = expression[it];
            // we have seen function as the previous token, expect open parentheses only
            if (was_function) {
                if (ch == '(') {
                    was_function = false;
                    queue.push_back(new LeftParenth(Brace::Round));
                } else {
                    result->error = new Error(Error::Kind::Lex, ch);
                }
            } else {
                if (is_digit(ch))
                    queue.push_back(new Number(get_number(expression, it, ch)));
                else if (is_letter(ch)) {
                    Result<Token, Error> *token = get_function(expression, it, ch);
                    if (token->is_ok()) {
                        was_function = true;
                        queue.push_back(token->token);
                    } else {
                        result->error = token->error;
                    }
                } else {
                    switch (ch) {
                        case '+':
                        case '-':
                            if (is_binary) {
                                queue.push_back(binary_left_assoc(ch, 5));
                            } else {
                                queue.push_back(unary_right_assoc(ch, 8));
                            }
                            break;
                        case '*':
                        case '/':
                            queue.push_back(binary_left_assoc(ch, 7));
                            break;
                        case '^':
                            queue.push_back(binary_left_assoc(ch, 6));
                            break;
                        case '<':
                        case '>':
                            queue.push_back(binary_left_assoc(ch, 4));
                            break;
                        case '=':
                        case '#':
                            queue.push_back(binary_left_assoc(ch, 4));
                            break;
                        case '~':
                            queue.push_back(unary_right_assoc(ch, 3));
                            break;
                        case '&':
                            queue.push_back(binary_left_assoc(ch, 2));
                            break;
                        case '!':
                            queue.push_back(binary_left_assoc(ch, 2));
                            break;
                        case 'R':
                            queue.push_back(new RealVar(get_identifier(expression, it, ch)));
                            break;
                        case 'B':
                            queue.push_back(new BoolVar(get_identifier(expression, it, ch)));
                            break;
                        case '[':
                            queue.push_back(new LeftParenth(Brace::Square));
                            break;
                        case ']':
                        case ')':
                            if (ch == ']')
                                queue.push_back(new RightParenth(Brace::Square));
                            else
                                queue.push_back(new RightParenth(Brace::Round));
                            break;
                        default:
                            result->error = new Error(Error::Kind::Lex, ch);
                            return result;
                    }
                }
                // if we have seen an operand, then we expect the binary operator
                is_binary = isBinary(ch);
            }
        }
        it++;
    }

    result->token = new std::vector<Token *>();
    for (auto pos_begin = queue.begin(); pos_begin != queue.end(); ++pos_begin)
        result->token->push_back(*pos_begin);
    return result;
}

float Compiler::to_digit(char ch) {
    return (ch - '0');
}

float Compiler::get_number(string &expr, int &it, char ch) {
    float number = to_digit(ch);

    while ((it + 1) < expr.length() && is_digit(expr[it + 1])) {
        char ch = expr[it + 1];
        number = number * 10. + to_digit(ch);
        it++;
    }
    // maybe there is a fractional part of the number
    if (expr[it + 1] == '.') {
        it++;
    }

    int dec = 1;
    while ((it + 1) < expr.length() && is_digit(expr[it + 1])) {
        char ch = expr[it + 1];
        dec = dec * 10.0;
        number = number + to_digit(ch) / dec;
        it++;
    }
    return number;
}

string Compiler::get_identifier(string &expr, int &it, char ch) {
    string id = "";
    id.push_back(ch);
    while ((it + 1) < expr.length() && isalnum(expr[it + 1])) {
        char ch = expr[it + 1];
        id.push_back(ch);
        it++;
    }
    return id;
}

Result<Token, Error> *Compiler::get_function(string &expr, int &it, char ch) {
    string fun = get_identifier(expr, it, ch);
    Result<Token, Error> *result = new Result<Token, Error>();

    if (fun == "exp" || fun == "ln") {
        result->token = new Function(fun);
    } else {
        result->error = new Error(Error::Kind::Lex, ch);
    }
    return result;
}

Result<vector<Token *>, Error> *Compiler::rpn(vector<Token *> *tokens) {
    Result<vector<Token *>, Error> *result = new Result<vector<Token *>, Error>();
    Stack<Token *> *stack = new Stack<Token *>();
    vector<Token *> output;

    int i = 0;
    for (auto pos_begin = tokens->begin(); pos_begin != tokens->end(); pos_begin++) {
        Token *token = *pos_begin;
        switch (token->getType()) {
            case Type::Number:
            case Type::BoolVar:
            case Type::RealVar:
                output.push_back(token);
                break;
            case Type::LeftParenth:
            case Type::Function:
                stack->push(token);
                break;
            case Type::Operation : {
                bool cond = false;
                Operator *op = (Operator *) token;
                while (!stack->isEmpty()) {
                    auto top = stack->peek();
                    // the top of the stack is not a left parenthesis AND
                    if (top->getType() == Type::LeftParenth)
                        break;
                    switch (top->getType()) {
                        // the top of the stack is a function
                        case Type::Function  :
                            cond = true;
                            break;
                        case Type::Operation : {
                            auto aTop = (Operator *) top;
                            // the top of the stack has greater prec than op
                            if (aTop->getOperation().prec > op->getOperation().prec) {
                                cond = true;
                            } else
                                cond = aTop->getOperation().prec == op->getOperation().prec
                                       && aTop->getOperation().assoc == Assoc::Left;
                        }
                            break;
                        default:
                            cond = false;
                    }
                    if (cond) {
                        // pop operators from the operator stack onto the output queue
                        // we know for sure the stack.pop() doesn't fail here
                        output.push_back(stack->pop());
                    } else {
                        // this operation should remain in the stack, put it back
                        break;
                    }
                }
                stack->push(token);
            }
                break;
            case Type::RightParenth : {
                RightParenth *cur = (RightParenth *) token;
                if (stack->isEmpty()) {
                    // braces are not balanced
                    if (cur->getBrace() == Brace::Square) {
                        result->error = new Error(Error::Kind::Rpn, ']');
                    } else {
                        result->error = new Error(Error::Kind::Rpn, ')');
                    }
                    return result;
                }
                while (!stack->isEmpty()) {
                    Token *top = stack->peek();
                    if (top->getType() == Type::LeftParenth) {
                        LeftParenth *brace = (LeftParenth *) top;
                        if (cur->getBrace() != brace->getBrace()) {
                            if (cur->getBrace() == Brace::Square) {
                                result->error = new Error(Error::Kind::Rpn, ']');
                            } else {
                                result->error = new Error(Error::Kind::Rpn, ')');
                            }
                        }
                        // consume the left paren and break
                        stack->pop();
                        break;
                    } else {
                        // we know for sure the top exists and is not a LeftBracket
                        output.push_back(stack->pop());
                    }
                }
            }
                break;
        }
    }

    // after the loop, if operator stack not empty, pop everything to output queue
    while (!stack->isEmpty()) {
        Token *token = stack->pop();
        if (token->getType() == Type::LeftParenth) {
            break;
        } else {
            output.push_back(token);
        }
    }

    // if stack still is not empty, then braces are not balanced
    if (stack->isEmpty()) {
        result->token = new std::vector<Token *>();
        for (auto pos_begin = output.begin(); pos_begin != output.end(); ++pos_begin)
            result->token->push_back(*pos_begin);
    } else {
        result->error = new Error(Error::Kind::Rpn, ')');
    }
    delete stack;
    // printToken(output);
    return result;
}

Result<Value *, Error> *Compiler::eval(vector<Token *> *tokens, map<string, Value *> &bindings) {
    Result<Value *, Error> *result = new Result<Value *, Error>();
    Stack<Value *> *stack = new Stack<Value *>();

    for (auto pos_begin = tokens->begin(); pos_begin != tokens->end(); pos_begin++) {
        Token *token = (*pos_begin);
        switch (token->getType()) {
            case Type::Number: {
                Number *number = ((Number *) (*pos_begin));
                Double *value = new Double(number->getValue());
                stack->push(value);
                cerr << value->value << "|";
                printValue(stack);
            }
                break;
            case Type::RealVar: {
                RealVar *realVar = ((RealVar *) (*pos_begin));
                auto it = bindings.find(realVar->getValue());
                if (it != bindings.end()) {
                    Double *value = new Double(it->second);
                    stack->push(value);
                    cerr << value->value << "|";
                    printValue(stack);
                } else {
                    std::string message = "Variable not defined: '" + realVar->getValue() + "'";
                    result->error = new Error(Error::Kind::Eval, message);
                }
            }
                break;
            case Type::BoolVar: {
                BoolVar *boolVar = ((BoolVar *) (*pos_begin));
                auto it = bindings.find(boolVar->getValue());
                if (it != bindings.end()) {
                    Bool *value = new Bool(it->second);
                    stack->push(value);
                    cerr << value->value << "|";
                    printValue(stack);
                } else {
                    std::string message = "Variable not defined: '" + boolVar->getValue() + "'";
                    result->error = new Error(Error::Kind::Eval, message);
                }
            }
                break;
            case Type::Operation: {
                Operator *anOperator = ((Operator *) (*pos_begin));
                switch (anOperator->getOperation().arity) {
                    case Arity::Unary: {
                        result = eval_unary_operator(anOperator, stack);
                        printValue(stack);
                    }
                        break;
                    case Arity::Binary: {
                        result = eval_binary_operator(anOperator, stack);
                    }
                        break;
                }
            }
                break;
            case Type::Function: {
                Function *function = ((Function *) (*pos_begin));
                result = eval_function(function, stack);
                cerr << function->getFunction() << "|";
                printValue(stack);
            }
                break;
            default: {
                std::string message = "Unexpected token in RPN: '" + token->toString() + "'";
                result->error = new Error(Error::Kind::Eval, message);
            }
                break;
        }
    }

    // the stack size should be exactly 1
    if (stack->size() == 1) {
        result->token = (Value **) stack->pop();
    } else if (stack->isEmpty()) {
        result->error = new Error(Error::Kind::Eval, "The expression is empty");
    } else {
        result->error = new Error(Error::Kind::Eval, "The expression is not exhaustive");
    }

    delete stack;
    return result;
}

Result<Value *, Error> *Compiler::eval_function(Function *pFunction, Stack<Value *> *stack) {
    auto result = new Result<Value *, Error>();
    if (!stack->isEmpty()) {
        Value *elem = stack->pop();
        if (Type::Number == elem->getType()) {
            string func = pFunction->getFunction();
            Double *val = (Double *) elem;
            if (func.compare("exp") == 0) {
                stack->push(new Double(exp(val->value)));
            } else if (func.compare("ln") == 0) {
                if (val->value > 0.0) {
                    stack->push(new Double(log(val->value)));
                } else {
                    std::string message = "Call function '" + pFunction->getFunction()
                                          + "' is invalid for argument '" + elem->toString() + "'";
                    result->error = new Error(Error::Kind::Eval, message);
                }
            } else {
                std::string message = "Not enough values to call function: '" + pFunction->getFunction() + "'";
                result->error = new Error(Error::Kind::Eval, message);
            }
        } else {
            std::string message = "Type error, expected real value for function '" + pFunction->getFunction()
                                  + "', found '" + elem->toString() + "'";
            result->error = new Error(Error::Kind::Eval, message);
        }
    } else {
        std::string message = "Not enough values to call function: '" + pFunction->getFunction() + "'";
        result->error = new Error(Error::Kind::Eval, message);
    }
    return result;
}

Result<Value *, Error> *Compiler::eval_unary_operator(Operator *pOperator, Stack<Value *> *stack) {
    auto result = new Result<Value *, Error>();
    if (!stack->isEmpty()) {
        Value *value = stack->pop();
        if (value->getType() == Type::Number) {
            auto aDouble = (Double *) value;
            switch (pOperator->getOperation().symbol) {
                case '-': {
                    stack->push(new Double(-aDouble->value));
                    //   cerr <<"m |";
                }
                    break;
                case '+': {
                    stack->push(aDouble);
                    // cerr <<"p |";
                }
                    break;
                default: {
                    std::string message = "Unknown real unary operator: ";
                    message.push_back(pOperator->getOperation().symbol);
                    result->error = new Error(Error::Kind::Eval, message);
                }
                    break;
            }
        } else if (value->getType() == Type::Bool) {
            auto aBool = (Bool *) value;
            switch (pOperator->getOperation().symbol) {
                case '~': {
                    stack->push(new Bool(!aBool->value));
                    //  cerr <<"!";
                }
                    break;
                default: {
                    std::string message = "Unknown real unary operator: ";
                    message.push_back(pOperator->getOperation().symbol);
                    result->error = new Error(Error::Kind::Eval, message);
                }
                    break;
            }
        }
    } else {
        std::string message = "Not enough values to call unary operator: '";
        message.push_back(pOperator->getOperation().symbol);
        result->error = new Error(Error::Kind::Eval, message);
    }
    return result;
}

Result<Value *, Error> *Compiler::eval_binary_operator(Operator *pOperator, Stack<Value *> *stack) {
    // NOTE: the arguments for the operations are taken from stack in the opposite order
    // e.g. if the original expression is `a / b`, then RPN is `a b /`, therefore we
    // take `b` at first then the second is `a`
    auto result = new Result<Value *, Error>();
    if (!stack->isEmpty()) {
        Value *b = stack->pop();
        if (!stack->isEmpty()) {
            Value *a = stack->pop();
            if (a->getType() == Type::Number && b->getType() == Type::Number) {
                // real functions
                auto aV = (Double *) a;
                auto bV = (Double *) b;

                switch (pOperator->getOperation().symbol) {
                    case '+':
                        stack->push(new Double(aV->value + bV->value));
                        break;
                    case '-':
                        stack->push(new Double(aV->value - bV->value));
                        break;
                    case '*':
                        stack->push(new Double(aV->value * bV->value));
                        break;
                    case '^':
                        stack->push(new Double(pow(aV->value, bV->value)));
                        break;
                    case '/': {
                        if (bV->value != 0.0) {
                            stack->push(new Double(aV->value / bV->value));
                        } else {
                            result->error = new Error(Error::Kind::Eval, "Division by zero!");
                        }
                    }
                        break;
                    case '=':
                        stack->push(new Bool(aV->value == bV->value));
                        break;
                    case '#':
                        stack->push(new Bool(aV->value != bV->value));
                        break;
                    case '>':
                        stack->push(new Bool(aV->value > bV->value));
                        break;
                    case '<':
                        stack->push(new Bool(aV->value < bV->value));
                        break;
                    default:
                        std::string message = "Unknown real binary operator: '";
                        message.push_back(pOperator->getOperation().symbol);
                        result->error = new Error(Error::Kind::Eval, message);
                        break;
                }
                cerr << pOperator->getOperation().symbol << "|";
                printValue(stack);
            } else if (a->getType() == Type::Bool && b->getType() == Type::Bool) {
                // boolean functions
                Bool *aV = (Bool *) a;
                Bool *bV = (Bool *) b;
                switch (pOperator->getOperation().symbol) {
                    case '&':
                        stack->push(new Bool(aV->value && bV->value));
                        break;
                    case '!':
                        stack->push(new Bool(aV->value || bV->value));
                        break;
                    default:
                        std::string message = "Unknown boolean binary operator: '";
                        message.push_back(pOperator->getOperation().symbol);
                        result->error = new Error(Error::Kind::Eval, message);
                        break;
                }
                cerr << pOperator->getOperation().symbol << "|";
                printValue(stack);
            } else {
                std::string message = "Type error to call binary operator: '";
                message.push_back(pOperator->getOperation().symbol);
                message += a->toString();
                message += " ";
                message += b->toString();
                result->error = new Error(Error::Kind::Eval, message);
            }
        } else {
            std::string message = "Not enough values to call operator: '";
            message.push_back(pOperator->getOperation().symbol);
            result->error = new Error(Error::Kind::Eval, message);
        }
    } else {
        std::string message = "Not enough values to call operator: '";
        message.push_back(pOperator->getOperation().symbol);
        result->error = new Error(Error::Kind::Eval, message);
    }

    return result;
}
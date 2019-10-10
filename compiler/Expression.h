//
// Created by user on 23.04.2019.
//

#ifndef COMPILER_EXPRESSION_H
#define COMPILER_EXPRESSION_H

#include <algorithm>
#include <string>
#include <map>
#include <vector>
#include "Compiler.h"

using namespace std;

class Expression {

    friend class Compiler;

private:
    map<int, string> messages;

    Compiler *compiler;

public:
    void errors(const char *fileName);

    string error(int code);

    Result<vector<Token*>, Error>* analize(string expression);

    Result<vector<Token*>, Error>* rpn(vector<Token *> *tokens);

    Result<Value*, Error>* eval(vector<Token *> *tokens, map<string, string>& bindings);

    Expression(const char *fileName);

    void bindings(const char *fileName, map<string, string>& bindMap);

    ~Expression();
};

#endif //COMPILER_EXPRESSION_H

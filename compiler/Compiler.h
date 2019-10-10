#ifndef COMPILER_COMPILER_H
#define COMPILER_COMPILER_H

#include "Stack.h"
#include <string.h>
#include <string>
#include <vector>
#include <map>

using namespace std;

enum Arity {
    Unary,
    Binary,
};

enum Assoc {
    Left,
    Right,
};

enum Brace {
    Round,
    Square
};

struct Operation {
    char symbol;
    Arity arity;
    Assoc assoc;
    int prec;
};


struct Error {
public:
    enum class Kind {
        Lex,
        Rpn,
        Eval,
        Unknown
    };

    Kind kind = Kind ::Unknown;
    int code = -1;
    std::string text ="";

    Error(int code){
        this->code=code;
    }

    Error(Kind kind, std::string text, int code) {
        this->kind = kind;
        this->text = text;
        this->code = code;
    }

    Error(Kind kind, std::string text) {
        this->kind = kind;
        this->text = text;
        this->code = -1;
    }

    Error(Kind kind, char ch, int code) {
        this->kind = kind;
        this->text.push_back(ch);
        this->code = code;
    }

    Error(Kind kind, char ch) {
        this->kind = kind;
        this->text.push_back(ch);
        this->code = -1;
    }

    string toString(){
        string value = "Type:";
        switch (kind){
            case Kind ::Lex:
                value+="Lex";
                value.push_back('(');
                value+=text;
                value.push_back(')');
                break;
            case Kind ::Rpn:
                value+="Rpn";
                value.push_back('(');
                value+=text;
                value.push_back(')');
                break;
            case Kind ::Eval:
                value+="Eval";
                value.push_back('(');
                value+=text;
                value.push_back(')');
                break;
        }
        return value;
    }
};

template<typename A, typename E>
struct Result  {
public:
    A* token;
    E* error;

   Result(){
       token = nullptr;
       error = nullptr;
   }

   Result(Result * result) {
      token = result->token;
      error = result->error;
   }

   Result(Result & result) {
      token = result.token;
      error = result.error;
   }

   bool is_ok() {
       return token != nullptr;
   }

   bool has_error() {
       return error != nullptr;
   }
};

enum class Type {
    Number,
    RealVar,
    BoolVar,
    Operation,
    LeftParenth,
    RightParenth,
    Function,
    UnKnown,
    Bool
};

/* interface class for tokens */
class Token {
    public:
        virtual Type getType()=0;
        virtual string toString(){ return "Unknown Token";};
        virtual ~Token(){};
};

class Value {
public:
    virtual Type getType()=0;
    virtual string toString(){ return "Unknown Value";};
    virtual ~Value(){};
};

class Double: public Value {
public:
    double value;

    Double(Value *pValue) {
      this->value = ((Double*)pValue)->value;
    }

    Double(Double& aDouble){
        this->value = aDouble.value;
    }

    Double(double value){
        this->value = value;
    }

    Type getType(){ return Type ::Number;}

    string toString(){
        return to_string(value);
    };
};

class Bool: public Value {
public:
    bool value;

    Bool(bool value){
        this->value = value;
    }

    Bool(Value *pValue) {
        this->value = ((Bool*)pValue)->value;
    }

    Bool(Bool& aBool){
        this->value = aBool.value;
    }

    Type getType(){ return Type ::Bool;}

    string toString(){
        return to_string(value);
    };
};

class Number : public Token {
    private:
    double value;
    public:
        Number(double value) : value(value) {}
        Type getType(){ return Type ::Number;}

        double getValue(){ return value;}
        string toString(){
            return to_string(value);
        }
};

class RealVar : public Token {
    private:
        string value;
    public:
        RealVar(string value) : value(value) {}
        Type getType(){ return Type ::RealVar;}
        string getValue(){ return value;}
        string toString(){
            return value;
        }
};

class BoolVar : public Token {
    private:
        string value;
    public:
        BoolVar(string value) : value(value) {}
        Type getType(){ return Type ::BoolVar;}
        string getValue(){ return value;}
        string toString(){
            return value;
        }
};

class Operator : public Token {
    private:
        Operation value;
    public:
        Operator(Operation value) : value(value) {}
        Type getType(){ return Type ::Operation;}
        Operation getOperation(){ return value; }
        string toString(){
            string str = "";
            str.push_back(value.symbol);
            return str;
        }
};

class LeftParenth : public Token {
    private:
        Brace value;
    public:
        LeftParenth(Brace value) : value(value) {}
        Type getType(){ return Type ::LeftParenth;}
        Brace getBrace(){ return value;}
        string toString(){
            if(value==Brace::Square)
                return "[";
            else
                return "(";
        }
};

class RightParenth : public Token {
    private:
        Brace value;
    public:
        RightParenth(Brace value) : value(value) {}
        Type getType(){ return Type ::RightParenth;}
        Brace getBrace(){ return value;}
        string toString(){
            if(value==Brace::Square)
                return "]";
            else
                return ")";
        }
};

class Function : public Token {
private:
    string value;
public:
    Function(string value): value(value) {}
    Type getType(){ return Type ::Function;}
    string getFunction(){ return value;}
    string toString(){
        return value;
    }
};

class Compiler {
public:
    Compiler();

    ~Compiler();

    Operator *binary_left_assoc(char c, int prec);

    Operator *unary_right_assoc(char c, int precedence);

    Result<vector<Token*>, Error>* lexical_analysis(string expression);

    Result<vector<Token*>, Error>* rpn(vector<Token*>* tokens);

    float get_number(string &expession, int &i, char ch);

    string get_identifier(string &expr, int &it, char ch);

    Result<Token, Error>* get_function(string &expr, int &it, char ch);

    void printToken(vector<Token*>& expression);

    void printToken(Stack<Token*>* stack);

    void printValue(Stack<Value *> *stack);

    float to_digit(char ch);

    bool is_letter(char ch);

    bool is_digit(char ch);

    bool isBinary(char ch);

    Result<Value*, Error>* eval(vector<Token *> *tokens,  map<string, Value*>& bindings);

    Result<Value*, Error>* eval_function(Function *pFunction, Stack<Value*> *pStack);

    Result<Value *, Error> *eval_unary_operator(Operator *pOperator, Stack<Value *> *pStack);

    Result<Value *, Error> *eval_binary_operator(Operator *pOperator, Stack<Value *> *pStack);
};

#endif //COMPILER_COMPILER_H

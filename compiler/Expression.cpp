
#include "Expression.h"
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <string>
#include <fstream>
#include <map>
#include <vector>

using  namespace std;

void Expression::errors(const char *fileName) {
    char message[256];
    int code;

    ifstream in(fileName);
    if (in.is_open()) {
        while (!message[0] == '\0')
        {
            in >> code;
            in.getline(message, 256);
            int size =sizeof(message);
            char* mess= new char[size];
            strcpy(mess, message );
            messages.insert(pair<int, string>(code, mess));
        }
    }
    in.close();
}

void Expression::bindings(const char *fileName, map<string, string>& bindMap) {
    string code;
    string value;

    ifstream in(fileName);
    if (in.is_open()) {
        do {
            in >> code;
            in >> value;
            bindMap.insert(pair<string, string>(code, value));
        }while (!in.eof());
    }
    in.close();
}

string Expression::error(int code){
    return messages.find(code)->second;
}

Expression::Expression(const char *fileName) {
    errors(fileName);
    compiler = new Compiler();
}

Expression::~Expression() {
    delete compiler;
}

Result<vector<Token*>, Error>* Expression::analize(string expression) {
    return compiler->lexical_analysis(expression);
}

Result<vector<Token*>, Error>* Expression::rpn(vector<Token *> *tokens) {
    return  compiler->rpn(tokens);
}

Result<Value*, Error>* Expression::eval(vector<Token *> *tokens, map<string, string>& bindings){
    Result<Value*, Error>* result = new Result<Value*, Error>();
    map<string, Value*> validBindings;
    for (auto pos_begin = bindings.begin(); pos_begin != bindings.end(); ++pos_begin){
        string key = (*pos_begin).first;
        string value = (*pos_begin).second;
        if(key[0] == 'R'){
            validBindings.insert(pair<string, Value*>(key, new Double(stold(value))));
        } else if (key[0] == 'B'){
            bool isTrue = (strcasecmp("true",value.c_str()) == 0);
            validBindings.insert(pair<string, Value*>(key, new Bool(isTrue)));
        } else {
            std::string message = "Unexpected identificator: '" + key + "' is not defined!";
            result->error = new Error(Error::Kind::Eval, message);
            return result;
        }
    }

    return compiler->eval(tokens, validBindings);
}

static void printToken(vector<Token*>* expression){
    cerr<<"--------------------------------------------------------------------------------"<<endl;
    for (auto pos_begin = expression->begin(); pos_begin != expression->end(); ++pos_begin){
        Type type = (*pos_begin)->getType();
        switch (type){
            case Type ::Operation: {
                 Operator* anOperator = ((Operator *) (*pos_begin));
                Operation operation = anOperator->getOperation();
                if (operation.arity == Arity::Unary) {
                    if(operation.symbol =='-'){
                        cerr<< " " <<"m";
                    } else {
                        cerr<< " " <<"p";
                    }
                } else {
                    cerr<<" "<<operation.symbol;
                }

            }
                break;
            case Type ::Number:
                cerr<< " " <<((Number*)(*pos_begin))->getValue();
                break;
            case Type ::RealVar:
                cerr<< " " <<((RealVar*)(*pos_begin))->getValue();
                break;
            case Type ::BoolVar:
                cerr<< " " <<((BoolVar*)(*pos_begin))->getValue();
                break;
            case Type ::RightParenth:
                cerr<< " " <<((RightParenth*)(*pos_begin))->toString();
                break;
            case Type ::LeftParenth:
                cerr<< " " <<((LeftParenth*)(*pos_begin))->toString();
                break;
            case Type ::Function:
                cerr<< " " <<((Function*)(*pos_begin))->getFunction();
                break;
        }

    }
    cerr<< "\n";
}

int main(int argc, const char * argv[]) {
    const char* messageName = "./../errors.lst";
    const char* bindName = "./../binding.txt";
    map<string, string> bindMap;
    string expression = "";
    Expression *parser = new Expression(messageName);
    parser->bindings(bindName, bindMap);
    if (argc != 2)
    {
        fflush(stdin);
        printf("\nPlease enter expression:\n");
        cin>>expression;

        if(expression.empty()) {
            parser->error(103);
            return 0;
        }
    } else {
        const char* fileName = argv[1];
        FILE *pFile = fopen(fileName, "r");
        fseek(pFile,0,SEEK_END);
        int size=ftell(pFile);
        fseek(pFile,0,SEEK_SET);

        if (pFile == nullptr || size == 0){
            printf ("Error opening file %s: %s\n", fileName, strerror(errno)); // текстовое описание последней ошибки
            cout<<"Would you like to open another file? 1-yes 2-no"<<endl;
            char choice='0';
            cout<<">";
            cin>>choice;
            if(choice == '1'){
                FILE*otherfile=fopen("other.txt","r");
                char ch1 = 0;
                fseek(otherfile,0,SEEK_SET);
                while (fscanf(otherfile, "%c", &ch1) != EOF)
                    expression.push_back(ch1);
                fclose(otherfile);
            } else if(choice=='2'){
                return 2;
            }
        } else {
            char ch = 0;
            while (fscanf(pFile, "%c", &ch) != EOF)
                expression.push_back(ch);
            fclose(pFile);
        }
    }

    Result<vector<Token*>, Error>* analized = parser->analize(expression);

    if(analized->has_error()) {
        Error*error = analized->error;
        cerr << "Error:" << error->toString() ;
        printToken(analized->token);
    }

    Result<vector<Token*>, Error>* notations =  parser->rpn(analized->token);

    if(notations->has_error()){
        cerr << "Error:" << notations->error->toString();
        return -1;
    } else {
        printToken(notations->token);
    }

    Result<Value*, Error>* result = parser->eval(notations->token, bindMap);
    if(result->has_error()){
        cerr << "Error:" << result->error->toString();
        return -1;
    } else {
        cerr <<"Answer:"<<((Value*)result->token)->toString()<< endl;
    }

  delete parser;

  return 0;
}
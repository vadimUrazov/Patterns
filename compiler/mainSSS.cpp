
#include <iostream>
#include <queue>
#include <stdlib.h>
#include <math.h>


template <class T>

class stack
{
public:
    
    T top()
    {
        /*if (isEmpty())
        {
            return ;
        }
         */
        return tail->value;
    }
    
    void push(T element)
    {
        node* new_node = new node(element);
        if (isEmpty())
        {
            tail = new_node;
        }
        else
        {
            new_node->next = tail;
            tail = new_node;
        }
        _size++;
    }
    
    
    T pop()
    {
       /* if (isEmpty())
        {
            return nullptr;
        }
        */
        T last_el = top();
        
        if (_size != 1) // ?????
        {
            tail = tail->next;
        }
        else
        {
            tail = nullptr;
            //tail->next = nullptr;
        }
        _size--;
        return last_el;
    }
    
    
    bool isEmpty()
    {
        if (tail == nullptr && _size == 0)
        {
            return true;
        }
        return false;
    }

    int size()
    {
        return _size;
    }
    
private:
    struct node
    {
        node(T element)
        {
            value = element;
        }
        T value;
        node* next = nullptr;
        
    };
    
    node* tail = nullptr;
    int _size = 0;
};


class salu
{
private:
    
    stack<std::string> stackDigitBool;
    stack<char> operation;
    bool startDigit = false;
    std::string tempForDigits;
    std::string tempForLetters;
    std::string opz;
    double digitForStack = 0.0;
    long int lenghtOfDigit = 0;
    int countOpenBracket = 0;
    bool startLetters = false;
    bool startSymbols = false;
    bool startCloseBracket = false;
    bool operandDigit = false;
    bool operandBool = false;
    bool startPoint = false;
    bool startTilda = false;
    std::queue<std::string> queueOpz;
    double operand_1 = 0.0;
    double operand_2 = 0.0;
    
    
    
    
public:
    
    int keys(char ch) // приоритет
    {
        
        if (ch == '^' || ch == '~')
        {
            return 1;
        }
        else if (ch == '*' || ch == '/')
        {
            return 2;
        }
        else if (ch == '&')
        {
            return 3;
        }
        else if (ch == '+' || ch == '-' || ch == '!' || ch == 'P' || ch == 'M')
        {
            return 4;
        }
        else if (ch == '<' || ch == '>' || ch == '#' || ch == '=')
        {
            return 5;
        }
        else if (ch == ')')
        {
            return 6;
        }
        else if (ch == '(')
        {
            return 7;
        }
        
            return 0;
        
    }
    
    void printCommands(char ch)
    {
        if ( ch == '^')
        {
           printf("Operation Exponentiation (^) \n");
        }
        else if ( ch == '~')
        {
             printf("Operation Negation (~) \n");
        }
        else if ( ch == '*')
        {
             printf("Operation Multiplication (*) \n");
        }
        else if ( ch == '/')
        {
             printf("Operation Division (/) \n");
        }
        else if ( ch == '&')
        {
             printf("Operation Conjunction (&) \n");
        }
        else if ( ch == '+')
        {
             printf("Operation Addition (+) \n");
        }
        else if ( ch == '-')
        {
             printf("Operation Subtraction (-) \n");
        }
        else if ( ch == '!')
        {
             printf("Operation Disjunction (!) \n");
        }
        else if ( ch == '<')
        {
             printf("Operation Less (<) \n");
        }
        else if ( ch == '>')
        {
             printf("Operation Larger (>) \n");
        }
        else if ( ch == '=')
        {
             printf("Operation Equally (=) \n");
        }
        else if ( ch == '#')
        {
             printf("Operation Not Equal (#) \n");
        }
        
    }
    
    void dkstr(char c)
    {
        if (operation.isEmpty())
        {
            operation.push('(');
        }
        
        if (keys(c) > keys(operation.top())) // в вершине стека оперция с более высоким приоритетом
        {
            // из стека выталкиваются все операции, приоритеты которых выше текущего, текущая - в стек
            opz.append(1, operation.top());
            queueOpz.push(std::string(1, operation.top()));
            printCommands(operation.top()); // записывать в файл наверное
            operation.pop();
            dkstr(c);
        }
        else if (keys(c) < keys(operation.top())) // в вершине стека оперция с более низким приоритетом
        {
            if (c == ')' && operation.top() == '(')
            {
               operation.pop();
               // queueOpz.pop();
                
                
            }
            else
            {
                operation.push(c);
            }
        }
        else if (keys(c) == keys(operation.top())) // приоритеты совпадают
        {
            if (c == '&') // левая ассоциативность
            {
                opz.append(2, operation.top());
                queueOpz.push(std::string(1, c));
                printCommands(operation.top());
                operation.pop();
                operation.push(c);
            }
            else
            {
                operation.push(c); // правая ассоциативность
            }
        }
        
    }
    

    int separator(char ch)
    {
        if (startDigit == true)
        {
            
            if (isdigit(ch))
            {
                tempForDigits.append(1, ch);
            }
            
            else if (ch == '.')
            {
                if (startPoint == true)
                {
                    printf("Invalid operand \n");
                    return -1;
                }
                else
                {
                    startPoint = true;
                    tempForDigits.append(1, ch);
                }
            }
            else
            {
                lenghtOfDigit = tempForDigits.size();
                digitForStack = atoi(tempForDigits.c_str());
                queueOpz.push(tempForDigits);
                opz.append(tempForDigits);
                opz.append("_");
                printf("Add operand %.2f to stack \n", digitForStack);
                tempForDigits.clear();
                startDigit = false;
                startPoint = false;
                operandDigit = true;
                if (separator(ch) != 0)
                {
                    return -1;
                }
            }
        }
        
        else if (startLetters == true)
        {
            if (ch == 'r' || ch == 'u' || ch == 'e' || ch == 'a' || ch == 'l' || ch == 's')
            {
                tempForLetters.append(1, ch);
                
            }
            
            else
            {
                if (tempForLetters.compare("true") == 0 || tempForLetters.compare("false") == 0)
                {
                    queueOpz.push(tempForLetters);
                    opz.append(tempForLetters);
                    opz.append("_");
                    printf("Add operand %s to stack \n", tempForLetters.c_str());
                    operandBool = true;
                    startLetters = false;
                    tempForLetters.clear();
                    if (separator(ch) != 0)
                    {
                        return -1;
                    }
                }
                else
                {
                    printf("Invalid operand \n");
                    return -1;
                }
                
            }
            
            
        }
        
        
        else
        {
            if (isdigit(ch))
            {
                if (operandBool == true) // ошибка, если число прилетает после true, false
                {
                    printf("Next two operands \n");
                    return -1;
                }
                else
                {
                    tempForDigits.append(1, ch);
                    startDigit = true;
                    startSymbols = false;
                    startTilda = false;
                }
            }
            
            else if (ch == 't' || ch == 'f')
            {
                if (operandDigit == true || startCloseBracket == true)
                {
                    printf("Next two operands \n");
                    return -1;
                }
                else
                {
                    startLetters = true;
                    startSymbols = false;
                    startTilda = false;
                    tempForLetters.append(1, ch);
                }
            }
            
            
            else if (ch == '^' || ch == '*' || ch == '/' || ch == '&' || ch == '!' || ch == '<' || ch == '>' || ch == '=' || ch == '#')
            {
                if (startSymbols == true)
                {
                    printf("Two binary operations are located nearby \n");
                    return -1;
                }
                
                else if (startTilda == true)
                {
                    printf("Binary operation comes after tilda \n");
                    return -1;
                }
                
                else if (operandDigit == true || operandBool == true || startCloseBracket == true)
                {
                    operandDigit = false;
                    operandBool = false;
                    startCloseBracket = false;
                    startSymbols = true;
                    dkstr(ch);
                }
                else
                {
                    printf("Error_5");
                }
            }
            
            else if (ch == '~')
            {
                startTilda = true;
                operandDigit = false;
                operandBool = false;
                startCloseBracket = false;
                startSymbols = false;
                dkstr(ch);
            }
            
            else if (ch == '(')
            {
                operandDigit = false;
                operandBool = false;
                startCloseBracket = false;
                startSymbols = false;
                startTilda = false;
                countOpenBracket += 1;
                dkstr(ch);
            }
            
            else if (ch == ')')
            {
                if (countOpenBracket == 0)
                {
                    printf("The formula starts with the opening bracket \n");
                    return -1;
                }
                else
                {
                    startCloseBracket = true;
                    operandDigit = false;
                    operandBool = false;
                    startSymbols = false;
                    startTilda = false;
                    countOpenBracket -= 1;
                    dkstr(ch);
                }
            }

            else if (ch == '+' || ch == '-')
            {
                if (operandDigit == true || operandBool == true || startCloseBracket == true)
                {
                    operandDigit = false;
                    operandBool = false;
                    startCloseBracket = false;
                    startSymbols = true;
                    dkstr(ch);
                }
                else
                {
                    startSymbols = true;
                    startTilda = false;
                    if (ch == '+')
                    {
                        dkstr('P');
                    }
                    dkstr('M');
                }
            }
            else if (ch == '\n' || ch == ' ')
            {
                
            }

            
           else
            {
                printf("Unknown symbol \n");
                return -1;
            }
            
            
        }
        return 0;
    }
    
    bool isDigit(std::string str)
    {
        if (str.compare("true") != 0 && str.compare("false") != 0 && str.compare("^") != 0 && str.compare("~") != 0 && str.compare("*") != 0 && str.compare("/") != 0 && str.compare("&") != 0 && str.compare("P") != 0 && str.compare("+") != 0 && str.compare("M") != 0 && str.compare("-") != 0 && str.compare("!") != 0 && str.compare("<") != 0 && str.compare(">") != 0 && str.compare("=") != 0 && str.compare("#") != 0)
        {
            return true;
        }
        else
        {
            return false;
            
        }
        /*long int temp = strtol(str.c_str(), nullptr, 10);
        if (errno == ERANGE)
        {
            return false;
        }
        else
        {
            return true;
        }
         */
            
    }
    
    std::string intToString(double i)
    {
        char buffer[20];
        sprintf(buffer, "%f", i);
        return std::string(buffer);
    }
    
    std::string result()
    {
        dkstr(')');
        printf("OPZ: %s \n", opz.c_str());
        if (isDigit(queueOpz.front()) == false && queueOpz.front().compare("true") != 0 && queueOpz.front().compare("false") != 0)
        {
            return std::string("There is no operand for first operation");
        }
        
        while (queueOpz.empty() == false)
        {
            if (isDigit(queueOpz.front()) == true)
            {
                stackDigitBool.push(queueOpz.front());
                queueOpz.pop();
            }
            else if (queueOpz.front().compare("true") == 0 || queueOpz.front().compare("false") == 0)
            {
                stackDigitBool.push(queueOpz.front());
                queueOpz.pop();
            }
            else if (queueOpz.front().compare("P") == 0 && isDigit(stackDigitBool.top()) == true)
            {
            }
            else if (queueOpz.front().compare("M") == 0 && isDigit(stackDigitBool.top()) == true)
            {
                operand_2 = atof(queueOpz.front().c_str())*(-1);
                stackDigitBool.pop();
                stackDigitBool.push(intToString(operand_2));
                queueOpz.pop();
            }
            else if (queueOpz.front().compare("~") == 0 && (stackDigitBool.top().compare("true") == 0 || stackDigitBool.top().compare("false") == 0))
            {
                if (stackDigitBool.top().compare("true") == 0)
                {
                    stackDigitBool.pop();
                    stackDigitBool.push(std::string("false"));
                    queueOpz.pop();
                }
                else
                {
                    stackDigitBool.pop();
                    stackDigitBool.push(std::string("true"));
                    queueOpz.pop();
                }
            }
            else if (stackDigitBool.size() >= 2)
            {
                if (isDigit(stackDigitBool.top()) == true)
                {
                    operand_2 = atof(stackDigitBool.top().c_str());
                    stackDigitBool.pop();
                    if (isDigit(stackDigitBool.top()) == true)
                    {
                        operand_1 = atof(stackDigitBool.top().c_str());
                        stackDigitBool.pop();
                        if (queueOpz.front().compare("^") == 0)
                        {
                            stackDigitBool.push(intToString(pow(operand_1, operand_2)));
                            queueOpz.pop();
                        }
                        else if (queueOpz.front().compare("*") == 0)
                        {
                            stackDigitBool.push(intToString(operand_1 * operand_2));
                            queueOpz.pop();
                        }
                        else if (queueOpz.front().compare("/") == 0)
                        {
                            stackDigitBool.push(intToString(operand_1 / operand_2));
                            queueOpz.pop();
                        }
                        else if (queueOpz.front().compare("+") == 0)
                        {
                            stackDigitBool.push(intToString(operand_1 + operand_2));
                            queueOpz.pop();
                        }
                        else if (queueOpz.front().compare("-") == 0)
                        {
                            stackDigitBool.push(intToString(operand_1 - operand_2));
                            queueOpz.pop();
                        }
                        else if (queueOpz.front().compare("<") == 0)
                        {
                            if (operand_1 < operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        else if (queueOpz.front().compare(">") == 0)
                        {
                            if (operand_1 > operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        else if (queueOpz.front().compare("=") == 0)
                        {
                            if (operand_1 == operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                            
                        }
                        else if (queueOpz.front().compare("#") == 0)
                        {
                            if (operand_1 != operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        
                    }
                    else if (stackDigitBool.top().compare("true") == 0 || stackDigitBool.top().compare("false") == 0)
                    {
                        return std::string("Mismatch between operation and operand");
                    }
                }
                
                else if (stackDigitBool.top().compare("true") == 0 || stackDigitBool.top().compare("false") == 0)
                {
                    if ((stackDigitBool.top().compare("true") == 0))
                    {
                        operand_2 = 1.0;
                    }
                    else
                    {
                        operand_2 = 0.0;
                    }
                    stackDigitBool.pop();
                    if (stackDigitBool.top().compare("true") == 0 || stackDigitBool.top().compare("false") == 0)
                    {
                        if ((stackDigitBool.top().compare("true") == 0))
                        {
                            operand_1 = 1.0;
                        }
                        else
                        {
                            operand_1 = 0.0;
                        }
                        stackDigitBool.pop();
                        if (queueOpz.front().compare("!") == 0)
                        {
                            if (operand_1 == 1.0 || operand_2 == 1.0)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        else if (queueOpz.front().compare("&") == 0)
                        {
                            if (operand_1 == 1.0 && operand_2 == 1.0)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        else if (queueOpz.front().compare("=") == 0)
                        {
                            if (operand_1 == operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                        else if (queueOpz.front().compare("#") == 0)
                        {
                            if (operand_1 != operand_2)
                            {
                                stackDigitBool.push(std::string("true"));
                                queueOpz.pop();
                            }
                            else
                            {
                                stackDigitBool.push(std::string("false"));
                                queueOpz.pop();
                            }
                        }
                    }
                    else if (isDigit(stackDigitBool.top()) == true)
                    {
                        return std::string("Mismatch between operation and operand");
                    }
                }
                
            }
            else
            {
                return std::string("It is impossible to apply a binary operation to one operand");
            }
            
        }
        return stackDigitBool.top();
    }
};



int main(int argc, const char * argv[]) {
    
    if (argc != 2)
    {
        printf("Wrong argument number \n");
        return 3;
    }
    
    const char* fileName = argv[1];
    FILE *pFile = fopen(fileName, "r");
    
    if (pFile == nullptr)
    {
        printf ("Error opening file %s: %s\n", fileName, strerror(errno)); // текстовое описание последней ошибки
        return 2;
    }
    
    salu s1;
    
    char ch = 0;
    while (fscanf(pFile, "%c", &ch) != EOF)
    {
        if (s1.separator(ch) != 0)
        {
            return 1;
        }
        
    }
    std::string result = s1.result();
    
    printf("Result: %s \n", result.c_str());
    return 0;
}




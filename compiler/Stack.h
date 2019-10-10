
#include <vector>
#include <string.h>
#include <string>
#include <iostream>

using namespace std;

#ifndef COMPILYTOR_STACK_H
#define COMPILYTOR_STACK_H

template <typename T>
class Stack {
private:
    struct Element
    {
        Element(T element)
        {
            value = element;
        }

        T value;

        Element* next = nullptr;
    };
public:
    Element* top = nullptr;
    Element* curr = nullptr;
    int _size = 0;

    Stack();

    ~Stack();

    T peek();

    T move();

    void push(T value);

    T pop();

    int size();

    bool isEmpty();

    void clearStack();

    void toTop();
};

template <typename T>
Stack<T>::Stack() {
    this->top = nullptr;
}

template <typename T>
Stack<T>::~Stack() { clearStack(); }

template <typename T>
T Stack<T>::peek() {
    return top->value;
}

template <typename T>
T Stack<T>::move() {
    Element* current = curr;
    curr = curr->next;
    return current->value;
}

template <typename T>
void Stack<T>::toTop() {
    curr = top;
}

template <typename T>
void Stack<T>::push(T value) {
    Element *element = new Element(value);
    if (isEmpty()) {
        top = element;
    } else {
        element->next = top;
        top = element;
    }
    _size++;
}

template <typename T>
T Stack<T>::pop() {
    Element *current = top;
    top = top->next;
    current->next = nullptr;
    _size--;
    return current->value;
}

template <typename T>
bool Stack<T>::isEmpty()
{
    if (top == nullptr && _size == 0)
    {
        return true;
    }
    return false;
}

template <typename T>
int Stack<T>::size()
{
    return _size;
}

template <typename T>
void Stack<T>::clearStack() {
    while (!isEmpty()) {
        pop();
    }
}

#endif //COMPILYTOR_STACK_H

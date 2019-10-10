/* Компиля:
 *   gcc -o rpn rpn.c
 *
 * Использование:
 *   ./rpn <выражение>
 *
 * Пример:
 *   ./rpn 3 5 +
 *
 * Замечание: знак умножения `*' заменен на `x', т.к. символ `*' используется
 * командной оболочкой.
 **/

#include <errno.h>
#include <stdio.h>
#include <stdlib.h>

#define STKDPTH 32 /* Глубина стека */

/* Значения, возвращаемые функцией parse */
#define VAL  0  /* В стек занесено новое значение */
#define ADD  1  /* Сложение */
#define SUB  2  /* Вычитание */
#define MUL  3  /* Умножение */
#define DIV  4  /* Деление */
#define SOF -1  /* Переполнение стека */
#define SUF -2  /* В стеке недостаточно операндов */
#define UNK -3  /* Неопознанное значение */

/* Глобальные переменные */
int scount;
double stack[STKDPTH];

/* Функция распознавания аргументов */
int parse(char *);

/* Точка входа */
int main111(int argc, char **argv)
{

    /* Организуем цикл для перебора аргументов командной строки */
    while (*++argv) {

        /* Пытаемся распознать текущий аргумент как число или
         * символ арифметической операции */
        switch (parse(*argv)) {
            case VAL: continue;

                /* Вычисляем */
            case ADD:
                stack[scount - 1] += stack[scount];
                break;

            case SUB:
                stack[scount - 1] -= stack[scount];
                break;

            case MUL:
                stack[scount - 1] *= stack[scount];
                break;

            case DIV:
                if (stack[scount] != 0) {
                    stack[scount - 1] /= stack[scount];
                    break;
                } else {
                    fprintf(stderr, "Деление на ноль!\n");
                    return(1);
                }

                /* Обработка ошибок */
            case SUF:
                fprintf(stderr, "Недостаточно операндов!\n");
                return(1);

            case SOF:
                fprintf(stderr, "Переполнение стека!\n");
                return(1);

            case UNK:
                fprintf(stderr, "Неопознанный аргумент!\n");
                return(1);
        }
    }

    /* Вывести результат */
    int i;
    for (i = 0; i < scount; i++) printf("%0.3f\n", stack[i]);

    return(0);
}

int parse(char *s)
{
    double tval = 0;
    char * endptr;

    /* Распознаем знаки арифметических операций */
    switch (*s) {
        case '-':
            /* Если минус является первым и не последним символом аргумента,
             * значит пользователь ввел отрицательное число и опознавать его
             * как операцию вычитания не нужно */
            if (*(s+1) != '\0') break;
            if (scount >= 2) {
                scount -= 1;
                return(SUB);
            }
            else return(SUF);

        case '+':
            if (scount >= 2) {
                scount -= 1;
                return(ADD);
            }
            else return(SUF);

        case 'x':
            if (scount >= 2) {
                scount -= 1;
                return(MUL);
            }
            else return(SUF);

        case '/':
            if (scount >= 2) {
                scount -= 1;
                return(DIV);
            }
            else return(SUF);
    }

    errno = 0;

    /* Пытаемся сконвертировать строковый аргумент в число */
    tval = strtod(s, &endptr);

    /* Вернуть ошибку `неопознанный аргумент' в случае неудачи */
    if (errno != 0 || *endptr != '\0') return(UNK);

    /* Проверяем, есть ли свободное место в стеке
     * и сохраняем в нем операнд, иначе возвращаем ошибку переполнения */
    if (scount < STKDPTH) stack[scount++] = tval;
    else return(SOF);

    return(VAL);
}
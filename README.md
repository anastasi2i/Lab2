## Отчет по лабораторной работе № 2

#### № группы: `ПМ-2403`

#### Выполнила: `Соколова Анастасия Павловна`

#### Вариант: `21`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Входные и выходные данные](#2-входные-и-выходные-данные)
- [Выбор структуры данных](#3-выбор-структуры-данных)
- [Алгоритм](#4-алгоритм)
- [Программа](#5-программа)
- [Анализ правильности решения](#6-анализ-правильности-решения)

### 1. Постановка задачи

> Напишите программу на Java, которая выполняет следующие действия
с двумерным массивом целых чисел:
1. Считывает с консоли размеры массива N и M, затем элементы
массива размером N × M.
2. Находит и выводит количество уникальных чисел в массиве.
3. Переставляет столбцы массива в порядке возрастания суммы их
элементов. Если суммы равны, сравнивает количество уникальных
чисел в столбце.
4. Выводит элементы массива по диагоналям, начиная с верхней левой диагонали (1 элемент) и двигаясь вправо и вниз.
5. Заменяет все числа в массиве на их факториалы и выводит полученный массив

> Программа получает на вход целые числа N и M, которые задают размер массива, а далее набор целых чисел - элементов массива.

Данную задачу можно разделить на подзадачи: 
1. Считывание параметров и элементов с консоли.
2. Поиск уникальных чисел.
3. Подсчет и сравнение суммы элементов и количества уникальных элементов по столбцам и их престановка в порядке возрастания.
4. Выведение элементов массива по диагонали.
5. Вычисление факториала каждого элемента и их выведение упорядоченно в виде массива.

### 2. Входные и выходные данные

#### Данные на вход

На вход программа должна получать 2 целых положительных числа, указывающих размер. Этим задается нижняя граница (число >=1), но верхней не существует (до бесконечности). 
Затем поступает набор целых чисел неограниченной величины, но в количестве, ограниченном ранее размером массива.

|            | Тип         | min значение | max значение |
|------------|-------------|--------------|--------------|
| N (Число 1)| Целое число |      1       |     +∞       |
| M (Число 2)| Целое число |      1       |     +∞       |
| a (массив) | Целые числа |     -∞       |     +∞       |


#### Данные на выход

У каждой задачи на выход идут разные типы данных:
1. Последовательность уникальных элементов, найденных в массиве.
3. Массивы, изменненые согласно подзадачам.

|                    | Тип                         | min значение | max значение   |
|--------------------|---------------------------- |--------------|----------------|
| Последовательность | Целое неотрицательное число |      -∞      |       +∞       |
| Массив 1           | Массив                      |              |                |
| Массив 2           | Массив                      |              |                |


### 3. Выбор структуры данных

Программа получает на вход целые числа. Двое из них указывают на размер,поэтому не могут быть отрицательными или равны нулю. Но остальные являются 
набором элементов массива и у них нет ограничений. Можно предположить, что находить факториал слишком больших чисел в крупном массиве будет затруднительно и эта 
программа не предназначена для вычисления масштабных объемов данных, поэтому можно использовать тип целочисленных данных не с макимальным диапазоном. 
Таким образом, все числа будет вполне разумно хранить в виде int. При необходимости в соответствии с потребностями можно легко преобразовать тип данных в long.

|             | название переменной | Тип (в Java)      | 
|-------------|---------------------|-------------------|
| N (Число 1) | `n`                 | `int`             |
| M (Число 2) | `m`                 | `int`             | 
| a (массив)  | `a`                 | `int`(у элементов)| 


Результат представлен в виде массива целых чисел.

|             | название переменной | Тип (в Java)   | 
|-------------|---------------------|----------------|
| z (массив)  | `z`                 | `int`(элементы)| 


### 4. Алгоритм

#### Алгоритм выполнения программы:

1. **Ввод данных:**  
   Программа считывает целые числа: длину массива N и его высоту M. Далее последовательность целочисленных элементов в указанном NxM размере и заносит их в массив.

2. **Уникальный элемент**  
   - Уникальный элемент ищем с помощью перебора и сравнения всех элементов между собой.
   - Сначала перебираем все индексы элементов двумерного массива: высоты и ширины - с помощью двух вложенных циклов.
   - Затем обнуляем "счетчик" k, который означает количество совпадения элементов. 
   - Далее снова перебираем все эелементы и сравниваем их с тем, который выбрали в первом переборе.
   - При наличии совпадения двух выбранных в обеих переборах циклах увеличиваем значение "счетчика" на 1.
   - Каждый элемент совпадет как минимум сам с собой. Если же совпадений больше - он не уникален. Поэтому проверяем количество совпадений и выводим только те элементы,
которые имеют лишь одно совпадение.

3. **Сортировка столбцов**
   - Для удобства подсчета создаем новую матрицу u, которая является транспорированной матрицей исходной матрицы a. Таким образом, столбцы матрицы a стали строками матрицы u.
   - В качестве помощи также создаем матрицу s, которая будет состоять из 2 строк: верхняя содержит сумму столбца, нижняя - номер столбца.
   - Методом перебора проходим по строкам матрицы u, запоминаем номер и считаем сумму элементов каждой строки.
   - Результаты подсета суммы вносим в верхнюю строку матрицы s, а зафиксированный номер строки - в нижнюю строку.
   - Затем необходимо отсортировать матрицу s по значению верхней строки в порядке возрастания и вывести номера строк матрицы u согласно полученному порядку номеров в нижней строке матрицы s.
   + необходимо учесть количество уникальных элементов. Посчитать их по строкам u и сравнить в случае равенства значений суммы.

5. **Вывод результата:**  
   

#### Блок-схема


### 5. Программа

```java
import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) {
        // вводим размерность массива
        int n = in.nextInt();
        int m = in.nextInt();
        // создаем массив согласно введенным размерам
        int[][]a=new int[n][m];
        // считываем элементы и заносим в массив
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++)
                a[i][j]=in.nextInt();}

        int max = n*m; //максимальное число уникальных элементов
        int[][]u=new int[m][n];
        for(int i=0; i<u.length; i++)
            for(int j=0; j<u[i].length; j++)
                u[i][j]=0;
        
        out.println("Уникальные элементы: ");

//поиск уникального элемента:
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int k = 0;
                for (int q = 0; q < a.length; q++) {
                    for (int p = 0; p < a[i].length; p++) {
                        if (a[i][j] == a[q][p])
                            k += 1;
                    }
                }
                if (k == 1)
                    out.print(a[i][j] + " ");
            }
        }
    out.println();

//Образуем транспорированную матрицу и временно выводим ее в качестве проверки 
    out.print("Транспорированная матрица ");
    out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                 u[j][i]=a[i][j];
                    }
                }
        for(int i=0; i<u.length; i++){
            for(int j=0; j<u[i].length; j++)
                out.print(u[i][j]+" ");
        out.println();}

//Создаем вспомогательную матрицу резултата подсчета суммы строк и их номера (и уникальных элементов?) и временно выводим для проверки 
        int[][]s=new int[2][n];
        //int nu =0;
        //int sum = 0;
        for(int i = 0; i<u.length; i++) {
            int sum = 0;
            int nu = i;
            for (int j = 0; j < n; j++)
                sum += u[i][j];
            for (int q = 0; q < s.length; q++) {
                s[0][q] = sum;
                s[1][q] = nu;
            }
        }
        out.println();
        for(int i=0; i<s.length; i++){
            for(int j=0; j<s[i].length; j++)
                out.print(s[i][j]+" ");
            out.println();}

        }

        //печатаем массив в конце, пока не актуальное действие 
        //for(int i=0; i<a.length; i++){
            //for(int j=0; j<a[i].length; j++)
                //out.print(a[i][j]+" ");
       // out.println();}

    }
```

### 6. Анализ правильности решения



1. Тест на 

    - **Input**:
        ```
        
        ```

    - **Output**:
        ```
        
        ```

2. Тест на 

    - **Input**:
        ```
       
        ```

    - **Output**:
        ```
        
        ```

3. Тест на 

    - **Input**:
        ```
      
        ```

    - **Output**:
        ```
        
        ```

4. Тест на 

    - **Input**:
        ```
        
        ```

    - **Output**:
        ```
        
        ```

5. Тест на 

    - **Input**:
        ```
  
        ```

    - **Output**:
        ```
    
        ```
 6. Тест на 

    - **Input**:
        ```
      
        ```

    - **Output**:
        ```
        ```

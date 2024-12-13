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
        int[][] Given = new int[n][m];
        // считываем элементы и заносим в массив
        for (int i = 0; i < Given.length; i++) {
            for (int j = 0; j < Given[i].length; j++)
                Given[i][j] = in.nextInt();
        }

        int CountU = 0;                                         //счетчик уникальных элементов в матрице
        for (int i = 0; i < Given.length; i++) {
            for (int j = 0; j < Given[i].length; j++) {         //рассматриваем каждый элемент матрицы
                int k = 0;                                      //счетчик количества рассматриваемого элемента в матрице
                for (int q = 0; q < Given.length; q++) {
                    for (int p = 0; p < Given[i].length; p++) { //перебираем каждый элемент матрицы
                        if (Given[i][j] == Given[q][p]) {       //если элемент равен рассматриваемому
                            k += 1;                             //то значит, что таких эелементов на 1 больше, чем было
                        }
                    }
                }
                if (k == 1) {                                   //если рассматриваемых элементов в матрице 1
                    CountU += 1;                                //то увеличиваем счетчик уникальных элементов в матрице
                }
            }
        }
        out.print("Количествово уникальных элементов: ");
        out.println(CountU);

        //создаем и печатаем для проверки транспорированный массив
        //out.print("Транспорированный массив");
        out.println();
        int[][] Trans = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Trans[j][i] = Given[i][j];                      //меняем местами индексы строки и столбца
            }
        }
        //for (int i = 0; i < Trans.length; i++) {
        //    for (int j = 0; j < Trans[i].length; j++)
        //        out.print(Trans[i][j] + " ");
        //    out.println();
        //}

        //создаем массив для соединения результатов подсчета количества уникальных элементов с номером соответствующей строки
        int[][] UnicCount = new int[2][m];
        for (int i = 0; i < Trans.length; i++) {            //берем строку i
            CountU = 0;                                     //начальное значение кол-ва уникальных элементов в ней
            for (int j = 0; j < Trans[i].length; j++) {     //рассматриваем каждый элемент строки i
                int k = 0;                                  //начальное значение кол-ва этого элемента в строке
                for (int p = 0; p < Trans[i].length; p++) { //перебираем каждый элемент строки
                    if (Trans[i][j] == Trans[i][p]) {       //если они равны
                        k += 1;                             //увеличиваем счетчик
                    }
                }
                if (k == 1) {                               //если рассматриваемого элемента 1
                    CountU += 1;                            //увеличиваем кол-ва уникальных элементов в строке
                }
            }
            UnicCount[0][i] = CountU;                       //после рассмотрения каждого элемента строки записываем
            UnicCount[1][i] = i;                            //полученное колво в массив и после берем следующую строку
        }

        //for (int i = 0; i < UnicCount.length; i++) {
        //    for (int j = 0; j < UnicCount[i].length; j++)
        //        out.print(UnicCount[i][j] + " ");
        //    out.println();
        //}

        //создаем массив для соединение результатов подсчета суммы строк и соответствующего номера и печатаем его для проверки
        //out.println("Матрица для сортировки");
        int[][] SumTable = new int[2][m];
        for (int i = 0; i < Trans.length; i++) {
            int sum = 0;                                            //обнуляем начальное значение суммы для каждой новой строки
            for (int j = 0; j < Trans[i].length; j++) {
                sum += Trans[i][j];                                 //суммируем каждый элемент строки поочередно
            }
            SumTable[0][i] = sum;
            SumTable[1][i] = i;
        }
        //for (int i = 0; i < SumTable.length; i++) {
        //    for (int j = 0; j < SumTable[i].length; j++)
        //        out.print(SumTable[i][j] + " ");
        //    out.println();
        //}

        //сортируем массив количества уникальных чисел столбца
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m - i; j++) {
                if (UnicCount[0][j] > UnicCount[0][j + 1]) {
                    int w = UnicCount[0][j];
                    int z = UnicCount[1][j];
                    UnicCount[0][j] = UnicCount[0][j + 1];
                    UnicCount[1][j] = UnicCount[1][j + 1];
                    UnicCount[0][j + 1] = w;
                    UnicCount[1][j + 1] = z;
                }

                }
            }
        //out.println();
        //for (int i = 0; i < UnicCount.length; i++) {
        //    for (int j = 0; j < UnicCount[i].length; j++)
        //        out.print(UnicCount[i][j] + " ");
        //    out.println();
        //}
        //out.println();

        //сортируем массив SumTable по возрастанию элементов верхней строки с учетом массива UnicCount
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m - i; j++) {
                if (SumTable[0][j] > SumTable[0][j + 1]) {
                    int x = SumTable[0][j];
                    int y = SumTable[1][j];
                    SumTable[0][j] = SumTable[0][j + 1];
                    SumTable[1][j] = SumTable[1][j + 1];
                    SumTable[0][j + 1] = x;
                    SumTable[1][j + 1] = y;
                }
                if(SumTable[0][j] == SumTable[0][j + 1]) {
                    if (UnicCount[0][j] > UnicCount[0][j + 1]) {
                        int x = SumTable[0][j];
                        int y = SumTable[1][j];
                        SumTable[0][j] = SumTable[0][j + 1];
                        SumTable[1][j] = SumTable[1][j + 1];
                        SumTable[0][j + 1] = x;
                        SumTable[1][j + 1] = y;
                    }
                }
            }
        }
        //for (int i = 0; i < SumTable.length; i++) {
        //    for (int j = 0; j < SumTable[i].length; j++)
        //        out.print(SumTable[i][j] + " ");
        //    out.println();}
        //    out.println();

        //создаем массив, в котором выведем результат
        int[][] Sorted = new int[m][n];
        //выводим строки по очереди значений, полученных в результате сортировки выше. очередность хранится в нижней строке массива SumTable
        for (int i = 0; i < m; i++)
            Sorted[i] = Trans[SumTable[1][i]];

        //Транспонируем его, т.к. использовали перевернутую матрицу в основе
        int[][] TransSorted = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                TransSorted[j][i] = Sorted[i][j];                      //меняем местами индексы строки и столбца
            }
        }

        //печатаем отсортированный массив
        out.println("Отсортированный массив");
        for (int i = 0; i < TransSorted.length; i++) {
            for (int j = 0; j < TransSorted[i].length; j++)
                out.print(TransSorted[i][j] + " ");
            out.println();
        }
        //Переходим к следующей задаче о выводе диагоналей
        out.println("По диагоналям");
        for (int k = 0; k <= m + n - 2; k++) {
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < n && j < m) {
                    System.out.print(Given[i][j] + " ");
                }
            }
            System.out.println();
        }

        // Задача о вычислении факториала каждого элемента: Проходим по каждому элементу, ищем факториал с помощью метода, присваеваем новое значение и выводим матрицу на экран
        for (int i = 0; i < Given.length; i++)
            for (int j = 0; j < Given[i].length; j++)
                Given[i][j] = factorial(Given[i][j]);
        out.println("Массив факториалов");
        for (int i = 0; i < Given.length; i++) {
            for (int j = 0; j < Given[i].length; j++)
                out.print(Given[i][j] + " ");
            out.println();
        }
    }

    // с помощью рекурсии вычисляем факториал числа, умножая данное число на факториал числа перед ним => перемножаем числа от 1 до а
    public static int factorial(int a) {
        if (a <= 1)
            return 1;
        else
            return a * factorial(a - 1);
    }
}

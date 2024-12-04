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

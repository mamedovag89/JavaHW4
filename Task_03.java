// 3. В калькулятор добавьте возможность отменить последнюю операцию.
// Пример
// 1 
// + 
// 2
// ответ:
// 3
// +
// 4
// ответ:
// 7
// Esc
// 3
// *
// 3
// ответ:
// 9
// Пример 2
// Ввод: 1
// Ввод: +
// Ввод: 2
// Вывод: 3
// Ввод:+
// Ввод:4
// Вывод 7
// Ввод:*
// Ввод:3
// Вывод 21
// Esc
// Вывод 7
// Esc
// Вывод 3
// Ввод:-
// Ввод:1
// Вывод 2
// ***Дополнительно*** каскадная отмена - отмена нескольких операций


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Task_03 {
    static double getSum(double a, double b) {
        return a + b;
    }

    static double getSubtraction(double a, double b) {
        return a - b;
    }

    static double getProduct(double a, double b) {
        return a * b;
    }

    static double getDivision(double a, double b) {
        return a / b;
    }

    static double getResultOfOperation(double a, double b, String signOperation) {
        switch (signOperation) {
            case "+":
                return (double) getSum(a, b);
            case "-":
                return (double) getSubtraction(a, b);
            case "*":
                return (double) getProduct(a, b);
            case "/":
                return (double) getDivision(a, b);
            default:
                return 0;
        }
    }

    static boolean isSignOperation(String signOperation) {
        if (signOperation.equals("+") || signOperation.equals("-") || signOperation.equals("*")
                || signOperation.equals("/")) {
            return true;
        }
        return false;
    }

    static boolean isEscape(String str) {
        if (str.equals("Esc")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Deque<Double> listResults = new LinkedList<Double>();
        Scanner scn = new Scanner(System.in);
        System.out.printf(" Hello! You can use calculator!\n After inputing of the first number it's nessesary to enter sign of operation from list{ +, -, *, /}. \n You can cancel the last operation by entering 'Esc'.\n");
        try {
            System.out.printf("Enter first number: ");
            double a = Double.parseDouble(scn.nextLine());
            listResults.addLast(a);
            while (scn.hasNextLine()) {
                String nextElement = scn.nextLine();
                while (isEscape(nextElement) && listResults.size() > 0) {
                    if (listResults.size() == 1) {
                        System.out.println("There is not last operation any more!!!");
                    } else {
                        listResults.pollLast();                        
                    }
                    System.out.printf("%.2f \n", listResults.getLast());
                    nextElement = scn.nextLine();
                }

                if (!isSignOperation(nextElement)) {
                    System.out.println("You entered wrong operation!!!");
                    continue;
                }
                String sign_operation = nextElement;
                double b = Double.parseDouble(scn.nextLine());
                if (b == 0 && sign_operation.equals("/")) {
                    System.out.println("ERROR: division by zero!!!");
                } else {
                    double result = getResultOfOperation(listResults.getLast(), b, sign_operation);
                    listResults.addLast(result);
                    System.out.println(result);
                    continue;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        scn.close();
    }
}
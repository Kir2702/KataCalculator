package org.example;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        //calc(expression);
        System.out.println(calc(expression));
    }

    public static String calc(String input){

        String expression = input;
        String result;
        try {
            Controller controller = new Controller(expression);
            result = controller.calculation();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    return result;
    }
}

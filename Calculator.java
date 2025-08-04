
import java.util.Scanner;
class Demo 
{
    static int add(int a, int b)
    {
        return a + b;
    }

    static int sub(int a, int b)
    {
        return a - b;
    }

    static int mul(int a, int b)
    {
        return a * b;
    }

    static int div(int a, int b)
    {
        return a / b;
    }
}

public class Calculator 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.print("Enter the operation ");
            char operation = scanner.next().charAt(0);

            System.out.print("Enter First Number ");
            int num1 = scanner.nextInt();

            System.out.print("Enter Second Number ");
            int num2 = scanner.nextInt();

            int result = 0;

        switch(operation)
        {
            case '+':
            result = Demo.add(num1, num2);
            break;

            case '-':
            result = Demo.sub(num1, num2);
            break;

            case '*':
            result = Demo.mul(num1, num2);
            break;

            case '/':
            if(num2 == 0)
            {
                System.out.println("Cannot divide by 0");
                continue;
            }
            result = Demo.div(num1, num2);
            break;

            default:
            System.out.println("Invalid operator");
            continue;
        }

        System.out.println("Result :" +result);

        System.out.println("Do you want to continue (y/n)? ");
        {
            char ch = scanner.next().charAt(0);
            if(ch == 'n' || ch == 'N')
            {
                break;
            }
        }

    }
    scanner.close();
    }
}
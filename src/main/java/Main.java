import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 15.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Parser parser = new Parser();
        List<String> expression = parser.parse(s);
        boolean flag = parser.flag;
        if (flag){
            for (String x : expression)
                System.out.println(x + " ");
            System.out.println();
            Calculator calc = new Calculator();
            System.out.println(calc.calc(expression));
        }
    }
}

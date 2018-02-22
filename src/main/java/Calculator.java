import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Daria on 15.02.2018.
 */
public class Calculator {
    public static Double calc(List<String> input){
        Deque<Double> stack = new ArrayDeque<Double>();
        for (String x : input){
            if (x.equals("sqrt"))
                stack.push(Math.sqrt(stack.pop()));
            else if (x.equals("cube")){
                Double tmp = stack.pop();
                stack.push(tmp * tmp * tmp);
            }
            else if (x.equals("+")) {
                if (stack.size() > 1)
                    stack.push(stack.pop() + stack.pop());
            }
            else if (x.equals("-")){
                Double b  = stack.pop(), a = stack.pop();
                stack.push(a - b);
            }
            else if (x.equals("*"))
                stack.push(stack.pop() * stack.pop());
            else if (x.equals("/")){
                if (stack.size() < 2){
                    System.out.println("Ошибка /3");
                    return stack.pop();
                }
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);

            }
            else if (x.equals("u-"))
                stack.push(-stack.pop());
            else if (x.equals("^")){
                Double b = stack.pop(), a = stack.pop();
                stack.push(Math.pow(a, b));
            }
            else stack.push(Double.valueOf(x));
        }
        return stack.pop();
    }
}

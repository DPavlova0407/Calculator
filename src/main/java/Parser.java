import java.util.*;

/**
 * Created by Daria on 15.02.2018.
 */
public class Parser {
    private static String operators = "+-*/^";
    private static String delimiters = "()+-*/^";
    public static boolean flag = true;
    private static boolean isDelimiter(String token){
        if (token.length() != 1)
            return false;
        for (int i= 0; i < delimiters.length(); i++){
            if (token.charAt(0) == delimiters.charAt(i))
                return true;
        }
        return false;
    }

    private static boolean isOperator(String token){
        if (token.equals("u-")) // унарный минус
            return true;
        for (int i = 0; i < operators.length(); i++){
            if (token.charAt(0) == operators.charAt(i))
                return true;
        }
        return false;
    }

    // ???????
    private static boolean isFunction(String token){
        if (token.equals("sqrt") || token.equals("cube") || token.equals("pow10")) return true;
        return false;
    }

    private static int priority(String token){
        char c = token.charAt(0);
        switch (c){
            case '(':
                return 1;
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 3;
            default:
                return 4;
        }
    }

    public static List<String> parse(String input){
        List<String> result = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        StringTokenizer tokenizer = new StringTokenizer(input, delimiters, true);
        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()){
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)){
                System.out.println("Некорректное выражение.");
                flag = false;
                return result;
            }
            if (curr.equals(" "))
                continue;
            if (isFunction(curr))
                stack.push(curr);
            else if (isDelimiter(curr)){
                if (curr.equals("("))
                    stack.push(curr);
                else if (curr.equals(")")){
                    if (isDelimiter(prev) && !prev.equals(")")){
                        System.out.println("Некорректное выражение");
                        flag = false;
                        return result;
                    }
                    while (!stack.peek().equals("(")){
                        result.add(stack.pop());
                        if (stack.isEmpty()){
                            System.out.println("Скобки не согласованны");
                            flag = false;
                            return result;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())){
                        result.add(stack.pop());
                    }
                }
                else{
                    if (curr.equals("-") && prev.equals("") || (curr.equals("-") && isDelimiter(prev) && !prev.equals(")"))){
                        // унарный минус
                        curr = "u-";
                    }
                    else{
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))){
                            result.add(stack.pop());
                        }
                    }
                    stack.push(curr);
                }
            }
            else {
                /*if (isDelimiter(prev)){
                    System.out.println("Некорректное выражение");
                    flag = false;
                    return result;
                }*/
                result.add(curr);
            }
            prev = curr;
        }
        while (!stack.isEmpty()){
            if (isOperator(stack.peek()))
                result.add(stack.pop());
            else {
                System.out.println("Скобки не согласованны.");
                flag = false;
                return result;
            }
        }
        return result;
    }
}

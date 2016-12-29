package com.company.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by sergey on 13.05.2016.
 */
public class Calculator {

    private static final String INVALID_STRING = "Cannot calculate an invalid expression.";

    public boolean validateExpression(String source) throws IllegalArgumentException
    {
        if (null == source || source.isEmpty())
            throw new IllegalArgumentException(INVALID_STRING);
        source = source.replaceAll("[\\sA-Za-zА-Яа-я]*", ""); // remove all spaces out of the string
        // todo add regexp to check if the string is an arithmetic expression

        if (source.isEmpty())
            throw new IllegalArgumentException(INVALID_STRING);

        // check if the amount of '(' matches the amount of ')'
        if (
                (source.length() - source.replace("(", "").length())
                !=
                (source.length() - source.replace(")", "").length())
                )
            throw new IllegalArgumentException(INVALID_STRING);

        if (!source.matches(".*\\d[\\+\\-\\*\\/]+.*[\\d\\)]+$"))
            throw new IllegalArgumentException(INVALID_STRING);

        if (source.matches("^[\\+\\*\\/]+[\\D\\d]*\\(.*$"))
            throw new IllegalArgumentException(INVALID_STRING);

        return true;
    }

    // transforms the incoming String into the Reverse Polish Notation
    private ArrayList<String> transformToRPN(String source) throws IllegalArgumentException {

        if (null != source)
            source = source.replaceAll(",", "."); // change floating comma to flotating point

        validateExpression(source);

        Stack<String> stack = new Stack<>();

        ArrayList<String> tokens = getTokens(source);

        ArrayList<String> result = new ArrayList<>();
        String temp = "";

        // RPN alrorythm as described here: http://trubetskoy1.narod.ru/ppn.html

        for (String s : tokens) {
            if (s.matches("\\-?\\d+(\\.\\d+)?"))
                result.add(s);
            else
                switch (s) {
                    case "(":
                        stack.push(s);
                        break;
                    case ")":
                        do {
                            if (!stack.isEmpty()) {
                                temp = stack.pop();
                                if (!temp.equals("("))
                                    result.add(temp);
                            }
                        }
                        while (!temp.equals("("));
                        break;
                    default:
                        if (!stack.isEmpty() &&
                                (stack.peek().equals("*") || stack.peek().equals("/")))
                        {
                            do {
                                result.add(stack.pop());
                            }
                            while (!stack.isEmpty() &&
                                    (stack.peek().equals("*") || stack.peek().equals("/")));
                            stack.push(s);
                        }
                        else if (stack.isEmpty() || stack.peek().equals("+") || stack.peek().equals("-") || stack.peek().equals("("))
                            stack.push(s);
                }
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    public BigDecimal calculate(String source) throws IllegalArgumentException {
        ArrayList<String> expression = transformToRPN(source);

        Stack<BigDecimal> stack = new Stack<>();

        for (String s : expression) {
            if (s.matches("\\-?\\d+(\\.\\d+)?"))
                stack.add(new BigDecimal(s));
            else {
                BigDecimal d1 = stack.pop();
                BigDecimal d2 = stack.pop();
                switch (s) {
                    case "-":
                        stack.push(d2.subtract(d1));
                        break;
                    case "+":
                        stack.push(d1.add(d2));
                        break;
                    case "/":
                        if (d1.compareTo(new BigDecimal(0)) == 0)
                            throw new IllegalArgumentException("Division by zero");
                        else
                            stack.push(d2.divide(d1, 7, BigDecimal.ROUND_HALF_UP));
                        break;
                    case "*":
                        stack.push(d1.multiply(d2));
                        break;
                }
            }
        }
        return stack.pop();
    }

    private ArrayList<String> getTokens(String source) {
        ArrayList<String> result = new ArrayList<>();
        char[] buf = source.toCharArray();
        String temp = "";
        for (int i = 0; i < buf.length; i++) {

            if (buf[i] == '-' &&
                    (i == 0 || buf[i - 1] == '(' || buf[i - 1] == '-')) // this is a negative number
                temp = temp + String.valueOf(buf[i]);
            else {
                if (    // trying to correct the wrong string, e.g. 2+*2 should become 2*2
                        (i > 0) &&
                                (buf[i] == '+' || buf[i] == '*' || buf[i] == '/' || buf[i] == '-') &&
                                (buf[i - 1] == '+' || buf[i - 1] == '-' || buf[i - 1] == '*' || buf[i - 1] == '/')) {
                    result.remove(result.size() - 1);
                    result.add(String.valueOf(buf[i]));
                } else // we have reached an arithmetic operator, need to save the number
                    if (buf[i] == '(' || buf[i] == ')' || buf[i] == '+' || buf[i] == '*' || buf[i] == '/' || buf[i] == '-') {
                        if (!temp.isEmpty()) {
                            result.add(temp);
                            temp = "";
                        }
                        result.add(String.valueOf(buf[i]));
                    } else
                        // this should be a number
                        temp = temp + String.valueOf(buf[i]);
            }
        }
        if (!temp.isEmpty())
            result.add(temp);
        return result;

    }
}

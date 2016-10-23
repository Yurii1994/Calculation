package com.project.myv.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Calculation
{
    private double getRadianWithGrad(double grad)
    {
        return grad * (Math.PI / 200);
    }

    private double getGradWithRadian(double radian)
    {
        return radian / (Math.PI / 200);
    }

    private double getRadianWithDeg(double deg)
    {
        return deg * (Math.PI / 180);
    }

    private double getDegWithRadian(double radian)
    {
        return radian / (Math.PI / 180);
    }

    private double getFactorial(double n)
    {
        double result;
        if (n % 1 == 0)
        {
            int a = 1;
            if (n < 0)
            {
                a = -1;
                n = n * -1;
            }
            if (n == 1)
            {
                return 1;
            }
            else
            {
                if (n > 5000)
                {
                    result = Double.POSITIVE_INFINITY;
                }
                else
                {
                    result = 1;
                    for (int i = 1; i <= n; ++i)
                    {
                        result *= i;
                    }
                }
                return result * a;
            }
        }
        else
        {
            double x = n + 1;
            return gamma(x);
        }
    }

    private double logGamma(double n)
    {
        double a = (n - 0.5) * Math.log(n + 4.5) - (n + 4.5);
        double b = 1.0 + 76.18009173 / (n + 0) - 86.50532033 / (n + 1) + 24.01409822 / (n + 2) - 1.231739516 / (n + 3)
                +  0.00120858003 / (n + 4) - 0.00000536382 / (n + 5);
        return a + Math.log(b * Math.sqrt(2 * Math.PI));
    }
    private double gamma(double x)
    {
        return Math.exp(logGamma(x));
    }


    private double getPercent(double a)
    {
        return a / 100;
    }

    static Double calculationResult;

    private int getCountOpenBr(String text)
    {
        int count = 0;
        char b;
        for (int i = 0; i < text.length(); i++)
        {
            b = text.charAt(i);
            if (b == '(')
            {
                count++;
            }
        }
        return count;
    }

    private ArrayList<String> getListBrOperationToCalculation(String text)
    {
        ArrayList<String> list = new ArrayList<>();
        String str;
        char b;
        int count = getCountOpenBr(text);
        int c = 0;
        if (count > 0)
        {
            for (int j = 0; j < count; j++)
            {
                int openBr = 0;
                int closeBr = 0;
                for (int i = 0; i < text.length(); i++)
                {
                    b = text.charAt(i);
                    if (b == '(')
                    {
                        openBr = i;
                    }
                    else
                    if (b == ')')
                    {
                        closeBr = i;
                        break;
                    }
                }
                if (closeBr - openBr > 1)
                {
                    c++;
                    str = "["+ c + "N]" + text.substring(openBr + 1, closeBr);
                    list.add(str);
                    text = text.substring(0, openBr) + "["+ c + "N]" + text.substring(closeBr + 1, text.length());
                    if (!text.contains("("))
                    {
                        c++;
                        text = "["+ c + "N]" + text;
                        list.add(text);
                    }
                }
            }
        }
        else
        {
            if (text.length() > 0)
            {
                c++;
                text = "["+ c + "N]" + text;
                list.add(text);
            }
        }
        return list;
    }

    private boolean getStatePercent(String text, String operation, int position)
    {
        boolean state = false;
        int x = 0;
        if (operation.equals("-") || operation.equals("+"))
        {
            for (int i = position + 1; i < text.length(); i++)
            {
                char b = text.charAt(i);
                if (b == '-' || b == '+' || b == '*' || b == '/' || b == '(')
                {
                    state = false;
                    break;
                }
                if (b == '%')
                {
                    if (text.charAt(i - 1) == ')' || text.charAt(i - 1) == ']')
                    {
                        int a = 0;
                        boolean t = false;
                        for (int z = i - 1; z >= 0; z--)
                        {
                            b = text.charAt(z);
                            if (b == '(' || b == '[')
                            {
                                a = z;
                                t = true;
                                break;
                            }
                        }
                        if (t)
                        {
                            if (getStateNumberToPercent(text, a))
                            {
                                state = true;
                                x = i;
                                break;
                            }
                        }
                    }
                    else
                    {
                        state = true;
                        x = i;
                        break;
                    }
                }
            }
        }
        if (getStateOperationAfterPercent(text, x))
        {
            state = false;
        }
        return state;
    }

    private boolean getStateOperationAfterPercent(String text, int pos)
    {
        boolean state = false;
        for (int i = pos; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if ((b == '*' || b == '/' || b == '^' || b == '!') & i - pos == 1)
            {
                state = true;
            }
        }
        return state;
    }

    public Map<String, String> getListMapOperation(String text)
    {
        Map<String, String> listMap = new LinkedHashMap<>();
        List<String> list = Arrays.asList( "sin", "cos", "tan", "asin", "acos", "atan", "ln", "log",
                "√", "!", "%", "^", "/", "*", "-", "+");
        int a = 0;
        String str = "";
        char c = '.';
        int listI = 0;
        for (int i = 0; i < text.length(); i++)
        {
            for (int j = listI; j < list.size(); j++)
            {
                String str1 = list.get(j);
                String str2 = "";
                if (i + str1.length() <= text.length())
                {
                    str2 = text.substring(i, i + str1.length());
                }
                if (str2.equals(str1))
                {
                    boolean z = getStatePercent(text, str1, i);
                    String statePercent;
                    if (z)
                    {
                        statePercent = "{T}";
                        list = Arrays.asList( "sin", "cos", "tan", "asin", "acos", "atan", "ln", "log",
                                "√", "!", "^", "*", "/", "-", "+");
                    }
                    else
                    {
                        statePercent = "{F}";
                        list = Arrays.asList( "sin", "cos", "tan", "asin", "acos", "atan", "ln", "log",
                                "√", "!", "%", "^", "*", "/", "-", "+");

                    }
                    if (str1.length() > 1)
                    {
                        if (i - a > 1)
                        {
                            str = "";
                        }
                        if (!str.contains(str1))
                        {
                            str = str1;
                            a = i;
                            listMap.put(i + statePercent, str1);
                        }
                    }
                    else
                    {
                        if (i > 0)
                        {
                            c = text.charAt(i - 1);
                        }
                        if (c != 'E')
                        {
                            listMap.put(i + statePercent, str1);
                        }
                    }
                }
            }
        }
        return listMap;
    }

    public String getKey(String key)
    {
        String res = key;
        int pos = 0;
        if (key.contains("{"))
        {
            for (int i = 0; i < key.length(); i++)
            {
                char b = key.charAt(i);
                if (b == '{')
                {
                    pos = i;
                    break;
                }
            }
            res = key.substring(0, pos);
        }
        return res;
    }

    public Map<String, String> getListMapOperationSorted(Map<String, String> listMap)
    {
        Map<String, String> listMapResult = new LinkedHashMap<>();
        List<String> listOperations = Arrays.asList("sin", "cos", "tan", "asin", "acos", "atan", "ln", "log",
                "√", "!", "%", "^", "/", "*", "-", "+");
        boolean statePercent = false;
        for (Map.Entry entry : listMap.entrySet())
        {
            String key = entry.getKey().toString();
            if (key.contains("{T}"))
            {
                statePercent = true;
            }
        }
        if (statePercent)
        {

            for (int j = 0; j < listOperations.size(); j++)
            {
                for (Map.Entry entry : listMap.entrySet())
                {
                    String valueKey = entry.getKey().toString();
                    String str = listOperations.get(j);
                    if (valueKey.contains("{F}"))
                    {
                        String value = entry.getValue().toString();
                        String key = getKey(entry.getKey().toString());
                        if (value.equals(str))
                        {
                            listMapResult.put(key, value);
                        }
                    }
                    else
                    {
                        String value = entry.getValue().toString();
                        String key = entry.getKey().toString();
                        if (value.equals(str))
                        {
                            listMapResult.put(key, value);
                        }
                    }
                }
            }
        }
        else
        {
            for (int j = 0; j < listOperations.size(); j++)
            {
                String str = listOperations.get(j);
                for (Map.Entry entry : listMap.entrySet())
                {
                    String value = entry.getValue().toString();
                    String key = getKey(entry.getKey().toString());
                    if (value.equals(str))
                    {
                        listMapResult.put(key, value);
                    }
                }
            }
        }

        return listMapResult;
    }

    private int getPositionMax(String text, int max)
    {
        if (max > 0)
        {
            return max;
        }
        else
        {
            char b;
            for (int i = 0; i < text.length(); i++)
            {
                b = text.charAt(i);
                if (b == '[')
                {
                    max = i;
                    break;
                }
            }
            String str = text.substring(0, max);
            Map<String, String> listMap = getListMapOperation(str);
            if (listMap.size() > 0)
            {
                max = 0;
            }
            if (max != text.length() - 1)
            {
                return max;
            }
            else
            {
                return 0;
            }
        }
    }

    private int getPositionMin(String text, int min)
    {
        if (min > 0)
        {
            return min;
        }
        else
        {
            return text.length();
        }
    }

    private ArrayList<String> getListOperationToCalculation(String text)
    {
        ArrayList<String> listResult = new ArrayList<>();
        Map<String, String> listMap = getListMapOperationSorted(getListMapOperation(text));
        List<String> listOperations = Arrays.asList("asin", "acos", "atan", "sin", "cos", "tan", "ln", "log",
                "√", "!", "%", "^", "/", "*", "-", "+");
        int count = 0;
        int e = listMap.size();
        for (int i = 0; i < e; i++)
        {
            if (listMap.size() == 1)
            {
                count++;
                text = "["+ count + "M]" + text;
                listResult.add(text);
                break;
            }
            boolean changeListMap = false;
            for (Map.Entry entry : listMap.entrySet())
            {
                String key = getKey(entry.getKey().toString());
                String value = entry.getValue().toString();
                Integer position = Integer.valueOf(key);
                if (!entry.getKey().toString().contains("T"))
                {
                    for (int j = 0; j < listOperations.size(); j++)
                    {
                        String operation = listOperations.get(j);
                        if (operation.equals(value))
                        {
                            int max = 0;
                            int min = 0;
                            ArrayList<Integer> list1 = new ArrayList<>();
                            ArrayList<Integer> list2 = new ArrayList<>();
                            String str;
                            if (operation.equals("√") || operation.equals("asin") || operation.equals("acos")
                                    || operation.equals("atan") || operation.equals("sin") || operation.equals("cos")
                                    || operation.equals("tan") || operation.equals("ln") || operation.equals("log"))
                            {
                                count++;
                                for (String keyAll : listMap.keySet())
                                {
                                    Integer positionAll = Integer.valueOf(getKey(keyAll));
                                    if (positionAll > position)
                                    {
                                        list1.add(positionAll);
                                    }
                                }
                                if (list1.size() > 0)
                                {
                                    min = list1.get(0);
                                }
                                for (int k = 0; k < list1.size(); k++)
                                {
                                    int a = list1.get(k);
                                    min = Math.min(min, a);
                                }
                                min = getPositionMin(text, min);
                                str = "[" + count + "M]" + text.substring(position, min);
                                text = text.substring(0, position) + "[" + count + "M]" + text.substring(min, text.length());
                                listResult.add(str);
                                listMap = getListMapOperationSorted(getListMapOperation(text));
                                changeListMap = true;
                            }
                            else
                            {
                                if (operation.equals("!") || operation.equals("%"))
                                {
                                    count++;
                                    for (String keyAll : listMap.keySet())
                                    {
                                        Integer positionAll = Integer.valueOf(getKey(keyAll));
                                        if (positionAll < position)
                                        {
                                            list1.add(positionAll);
                                        }
                                    }
                                    if (list1.size() > 0)
                                    {
                                        max = list1.get(0);
                                    }
                                    for (int k = 0; k < list1.size(); k++)
                                    {
                                        int a = list1.get(k);
                                        max = Math.max(max, a);
                                    }
                                    max = getPositionMax(text, max);
                                    if (max == 0)
                                    {
                                        str = "[" + count + "M]" + text.substring(max, position) + value;
                                        text = text.substring(0, max) + "[" + count + "M]" + text.substring(position + 1, text.length());
                                    }
                                    else
                                    {
                                        str = "[" + count + "M]" + text.substring(max + 1, position) + value;
                                        text = text.substring(0, max + 1) + "[" + count + "M]" + text.substring(position + 1, text.length());
                                    }
                                    listResult.add(str);
                                    listMap = getListMapOperationSorted(getListMapOperation(text));
                                    changeListMap = true;
                                }
                                else
                                if (operation.equals("^") || operation.equals("+") || operation.equals("-")
                                        || operation.equals("*") || operation.equals("/"))
                                {
                                    count++;
                                    max = 0;
                                    min = 0;
                                    for (String keyAll : listMap.keySet())
                                    {
                                        Integer positionAll = Integer.valueOf(getKey(keyAll));
                                        if (positionAll < position)
                                        {
                                            list1.add(positionAll);
                                        }
                                        else
                                        if (positionAll > position & positionAll - position != 1)
                                        {
                                            list2.add(positionAll);
                                        }
                                    }
                                    if (list1.size() > 0)
                                    {
                                        max = list1.get(0);
                                    }
                                    for (int k = 0; k < list1.size(); k++)
                                    {
                                        int a = list1.get(k);
                                        max = Math.max(max, a);
                                    }
                                    if (list2.size() > 0)
                                    {
                                        min = list2.get(0);
                                    }
                                    for (int k = 0; k < list2.size(); k++)
                                    {
                                        int a = list2.get(k);
                                        min = Math.min(min, a);
                                    }
                                    min = getPositionMin(text, min);
                                    max = getPositionMax(text, max);
                                    if (max == position)
                                    {
                                        str = "[" + count + "M]" + text.substring(max, position) + value + text.substring(position + 1, min);
                                        text = text.substring(0, max) + "[" + count + "M]" + text.substring(min, text.length());
                                    }
                                    else
                                    if (min - position == 1)
                                    {
                                        list2.clear();
                                        for (String keyAll : listMap.keySet())
                                        {

                                            Integer positionAll = Integer.valueOf(keyAll);
                                            if (positionAll > position & positionAll > min)
                                            {
                                                list2.add(positionAll);
                                            }
                                        }
                                        min = 0;
                                        for (int k = 0; k < list2.size(); k++)
                                        {
                                            int a = list2.get(k);
                                            min = Math.min(min, a);
                                        }
                                        min = getPositionMin(text, min);
                                        str = "[" + count + "M]" + text.substring(max, position) + value + text.substring(position + 1, min);
                                        text = text.substring(0, max) + "[" + count + "M]" + text.substring(min, text.length());
                                    }
                                    else
                                    if (max == 0)
                                    {
                                        str = "[" + count + "M]" + text.substring(max, position) + value + text.substring(position + 1, min);
                                        text = text.substring(0, max) + "[" + count + "M]" + text.substring(min, text.length());
                                    }
                                    else
                                    {
                                        str = "[" + count + "M]" + text.substring(max + 1, position) + value + text.substring(position + 1, min);
                                        text = text.substring(0, max + 1) + "[" + count + "M]" + text.substring(min, text.length());
                                    }
                                    listResult.add(str);
                                    listMap = getListMapOperationSorted(getListMapOperation(text));
                                    changeListMap = true;
                                }
                            }
                        }
                        if (changeListMap)
                        {
                            break;
                        }
                    }
                    if (changeListMap)
                    {
                        break;
                    }
                }
            }
        }
        return listResult;
    }

    private int getPositionClosedQuadraticBr(String text)
    {
        int position = 0;
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == ']')
            {
                position = i;
                break;
            }
        }
        return position;
    }

    private Map<String, String> getListMapOperation(ArrayList<String> list)
    {
        Map<String, String> listMapBrOperation = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            int position = getPositionClosedQuadraticBr(str) + 1;
            String key = str.substring(0, position);
            String value = str.substring(position, str.length());
            listMapBrOperation.put(key, value);
        }
        return listMapBrOperation;
    }

    private int getCountChangeNumber(String text)
    {
        int count;
        char b;
        int a = 0;
        int c = 0;
        for (int i = 0; i < text.length(); i++)
        {
            b = text.charAt(i);
            if (b == 'N')
            {
                a++;
            }
        }
        for (int i = 0; i < text.length(); i++)
        {
            b = text.charAt(i);
            if (b == 'M')
            {
                c++;
            }
        }
        count = Math.max(a, c);
        return count;
    }

    private Map<Integer, String> getMapSubBrSub(String text)
    {
        String str1 = "-(-";
        Map<Integer, String> listMap = new LinkedHashMap<>();
        for (int i = 0; i < text.length(); i++)
        {
            String str2 = "";
            if (i + str1.length() <= text.length())
            {
                str2 = text.substring(i, i + str1.length());
            }
            if (str2.equals(str1))
            {
                listMap.put(i, str1);
            }
        }
        return listMap;
    }

    private String getTextToCalc(String text, String key, Double a)
    {
        text = text.replace(key, "(" + a + ")");
        return text;
    }

    private ArrayList<String> getListTrigonometricAndSqrtOperation(String text)
    {
        ArrayList<String> list = new ArrayList<>();
        List<String> listOperations = Arrays.asList("asin", "acos", "atan", "sin", "cos", "tan", "ln", "log",
                "√");
        int a = 0;
        String str = "";
        for (int i = 0; i < text.length(); i++)
        {
            for (int j = 0; j < listOperations.size(); j++)
            {
                String str1 = listOperations.get(j);
                String str2 = "";
                if (i + str1.length() <= text.length())
                {
                    str2 = text.substring(i, i + str1.length());
                }
                if (str2.equals(str1))
                {
                    if (str1.length() > 1)
                    {
                        if (i - a > 1)
                        {
                            str = "";
                        }
                        if (!str.contains(str1))
                        {
                            str = str1;
                            a = i;
                            list.add(str1);
                        }
                    }
                    else
                    {
                        list.add(str1);
                    }
                }
            }
        }
        return list;
    }

    private ArrayList<Integer> getListPositionOperationToPercent(String text)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == '-' || b == '+' || b == '*' || b == '/')
            {
                list.add(i);
            }
        }
        return list;
    }

    private Double getCalc(String text)
    {
        double result = 0.0;
        double pi = Math.PI;
        double e = Math.E;
        String textCalculationResult = text;
        if (text.contains("√") || text.contains("asin") || text.contains("acos")
                || text.contains("atan") || text.contains("sin") || text.contains("cos")
                || text.contains("tan") || text.contains("ln") || text.contains("log"))
        {
            String str1 = "";
            ArrayList<String> list = getListTrigonometricAndSqrtOperation(text);
            for (int i = 0; i < list.size(); i++)
            {
                str1 = list.get(i);
            }
            String str2 = "";
            if (str1.length() > 0)
            {
                str2 = text.substring(str1.length(), text.length());
            }
            if (str2.contains("("))
            {
                str2 = str2.replace("(", "");
                str2 = str2.replace(")", "");
            }
            if (str2.contains("e") || str2.contains("π"))
            {
                if (str2.contains("e"))
                {
                    str2 = str2.replace("e", String.valueOf(e));
                }
                else
                {
                    str2 = str2.replace("π", String.valueOf(pi));
                }
            }
            String TAG_ANGLE = MainActivity.TAG_ANGLE;
            switch (str1)
            {
                case "√":
                    result = Math.sqrt(Double.parseDouble(str2));
                    break;

                case "asin":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.asin(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = getGradWithRadian(Math.asin(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = getDegWithRadian(Math.asin(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "acos":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.acos(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = getGradWithRadian(Math.acos(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = getDegWithRadian(Math.acos(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "atan":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.atan(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = getGradWithRadian(Math.atan(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = getDegWithRadian(Math.atan(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "sin":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.sin(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = Math.sin(getRadianWithGrad(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = Math.sin(getRadianWithDeg(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "cos":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.cos(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = Math.cos(getRadianWithGrad(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = Math.cos(getRadianWithDeg(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "tan":
                    switch (TAG_ANGLE)
                    {
                        case "RAD":
                            result = Math.tan(Double.parseDouble(str2));
                            break;

                        case "GRAD":
                            result = Math.tan(getRadianWithGrad(Double.parseDouble(str2)));
                            break;

                        case "DEG":
                            result = Math.tan(getRadianWithDeg(Double.parseDouble(str2)));
                            break;
                    }
                    break;

                case "ln":
                    result = Math.log(Double.parseDouble(str2));
                    break;

                case "log":
                    result = Math.log10(Double.parseDouble(str2));
                    break;
            }
        }
        else
        if (text.contains("%"))
        {
            try
            {
                int a = 0;
                char b;
                for (int i = 0; i < text.length(); i++)
                {
                    b = text.charAt(i);
                    if (b == '%')
                    {
                        a = i;
                    }
                }
                String str = text.substring(0, a);
                str = str.replace("(", "");
                str = str.replace(")", "");
                str = str.replace("--", "");
                if (str.contains("e") || str.contains("π"))
                {
                    if (str.contains("e"))
                    {
                        str = str.replace("e", String.valueOf(e));
                    }
                    else
                    {
                        str = str.replace("π", String.valueOf(pi));
                    }
                }
                result = getPercent(Double.parseDouble(str));
            }
            catch (Exception z)
            {
                text = text.replace("(", "");
                text = text.replace(")", "");
                text = text.replace("--", "+");
                text = text.replace("+-", "-");
                ArrayList<Integer> list = getListPositionOperationToPercent(text);
                int position = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    position = list.get(i);
                }
                String str1 = text.substring(0, position);
                if (str1.contains("e") || str1.contains("π"))
                {
                    if (str1.contains("e"))
                    {
                        str1 = str1.replace("e", String.valueOf(e));
                    }
                    else
                    {
                        str1 = str1.replace("π", String.valueOf(pi));
                    }
                }
                String operation = text.substring(position, position + 1);
                String str2 = text.substring(position + 1, text.length());
                str2 = str2.replace("%", "");
                if (str2.contains("e") || str2.contains("π"))
                {
                    if (str2.contains("e"))
                    {
                        str2 = str2.replace("e", String.valueOf(e));
                    }
                    else
                    {
                        str2 = str2.replace("π", String.valueOf(pi));
                    }
                }
                if (Double.parseDouble(str1) == 0)
                {
                    result = getPercent(Double.parseDouble(str2));
                }
                else
                {
                    if (operation.equals("-"))
                    {
                        result = Double.parseDouble(str1) - Double.parseDouble(str1) * getPercent(Double.parseDouble(str2));
                    }
                    else
                    {
                        result = Double.parseDouble(str1) + Double.parseDouble(str1) * getPercent(Double.parseDouble(str2));
                    }
                }
            }
        }
        else
        if (text.contains("!"))
        {
            int a = 0;
            char b;
            for (int i = 0; i < text.length(); i++)
            {
                b = text.charAt(i);
                if (b == '!')
                {
                    a = i;
                }
            }
            String str = text.substring(0, a);
            str = str.replace("(", "");
            str = str.replace(")", "");
            str = str.replace("--", "");
            if (str.contains("e") || str.contains("π"))
            {
                if (str.contains("e"))
                {
                    str = str.replace("e", String.valueOf(e));
                }
                else
                {
                    str = str.replace("π", String.valueOf(pi));
                }
            }
            result = getFactorial(Double.parseDouble(str));
        }
        else
        if (text.contains("+") || text.contains("-") || text.contains("*")
                || text.contains("/") || text.contains("^"))
        {
            boolean statePow = false;
            if (text.contains("^") & text.contains("-(-") || text.contains("-(") & text.contains("^"))
            {
                statePow = true;
            }
            if (text.contains("-(-") & !text.contains("^"))
            {
                Map<Integer, String> listMapSubBrSub = getMapSubBrSub(text);
                for (int i = 0; i < listMapSubBrSub.size(); i++)
                {
                    for (Map.Entry entry : listMapSubBrSub.entrySet())
                    {
                        Integer key = Integer.parseInt(entry.getKey().toString());
                        if (key == 0)
                        {
                            String str1 = text.substring(0, "-(-".length());
                            String str2 = text.substring("-(-".length(), text.length());
                            str1 = str1.replace("-(-", "");
                            text = str1 + str2;
                        }
                        else
                        {
                            text = text.replace("-(-", "+");
                        }
                    }
                }
                text = text.replace("(", "");
                text = text.replace(")", "");
                if (text.contains("e") || text.contains("π"))
                {
                    if (text.contains("e"))
                    {
                        text = text.replace("e", String.valueOf(e));
                    }
                    else
                    {
                        text = text.replace("π", String.valueOf(pi));
                    }
                }
            }
            try
            {
                result = Double.parseDouble(text);
            }
            catch (Exception z)
            {
                String str = text;
                if (text.contains("e") || text.contains("π"))
                {
                    if (text.contains("e"))
                    {
                        text = text.replace("e", String.valueOf(e));
                    }
                    else
                    {
                        text = text.replace("π", String.valueOf(pi));
                    }
                }
                try
                {
                    text = text.replace("(", "");
                    text = text.replace(")", "");
                    result = Double.parseDouble(text);
                }
                catch (Exception c)
                {
                    text = str;
                    Map<String, String> listMap = getListMapOperationSorted(getListMapOperation(text));
                    String operation = "";
                    int positionOp = 0;
                    String str1 = "";
                    String str2 = "";
                    if (listMap.size() > 2)
                    {
                        int max = 0;
                        for (Map.Entry entry : listMap.entrySet())
                        {
                            Integer key = Integer.parseInt(entry.getKey().toString());
                            max = Math.max(max, key);
                        }
                        listMap.remove(max + "");
                        for (Map.Entry entry : listMap.entrySet())
                        {
                            Integer key = Integer.parseInt(entry.getKey().toString());
                            String value = entry.getValue().toString();
                            if (key != 0 & key != 1)
                            {
                                operation = value;
                                positionOp = key;
                                break;
                            }
                        }
                        str1 = text.substring(0, positionOp);
                        str2 = text.substring(positionOp + 1, text.length());
                        if (str1.contains("("))
                        {
                            str1 = str1.replace("(", "");
                            str1 = str1.replace(")", "");
                        }
                        if (str2.contains("("))
                        {
                            str2 = str2.replace("(", "");
                            str2 = str2.replace(")", "");
                        }
                    }
                    else
                    {

                        if (!statePow)
                        {
                            text = text.replace("(", "");
                            text = text.replace(")", "");
                            listMap = getListMapOperationSorted(getListMapOperation(text));
                            Integer key = 0;
                            if (listMap.size() == 2)
                            {
                                for (Map.Entry entry : listMap.entrySet())
                                {
                                    if (key != 0)
                                    {
                                        int key2 = key;
                                        key = Integer.parseInt(entry.getKey().toString());
                                        int max = Math.max(key2, key);
                                        int min = Math.min(key2, key);
                                        if (max - min == 1)
                                        {
                                            listMap.remove(max + "");
                                        }
                                    }
                                    else
                                    {
                                        key = Integer.parseInt(entry.getKey().toString());
                                    }
                                }
                            }
                            for (Map.Entry entry : listMap.entrySet())
                            {
                                key = Integer.parseInt(entry.getKey().toString());
                                String value = entry.getValue().toString();
                                if (key != 0)
                                {
                                    operation = value;
                                    positionOp = key;
                                    break;
                                }
                            }
                            str1 = text.substring(0, positionOp);
                            str2 = text.substring(positionOp + 1, text.length());
                        }

                    }
                    if (!statePow)
                    {
                        if (str1.contains("e") || str1.contains("π"))
                        {
                            if (str1.contains("e"))
                            {
                                str1 = str1.replace("e", String.valueOf(e));
                            }
                            else
                            {
                                str1 = str1.replace("π", String.valueOf(pi));
                            }
                        }
                        if (str2.contains("e") || str2.contains("π"))
                        {
                            if (str2.contains("e"))
                            {
                                str2 = str2.replace("e", String.valueOf(e));
                            }
                            else
                            {
                                str2 = str2.replace("π", String.valueOf(pi));
                            }
                        }
                        switch (operation)
                        {
                            case "-":
                                result = Double.parseDouble(str1) - Double.parseDouble(str2);
                                break;

                            case "+":
                                result = Double.parseDouble(str1) + Double.parseDouble(str2);
                                break;

                            case "*":
                                result = Double.parseDouble(str1) * Double.parseDouble(str2);
                                break;

                            case "/":
                                result = Double.parseDouble(str1) / Double.parseDouble(str2);
                                break;

                            case "^":
                                result = Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                                break;
                        }
                    }
                    else
                    {
                        result = getCalcPow(text);
                    }
                }
            }
        }
        else
        {
            text = text.replace("(", "");
            text = text.replace(")", "");
            result = Double.parseDouble(text);
        }
        if (MainActivity.stateCalculationCourse)
        {
            MainActivity.etNum.getArrayListToCalculationCourse(ExtendEditText.listCalculatingCourse, textCalculationResult, result);
        }
        return result;
    }

    private double getCalcPow(String text)
    {
        double result = 0.0;
        double pi = Math.PI;
        double e = Math.E;
        LinkedHashMap<Integer, String> list = getListPositionBr(text);
        boolean v = false;
        Integer posOpen = -1;
        Integer posClose = -1;
        String textToCalc = "";
        for (Map.Entry entry : list.entrySet())
        {
            String value = entry.getValue().toString();
            if (value.equals("(") & !v)
            {
                v = true;
                posOpen = (int) entry.getKey();
            }
            else
            {
                v = false;
                posClose = (int) entry.getKey();
            }
            if (posOpen != -1 & posClose != -1)
            {
                textToCalc = text.substring(posOpen + 1, posClose);
            }
        }
        String pow = "";
        v = false;
        int a = -1;
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == '^')
            {
                v = true;
                a = i;
            }
            if (v & a < i)
            {
                pow = pow + b;
            }
        }
        if (textToCalc.length() > 0 & pow.length() > 0)
        {
            if (textToCalc.contains("e") || textToCalc.contains("π"))
            {
                if (textToCalc.contains("e"))
                {
                    textToCalc = textToCalc.replace("e", String.valueOf(e));
                }
                else
                {
                    textToCalc = textToCalc.replace("π", String.valueOf(pi));
                }
            }
            if (pow.contains("e") || pow.contains("π"))
            {
                if (pow.contains("e"))
                {
                    pow = pow.replace("e", String.valueOf(e));
                }
                else
                {
                    pow = pow.replace("π", String.valueOf(pi));
                }
            }
            pow = pow.replace("(", "");
            pow = pow.replace(")", "");
            result = Math.pow(Double.parseDouble(textToCalc), Double.parseDouble(pow)) * -1;
        }
        return result;
    }

    private LinkedHashMap<Integer, String> getListPositionBr(String text)
    {
        LinkedHashMap<Integer, String> list = new LinkedHashMap<>();
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == '(' || b == ')') {
                list.put(i, b + "");
            }
        }
        return list;
    }

    private ArrayList<Integer> getListStatePercent(String text)
    {
        ArrayList<Integer> listStatePercent = new ArrayList<>();
        for (int j = 0; j < text.length(); j++)
        {
            char b = text.charAt(j);
            if (b == '-' || b == '+')
            {
                for (int i = j + 1; i < text.length(); i++)
                {
                    b = text.charAt(i);
                    if (b == '-' || b == '+' || b == '*' || b == '/' || b == '(')
                    {
                        break;
                    }
                    if (b == '%')
                    {
                        if (text.charAt(i - 1) == ')')
                        {
                            int a = 0;
                            boolean x = false;
                            for (int z = i - 1; z >= 0; z--)
                            {
                                b = text.charAt(z);
                                if (b == '(')
                                {
                                    a = z;
                                    x = true;
                                    break;
                                }
                            }
                            if (x)
                            {
                                if (getStateNumberToPercent(text, a))
                                {
                                    listStatePercent.add(i);
                                    break;
                                }
                            }
                        }
                        else
                        if (!getStateOperationAfterPercent(text, i))
                        {
                            listStatePercent.add(i);
                            break;
                        }
                    }
                }
            }
        }
        return listStatePercent;
    }

    private boolean getStateNumberToPercent(String text, int pos)
    {
        boolean a = false;
        for (int i = pos - 1; i >= 0; i--)
        {
            char b = text.charAt(i);
            if (b == '(')
            {
                break;
            }
            if (b == '1' || b == '2' || b == '3' || b == '4' || b == '5' || b == '6' || b == '7' || b == '8' || b == '9' || b == '0')
            {
                a = true;
            }
        }
        return a;
    }

    private int getCountCloseBrToStatePercent(String text, int positionPercent, int positionOpenBr)
    {
        int count = 0;
        for (int i = positionOpenBr; i <= positionPercent; i++)
        {
            char b = text.charAt(i);
            if (b == ')')
            {
                count++;
            }
        }
        return count;
    }

    private int getCountOpenBrToStatePercent(String text, int positionPercent, int positionOpenBr)
    {
        int count = 0;
        for (int i = positionOpenBr; i <= positionPercent; i++)
        {
            char b = text.charAt(i);
            if (b == '(')
            {
                count++;
            }
        }
        return count;
    }

    private boolean getStateCloseBrAfterStatePercent(String text, int pos)
    {
        boolean s = false;
        if (pos < text.length() - 1)
        {
            char b = text.charAt(pos + 1);
            if (b == ')')
            {
                s = true;
            }
        }
        return s;
    }

    private String getTextStatePercent(String text, ArrayList<Integer> list)
    {
        String textRes = text;
        for (int i = 0; i < list.size(); i++)
        {
            int positionPercent = list.get(i);
            int positionToPercent = 0;
            boolean c = getStateCloseBrAfterStatePercent(text, positionPercent);
            for (int j = positionPercent; j >= 0; j--)
            {
                char b = text.charAt(j);
                if (b == '(')
                {
                    int countOpenBr = getCountOpenBrToStatePercent(text, positionPercent, j);
                    int countCloseBr = getCountCloseBrToStatePercent(text, positionPercent, j);
                    if (c)
                    {
                        countCloseBr++;
                    }
                    if (countOpenBr != countCloseBr & countOpenBr > countCloseBr )
                    {
                        positionToPercent = j;
                        break;
                    }
                }
            }
            String str1 = text.substring(0, positionToPercent);
            String str2 = text.substring(positionToPercent, positionPercent + 1);
            String str3 = text.substring(positionPercent + 1, text.length());

            String strRes;
            if (c)
            {
                strRes = str1 + str2 + str3;
            }
            else
            {
                strRes = str1 + "(" + str2 + ")" + str3;
            }
            text = strRes;
            list = getListStatePercent(text);
            textRes = text;
        }
        return textRes;
    }

    private ArrayList<String> getListStateFactorial(String text)
    {
        ArrayList<String> listStateFactorial = new ArrayList<>();
        for (int j = 0; j < text.length(); j++)
        {
            char b = text.charAt(j);
            if (j < text.length() - 1)
            {
                if (b == '%')
                {
                    b = text.charAt(j + 1);
                    if (b == '!')
                    {
                        listStateFactorial.add(j + "[" + "T" + "]");
                    }
                }
                else
                if (b == '!')
                {
                    b = text.charAt(j + 1);
                    if (b == '%')
                    {
                        listStateFactorial.add(j + "[" + "F" + "]");
                    }
                }
            }
        }
        return listStateFactorial;
    }

    private int getPosOperationForFac(String text)
    {
        int pos = 0;
        int a = 0;
        for (int i = 0; i <= text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == '[')
            {
                a = i;
                break;
            }
        }
        String str = text.substring(0, a);
        if (!str.equals(""))
        {
            pos = Integer.parseInt(str);
        }
        return pos;
    }

    private int getPositionOperationToFac(String text, int position)
    {
        int pos = 0;
        for (int i = position; i >= 0; i--)
        {
            char b = text.charAt(i);
            if (b == '+' || b == '-' || b == '*' || b == '/' || b == '(')
            {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private String getTextStateFactorial(String text, ArrayList<String> list)
    {
        String textRes = text;
        for (int i = 0; i < list.size(); i++)
        {
            int position = getPosOperationForFac(list.get(i));
            int posToPosition = getPositionOperationToFac(text, position);
            String str1;
            String str2;
            if (posToPosition == 0)
            {
                str1 = text.substring(0, posToPosition);
                str2 = text.substring(posToPosition, position + 1);
            }
            else
            {
                str1 = text.substring(0, posToPosition + 1);
                str2 = text.substring(posToPosition + 1, position + 1);
            }
            String str3 = text.substring(position + 1, text.length());
            textRes = str1 + "(" + str2 + ")" + str3;
            text = textRes;
            list = getListStateFactorial(text);
            i--;
        }
        return textRes;
    }

    private ArrayList<Integer> getListPositionPow(String text)
    {
        ArrayList<Integer> position = new ArrayList<>();
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == '^')
            {
                position.add(i);
            }
        }
        return position;
    }

    private int getCountNumber(String text)
    {
        int count = 0;
        boolean c = false;
        for (int i = 0; i < text.length(); i++)
        {
            char b = text.charAt(i);
            if (b == 'E')
            {
                char v = text.charAt(i + 1);
                if (v != '−' & v != '-')
                {
                    i++;
                    b = text.charAt(i);
                }
                else
                {
                    i = i + 2;
                    b = text.charAt(i);
                }
            }
            if ((b == '1' || b == '2' || b == '3' || b == '4' || b == '5'
                    || b == '6' || b == '7' || b == '8' || b == '9' || b == '0'  || b == 'X') & !c)
            {
                count++;
                c = true;
            }
            else
            if (b == '+' || b == '-' || b == '*' || b == '/' || b == '^')
            {
                c = false;
            }
        }
        return count;
    }

    private String getTextStatePow(String text, ArrayList<Integer> list)
    {
        ArrayList<Integer> position = list;
        for (int i = 0; i < position.size(); i++)
        {
            String str1;
            String str2 = "";
            String str3;
            Integer pos = position.get(i);
            for (int j = pos + 1; j < text.length(); j++)
            {
                str1 = text.substring(0, pos + 1);
                char b = text.charAt(j);
                if (b == '+' || b == '-' || b == '*' || b == '/')
                {
                    int countNumber = getCountNumber(str2);
                    str3 = text.substring(j, text.length());
                    if (countNumber > 1)
                    {
                        text = str1 + "(" + str2 + ")" + str3;
                    }
                    else
                    {
                        text = str1 + str2 + str3;
                    }
                    position.clear();
                    position = getListPositionPow(text);
                    break;
                }
                else
                {
                    str2 = str2 + b;
                }
                if (j == text.length() - 1)
                {
                    int countNumber = getCountNumber(str2);
                    if (countNumber > 1)
                    {
                        text = str1 + "(" + str2 + ")";
                    }
                    else
                    {
                        text = str1 + str2;
                    }
                    position.clear();
                    position = getListPositionPow(text);
                    break;
                }
            }
        }
        return text;
    }

    public Double getCalculation(String text)
    {
        Double result = 0.0;
        double pi = Math.PI;
        double e = Math.E;
        ArrayList<Integer> listStatePercent = getListStatePercent(text);
        if (listStatePercent.size() > 0)
        {
            text = getTextStatePercent(text, listStatePercent);
        }
        ArrayList<String> listStateFactorial = getListStateFactorial(text);
        if (listStateFactorial.size() > 0)
        {
            text = getTextStateFactorial(text, listStateFactorial);
        }
        ArrayList<Integer> listStatePow = getListPositionPow(text);
        if (listStatePow.size() > 1)
        {
            text = getTextStatePow(text, listStatePow);
        }
        Map<String, String> listMapBrOperation = getListMapOperation(getListBrOperationToCalculation(text));
        Map<String, Double> list1 = new LinkedHashMap<>();
        for (Map.Entry entry1 : listMapBrOperation.entrySet())
        {
            String textToCalc;
            Map<String, Double> list2 = new LinkedHashMap<>();
            String key1 = entry1.getKey().toString();
            String value1 = entry1.getValue().toString();
            Map<String, String> listMapOperation = getListMapOperation(getListOperationToCalculation(value1));
            if (listMapOperation.size() == 0)
            {
                textToCalc = value1;
                if (textToCalc.contains("N]"))
                {
                    for (Map.Entry entry3 : list1.entrySet())
                    {
                        String key3 = entry3.getKey().toString();
                        Double value3 = Double.parseDouble(entry3.getValue().toString());
                        if (textToCalc.contains(key3))
                        {
                            textToCalc = getTextToCalc(textToCalc, key3, value3);
                        }
                    }
                    if (textToCalc.length() > 0)
                    {
                        try
                        {
                            result = getCalc(textToCalc);
                        }
                        catch (Exception r)
                        {
                            //not error
                        }
                    }
                }
                else
                {
                    if (value1.contains("e") || value1.contains("π"))
                    {
                        if (value1.contains("e"))
                        {
                            value1 = value1.replace("e", String.valueOf(e));
                        }
                        else
                        {
                            value1 = value1.replace("π", String.valueOf(pi));
                        }
                    }
                    result = Double.parseDouble(value1);
                }
            }
            else
            {
                for (Map.Entry entry2 : listMapOperation.entrySet())
                {
                    String key2 = entry2.getKey().toString();
                    String value2 = entry2.getValue().toString();
                    textToCalc = value2;
                    try
                    {
                        if (value2.contains("e") || value2.contains("π"))
                        {
                            if (value2.contains("e"))
                            {
                                value2 = value1.replace("e", String.valueOf(e));
                            }
                            else
                            {
                                value2 = value1.replace("π", String.valueOf(pi));
                            }
                        }
                        result = Double.parseDouble(value2);
                    }
                    catch (Exception z)
                    {
                        value2 = textToCalc;
                        int count = getCountChangeNumber(value2);
                        if (count > 0)
                        {
                            for (int i = 0; i < count; i++)
                            {
                                if (textToCalc.contains("N]"))
                                {
                                    for (Map.Entry entry3 : list1.entrySet())
                                    {
                                        String key3 = entry3.getKey().toString();
                                        Double value3 = Double.parseDouble(entry3.getValue().toString());
                                        if (textToCalc.contains(key3))
                                        {
                                            textToCalc = getTextToCalc(textToCalc, key3, value3);
                                        }
                                        if (textToCalc.length() > 0)
                                        {
                                            try
                                            {
                                                result = getCalc(textToCalc);
                                            }
                                            catch (Exception r)
                                            {
                                                //not error
                                            }
                                        }
                                    }
                                }
                                if (textToCalc.contains("M]"))
                                {
                                    for (Map.Entry entry3 : list2.entrySet())
                                    {
                                        String key3 = entry3.getKey().toString();
                                        Double value3 = Double.parseDouble(entry3.getValue().toString());
                                        if (textToCalc.contains(key3))
                                        {
                                            textToCalc = getTextToCalc(textToCalc, key3, value3);
                                        }
                                        if (textToCalc.length() > 0)
                                        {
                                            try
                                            {
                                                result = getCalc(textToCalc);
                                            }
                                            catch (Exception r)
                                            {
                                                //not error
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            result = getCalc(textToCalc);
                        }
                    }
                    list2.put(key2, result);
                }
            }
            list1.put(key1, result);
        }
        return result;
    }
}

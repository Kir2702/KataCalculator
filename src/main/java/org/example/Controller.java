package org.example;

import org.example.converters.RomanConverter;
import org.example.exceptions.InvalidResultValue;
import org.example.exceptions.InvalidValueException;

import java.util.ArrayList;
import java.util.List;

final class Controller {
    private String expression;
    private ItemType firstType;
    private ItemType secondType;

    private List<String> expressionPars = new ArrayList<String>();

    public Controller(String expression) throws InvalidValueException {
        this.expression = expression;
        // Преобразуем строку в List для удобства работы
        for (String retval : expression.split(" ")) {
            expressionPars.add(retval);
        }
        // Проверка на строку без пробелов
        if (expressionPars.size() == 1){
            throw new InvalidValueException("т.к. строка не является математической операцией");
        }
        // Проверка на наличие операторов
        if (!expressionPars.get(1).equals("+") &&
            !expressionPars.get(1).equals("-") &&
            !expressionPars.get(1).equals("/") &&
            !expressionPars.get(1).equals("*")
        ){
            throw new InvalidValueException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        // Проверка на структуру
        if (expressionPars.size() != 3){
            throw new InvalidValueException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        RomanConverter romanConverter = new RomanConverter();
        //Проверка на системы счисления первого элемента
        try {
            romanConverter.convertToInt(expressionPars.get(0));
            firstType = ItemType.ROMAN;
        } catch (Exception e){
            try {
                Integer.valueOf((String) expressionPars.get(0));
                firstType = ItemType.ARABIAN;
            }catch (Exception ee){
                firstType = ItemType.UNKNOWN;
                throw new InvalidValueException("т.к. формат первого элемента не удалось определить (" + expressionPars.get(0) + ")");
            }
        }
        //Проверка на системы счисления первого элемента
        try {
            romanConverter.convertToInt(expressionPars.get(2));
            secondType = ItemType.ROMAN;
        } catch (Exception e){
            try {
                Integer.valueOf((String) expressionPars.get(2));
                secondType = ItemType.ARABIAN;
            }catch (Exception ee){
                secondType = ItemType.UNKNOWN;
                throw new InvalidValueException("т.к. формат второго элемента не удалось определить (" + expressionPars.get(2) + ")");
            }
        }
        // Проверка на однородность данных
        if (!firstType.equals(secondType)){
            throw new InvalidValueException("т.к. используются одновременно разные системы счисления");
        }

        // Проверка на допустимый диапазон
        if(firstType == ItemType.ROMAN){
            int vel1 = romanConverter.convertToInt(expressionPars.get(0));
            int vel2 = romanConverter.convertToInt(expressionPars.get(2));
            if (!(0 < vel1 && 11 > vel1)){
                throw new InvalidValueException("т.к. Первое значение за пределами допустимого диапазона (от 1 до 10).");
            }
            if (!(0 < vel2 && 11 > vel2)){
                throw new InvalidValueException("т.к. Второе значение за пределами допустимого диапазона (от 1 до 10).");
            }
        }
        if(firstType == ItemType.ARABIAN){
            int vel1 = Integer.valueOf((String) expressionPars.get(0));
            int vel2 = Integer.valueOf((String) expressionPars.get(2));
            if (!(0 < vel1 && 11 > vel1)){
                throw new InvalidValueException("т.к. Первое значение за пределами допустимого диапазона (от 1 до 10).");
            }
            if (!(0 < vel2 && 11 > vel2)){
                throw new InvalidValueException("т.к. Второе значение за пределами допустимого диапазона (от 1 до 10).");
            }
        }
    }

    public String calculation() throws InvalidResultValue {

        String resultSrt = null;
        if(firstType == ItemType.ARABIAN){
            Integer result = null;
            int a = Integer.parseInt(expressionPars.get(0));
            int b = Integer.parseInt(expressionPars.get(2));
            switch(expressionPars.get(1)){
                case "+": result = a + b;
                break;
                case "-": result = a - b;
                break;
                case "/": result = a / b;
                break;
                case "*": result = a * b;
                break;
            }
            resultSrt = result.toString();

        }
        if(firstType == ItemType.ROMAN){
            RomanConverter romanConverter = new RomanConverter();
            Integer result = null;
            int a = romanConverter.convertToInt(expressionPars.get(0));
            int b = romanConverter.convertToInt(expressionPars.get(2));
            switch(expressionPars.get(1)){
                case "+": result = a + b;
                    break;
                case "-": result = a - b;
                    break;
                case "/": result = a / b;
                    break;
                case "*": result = a * b;
                    break;
            }
            if (result<1){
                throw new InvalidResultValue("Результатом работы калькулятора с римскими числами могут быть только положительные числа.");
            }
            resultSrt = romanConverter.convertToRoman(result);
        }

    return resultSrt;
    }
}

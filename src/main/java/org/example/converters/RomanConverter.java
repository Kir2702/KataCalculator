package org.example.converters;

import java.util.HashMap;

public class RomanConverter {

    public int convertToInt(String romanInt) {
        HashMap<Character, Integer> correlation = new HashMap<>();
        correlation.put('I', 1);
        correlation.put('V', 5);
        correlation.put('X', 10);
        correlation.put('L', 50);
        correlation.put('C', 100);
        correlation.put('D', 500);
        correlation.put('M', 1000);

        int end = romanInt.length()-1;
        char[] arr = romanInt.toCharArray();
        int arabian;
        int result = correlation.get(arr[end]);
        for (int i = end-1; i>=0; i--){
            arabian = correlation.get(arr[i]);

            if (arabian < correlation.get(arr[i+1])){
                result -= arabian;
            } else {
                result += arabian;
            }
        }
    return result;
    }


    public String convertToRoman(int arabicInt) {
        // создаем таблицу соответствия между арабскими и римскими числами
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        // разбиваем число на разряды
        int thousandDigit = arabicInt / 1000;
        int hundredDigit = (arabicInt % 1000) / 100;
        int tenDigit = (arabicInt % 100) / 10;
        int oneDigit = arabicInt % 10;

        // преобразуем каждый разряд в его римское представление
        String roman = thousands[thousandDigit] + hundreds[hundredDigit] + tens[tenDigit] + ones[oneDigit];

        return roman;
    }
}

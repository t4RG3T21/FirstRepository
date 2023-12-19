import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private static final Map<Character, Integer> romanNumerals = new HashMap<>();

    static {
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
    }

    public static String calc(String input) throws Exception {
        int a, b;
        char operator;

        if (!input.contains(" ")) {
            throw new Exception("Формат математической операции некорректен. Пожалуйста, используйте пробелы между операндами.");
        }

        boolean isRoman;

        try {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new Exception("Формат математической операции некорректен. " +
                        "Используйте два операнда и один оператор (+, -, /, *)");
            }

            // Проверяем, какой тип чисел введен
            isRoman = isRomanNumeral(parts[0]) && isRomanNumeral(parts[2]);

            if (isRoman) {
                // Если введены римские цифры, преобразуем их в арабские
                a = romanToArabic(parts[0]);
                b = romanToArabic(parts[2]);
            } else {
                // Если введены арабские цифры, парсим их как int
                a = Integer.parseInt(parts[0]);
                b = Integer.parseInt(parts[2]);

                // Проверяем, что числа в диапазоне от 1 до 10
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new Exception("Операнды должны быть числами от 1 до 10");
                }
            }

            operator = parts[1].charAt(0);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        int result;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new Exception("Некорректный оператор");
        }

        // Если результат отрицательный или равен нулю,
        // и вводились римские цифры, калькулятор выбрасывает ошибку
        if (result <= 0 && isRoman) {
            throw new Exception("Результатом работы с римскими цифрами могут быть только положительные числа");
        }

        // Если вводились римские цифры, преобразуем результат обратно в римские цифры
        if (isRoman) {
            return arabicToRoman(result);
        }

        return String.valueOf(result);
    }

    private static boolean isRomanNumeral(String input) {
        for (char c : input.toCharArray()) {
            if (!romanNumerals.containsKey(c)) {
                return false;
            }
        }
        return true;
    }

    private static int romanToArabic(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && romanNumerals.get(input.charAt(i)) > romanNumerals.get(input.charAt(i - 1))) {
                result += romanNumerals.get(input.charAt(i)) - 2 * romanNumerals.get(input.charAt(i - 1));
            } else {
                result += romanNumerals.get(input.charAt(i));
            }
        }
        return result;
    }

    private static String arabicToRoman(int input) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : romanNumerals.entrySet()) {
            while (input >= entry.getValue()) {
                result.append(entry.getKey());
                input -= entry.getValue();
            }
        }
        return result.toString();
    }
}


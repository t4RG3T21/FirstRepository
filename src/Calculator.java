public class Calculator {
    public static String calc(String input) throws Exception {
        int a, b;
        char operator;

        if (!input.contains(" ")) {
            throw new Exception("Формат математической операции некорректен. Пожалуйста, используйте пробелы между операндами.");
        }

        try {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new Exception("Формат математической операции некорректен. " +
                        "Используйте два операнда и один оператор (+, -, /, *)");
            }
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[2]);

            operator = parts[1].charAt(0);

            if ((b <= 0 || b >= 11) && (a <= 0 || a >= 11)) {
                throw new Exception("Первый и второй операнд должен быть от 1 до 10");
            }
            if (a <= 0 || a >= 11) {
                throw new Exception("Первый операнд должен быть от 1 до 10");
            }
            if (b <= 0 || b >= 11) {
                throw new Exception("Второй операнд должен быть от 1 до 10");
            }
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

        return String.valueOf(result);
    }
}


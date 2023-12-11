import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }

    public static String calc(String input) throws Exception {
        input = input.replaceAll(" ", ""); // Удаляем пробелы из строки
        int a, b;
        char operator;

        try {
            String[] parts = input.split("[+\\-*/]");
            if (parts.length >= 3){
                throw new Exception("Формат математической операции не" +
                        " удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (parts.length <= 1) {
                throw new Exception("Cтрока не является математической операцией");
            }

            b = Integer.parseInt(parts[1]);
            a = Integer.parseInt(parts[0]);
             operator = input.charAt(parts[0].length());
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
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль недопустимо");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректный оператор");
        }

        return String.valueOf(result);
    }
}
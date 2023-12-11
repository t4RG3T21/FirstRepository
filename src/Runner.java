import java.util.Scanner;
    public class Runner {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите арифметическое выражение: ");
            String input = scanner.nextLine();

            try {
                String result = Calculator.calc(input);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            scanner.close();
        }
    }


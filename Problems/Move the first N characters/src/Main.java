import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.next();
        int numOfChars = scanner.nextInt();
        if (inputString == null) {
            System.out.println("The input string is NULL!");
        } else if (numOfChars >= inputString.length()) {
            System.out.println(inputString);
        } else {
            System.out.print(inputString.substring(numOfChars));
            System.out.print(inputString.substring(0, numOfChars));
            System.out.println();
        }
    }
}
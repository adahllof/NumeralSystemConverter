import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder resultString = new StringBuilder("");
        StringBuilder word = new StringBuilder("anything");
        for (String s : strings) {
            word = word.replace(0, word.length(), s);
            resultString = resultString.append(removeDigits(word));
        }
        return resultString.toString();
    }
    private static StringBuilder removeDigits(StringBuilder s) {
        if (s == null) {
            return null;
        } else {
            int i = 0;
            while (i < s.length()) {
                if (Character.toString(s.charAt(i)).matches("[0-9]")) {
                    s.deleteCharAt(i);
                } else {
                    i++;
                }

            }
            return s;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}
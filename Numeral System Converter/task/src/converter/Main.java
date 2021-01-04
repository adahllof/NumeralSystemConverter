package converter;
import java.util.Scanner;
import java.lang.*;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Boolean ok = scanner.hasNextInt();
        int sourceRadix;
        int targetRadix;
        String sourceNumber;
        if (ok) {
            sourceRadix = scanner.nextInt();
        } else {
            sourceRadix = 0;
        }

        if (ok && scanner.hasNextLine()) {
            scanner.nextLine();
            sourceNumber = scanner.next();
        } else {
            ok = false;
            sourceNumber = "";
        }

        if (ok && scanner.hasNextInt()) {
            targetRadix = scanner.nextInt();
        } else {
            ok = false;
            targetRadix = 0;
        }
        if (!ok) {
            System.out.println("Error, input data is missing.  Note that items should be on separate lines.");
        } else {
            double number = str2double(sourceNumber, sourceRadix);
            System.out.println(double2str(number, targetRadix));
        }

    }
private static int radix1_2int(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        } else {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    count++;
                }
            }
            return count;
        }
}
private static double str2double(String numberStr, int radix) {
        //Convert a string to double
        if (numberStr == null) {
            System.out.println("Error, cannot convert null string to double.");
            return 0.0;
        } else {
            String[] strings = numberStr.split("\\.", 2);
            double x = str2int(strings[0], radix);
            if (strings.length == 2) {
                return x + fracStr2double(strings[1], radix);
            } else if(strings.length == 1){
                return x;
            } else {
                System.out.println("Error, invalid number format: " + numberStr);
                return 0.0;
            }
        }
}
private static String double2str(double x, int radix) {
        //Split number in integer and fractional part
    int n = (int) x;
    double f = x - n;
    if (f <= 0) {
        return int2str(n, radix);
    } else {
        return int2str(n, radix) + "." + dbl2fracStr(f,radix, 5);
    }
}
private static String int2str(int number, int radix) {
    StringBuilder s = new StringBuilder();
    if (radix == 1) {
        for (int i = 1; i <= number; i++) {
            s.append(1);
        }
    } else if (1 < radix && radix <= 36) {
        int remainder;
        do {
            remainder = number % radix;
            s = s.append(int2char(remainder));
            number = number / radix;
        } while (number > 0);

    } else {
        System.out.println("Error, radix: " + radix);
    }
    return s.reverse().toString();
}
private static String dbl2fracStr(double fraction, int radix, int count) {
        if (fraction >= 1) {
            System.out.println("Error, numbers that are GT or equal to 1 cannot be converted to fractional string");
            return "";
        } else {
            StringBuilder s = new StringBuilder();
            int digit;
            int i = 0;
            do {
                i++;
                fraction = fraction * radix;
                digit = (int) fraction;
                s.append(int2char(digit));
                fraction = fraction - digit;
            } while (i < count && fraction > 0);
            return s.toString();
        }
}
private static int str2int(String number, int radix) {
    //Convert from string to decimal integer
    if (radix < 1 || radix > 36 ) {
        System.out.println("Error, radix " + radix + " is not supported!" );
        return 0;
    } else if (radix == 1) {
        return radix1_2int(number);
    } else {
        try {
            return Integer.parseInt(number, radix);
        } catch (NumberFormatException nfe) {
            System.out.println("Error, " + nfe.getMessage());
            return 0;
        }

    }
}
private static double fracStr2double(String fracPart, int radix) {
    //Convert fractional part to decimal number
    double number = 0.0;
    double d = 1.0 / radix;
    for (char c : fracPart.toCharArray()) {
        number += (double) char2int(c) * d;
        d = d / radix;
    }
    return number;
}

private static int char2int(char c) {
        if ('0' <= c && c <= '9') {
            return Integer.parseInt(String.valueOf(c));
        } else if ('a' <= c && c <= 'z') {
            return 10 + (int) c - (int) 'a';
        } else {
            System.out.println("Error, cannot convert character " + c + " to number.");
            return 0;
        }
}
private static char int2char(int number) {
        if (number > 36) {
            System.out.println("Error, " + number + " is too large to be converted to an alphanumeric character");
            return '#';
        } else if (number < 0) {
            System.out.println("Error, " + number + " cannot convert negative numbers to an alphanumeric character");
            return '#';
        } else if (number <= 9) {
            return (char) (number + (int) '0');
        } else {
            return (char) (number + (int) 'a' - 10);
        }
}




}

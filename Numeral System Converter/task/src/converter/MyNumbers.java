package converter;

public class MyNumbers {
    private double number;
    private final int base;

    public MyNumbers(double number, int base) {
        this.number = number;
        this.base = base;
    }
    public MyNumbers(String numStr, int base) {
        this.base = base;
        this.number = string2double(numStr);
    }

    public double getAsNumber() {
        return number;
    }


    protected String getAsString(int precision) {
        if (number >= 0) {
            return int2String((int) number) + "." + fraction2string(number % 1, precision);
        } else {
            return "-" + int2String((-1) * (int) number) + "." + fraction2string((-1) * number % 1, precision);
        }
    }
    protected String getAsString() {
        //The no arguments version returns an integer string i.e. no fractional characters and no dot
        if (number >= 0) {
            return int2String((int) number);
        } else {
            return "-" + int2String((-1) * (int) number);
        }
    }

    protected int string2int(String i) {
        /**
         Converts a string to integer value
         */
        if (i == null) {
            System.out.println("Error, trying to convert null string to int");
            return 0;
        } else if (i.length() == 0) {
            System.out.println("Error, trying to convert string with 0 length");
            return 0;
        } else if (i.length() == 1) return char2int(i.charAt(0));
        else return base * string2int(i.substring(0, i.length() - 1)) + char2int(i.charAt(i.length() - 1));
    }

    private double str2frac(String fracStr) {
        if (fracStr == null) {
            System.out.println("Error, trying to convert null string to fractional part of a number.");
            return 0.0;
        } else if (fracStr.length() == 0) {
            System.out.println("Error, trying to convert zero length string to fractional part of a number.");
            return 0;
        } else {
            int d = 1;
            for (int i = 1; i < fracStr.length() + 1; i++) {
                d = d * base;
            }
            return string2int(fracStr) / (double) d;
        }
    }

    protected double string2double(String numStr) {
        if (numStr == null) {
            System.out.println("Error, trying to convert null string");
            return 0.0;
        } else if (numStr.length() == 0) {
            System.out.println("Warning, trying to convert string with zero length");
            return 0.0;
        } else if (numStr.charAt(0) == '-') {
            return -1.0 * string2double(numStr.substring(1));
        } else if (numStr.length() == 1) {
            return char2int(numStr.charAt(0));
        } else if (numStr.charAt(0) == '.') {
            return str2frac(numStr.substring(1));
        } else {
            String[] s = numStr.split("\\.", 2);
            if (s.length == 1) {
                //No fractional part
                return string2int(s[0]);
            } else {
                return string2int(s[0] ) +  str2frac(s[1]) ;
            }
        }


    }



    private int char2int(char c){
        int i = (int) c;
        if (i < 0) {
            System.out.println("Error atempt to convert integer < 0 to character");
            i = 0;
        } else if (48 <= i && i <= 57) {
            i = i - 48; //Digit 0-9
        } else if (64 < i && i < 91) {
            i = i - 55; // Capital letter A-Z
        } else {
            System.out.println("Illegal character encountered");
            i = 0;
        }
        if (i >= base) {
            System.out.println("Illegal character encountered");
            return 0;
        } else {
            return i;
        }
    }

    protected static char num2char(int number) {
        /**
         * Convert integer number to a single character
         */
        if (number < 0) {
            System.out.println("Error, trying to convert negative number to character");
            return '#';
        } else if (number <= 9) {
            return (char) (number + 48);
        } else if (number <= 35 ) {
            return (char) (number + 55);
        } else {
            System.out.println("Error converting numeric to character!");
            return '"';
        }
    }
    protected String int2String(int number) {
        /**
         * Converts integer number to a string of characters
         */
        int i = number / base;
        int rem = number % base;
        if (i <= 0) {
            return String.valueOf(num2char(rem));
        } else {
            return int2String(i) + num2char(rem);
        }

    }
    protected String fraction2string(double frac, int precision) {
        /**
         * Converts the fractional part of a number to a string.  frac must be greater than or equal to 0 and less than 1
         */
        for (int i = 1; i <= precision; i++) {
            frac = frac * base;
        }
        return int2String((int) (frac + 0.5));
    }
}

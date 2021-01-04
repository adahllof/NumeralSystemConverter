package converter;

public class BinaryNumber extends MyNumbers {

    public BinaryNumber(double number) {
        super(number, 2);
    }

    public BinaryNumber(String strNum) {
        super(strNum, 2);
    }

    @Override
    protected String getAsString(int precision) {
        //Overridden just to add 0b at the beginning
        if (getAsNumber() >= 0) {
            return "0b" + int2String((int) getAsNumber()) + "." + fraction2string(getAsNumber() % 1, precision);
        } else {
            return "-0b" + int2String((-1) * (int) getAsNumber()) + "." + fraction2string((-1) * getAsNumber() % 1, precision);
        }
    }

    @Override
    protected String getAsString() {
        //The no arguments version returns an integer string i.e. no fractional characters and no dot
        if (getAsNumber() >= 0) {
            return "0b" + int2String((int) getAsNumber());
        } else {
            return "-0b" + int2String((-1) * (int) getAsNumber());
        }
    }
}

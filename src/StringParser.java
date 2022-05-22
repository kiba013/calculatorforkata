import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
    private static String string;

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int vlues;

        RomanNumeral(int value) {
            this.vlues = value;
        }

        public int getVlues() {
            return vlues;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.vlues).reversed())
                    .collect(Collectors.toList());
        }
    }

    public StringParser(String si) {
        string =si;
    }

    public static int toInt(String si) throws NoValidateTipExeption{
        if (Main.romanR == Main.romantoarab.Arab) {
            return Integer.parseInt(si);
        } else if (Main.romanR == Main.romantoarab.Rom){
            return romanToArabic(si);
        } else throw new  NoValidateTipExeption("НЕ УСТАНОВЛЕН ТИП ЧИСЛА:"+si);
    }

    public static Main.Action toAction(String si){
        switch (si){
            case "+":
                return Main.Action.Plus;
            case "-":
                return Main.Action.Minus;
            case "/":
                return Main.Action.Divide;
            case "*":
                return Main.Action.Multiply;
            default:
        }
        return Main.Action.Err;
    }


    private static int romanToArabic(String input) throws NoValidateTipExeption{
        String romNum = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNum = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romNum.length() > 0) && (i < romanNum.size())) {
            RomanNumeral symbol = romanNum.get(i);
            if (romNum.startsWith(symbol.name())) {
                result += symbol.getVlues();
                romNum = romNum.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romNum.length() > 0) {
            throw new  NoValidateTipExeption("НЕ распознала, как РИМСКИЕ:"+input);
        }

        return result;
    }
}

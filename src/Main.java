import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static boolean from0to10 =true;
    static boolean only2 =false;

    enum romantoarab { Arab(1),Rom(2),NoElse(0),Err(-1);
        private int values;
        romantoarab(int v1) { this.values =v1;
        }
    }
    public static romantoarab romanR;

    enum Action{
        Plus,Minus,Divide,Multiply,Eqv,Err;
    }
    public static Action action=null;

    public static void main(String[] args) throws IOException {

        System.out.println("Введите от 0 до 10");
        Scanner userinput=new Scanner(System.in);
        try {
            Pattern pattern=Pattern.compile("(?:([0-9]+)|([IVXLDMC]+))(\\s*[*/+-]\\s*)?+");
            while (userinput.hasNext()) {
                int condigit=0;
                String inputs=userinput.nextLine();
                Matcher mathcer1=pattern.matcher(inputs);
                Expression express1=new Expression();
                romanR = romantoarab.NoElse;
                while (mathcer1.find()) {
                    int ival=-1;
                    if (mathcer1.start(1)>=0) {
                        String st2=mathcer1.group(1);
                        if (romanR == romantoarab.Rom) {
                            throw new NoValidateTipExeption("не правильная выражения:"+st2);
                        }
                        romanR = romantoarab.Arab;
                        condigit++;
                        ival=StringParser.toInt(st2);
                        if (from0to10 && (ival< 0 || ival>10)) throw new NoValidateTipExeption("Только от 0 до 10ти, а ввели:"+st2);
                        if (only2 && (condigit>2)) throw new NoValidateTipExeption("Только 2 числа, а ввели:"+condigit);

                    }
                    if (mathcer1.start(2)>=0) {
                        String string2=mathcer1.group(2);
                        if (romanR == romantoarab.Arab) {
                            throw new NoValidateTipExeption("не правильная выражения:"+string2);
                        }
                        romanR = romantoarab.Rom;
                        ival=StringParser.toInt(string2);
                        if (from0to10 && (ival< 0 || ival>10)) throw new NoValidateTipExeption("Только от 0 до 10ти, а ввели:"+string2);
                        if (only2 && (condigit>2)) throw new NoValidateTipExeption("Только 2, а ввели:"+condigit);

                    }
                    if (mathcer1.start(3)>=0) {
                        String string3 = mathcer1.group(3).trim();
                        action = StringParser.toAction(string3);
                    } else action=Action.Eqv;
                    express1.calculate(ival,action);

                }
                System.out.println("Результат="+express1.result());

            }
        } catch (Exception | NoValidateTipExeption e) {
            System.out.println("whoops");
        }
        userinput.close();

    }
}

public class Expression  {
    private  Integer inresult;
    private  Main.Action action;



    public  int calculate(int invalue, Main.Action operation){
        if (inresult ==null){
            inresult =invalue;
        } else {
            switch (action) {
                case Plus:
                    inresult +=invalue;
                    break;
                case Minus:
                    inresult -=invalue;
                    break;
                case Multiply:
                    inresult = inresult *invalue;
                    break;
                case Divide:
                    inresult =(Integer) inresult /invalue;
            }

        }
        action=operation;
        return inresult;
    }

    public  Integer result() {
        return inresult;
    }
}

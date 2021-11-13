package jump2java.chap7;

public class HandleException{

    static class FoolException extends RuntimeException {}

    static class FoolException2 extends Exception {}

    public static void test1() {
        int c = 0;
        try {
            c = 4 / 0;
        } catch (ArithmeticException e) {
            c = -1;
        } finally {
            System.out.println(c);
        }
    }

    public static void test2() {
        throw new FoolException();
    }

    public static void test3() throws Exception {
        throw new FoolException2();
    }

    public static void test4() {
        try {
            throw new FoolException2();
        } catch (FoolException2 e) {
            System.out.println("Handled exception");
        }
    }

    public static void main(String[] args) throws Exception {
        test4();
    }
}

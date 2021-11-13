package jump2java.chap8;

public class Euler1 {

    int max, result;

    public Euler1(int max) {
        this.max = max;
        result = 0;
        for (int i = 0; i < max; i++) if (i % 3 == 0 || i % 5 == 0) result += i;
    }

    @Override public String toString() { return Integer.toString(result); }

    public static void main(String[] args) {
        System.out.println(new Euler1(10));
        System.out.println(new Euler1(1000));
    }

}  // src: https://projecteuler.net/problem=1

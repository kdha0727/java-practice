package jump2java.chap8;

import java.util.ArrayList;

public class SelfNumber {

    int max, result = 0;

    public SelfNumber(int max) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 1; i < max; i++) {
            int j = i;
            for (int k =i; k > 0; k /= 10) {
                j += k % 10;
            }
            lst.add(j);
        }
        for (int i = 1; i < max; i++) {
            if (! lst.contains(i)) {
                result += i;
            }
        }
        this.max = max;
    }

    @Override public String toString() { return Integer.toString(result); }

    public static void main(String[] args) { System.out.println(new SelfNumber(5000)); }

}

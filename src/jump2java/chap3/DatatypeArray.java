package jump2java.chap3;

public class DatatypeArray {

    public static void main(String[] args) {

        int[] odds = new int[5];
        odds[0] = 1;
        odds[1] = 3;
        odds[2] = 5;
        odds[3] = 7;
        odds[4] = 9;

        String[] weeks = {"월", "화", "수", "목", "금", "토", "일"};

        System.out.println(weeks.length);
        for (String week: weeks)
            System.out.println(week);

//        raise_ArrayIndexOutOfBoundsException();

    }

    public static void raise_ArrayIndexOutOfBoundsException() {
        int[] temp = {0};
        System.out.println(temp[1]);
    }

}

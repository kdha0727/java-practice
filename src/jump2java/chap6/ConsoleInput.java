package jump2java.chap6;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Scanner;

public class ConsoleInput {

    public static void test1() throws IOException {

        InputStream in = System.in;  // abc

        int
                a = in.read(),
                b = in.read(),
                c = in.read();

        System.out.println(a);  // 97
        System.out.println(b);  // 98
        System.out.println(c);  // 99

    }

    public static void test2() throws IOException {

        InputStream in = System.in;  // abc

        byte[] a = new byte[3];
        int result = in.read(a);

        System.out.println(result);  // 3

        System.out.println(a[0]);  // 97
        System.out.println(a[1]);  // 98
        System.out.println(a[2]);  // 99

    }

    public static void test3() throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in);  // abc

        char[] a = new char[3];
        int result = reader.read(a);

        System.out.println(result);  // 3

        System.out.println(a);  // abc

    }

    public static void test4() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        System.out.println(a);

    }

    public static void test5() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.next());
        // next -> word, nextInt -> integer, nextLine -> line
    }

    public static void main(String[] args) {
        try { test5(); } catch (Exception e) { e.printStackTrace(); }
    }

}

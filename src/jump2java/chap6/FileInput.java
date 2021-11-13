package jump2java.chap6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;

public class FileInput {

    public static void test1 () throws IOException {

        try (FileInputStream input = new FileInputStream("out.txt")) {

            byte[] b = new byte[1024];
            int result = input.read(b);

            System.out.println(result);
            System.out.println(new String(b));

        }

    }

    public static void test2() throws IOException {

        try (FileReader reader = new FileReader("out.txt")) {

            char[] c = new char[1024];
            int result = reader.read(c);

            System.out.println(result);
            System.out.println(c);

        }

    }

    public static void test3() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("out.txt"))) {

            while(true) {
                String line = br.readLine();
                if (line==null) break;
                System.out.println(line);
            }

        }

    }

    public static void main(String[] args) {
        try { test3(); } catch (Exception e) { e.printStackTrace(); }
    }
}

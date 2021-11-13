package jump2java.chap6;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutput {

    public static void test1() throws IOException {

        FileOutputStream output = new FileOutputStream("out.txt");
        output.close();

    }

    public static void test2() throws IOException {

        try (FileOutputStream output = new FileOutputStream("out.txt")) {
            for (int i = 1; i < 11; i++) {
                String data = i + " 번째 줄입니다.\r\n";
                output.write(data.getBytes());
            }
        }  // auto close

    }

    public static void test3() throws IOException {

        try (FileWriter fw = new FileWriter("out.txt")) {
            for (int i = 1; i < 11; i++) {
                String data = i + " 번째 줄입니다.\r\n";
                fw.write(data);
            }
        }  // auto close

    }

    public static void test4() throws IOException {

        try (PrintWriter pw = new PrintWriter("out.txt")) {
            for (int i = 1; i < 11; i++) {
                String data = i + " 번째 줄입니다.";
                pw.println(data);
            }
        }  // auto close

    }

    public static void test5() throws IOException {

        try (FileWriter fw = new FileWriter("out.txt")) {
            for (int i = 1; i < 11; i++) {
                String data = i + " 번째 줄입니다.\r\n";
                fw.write(data);
            }
        }  // auto close

        try (FileWriter fw = new FileWriter("out.txt", true)) {
            for (int i = 11; i < 21; i++) {
                String data = i + " 번째 줄입니다.\r\n";
                fw.write(data);
            }
        }  // auto close

    }

    public static void test6() throws IOException {

        try (PrintWriter pw = new PrintWriter("out.txt")) {
            for (int i = 1; i < 11; i++) {
                String data = i + " 번째 줄입니다.";
                pw.println(data);
            }
        }  // auto close

        try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt", true))) {
            for (int i = 11; i < 21; i++) {
                String data = i + " 번째 줄입니다.";
                pw.println(data);
            }
        }  // auto close

    }

    public static void main(String[] args) {
        try { test6(); } catch (Exception e) { e.printStackTrace(); }
    }

}

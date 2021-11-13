package jump2java.chap7;

import java.util.ArrayList;

public class Threading {

    static public class Test1 extends Thread {
        public void run() {
            System.out.println("thread run.");
        }
        public static void main() {
            new Test1().start();
            System.out.println("main end.");
        }
    }

    static public class Test2 extends Thread {
        int seq;
        public Test2(int seq) {
            this.seq = seq;
        }
        public void run() {
            System.out.println(this.seq+" thread start.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(this.seq+" thread end.");
        }
        public static void main() {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Thread t = new Test2(i);
                t.start();
                threads.add(t);
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main end.");
        }
    }

    static public class Test3 implements Runnable {
        int seq;
        public Test3(int seq) {
            this.seq = seq;
        }
        public void run() {
            System.out.println(this.seq+" thread start.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(this.seq+" thread end.");
        }
        public static void main() {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(new Test3(i));
                t.start();
                threads.add(t);
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main end.");
        }
    }

    public static void main(String[] args) {
        Test1.main();
        Test2.main();
        Test3.main();
    }

}

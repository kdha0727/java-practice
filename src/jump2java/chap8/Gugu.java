package jump2java.chap8;

/*
 * python:

import io

def gugu(dan):
    b = io.StringIO()
    fmt = ">" + str(len(str(dan * 2)))
    for i in range(1, dan + 1):
        for j in range(1, dan + 1):
            b.write(format(i * j, fmt))
            b.write(" ")
        b.write("\n")
    return b.getvalue()

 *
 */

public class Gugu {

    private final int dan, maxLen;

    public Gugu(int dan) {
        assert dan > 0;
        this.dan = dan;
        this.maxLen = Integer.toString(dan * dan).length();
    }

    static private String formatInt(int src, int len) {
        for (StringBuilder s = new StringBuilder(Integer.toString(src)) ;; s.insert(0, " ")) {
            if (s.length() >= len) return s.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder((maxLen + 1) * (dan ^ 2));
        for (int i = 1; i <= dan; i++) {
            for (int j = 1; j <= dan; j++) {
                b.append(formatInt(i * j, maxLen));
                b.append(' ');
            }
            b.replace(b.length() - 1, b.length(), "\n");
        }
        b.deleteCharAt(b.length() - 1);
        return b.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Gugu(20));
    }

}

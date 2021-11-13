package jump2java.chap3;

public class DatatypeStringBuffer {

    public static String using_StringBuffer() {
        StringBuffer sb = new StringBuffer();  // construct new object
        sb.append("hello");
        sb.append("java");
        sb.insert(5, " ");
        // substring test
        System.out.println(sb.substring(6));
        return sb.toString();
    }

    public static String using_String() {
        String temp = "";  // construct new object - String is immutable
        temp += "hello";  // construct new object
        temp += " " ;  // construct new object
        temp += "java";  // construct new object
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(using_StringBuffer().equals(using_String()));
    }
}

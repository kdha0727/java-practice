package jump2java.chap3;

public class DatatypeString {
    public static void main(String[] args) {
        // constructor
        String s1 = "hello";  // literal String: save literal into 'intern pool' and returns cached String
        String s2 = new String("hello");  // Always construct new String

        // equals
        System.out.print("String.equals result: ");
        System.out.println(s1.equals(s2));

        // indexOf
        String s3 = new String("Hello Java");
        System.out.print("String.indexOf result: ");
        System.out.println(s3.indexOf("Java") == 6);

        // replaceAll
        String s4 = s3.replaceAll("Java", "World");  // Using Regex
        System.out.print("String.replaceAll result: ");
        System.out.println(s4.equals("Hello World"));

        // substring
        String s5 = s3.substring(0, 4);
        System.out.print("String.substring result: ");
        System.out.println(s5.equals("Hell"));

        // toUpperCase
        String s6 = s3.toUpperCase();
        System.out.print("String.toUpperCase result: ");
        System.out.println(s6.equals("HELLO JAVA"));
    }
}

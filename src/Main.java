
public class Main {

    public static native void yang();

    public static void yang(float... p) {}

    public static <T> void yang(T o, Class<T> p) {
        yang(new float[]{0, 256});
        yang(Object.class);
    }

    public static void yang(Class<?> p) {
        if (Object.class == p) {} else if (p.isInstance(new Object())) {}
    }

    public static void yang(int a, int b, int ... p) {}

    public static void main(String[] args) {
	// write your code here
    }
}

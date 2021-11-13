package jump2java.chap3;

import java.util.ArrayList;

public class DatatypeArrayList {

    private final ArrayList<String> data;

    private DatatypeArrayList() {
        this.data = new ArrayList<>();
    }

    private void add_test() {

        if (this.data.size()!=0) {
            return;
        }

        this.data.add("first");
        this.data.add("third");
        this.data.add(1, "second");

        System.out.print("Add test result: ");
        System.out.println(this.data.size()==3);

    }

    private void get_contains_test() {

        if (this.data.size()==0) {
            return;
        }

        System.out.print("Get test result: ");
        System.out.println(this.data.get(1).equals("second"));

        System.out.print("Contains test result: ");
        System.out.println(this.data.contains("third"));

    }

    private void remove_test() {

        if (this.data.size()==0) {
            return;
        }

        System.out.print("Remove test result: ");
        System.out.println(
                this.data.remove(2).equals("third") &&
                        this.data.remove("first") &&
                        this.data.remove("second") &&
                        this.data.size() == 0
        );

    }

    private static void without_generics_test() {
        ArrayList non_generic = new ArrayList();
        non_generic.add("string");
        String string = (String) non_generic.get(0);
        System.out.print("When using ArrayList without Generics, it requires type casting.");
    }

    private static void test_all() {
        DatatypeArrayList test_obj = new DatatypeArrayList();
        test_obj.add_test();
        test_obj.get_contains_test();
        test_obj.remove_test();
        without_generics_test();
    }

    public static void main(String[] args) {
        test_all();
    }

}

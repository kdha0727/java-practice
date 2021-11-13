package jump2java.chap3;

import java.util.ArrayList;
import java.util.HashMap;

public class DatatypeHashMap {

    private final HashMap<String, String> data;

    private DatatypeHashMap() {
        this.data = new HashMap<>();
    }

    private void test() {

        ArrayList<Boolean> test = new ArrayList<>();

        this.data.put("people", "사람");
        this.data.put("baseball", "야구");

        test.add(this.data.size() == 2);

        test.add(this.data.containsKey("people"));

        test.add(this.data.get("baseball").equals("야구"));

        test.add(
                this.data.remove("people").equals("사람") &&
                        this.data.size() == 1
        );

        boolean all_result = true;
        for (boolean each_result: test) {
            all_result = all_result && each_result;
        }
        System.out.print("Test result: ");
        System.out.println(all_result);

    }

    public static void main(String[] args) {
        DatatypeHashMap test_obj = new DatatypeHashMap();
        test_obj.test();
    }

}

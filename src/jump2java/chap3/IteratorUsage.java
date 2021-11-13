package jump2java.chap3;

import java.util.*;

public class IteratorUsage {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(1);
        list.add(2);

        for(Iterator<Integer> itr = list.iterator(); itr.hasNext();)
        {
            System.out.println(itr.next());
        }

        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

}

package kjm;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);

        System.out.println(findMiddle(7, list));
        System.out.println(list);
    }

    private static int findMiddle(int value, List<Integer> sortedList) {
        for (int i = sortedList.size() - 1; i >= 0; i--) {
            if (value > sortedList.get(i)) {
                sortedList.add(i + 1, value);
                break;
            }
        }

        int idx = sortedList.size() / 2 - Math.abs(sortedList.size() % 2 - 1);

        return sortedList.get(idx);
    }
}

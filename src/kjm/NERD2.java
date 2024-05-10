package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class NERD2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int sum = 0;

            TreeMap<Integer, Integer> tree = new TreeMap<>();

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                String[] inputs = input.split(" ");

                int p = Integer.parseInt(inputs[0]);
                int q = Integer.parseInt(inputs[1]);

                Map.Entry<Integer, Integer> higherEntry = tree.higherEntry(p);

                if (higherEntry == null || higherEntry.getValue() < q) {
                    tree.put(p, q);

                    Map.Entry<Integer, Integer> lowerEntry = tree.lowerEntry(p);

                    while (lowerEntry != null) {
                        if (lowerEntry.getValue() < q)
                            tree.remove(lowerEntry.getKey());
                        else
                            break;

                        lowerEntry = tree.lowerEntry(p);
                    }
                }

                sum += tree.size();
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }

}

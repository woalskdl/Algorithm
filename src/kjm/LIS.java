package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LIS {
    static int[] array;
    static int[] lengths;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());

            String input = br.readLine();
            String[] inputs = input.split(" ");

            array = new int[N];
            lengths = new int[N + 1];

            for (int i = 0; i < N; i++) {
                array[i] = Integer.parseInt(inputs[i]);
            }

            int longest = getLongest(-1) - 1;

            sb.append(longest).append("\n");
        }

        System.out.println(sb);
    }

    static int getLongest(int start) {

        int ret = lengths[start + 1];

        if (ret != 0)
            return ret;

        int longest = 1;

        for (int i = start + 1; i < array.length; i++) {
            if (start == -1 || array[start] < array[i])
                longest = Math.max(longest, 1 + getLongest(i));
        }

        lengths[start + 1] = longest;
        return lengths[start + 1];
    }
}

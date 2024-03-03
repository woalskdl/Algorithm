package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PI {
    static int[] input;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine().replaceAll(" ", ""));

        while (C-- > 0) {
            String line = br.readLine().replaceAll(" ", "");
            int length = line.length();

            input = new int[length];
            cache = new int[length];

            for (int i = 0; i < length; i++)
                input[i] = Integer.parseInt(String.valueOf(line.charAt(i)));

            sb.append(level(0)).append("\n");
        }

        System.out.println(sb);
    }

    static int level(int start) {
        if (start == input.length)
            return 0;

        int ret = cache[start];

        if (ret != 0)
            return ret;

        int level = 999999;

        for (int length = 3; length <= 5 && start + length <= input.length; length++) {
            level = Math.min(level, getLevel(start, length) + level(start + length));
        }

        cache[start] = level;
        return level;
    }

    static int getLevel(int start, int length) {
        if (length < 3)
            return 999999;

        boolean level1 = true;

        int prev = input[start];
        for (int i = start + 1; i < start + length; i++) {
            if (prev != input[i]) {
                level1 = false;
                break;
            }
        }

        if (level1)
            return 1;

        boolean level2 = true;

        int diff = input[start + 1] - prev;

        if (Math.abs(diff) == 1) {
            for (int i = start + 2; i < start + length; i++) {
                if (input[i] != input[i - 1] + diff) {
                    level2 = false;
                    break;
                }
            }
        } else {
            level2 = false;
        }

        if (level2)
            return 2;

        boolean level4 = true;

        int first = input[start];
        int second = input[start + 1];

        for (int i = 2; i < length; i++) {
            if (i % 2 == 0) {
                if (input[start + i] != first) {
                    level4 = false;
                    break;
                }
            } else {
                if (input[start + i] != second) {
                    level4 = false;
                    break;
                }
            }
        }

        if (level4)
            return 4;

        boolean level5 = true;

        diff = second - first;

        for (int i = start + 2; i < start + length; i++) {
            if (input[i] != input[i - 1] + diff) {
                level5 = false;
                break;
            }
        }

        if (level5)
            return 5;

        return 10;
    }
}

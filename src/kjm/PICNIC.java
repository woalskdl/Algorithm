package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PICNIC {
    // https://algospot.com/judge/problem/read/PICNIC

    static int C;
    static int n;
    static int m;
    static boolean[][] mates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine());

        while (C > 0) {
            String line = br.readLine();

            n = Integer.parseInt(line.split(" ")[0]);
            m = Integer.parseInt(line.split(" ")[1]);

            mates = new boolean[n][n];
            String mate = br.readLine();
            int[] mateArr = Arrays.stream(mate.split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < m; i++) {
                mates[mateArr[2 * i]][mateArr[2 * i + 1]] = true;
            }

            sb.append(mateCount(new boolean[n], n, 0)).append("\n");

            C --;
        }

        System.out.println(sb);
    }

    static int mateCount(boolean[] grouped, int leftCount, int count) {
        if (leftCount == 0)
            return 1;

        for (int i = 0; i < grouped.length; i++) {
            if (grouped[i])
                continue;

            boolean[] friends = mates[i];

            for (int j = 0; j < friends.length; j++) {
                if (friends[j] && !grouped[j]) {
                    grouped[i] = grouped[j] = true;

                    count += mateCount(grouped, leftCount - 2, count);

                    grouped[i] = grouped[j] = false;
                }
            }
        }


        return count;
    }
}

// count가 누적되어 더해짐
// 한번 맺었던 커플이 또 맺어짐

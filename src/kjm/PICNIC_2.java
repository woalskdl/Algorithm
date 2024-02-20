package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PICNIC_2 {
    // https://algospot.com/judge/problem/read/PICNIC

    static int C;
    static int n;
    static int m;
    static int[] mates;
    static boolean[] matched;
    static int allMatched;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine());

        while (C > 0) {
            String line = br.readLine();

            n = Integer.parseInt(line.split(" ")[0]);
            m = Integer.parseInt(line.split(" ")[1]);

            mates = new int[m];
            matched = new boolean[m];
            String mate = br.readLine();
            int[] mateArr = Arrays.stream(mate.split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < n; i++) {
                allMatched += i;
            }

            for (int i = 0; i < m; i++) {
                mates[i] = mateArr[2 * i] + mateArr[2 * i + 1];
            }


            count = 0;
            mateCount(0, 0);
            sb.append(count).append("\n");

            C --;
        }

        System.out.println(sb);
    }

    static void mateCount(int matchedCnt, int curMatched) {
        if (matchedCnt == n / 2) {
            if (curMatched == allMatched)
                count += 1;

            return;
        }

        for (int i = 0; i < mates.length && !matched[i]; i++) {
            matched[i] = true;
            mateCount(matchedCnt + 1, curMatched + mates[i]);
            matched[i] = false;
        }
    }
}

// 15
// 0 1 2 3 4 5
// 0 1, 4 5, 0 5
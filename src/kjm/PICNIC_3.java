package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PICNIC_3 {
    // https://algospot.com/judge/problem/read/PICNIC

    static int C;
    static int n;
    static int m;
    static boolean[] matched;
    static int[][] mates;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine().replaceAll(" ", ""));

        while (C > 0) {
            String line = br.readLine();

            n = Integer.parseInt(line.split(" ")[0]);
            m = Integer.parseInt(line.split(" ")[1]);

            matched = new boolean[n];
            mates = new int[m][2];
            String mate = br.readLine();
            String[] mateArr = mate.split(" ");

            for (int i = 0; i < m; i++) {
                mates[i][0] = Integer.parseInt(mateArr[2 * i]);
                mates[i][1] = Integer.parseInt(mateArr[2 * i + 1]);
            }

            count = 0;

            for (int i = 0; i < m; i++) {
                mateCount(i, n);
            }

            sb.append(count).append("\n");
            C --;
        }

        System.out.print(sb);
    }

    static void mateCount(int startIdx, int leftCount) {
        int a = mates[startIdx][0];
        int b = mates[startIdx][1];

        matched[a] = true;
        matched[b] = true;
        leftCount -= 2;

        if (leftCount == 0) {
            matched[a] = false;
            matched[b] = false;

            count += 1;
            return;
        }

        for (int i = startIdx; i < m; i++) {
            int aa = mates[i][0];
            int bb = mates[i][1];

            if (!matched[aa] && !matched[bb])
                mateCount(i, leftCount);
        }

        matched[a] = false;
        matched[b] = false;
    }
}

// https://velog.io/@woalskdl/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0-%EC%A0%84%EB%9E%B5PICNIC

package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class POLY {

    private static int[][] cache;
    private static final int REM = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int n = Integer.parseInt(br.readLine());

            cache = new int[n + 1][n + 1];

            for (int i = 0; i < n + 1; i++)
                Arrays.fill(cache[i], -1);

            sb.append(count(0, n)).append("\n");
        }

        System.out.println(sb);
    }

    // 바로 윗줄길이에 대비해 현재 내가 length개의 정사각형을 둘 때 경우의 갯수
    // upperLength , left 로 2차원 배열 만들어서 memoization 하면 될듯
    private static int count(int upperLength, int left) {
        if (left == 0)
            return 1;

        if (cache[upperLength][left] != -1)
            return cache[upperLength][left];

        int count = 0;
        if (upperLength == 0) {
            for (int length = 1; length <= left; length++)
                count = (count + count(length, left - length)) % REM;
        } else {
            for (int length = 1; length <= left; length++)
                count = (count + (upperLength + length - 1) * count(length, left - length)) % REM;
        }

        cache[upperLength][left] = count;

        return count;
    }
}

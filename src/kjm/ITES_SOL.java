package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ITES_SOL {
    private static int K;
    private static int N;
    private static int[] A;
    private static int[] sign;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            K = Integer.parseInt(input.split(" ")[0]);
            N = Integer.parseInt(input.split(" ")[1]);

            A = new int[N];
            sign = new int[N];

            A[0] = 1983;
            sign[0] = A[0] % 10000 + 1;

            for (int i = 1; i < N; i++) {
                A[i] = (int) ((A[i - 1] * 214013 + 2531011) % Math.pow(2, 32)); // overflow 발생
                sign[i] = A[i] % 10000 + 1;
            }

            sb.append(optimized()).append("\n");
        }

        System.out.println(sb);
    }

    private static int optimized() {
        int ret = 0, tail = 0, rangeSum = sign[0];

        for (int head = 0; head < sign.length; head++) {
            while (rangeSum < K && tail + 1 < sign.length)
                rangeSum += sign[++tail];

            if (rangeSum == K)
                ret++;

            rangeSum -= sign[head];
        }

        return ret;
    }
}

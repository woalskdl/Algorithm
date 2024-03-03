package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QUANTIZATION_SOL {
    static int N;
    static int S;
    static int[] nums;
    static int[] pSum;
    static int[] pSqSum;
    static int[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            N = Integer.parseInt(input.split(" ")[0]);
            S = Integer.parseInt(input.split(" ")[1]);

            nums = new int[N];
            pSum = new int[N];
            pSqSum = new int[N];
            cache = new int[N][N];

            input = br.readLine();
            String[] inputs = input.split(" ");

            if (S >= N) {
                sb.append(0).append("\n");
                continue;
            }

            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(inputs[i]);

                for (int j = 0; j < N; j++)
                    cache[i][j] = -1;
            }

            Arrays.sort(nums);

            pSum[0] = nums[0];
            pSqSum[0] = nums[0] * nums[0];

            // 미리 부분합들 계산
            for (int i = 1; i < N; i++) {
                pSum[i] = pSum[i - 1] + nums[i];
                pSqSum[i] = pSqSum[i - 1] + nums[i] * nums[i];
            }

            sb.append(part(0, S)).append("\n");
        }

        System.out.println(sb);
    }

    static int part(int start, int count) {
        // 숫자가 다 양자화 됨
        if (start == N)
            return 0;

        // 더이상 묶을 수 없음
        if (count == 0)
            return 987654321;

        int minSum = cache[start][count];

        if (minSum != -1)
            return minSum;

        minSum = 987654321;

        for (int length = 1; start + length <= N; length++) {
            minSum = Math.min(minSum, getSum(start, start + length - 1) + part(start + length, count - 1));
            cache[start][count] = minSum;
        }

        return minSum;
    }

    static int getSum(int start, int end) {
        int sum = pSum[end] - (start == 0 ? 0 : pSum[start - 1]);
        int sqSum = pSqSum[end] - (start == 0 ? 0 : pSqSum[start - 1]);
        int avg = Math.round((float) sum / (end - start + 1));

        return sqSum - 2 * avg * sum + avg * avg * (end - start + 1);
    }

}

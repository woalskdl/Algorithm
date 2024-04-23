package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ITES {
    private static int K;
    private static int N;
    private static int A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            K = Integer.parseInt(input.split(" ")[0]);
            N = Integer.parseInt(input.split(" ")[1]);

            int count = 0;

            Queue<Integer> q = new LinkedList<>();

            A = 1983;

            for (int i = 0; i < N; i++) {
                int sign = A % 10000 + 1;

                A = (int) ((A * 214013 + 2531011) % Math.pow(2, 32));

                if (sign == K) {
                    count += 1;
                    continue;
                } else if (sign > K) {
                    continue;
                }

                int qSize = q.size();

                for (int j = 0; j < qSize; j++) {
                    int sum = q.poll() + sign;

                    if (sum == K)
                        count += 1;
                    else if (sum < K)
                        q.offer(sum);
                }

                q.offer(sign);
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}

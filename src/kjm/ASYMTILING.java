package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ASYMTILING {

    static int n;
    static int[] memo;
    final static int REM = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (C-- > 0) {
            n = Integer.parseInt(br.readLine());
            memo = new int[n + 1];

            Arrays.fill(memo, -1);

            sb.append(asymmetric()).append("\n");
        }

        System.out.println(sb);
    }

    private static int count(int length) {
        if (length <= 1)
            return 1;

        if (memo[length] != -1)
            return memo[length];

        return memo[length] = (count(length - 1) + count(length - 2)) % REM;
    }

    private static int asymmetric() {
        if (n % 2 == 1)
            return (count(n) - count(n / 2) + REM) % REM;
        else
            return ((count(n) - count(n / 2) + REM) % REM - count(n / 2 - 1) + REM) % REM;
    }

}

package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JLIS_SOL {
    static int[] A;
    static int[] B;
    static int[][] lengths;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            String input = br.readLine();

            int n = Integer.parseInt(input.split(" ")[0]);
            int m = Integer.parseInt(input.split(" ")[1]);

            A = new int[n];
            B = new int[m];

            input = br.readLine();
            String[] as = input.split(" ");

            for (int i = 0; i < n; i++)
                A[i] = Integer.parseInt(as[i]);

            input = br.readLine();
            String[] bs = input.split(" ");
            for (int i = 0; i < m; i++)
                B[i] = Integer.parseInt(bs[i]);

            lengths = new int[n + 1][m + 1];

            sb.append(getLongest(-1, -1) - 2).append("\n");
        }

        System.out.println(sb);
    }

    static int getLongest(int a, int b) {
        int saved = lengths[a + 1][b + 1];

        if (saved != 0)
            return saved;

        int ret = 2;

        int aVal = a == -1 ? Integer.MIN_VALUE : A[a];
        int bVal = b == -1 ? Integer.MIN_VALUE : B[b];

        int lastVal = Math.max(aVal, bVal);

        for (int aa = a + 1; aa < A.length; aa++) {
            if (lastVal < A[aa])
                ret = Math.max(ret, 1 + getLongest(aa, b));
        }

        for (int bb = b + 1; bb < B.length; bb++) {
            if (lastVal < B[bb])
                ret = Math.max(ret, 1 + getLongest(a, bb));
        }

        lengths[a + 1][b + 1] = ret;
        return ret;
    }
}

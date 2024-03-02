package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JLIS {
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

            sb.append(getLongest(-1, -1, Integer.MIN_VALUE) - 1).append("\n");
        }

        System.out.println(sb);
    }

    static int getLongest(int a, int b, int lastVal) {
        int saved = lengths[a + 1][b + 1];

        if (saved != 0)
            return saved;

        int ret = 1;

        int aa = a + 1;
        int bb = b + 1;

        while (aa < A.length || bb < B.length) {
            if (aa < A.length && bb < B.length) {
                if (A[aa] <= B[bb] && A[aa] > lastVal) {
                    ret = Math.max(ret, 1 + getLongest(aa, b, A[aa]));
                    aa++;
                } else if (A[aa] > B[bb] && B[bb] > lastVal) {
                    ret = Math.max(ret, 1 + getLongest(a, bb, B[bb]));
                    bb++;
                } else if (A[aa] > lastVal) {
                    ret = Math.max(ret, 1 + getLongest(aa, b, A[aa]));
                    aa++;
                } else if (B[bb] > lastVal) {
                    ret = Math.max(ret, 1 + getLongest(a, bb, B[bb]));
                    bb++;
                } else {
                    aa++;
                    bb++;
                }
            } else if (aa < A.length) {
                if (A[aa] > lastVal)
                    ret = Math.max(ret, 1 + getLongest(aa, b, A[aa]));

                aa++;
            } else {
                if (B[bb] > lastVal)
                    ret = Math.max(ret, 1 + getLongest(a, bb, B[bb]));

                bb++;
            }
        }

        lengths[a + 1][b + 1] = ret;
        return ret;
    }
}

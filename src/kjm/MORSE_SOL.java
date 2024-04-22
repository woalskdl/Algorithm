package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MORSE_SOL {
    private static int n;
    private static int m;
    private static int k;
    private static int idx;
    private static final int M = 1000000000 + 100;
    private static int[][] bino;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            n = Integer.parseInt(input.split(" ")[0]);
            m = Integer.parseInt(input.split(" ")[1]);
            k = Integer.parseInt(input.split(" ")[2]);

            idx = k - 1;
            bino = new int[201][201];

            calcBino();
            align(n, m, "");
//            sb.append(kth(n, m)).append("\n");
        }

        System.out.println(sb);
    }

    private static void calcBino() {
        for (int i = 0; i <= 200; i++) {
            bino[i][0] = bino[i][i] = 1;
            for (int j = 1; j < i; j++)
                bino[i][j] = Math.min(M, bino[i - 1][j - 1] + bino[i - 1][j]);
        }
    }

    private static String kth(int a, int b) {
        if (a == 0) {
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < b; i++)
                ret.append("o");

            return ret.toString();
        }

        if (idx < bino[a + b - 1][a - 1])
            return "-" + kth(a - 1, b);
        else {
            idx -= bino[a + b - 1][a - 1];
            return "o" + kth(a, b - 1);
        }
    }

    private static void align(int a, int b, String morse) {
        if (idx < 0)
            return;

        if (a == 0 && b == 0) {
            if (idx == 0)
                System.out.println(morse);

            idx -= 1;
            return;
        }

        if (bino[a + b][a] <= idx) {
            idx -= bino[a + b][a];
            return;
        }

        if (a > 0)
            align(a - 1, b, morse + "-");

        if (b > 0)
            align(a, b - 1, morse + "o");
    }
}

//public class MORSE_SOL {
//    private static int n;
//    private static int m;
//    private static int k;
//    private static int idx;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        int C = Integer.parseInt(br.readLine());
//
//        while (C-- > 0) {
//            String input = br.readLine();
//
//            n = Integer.parseInt(input.split(" ")[0]);
//            m = Integer.parseInt(input.split(" ")[1]);
//            k = Integer.parseInt(input.split(" ")[2]);
//
//            idx = k - 1;
//            align(n, m, "");
//        }
//
//        System.out.println(sb);
//    }
//
//    private static void align(int a, int b, String morse) {
//        if (idx < 0)
//            return;
//
//        if (a == 0 && b == 0) {
//            if (idx == 0)
//                System.out.println(morse);
//
//            idx -= 1;
//            return;
//        }
//
//        if (a > 0)
//            align(a - 1, b, morse + "-");
//
//        if (b > 0)
//            align(a, b - 1, morse + "o");
//    }
//}

package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NUMB3RS {

    private static int N;
    private static int D;
    private static int P;
    private static int[][] route;
    private static int[] connected;
    private static double[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();
            N = Integer.parseInt(input.split(" ")[0]);
            D = Integer.parseInt(input.split(" ")[1]);
            P = Integer.parseInt(input.split(" ")[2]);

            route = new int[N][N];
            connected = new int[N];

            for (int i = 0; i < N; i++) {
                input = br.readLine();

                String[] r = input.split(" ");
                for (int j = 0; j < N; j++) {
                    int c = Integer.parseInt(r[j]);
                    route[i][j] = c;

                    if (c == 1)
                        connected[i] += 1;
                }
            }

            cache = new double[D + 1][N];

            int T = Integer.parseInt(br.readLine());
            String[] cities = br.readLine().split(" ");

            for (int i = 0; i < T; i++) {
                for (int j = 0; j < D + 1; j++)
                    Arrays.fill(cache[j], -1);

                int t = Integer.parseInt(cities[i]);
                System.out.print(rate(0, P, t));
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    private static double rate(int days, int curCity, int target) {
        if (days == D)
            return curCity == target ? 1 : 0;

        if (cache[days][curCity] != -1)
            return cache[days][curCity];

        int[] r = route[curCity];

        double ret = 0;
        for (int i = 0; i < N; i++) {
            if (r[i] == 1)
                ret += rate(days + 1, i, target) / connected[curCity];
        }

        return cache[days][curCity] = ret;
    }
}

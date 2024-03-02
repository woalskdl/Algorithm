package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUMPGAME {

    static int n;
    static int[][] grid;
    static int[][] memoization;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            n = Integer.parseInt(br.readLine());

            grid = new int[n][n];
            memoization = new int[n][n];

            for (int i = 0; i < n; i++) {

                String input = br.readLine();
                String[] steps = input.split(" ");

                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(steps[j]);
                }
            }

            sb.append(forward(0, 0) ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    static boolean forward(int y, int x) {
        if (!inArea(y, x))
            return false;

        if (memoization[y][x] != 0)
            return memoization[y][x] == 1;

        if (y == n - 1 && x == n - 1)
            return true;

        int step = grid[y][x];

        boolean f = forward(y + step, x) || forward(y, x + step);

        memoization[y][x] = f ? 1 : -1;

        return f;
    }

    static boolean inArea(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}

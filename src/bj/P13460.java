
package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P13460 {

    private static char[][] map;
    private static int ry;
    private static int rx;
    private static int by;
    private static int bx;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int N = Integer.parseInt(input.split(" ")[0]);
        int M = Integer.parseInt(input.split(" ")[1]);

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine();

            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                map[i][j] = c;

                if (c == 'R') {
                    ry = i;
                    rx = j;
                } else if (c == 'B') {
                    by = i;
                    bx = j;
                }
            }
        }

        System.out.println(getMoveCount(ry, rx, by, bx, 0));
    }

    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static int getMoveCount(int cry, int crx, int cby, int cbx, int moveCount) {
        if (moveCount >= 10)
            return -1;

        int count = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int pry = cry;
            int prx = crx;
            int pby = cby;
            int pbx = cbx;

            boolean rGoal = false;
            boolean bGoal = false;

            while (((!rGoal && map[pry + dy[i]][prx + dx[i]] != '#') || (!bGoal && map[pby + dy[i]][pbx + dx[i]] != '#'))) {
                if (map[pry + dy[i]][prx + dx[i]] == 'O') {
                    rGoal = true;
                } else if (map[pry + dy[i]][prx + dx[i]] != '#') {
                    pry += dy[i];
                    prx += dx[i];
                }

                if (map[pby + dy[i]][pbx + dx[i]] == 'O') {
                    bGoal = true;
                } else if (map[pby + dy[i]][pbx + dx[i]] != '#') {
                    pby += dy[i];
                    pbx += dx[i];
                }
            }

            if (rGoal || bGoal) {
                if (bGoal)
                    continue;

                count = moveCount + 1;
                continue;
            }

            if (pry == pby && prx == pbx) {
                switch (i) {
                    case 0:
                        if (cry > cby)
                            pry -= dy[i];
                        else
                            pby -= dy[i];
                        break;
                    case 1:
                        if (cry > cby)
                            pby -= dy[i];
                        else
                            pry -= dy[i];
                        break;
                    case 2:
                        if (crx > cbx)
                            prx -= dx[i];
                        else
                            pbx -= dx[i];
                        break;
                    case 3:
                        if (crx > cbx)
                            pbx -= dx[i];
                        else
                            prx -= dx[i];
                        break;
                }
            }

            int m = getMoveCount(pry, prx, pby, pbx, moveCount + 1);
            if (m != -1)
                count = Math.min(count, m);
        }

        return count == Integer.MAX_VALUE ? -1 : count;
    }
}

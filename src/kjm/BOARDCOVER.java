package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOARDCOVER {
    // https://algospot.com/judge/problem/read/BOARDCOVER
    static int C;
    static int H, W;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine().replaceAll(" ", ""));

        while (C-- > 0) {
            String boardHW = br.readLine();

            H = Integer.parseInt(boardHW.split(" ")[0]);
            W = Integer.parseInt(boardHW.split(" ")[1]);

            board = new char[H][W];

            int coverableCnt = 0;

            for (int i = 0; i < H; i++) {
                String line = br.readLine();

                for (int j = 0; j < W; j++) {
                    char point = line.charAt(j);
                    board[i][j] = point;

                    if (point == '.')
                        coverableCnt += 1;
                }
            }

            if (coverableCnt % 3 != 0) {
                sb.append(0).append("\n");
                continue;
            }

            sb.append(cover(coverableCnt)).append("\n");
        }

        System.out.println(sb);
    }

    static int[][] dy = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}};
    static int[][] dx = {{0, -1}, {0, 1}, {0, 1}, {0, -1}};

    static int cover(int coverableCnt) {
        if (coverableCnt == 0)
            return 1;

        int result = 0;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (board[y][x] == '#')
                    continue;

                for (int i = 0; i < 4; i++) {

                    int yy1 = y + dy[i][0];
                    int xx1 = x + dx[i][0];
                    int yy2 = y + dy[i][1];
                    int xx2 = x + dx[i][1];

                    if (coverable(yy1, xx1) && coverable(yy2, xx2)) {
                        board[y][x] = '#';
                        board[yy1][xx1] = '#';
                        board[yy2][xx2] = '#';

                        result += cover(coverableCnt - 3);

                        board[y][x] = '.';
                        board[yy1][xx1] = '.';
                        board[yy2][xx2] = '.';
                    }
                }
            }
        }

        return result;
    }

    static boolean coverable(int y, int x) {
        return y >= 0 && y < H && x >= 0 && x < W && board[y][x] == '.';
    }
}

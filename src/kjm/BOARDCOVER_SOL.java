package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOARDCOVER_SOL {
    // https://algospot.com/judge/problem/read/BOARDCOVER
    static int C;
    static int H, W;
    static int[][] board;
    static int coverCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine().replaceAll(" ", ""));

        while (C-- > 0) {
            String boardHW = br.readLine();

            H = Integer.parseInt(boardHW.split(" ")[0]);
            W = Integer.parseInt(boardHW.split(" ")[1]);

            board = new int[H][W];

            int coverableCnt = 0;

            for (int i = 0; i < H; i++) {
                String line = br.readLine();

                for (int j = 0; j < W; j++) {
                    char point = line.charAt(j);

                    if (point == '#') {
                        board[i][j] = 1;
                    } else {
                        coverableCnt += 1;
                    }
                }
            }

            if (coverableCnt % 3 != 0) {
                sb.append(0).append("\n");
                continue;
            }

            coverCnt = coverableCnt / 3;

            sb.append(getAllCoverCount()).append("\n");
        }

        System.out.println(sb);
    }

    /*
     ** 빈 칸 중 가장 위, 왼쪽을 채운다고 가정 **
      >> 왼쪽과 위에 있는 칸은 항상 채워져있다고 가정한다
      >> 앞서 채웠던 도형에 대해서는 다시 생각할 필요 없으므로 중복 제거
      >> 아래와 같은 모양으로 채운다.
    */

    // 나의 오답 사유
    // 1. *빈 칸 중 가장 위, 왼쪽을 채운다고 기준이 없었음
    //   > 제일 먼저 위, 왼쪽을 찾은 뒤 현재 반복문 계속 진행
    //   > 순서가 강제되지 않아서 같은 모양으로 채우는 경우도 중복으로 계산 및 무한반복 문제 발생
    // 2. 1번에 의해 블록의 모양을 잡을때 위, 왼쪽을 덮는 경우의 모양도 잡았다. (현재 포인트를 기준으로 +모양으로 4가지)
    // 3. 중복을 제거하는 방법이 전부 다 채웠을 때의 어떤 포인트에 어떤 모양이 있는지를 비교해 보아야 한다고만 생각
    //   > 결국 다 채울때까지 재귀호출 및 반복문을 할 수 밖에 없었고, 1번의 사유로 무한반복 문제를 해결하지 못함.
    //   > 완전탐색 시 재귀 호출 조건의 기준을 제대로 정하지 못함.
    static int[][] dy = {{0, 1, 0}, {0, 0, 1}, {0, 1, 1}, {0, 1, 1}};
    static int[][] dx = {{0, 0, 1}, {0, 1, 1}, {0, 0, 1}, {0, 0, -1}};

    // 완성되었을때 모양을 저장해두어서 같은 모양으로 완성되지 않도록 해야한다.
    static int getAllCoverCount() {
        int y = -1, x = -1;

        // 아직 채우지 못한 칸 중 제일 윗줄 왼쪽의 칸을 찾는다.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }

            if (y != -1)
                break;
        }

        // 기저 사례 : 모든 칸을 채웠으면 1을 반환한다.
        if (y == -1)
            return 1;

        int result = 0;
        for (int type = 0; type < 4; type++) {
            // 덮을 수 있다면 재귀 호출
            if (cover(1, y, x, type))
                result += getAllCoverCount();

            // 덮었던 블록 치우기
            cover(-1, y, x, type);
        }

        return result;
    }

    static boolean coverable(int y, int x) {
        return y >= 0 && y < H && x >= 0 && x < W;
    }

    static boolean cover(int delta, int y, int x, int type) {
        boolean coverable = true;

        for (int i = 0; i < 3; i++) {
            int yy = y + dy[type][i];
            int xx = x + dx[type][i];

            if (!coverable(yy, xx)) {
                coverable = false;
                break;
            } else {
                board[yy][xx] += delta;
                if (board[yy][xx] > 1)
                    coverable = false;
            }
        }

        return coverable;
    }
}

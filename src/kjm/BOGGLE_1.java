package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOGGLE_1 {
    // 문제
    // https://algospot.com/judge/problem/read/BOGGLE

    static int C;
    static char[][] boggle;
    static int[][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (C > 0) {
            boggle = new char[5][5];

            for (int i = 0; i < 5; i++) {
                String line = br.readLine();
                boggle[i] = line.toCharArray();
            }

            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                String word = br.readLine();

                memo = new int[5][5][11];

                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++)
                        Arrays.fill(memo[j][k], -1);
                }

                boolean found = false;
                for (int y = 0; y < 5 && !found; y++) {
                    for (int x = 0; x < 5 && !found; x++) {
                        if (boggle[y][x] == word.charAt(0))
                            found = findWord(word, 1, y, x);
                    }
                }

                sb.append(word).append(" ").append(found ? "YES\n" : "NO\n");
            }

            C--;
        }

        System.out.println(sb);
    }

    static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    static boolean findWord(String word, int charIdx, int y, int x) {
        if (charIdx == word.length())
            return true;

        if (memo[y][x][charIdx] != -1)
            return memo[y][x][charIdx] == 1;

        char findChar = word.charAt(charIdx);
        boolean found = false;

        for (int i = 0; i < 8 && !found; i++) {
            int yy = y + dy[i];
            int xx = x + dx[i];

            if (yy >= 0 && xx >= 0 && yy < 5 && xx < 5) {
                if (boggle[yy][xx] == findChar)
                    found = findWord(word, charIdx + 1, yy, xx);
            }
        }

        if (found)
            memo[y][x][charIdx] = 1;
        else
            memo[y][x][charIdx] = 0;

        return found;
    }
}

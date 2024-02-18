package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOGGLE_2 {
    // 문제
    // https://algospot.com/judge/problem/read/BOGGLE

    static int C;
    static char[][] boggle;

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

                boolean found = false;
                for (int y = 0; y < 5 && !found; y++) {
                    for (int x = 0; x < 5 && !found; x++) {
                        found = findWord(word, y, x);
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

    static boolean findWord(String word, int y, int x) {
        if (!inRange(y, x))
            return false;

        char firstChar = word.charAt(0);

        if (boggle[y][x] != firstChar)
            return false;

        if (word.length() == 1)
            return true;

        boolean found = false;

        for (int i = 0; i < 8 && !found; i++) {
            found = findWord(word.substring(1), y + dy[i], x + dx[i]);
        }

        return found;
    }

    static boolean inRange(int y, int x) {
        return y >= 0 && x >= 0 && y < 5 && x < 5;
    }
}

/*
1
NNNNN
NEEEN
NEYEN
NEEEN
NSNNN
1
YES
 */
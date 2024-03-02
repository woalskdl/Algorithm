package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WILDCARD_SOL {

    static int[][] memoization;
    static String W;
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            W = br.readLine();

            int N = Integer.parseInt(br.readLine());

            List<String> files = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                S = br.readLine();
                memoization = new int[101][101];

                if (find(0, 0) == 1)
                    files.add(S);
            }

            Collections.sort(files);

            for (String file : files)
                sb.append(file).append("\n");
        }

        System.out.println(sb);
    }

    static int find(int w, int s) {
        int ret = memoization[w][s];

        if (ret != 0)
            return ret;

        if (s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            ret = find(w + 1, s + 1);
            return ret;
        }

        if (w == W.length()) {
            ret = (s == S.length() ? 1 : -1);
            return ret;
        }

        if (W.charAt(w) == '*') {
            if (find(w + 1, s) == 1 || (s < S.length() && (find(w, s + 1) == 1))) {
                ret = 1;
                return ret;
            }
        }

        memoization[w][s] = -1;
        return -1;
    }
}

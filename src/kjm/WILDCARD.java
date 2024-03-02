package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WILDCARD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String W = br.readLine();

            int N = Integer.parseInt(br.readLine());

            List<String> files = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String fileName = br.readLine();

                if (find(W, fileName))
                    files.add(fileName);
            }

            Collections.sort(files);

            for (String file : files)
                sb.append(file).append("\n");
        }

        System.out.println(sb);
    }

    static boolean find(String w, String file) {
        if (w.equals(file))
            return true;

        boolean equal = true;
        int fileIdx = 0;
        for (int i = 0; i < w.length() && equal; i++) {
            char p = w.charAt(i);

            if (p != '*' && p != '?') {
                char f = file.charAt(fileIdx);
                equal = (p == f);
                fileIdx ++;
                continue;
            }

            if (p == '?') {
                fileIdx ++;
                continue;
            }

            if (i + 1 < w.length()) {
                int find = file.indexOf(w.charAt(i + 1), fileIdx);
                equal = find != -1;

                if (equal)
                    fileIdx = find;
            } else {
                fileIdx = file.length();
            }
        }

        return equal && fileIdx == file.length();
    }
}

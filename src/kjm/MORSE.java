package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MORSE {

    private static int n;
    private static int m;
    private static int k;
    private static int size;
    private static int idx;
    private static String morseAtK;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            n = Integer.parseInt(input.split(" ")[0]);
            m = Integer.parseInt(input.split(" ")[1]);
            k = Integer.parseInt(input.split(" ")[2]);

            size = n + m;

            idx = 0;
            morseAtK = "";

            align(0, 0, "");

            sb.append(morseAtK).append("\n");
        }

        System.out.println(sb);
    }

    private static void align(int a, int b, String morse) {
        if (idx == k)
            return;

        if (a == n && b == m) {
            if (morse.length() == size) {
                idx += 1;

                if (idx == k)
                    morseAtK = morse;
            }

            return;
        }

        for (int i = a; i < n; i++)
            align(i + 1, b, morse + "-");

        for (int i = b; i < m; i++)
            align(a, i + 1, morse + "o");
    }
}

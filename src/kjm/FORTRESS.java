package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FORTRESS {
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            map = new int[1001][1001];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                String[] inputs = input.split(" ");

                int x = Integer.parseInt(inputs[0]);
                int y = Integer.parseInt(inputs[1]);
                int r = Integer.parseInt(inputs[2]);

                for (int j = 0; j < 1001; j++) {
                    for (int k = 0; k < 1001; k++) {
                        if (j + k <= x + y + r) // 이거 아님
                            map[j][k] = i + 1;
                    }
                }
            }
        }
    }
}

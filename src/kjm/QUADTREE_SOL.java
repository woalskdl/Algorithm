package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QUADTREE_SOL {
    static String quad;
    static int idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            quad = br.readLine();
            idx = 0;

            sb.append(flip()).append("\n");
        }

        System.out.println(sb);
    }

    static String flip() {
        char pixel = quad.charAt(idx++);

        if (pixel == 'w' || pixel == 'b')
            return pixel + "";

        String ul = flip();
        String ur = flip();
        String ll = flip();
        String lr = flip();

        return "x" + ll + lr + ul + ur;
    }
}

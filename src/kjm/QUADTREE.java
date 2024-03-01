package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QUADTREE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String quad = br.readLine();

            sb.append(flip(quad)).append("\n");
        }

        System.out.println(sb);
    }

    static String flip(String quad) {
        String[] quads = new String[4];

        int i;
        int area = 0;

        for (i = 0; i < quad.length() && area < 4; i++) {
            char pixel = quad.charAt(i);

            if (pixel == 'x') {
                String flipped = flip(quad.substring(i + 1));
                quads[area] = (pixel + flipped);
                i += flipped.length();
            } else {
                quads[area] = pixel + "";
            }

            area += 1;
        }

        String fliped = "";
        if (area == 4) {
            fliped += quads[2];
            fliped += quads[3];
            fliped += quads[0];
            fliped += quads[1];
        } else {
            fliped += quads[0];
        }

        return fliped;
    }
}

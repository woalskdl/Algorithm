package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FORTRESS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine().replaceAll(" ", ""));

            String input = br.readLine();
            String[] inputs = input.split(" ");

            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            int r = Integer.parseInt(inputs[2]);

            Castle castle = new Castle();
            castle.x = x;
            castle.y = y;
            castle.r = r;
            castle.depth = 0;

            for (int i = 1; i < N; i++) {
                input = br.readLine();
                inputs = input.split(" ");

                x = Integer.parseInt(inputs[0]);
                y = Integer.parseInt(inputs[1]);
                r = Integer.parseInt(inputs[2]);

                castle(x, y, r, castle);
            }

            List<Integer> depthList = new ArrayList<>();

            deepest(castle, depthList);

            depthList.sort(Collections.reverseOrder());

            if (depthList.size() == 1)
                sb.append(depthList.get(0));
            else if (depthList.isEmpty())
                sb.append(0);
            else
                sb.append(Math.max(depthList.get(0) + depthList.get(1), depthList.get(0)));

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void deepest(Castle castle, List<Integer> depthList) {
        if (castle.castleList.isEmpty()) {
            depthList.add(castle.depth);
            return;
        }

        for (Castle c : castle.castleList)
            deepest(c, depthList);
    }

    private static void castle(int x, int y, int r, Castle parent) {
        if (parent.castleList.isEmpty()) {
            Castle c = new Castle();
            c.x = x;
            c.y = y;
            c.r = r;
            c.depth = parent.depth + 1;

            parent.castleList.add(c);
            return;
        }

        boolean found = false;
        for (Castle c : parent.castleList) {
            double distance = Math.pow(x - c.x, 2) + Math.pow(y - c.y, 2);
            double biggerR = Math.pow(Math.max(r, c.r), 2);

            if (distance < biggerR) {
                castle(x, y, r, c);
                found = true;
                break;
            }
        }

        if (!found) {
            Castle c = new Castle();
            c.x = x;
            c.y = y;
            c.r = r;
            c.depth = parent.depth + 1;

            parent.castleList.add(c);
        }
    }

    static class Castle {
        int x;
        int y;
        int r;
        int depth;
        List<Castle> castleList = new ArrayList<>();
    }
}

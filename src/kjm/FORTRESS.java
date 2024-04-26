package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FORTRESS {
    private static int deepest;
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

            for (int i = 1; i < N; i++) {
                input = br.readLine();
                inputs = input.split(" ");

                x = Integer.parseInt(inputs[0]);
                y = Integer.parseInt(inputs[1]);
                r = Integer.parseInt(inputs[2]);

                castle(x, y, r, castle);
            }

            deepest = 0;
            int d = deepest(castle);

            sb.append(Math.max(d, deepest));

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int deepest(Castle castle) {
        List<Integer> depthList = new ArrayList<>();

        for (int i = 0; i < castle.castleList.size(); i++)
            depthList.add(deepest(castle.castleList.get(i)));

        if (depthList.isEmpty())
            return 0;

        depthList.sort(Collections.reverseOrder());

        if (depthList.size() >= 2)
            deepest = Math.max(deepest, 2 + depthList.get(0) + depthList.get(1));

        return depthList.get(0) + 1;
    }

    private static void castle(int x, int y, int r, Castle parent) {
        if (parent.castleList.isEmpty()) {
            Castle c = new Castle();
            c.x = x;
            c.y = y;
            c.r = r;

            parent.castleList.add(c);
            return;
        }

        boolean found = false;
        for (Castle c : parent.castleList) {
            if (r > c.r)
                continue;

            double distance = Math.pow(x - c.x, 2) + Math.pow(y - c.y, 2);
            double R = Math.pow(c.r, 2);

            if (distance < R) {
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

            parent.castleList.add(c);
        }
    }

    static class Castle {
        int x;
        int y;
        int r;
        List<Castle> castleList = new ArrayList<>();
    }
}

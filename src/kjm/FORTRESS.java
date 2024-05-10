package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FORTRESS {
    private static int N;
    private static int[] x;
    private static int[] y;
    private static int[] r;
    private static int deepest;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            N = Integer.parseInt(br.readLine().replaceAll(" ", ""));

            x = new int[N];
            y = new int[N];
            r = new int[N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                String[] inputs = input.split(" ");

                x[i] = Integer.parseInt(inputs[0]);
                y[i] = Integer.parseInt(inputs[1]);
                r[i] = Integer.parseInt(inputs[2]);
            }

            Castle castle = castle(0);

            deepest = 0;
            int d = deepest(castle);

            sb.append(Math.max(d, deepest)).append("\n");
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

    private static Castle castle(int root) {
        Castle castle = new Castle();

        for (int i = 0; i < N; i++) {
            if (isChild(root, i))
                castle.castleList.add(castle(i));
        }

        return castle;
    }

    private static boolean isChild(int root, int ch) {
        if (!enclose(root, ch))
            return false;

        for (int i = 0; i < N; i++) {
            if (i != root && i != ch && enclose(root, i) && enclose(i, ch))
                return false;
        }

        return true;
    }

    private static boolean enclose(int root, int ch)  {
        if (r[ch] > r[root])
            return false;

        double distance = Math.pow(x[ch] - x[root], 2) + Math.pow(y[ch] - y[root], 2);
        double R = Math.pow(r[root] - r[ch], 2);

        return distance < R;
    }

    static class Castle {
        List<Castle> castleList = new ArrayList<>();
    }

}

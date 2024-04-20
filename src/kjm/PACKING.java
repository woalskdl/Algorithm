package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PACKING {

    private static int N;
    private static int W;
    private static String[] goods;
    private static int[] volumes;
    private static int[] needs;
    private static int[] brings;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            N = Integer.parseInt(input.split(" ")[0]);
            W = Integer.parseInt(input.split(" ")[1]);

            goods = new String[N];
            volumes = new int[N];
            needs = new int[N];

            for (int i = 0; i < N; i++) {
                input = br.readLine();
                String[] inputs = input.split(" ");

                goods[i] = inputs[0];
                volumes[i] = Integer.parseInt(inputs[1]);
                needs[i] = Integer.parseInt(inputs[2]);
            }

            brings = new int[N + 1];
            Arrays.fill(brings, -1);

            cache = new int[N][1001];

            for (int i = 0; i < N; i++)
                Arrays.fill(cache[i], -1);

            sb.append(getMaxNeed(0, 0)).append(" ");

            reconstruct(0, 0);

            int count = 0;
            for (int i = 0; i < N; i++) {
                if (brings[i] == 1)
                    count += 1;
            }

            sb.append(count).append("\n");

            for (int i = 0; i < N; i++) {
                if (brings[i] == 1)
                    sb.append(goods[i]).append("\n");
            }

        }

        System.out.println(sb);
    }

    private static int getMaxNeed(int bring, int volume) {
        if (bring == N)
            return 0;

        if (cache[bring][volume] != -1)
            return cache[bring][volume];

        int ret = getMaxNeed(bring + 1, volume);

        if (volume + volumes[bring] <= W)
            ret = Math.max(ret, needs[bring] + getMaxNeed(bring + 1, volume + volumes[bring]));

        return cache[bring][volume] = ret;
    }

    private static void reconstruct(int item, int volume) {
        if (item == N)
            return;

        if (getMaxNeed(item, volume) == getMaxNeed(item + 1, volume)) {
            reconstruct(item + 1, volume);
        } else {
            brings[item] = 1;
            reconstruct(item + 1, volume + volumes[item]);
        }

    }
}

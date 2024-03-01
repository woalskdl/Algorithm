package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLOCKSYNC_SOL {
    static int pressed;
    final static int[][] switches = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int[] clocks = new int[16];
            pressed = 0;

            String input = br.readLine();

            int i = 0;
            for (String time : input.split(" "))
                clocks[i++] = Integer.parseInt(time);

            int pressed = getPressCount(clocks, 0);

            sb.append(pressed > 40 ? -1 : pressed).append("\n");
        }

        System.out.println(sb);
    }

    static int getPressCount(int[] clocks, int switchIdx) {
        if (switchIdx > 9)
            return areAllAligned(clocks) ? 0 : 9999;

        int pressed = 9999;
        for (int cnt = 0; cnt < 4; cnt++) {
            pressed = Math.min(pressed, cnt + getPressCount(clocks, switchIdx + 1));
            press(switchIdx, clocks);
        }

        return pressed;
    }

    static void press(int switchIdx, int[] clocks) {
        for (int idx : switches[switchIdx]) {
            int nextTime = clocks[idx] + 3;
            clocks[idx] = nextTime > 12 ? 3 : nextTime;
        }
    }

    static boolean areAllAligned(int[] clocks) {
        for (int time : clocks) {
            if (time != 12)
                return false;
        }

        return true;
    }
}

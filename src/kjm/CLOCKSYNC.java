package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLOCKSYNC {
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

            sb.append(getPressCount(clocks, new int[10])).append("\n");
        }

        System.out.println(sb);
    }

    // 1. 시계는 12시에 맞춰졌다가도 다시 더 눌릴 수 있음
    // 2. 한 버튼을 3번까지만 눌러볼 수 있음. 그 이상 눌러도 안맞으면 걔는 아닌거임.
    static int getPressCount(int[] clocks, int[] pressedCounts) {

        // 기저사례 : 12시가 아닌 시계가 없으면 다 맞춤
        if (!areAllAligned(clocks)) {
            int pressed = 0;

            for (int i = 0; i < pressedCounts.length; i++)
                pressed += pressedCounts[i];

            return pressed;
        }

        int minPressed = Integer.MAX_VALUE;
        boolean completed = false;

        for (int i = 0; i < pressedCounts.length; i++) {
            if (pressedCounts[i] == 3)
                continue;

            // 현재 버튼을 누르고 다음 버튼 누르기
            int[] nextPressedCnt = pressedCounts.clone();
            int[] pressedClocks = clocks.clone();

            nextPressedCnt[i] += 1;

            press(i, pressedClocks);

            int pressed = getPressCount(pressedClocks, nextPressedCnt);

            // 완성되었다면 0보다 큰 수가 나옴
            completed = pressed > 0;

            if (completed)
                minPressed = Math.min(minPressed, pressed);
        }

        if (completed)
            return minPressed;
        // 한번도 완성하지 못했다면 -1
        else
            return -1;
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
                return true;
        }

        return false;
    }
}

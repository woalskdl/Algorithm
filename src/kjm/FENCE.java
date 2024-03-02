package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FENCE {

    static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            heights = new int[N];

            String[] inputs = br.readLine().split(" ");

            for (int i = 0; i < N; i++)
                heights[i] = Integer.parseInt(inputs[i]);

            sb.append(getMaxFence(0, N - 1)).append("\n");
        }

        System.out.println(sb);
    }

    static int getMaxFence(int left, int right) {
        if (left == right)
            return heights[left];

        int middle = (left + right) / 2;

        int leftMax = getMaxFence(left, middle);
        int rightMax = getMaxFence(middle + 1, right);

        int max = Math.max(leftMax, rightMax);

        int leftIdx = middle;
        int rightIdx = middle + 1;

        int minHeight = Math.min(heights[leftIdx], heights[rightIdx]);
        max = Math.max(max, minHeight * 2);

        while (leftIdx > left || rightIdx < right) {
            if (rightIdx < right && (leftIdx == left || heights[leftIdx - 1] < heights[rightIdx + 1])) {
                rightIdx += 1;
                minHeight = Math.min(minHeight, heights[rightIdx]);
            } else {
                leftIdx -= 1;
                minHeight = Math.min(minHeight, heights[leftIdx]);
            }

            max = Math.max(max, (rightIdx - leftIdx + 1) * minHeight);
        }

        return max;
    }
}

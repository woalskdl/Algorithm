package test;

import java.util.Arrays;

public class TOSS_20240427_1 {
    public int solution(int[] levels) {

        int size = levels.length;

        if (size < 4)
            return -1;

        Arrays.sort(levels);
        return levels[size - (size / 4)];
    }
}

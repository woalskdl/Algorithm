package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KAKAO_DEMO_240302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] inputs = input.split(",");

        int[] A = Arrays.stream(inputs).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(A));
    }
    /*
    This is a demo task.
    Write a function:
    class Solution { public int solution(int[] A); }
    that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
    For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
    Given A = [1, 2, 3], the function should return 4.
    Given A = [−1, −3], the function should return 1.
    Write an efficient algorithm for the following assumptions:
    N is an integer within the range [1..100,000];
    each element of array A is an integer within the range [−1,000,000..1,000,000].
     */

    public static int solution(int[] A) {
        boolean[] exist = new boolean[1000000];

        for (int i : A) {
            if (i > 0)
                exist[i] = true;
        }

        for (int smallest = 1; smallest < 100000; smallest++) {
            if (!exist[smallest])
                return smallest;
        }

        return 1;
    }
}

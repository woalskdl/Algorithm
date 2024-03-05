package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KAKAO_240303_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        System.out.println(solution(3, new int[]{1, 3}, new int[]{2, 2}));

//        for (int i = 0; i < n + 1; i++) {
//            for (int j = 0; j < n + 1; j++) {
//                System.out.print(path[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    static int n;
    static boolean[][] path;

    public static boolean solution(int N, int[] A, int[] B) {

        n = N;
        path = new boolean[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            path[0][i] = true;
            path[i][0] = true;
        }

        for (int i = 0; i < A.length; i++) {
            path[A[i]][B[i]] = path[B[i]][A[i]] = true;
        }

        return reachable(0);
    }

    static boolean reachable(int lastVal) {
        int nextVal = lastVal + 1;

        if (nextVal == n)
            return true;

        return path[lastVal][nextVal] && reachable(nextVal);
    }

}

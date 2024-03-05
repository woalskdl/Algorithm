package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KAKAO_240303 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        solution(999999999);
//        System.out.println(solution(4, new int[]{1, 2, 4, 4, 3}, new int[]{2, 3, 1, 3, 1}));
    }

    //

    static void solution(int N) {

        int enable_print = N % 10;

        while (N > 0) {
            if (enable_print == 0 && N % 10 != 0) {
                enable_print = 1;
                continue;
            } else if (enable_print != 0) {
                System.out.print(N % 10);
            }

            N = N / 10;
        }
    }

    //

//    static int n;
//    static int m;
//    static int[] a;
//    static int[] b;
//    static boolean[] visited;
//
//    static public boolean solution(int N, int[] A, int[] B) {
//        n = N;
//        a = A;
//        b = B;
//        m = A.length;
//        visited = new boolean[m];
//
//        return reachable(0);
//    }
//
//    static boolean reachable(int lastVal) {
//        int nextVal = lastVal + 1;
//
//        if (nextVal == n)
//            return true;
//
//        for (int i = 0; i < m; i++) {
//            if (!visited[i] && (a[i] == nextVal && b[i] == a[i] + 1 || b[i] == nextVal && a[i] == b[i] + 1)) {
//                visited[i] = true;
//                return reachable(nextVal);
//            }
//        }
//
//        return false;
//    }

    //

    static int n;
    static boolean[][] occupied;

    public static int solution(int N, String S) {
        n = N;
        occupied = new boolean[N][10];

        if (S.length() > 1) {
            for (String psnger : S.split(" ")) {
                int length = psnger.length();

                StringBuilder rowInput = new StringBuilder();
                int col = -1;

                for (int i = 0; i < length; i++) {
                    char s = psnger.charAt(i);

                    if (s < 65)
                        rowInput.append(s);
                    else
                        col = (s - 'A');
                }

                int row = Integer.parseInt(rowInput.toString()) - 1;

                occupied[row][col] = true;
            }
        }

        return getFamilyAvailable();
    }

    static boolean[][] available;

    static int getFamilyAvailable() {
        // 1. 1234
        // 2. 3456
        // 3. 5678
        available = new boolean[n][3];

        for (int i = 0; i < n; i++) {
            available[i][0] = !occupied[i][1] && !occupied[i][2] && !occupied[i][3] && !occupied[i][4];
            available[i][1] = !occupied[i][3] && !occupied[i][4] && !occupied[i][5] && !occupied[i][6];
            available[i][2] = !occupied[i][5] && !occupied[i][6] && !occupied[i][7] && !occupied[i][8];
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (available[i][0] && available[i][1] && available[i][2])
                count += 2;
            else if (available[i][0] || available[i][1] || available[i][2])
                count += 1;
        }

        return count;
    }
}

package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FESTIVAL_refactoring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        for (int i = 0; i < C; i++) {
            String line = br.readLine();

            int N = Integer.parseInt(line.split(" ")[0]);
            int L = Integer.parseInt(line.split(" ")[1]);

            String costStr = br.readLine();
            String[] costStrArr = costStr.split(" ");

            int[] costSums = new int[N];

            costSums[0] = Integer.parseInt(costStrArr[0]);

            for (int j = 1; j < N; j++) {
                costSums[j] = costSums[j - 1] + Integer.parseInt(costStrArr[j]);
            }

            double minAvg = Float.MAX_VALUE;
            for (int days = L; days <= N; days++) {
                int sumCases = N - days + 1;
                int minSum = Integer.MAX_VALUE;

                for (int start = 0; start < sumCases; start++) {
                    minSum = Math.min(minSum, costSums[start + days - 1] - (start > 0 ? costSums[start - 1] : 0));
                }

                minAvg = Math.min(minAvg, (double) minSum / days);
            }

            sb.append(String.format("%.8f", minAvg)).append("\n");
        }

        System.out.println(sb);
    }
}

// https://velog.io/@woalskdl/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0-%EC%A0%84%EB%9E%B5FESTIVAL

//float의 정밀도보다 더 높은 정밀도가 필요하다면 double을 사용해야 합니다.

// 소수점 오차를 찾을 수 있는 입력값
//2
//6 3
//789 191 841 920 852 110
//6 2
//435 999 239 129 949 190
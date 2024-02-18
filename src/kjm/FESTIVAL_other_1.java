package kjm;

import java.io.*;

public class FESTIVAL_other_1 {

    static BufferedReader input_stdin = new BufferedReader(new InputStreamReader(System.in));
    //입력의 첫 줄에는 테스트 케이스의 수 C (C ≤ 100)가 주어집니다.
    static int C;
    //각 테스트 케이스의 첫 줄에는 공연장을 대여할 수 있는 날들의 수 N (1 ≤ N ≤ 1000)
    static int N;
    //이미 섭외한 공연 팀의 수 L (1 ≤ L ≤ 1000, L ≤ N)
    static int L;
    //그 다음 줄에는 N개의 숫자로 공연장 대여 비용이 날짜별로 주어집니다.
    static int[] days;
    //결과
    static double[] result;

    public static void main(String[] args) throws Exception {

        //테스트 케이스 우선 받기
        C = Integer.parseInt(input_stdin.readLine());
        //결과는 테스트 케이스와 동일한 갯수의 배열로 정의 더블형으로 ..
        result = new double[C];
        //문자열 클래스로 splitLine 을 선언하여 테스트 케이스 갯수만큼 돌려서 값 받기
        for(int i = 0; i < C; i++) {
            //첫번째 줄은 공연장대여날의 수 N 과 섭외한 공연팀 수 L
            String[] splitLine = input_stdin.readLine().split(" ");
            //받아서 공백으로 자르기
            N = Integer.parseInt(splitLine[0]); //N 공연장을 대여할수 있는 날의 수
            L = Integer.parseInt(splitLine[1]); //L 섭외팀의 수
            //days 를 공연 가능일의 숫자 만큼 선언
            days = new int[N];
            //두번재 줄을 자르기
            splitLine = input_stdin.readLine().split(" ");
            //공백으로 자른 값들을 days 배열에 넣기
            for(int j = 0; j < N; j++){
                days[j] = Integer.parseInt(splitLine[j]);
            }
            //festival함수를 선언
            //결과 호출하기
            result[i] = festival();
        }

        // 결과 돌리기
        for(int k = 0 ; k < C ; k++){
            System.out.printf("%.11f\n", result[k]);
        }
    }

    static double festival() {

        //더블형으로 선언 100 이하의 비용만 나오기에 Byte 로 최대값 조절
        double min_AVG = Byte.MAX_VALUE;
        // 3바퀴 돌리기
        for(int i = 0; i <= N - L; i++) {
            //포문 돌때마다 sum 값을 측정 3개씩.. 4개씩.. 5개씩 묶어서..
            int sum = 0;
            for(int j = i; j < i + L - 1; j++)
                sum += days[j];
            for(int k = i + L - 1; k < N; k++) {
                sum += days[k];
                double newAvg = (double) sum / (k + 1 - i);
                if(newAvg < min_AVG)
                    min_AVG = newAvg;
            }
        }

        return min_AVG;
    }
}

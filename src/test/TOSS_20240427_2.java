package test;

public class TOSS_20240427_2 {
    public int solution(String s) {
        int answer = -1;

        char[] c = s.toCharArray();

        int size = c.length;

        if (size < 3)
            return answer;

        for (int i = 0; i <= size - 3 && answer != 999; i++) {

            int a = c[i] - '0';
            int ret = a;
            int count = 1;

            for (int j = i + 1; j < i + 3; j++) {
                if (a != (c[j] - '0'))
                    break;

                ret = (ret * 10) + (c[j] - '0');
                count += 1;
            }

            if ((ret == 0 && count == 3) || ret > 100)
                answer = Math.max(answer, ret);
        }

        return answer;
    }
}

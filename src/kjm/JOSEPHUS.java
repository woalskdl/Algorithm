package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class JOSEPHUS {
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            N = Integer.parseInt(input.split(" ")[0]);
            K = Integer.parseInt(input.split(" ")[1]);

            List<Integer> list = new LinkedList<>();

            for (int i = 1; i <= N; i++)
                list.add(i);

            kill(list, 0);

            for (int i = 0; i < 2; i++)
                sb.append(list.get(i)).append(" ");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void kill(List<Integer> list, int target) {
        int left = list.size();

        if (left == 2)
            return;

        target = (target >= left ? target % left : target);

        list.remove(target);

        int next = target + K - 1;

        kill(list, next);
    }
}

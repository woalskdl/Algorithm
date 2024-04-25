package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TRAVERSAL_SOL {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int n = Integer.parseInt(br.readLine());

            List<Integer> front = new ArrayList<>();
            List<Integer> middle = new ArrayList<>();

            String input = br.readLine();
            String[] nodesString = input.split(" ");

            for (int i = 0; i < n; i++)
                front.add(Integer.parseInt(nodesString[i]));

            input = br.readLine();
            nodesString = input.split(" ");

            for (int i = 0; i < n; i++)
                middle.add(Integer.parseInt(nodesString[i]));

            printRear(front, middle, sb);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void printRear(List<Integer> front, List<Integer> middle, StringBuilder sb) {
        if (front.isEmpty())
            return;

        int root = front.get(0);
        int rootIdx = middle.indexOf(root);

        List<Integer> leftMiddle = middle.subList(0, rootIdx);
        List<Integer> leftFront = front.subList(1, 1 + leftMiddle.size());

        printRear(leftFront, leftMiddle, sb);

        List<Integer> rightMiddle = middle.subList(rootIdx + 1, middle.size());
        List<Integer> rightFront = front.subList(1 + leftMiddle.size(), front.size());

        printRear(rightFront, rightMiddle, sb);

        sb.append(root).append(" ");
    }
}

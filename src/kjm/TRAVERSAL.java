package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TRAVERSAL {
    private static int N;
    private static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());

            nodes = new int[N][N];

            String input = br.readLine();
            String[] nodesString = input.split(" ");

            for (int i = 0; i < N; i++)
                Arrays.fill(nodes[i], Integer.parseInt(nodesString[i]));

            input = br.readLine();
            nodesString = input.split(" ");

            for (int left = 0; left < N; left++) {
                int val = Integer.parseInt(nodesString[left]);

                for (int top = 0; top < N; top++) {
                    if (nodes[top][left] != val)
                        nodes[top][left] = 0;
                }
            }

            Node node = new Node();
            for (int i = 0; i < N; i++) {
                if (nodes[0][i] != 0)
                    node = makeNode(0, i);
            }

            printRear(node, sb);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static Node makeNode(int top, int left) {
        Node node = new Node();
        node.value = nodes[top][left];

        boolean found = false;
        if (top + 1 < N) {
            for (int j = 0; j < left && !found; j++) {
                if (nodes[top + 1][j] != 0) {
                    node.left = makeNode(top + 1, j);
                    found = true;
                }
            }
        }

        int y = found ? node.y : top;
        if (y + 1 < N) {
            found = false;
            for (int j = left + 1; j < N && !found; j++) {
                if (nodes[y + 1][j] != 0) {
                    node.right = makeNode(y + 1, j);
                    found = true;
                }
            }
        }

        return node;
    }

    private static void printRear(Node node, StringBuilder sb) {
        if (node.left == null && node.right == null)
            sb.append(node.value).append(" ");
        else if (node.left != null)
            printRear(node.left, sb);
        else
            printRear(node.right, sb);

        sb.append(node.value).append(" ");
    }

    static class Node {
        int value;
        int y;
        int x;

        Node left;
        Node right;
    }
}

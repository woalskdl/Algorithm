package kjm;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
전위 순회는 맨 처음에 트리의 루트를 방문하고, 왼쪽과 오른쪽 서브트리를 순서대로 방문합니다.
중위 순회는 왼쪽과 오른쪽 서브트리 사이에 트리의 루트를 방문하고, 후위 순회는 왼쪽과 오른쪽 서브트리를 모두 방문한 뒤에야 루트를 방문합니다.

다음 그림은 이진 트리의 한 예를 보여 줍니다. 이 트리를 전위 순회하면 모든 노드를 27, 16, 9, 12, 54, 36, 72의 순서대로 방문하게 됩니다.
반면 중위 순회했을 때는 9, 12, 16, 27, 36, 54, 72의 순서로, 후위 순회했을 때는 12, 9, 16, 36, 72, 54, 27의 순서로 방문하게 되지요.


어떤 이진 트리를 전위 순회했을 때 노드들의 방문 순서와, 중위 순회했을 때 노드들의 방문 순서가 주어졌다고 합시다.
이 트리를 후위 순회했을 때 각 노드들을 어떤 순서대로 방문하게 될지 계산하는 프로그램을 작성하세요.

입력
입력의 첫 줄에는 테스트 케이스의 수 C (1≤C≤100)가 주어집니다. 각 테스트 케이스는 세 줄로 구성되며, 첫 줄에는 트리에 포함된 노드의 수 N (1≤N≤100)이 주어집니다.
그 후 두 줄에 각각 트리를 전위 순회했을 때와 중위순회 했을 때의 노드 방문 순서가 N개의 정수로 주어집니다.
각 노드는 1000 이하의 자연수로 번호 매겨져 있으며, 한 트리에서 두 노드의 번호가 같은 일은 없습니다.

출력
각 테스트 케이스마다, 한 줄에 해당 트리의 후위 순회했을 때 노드들의 방문 순서를 출력합니다.

예제 입력
2
7
27 16 9 12 54 36 72
9 12 16 27 36 54 72
6
409 479 10 838 150 441
409 10 479 150 838 441
예제 출력
12 9 16 36 72 54 27
10 150 441 838 479 409

 */
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



        }
    }

    private static Node makeNode(int top, int left) {
        Node node = new Node();
        node.value = nodes[top][left];

        boolean notFound = true;
        for (int i = top + 1; i < N && notFound; i++) {
            for (int j = 0; j < left && notFound; j++) {
                if (nodes[i][j] != 0) {
                    node.left = makeNode(i, j);
                    notFound = false;
                }
            }
        }

        notFound = true;
        for (int i = top + 1; i < N && notFound; i++) {
            for (int j = left + 1; j < N && notFound; j++) {
                if (nodes[i][j] != 0) {
                    node.right = makeNode(i, j);
                    notFound = false;
                }
            }
        }

        return node;
    }

    static class Node {
        int value;
        Node left;
        Node right;
    }
}

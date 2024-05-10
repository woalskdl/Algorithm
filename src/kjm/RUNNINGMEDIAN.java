package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class RUNNINGMEDIAN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();
            int N = Integer.parseInt(input.split(" ")[0]);
            int a = Integer.parseInt(input.split(" ")[1]);
            int b = Integer.parseInt(input.split(" ")[2]);

            long[] A = new long[N];
            long sum = 0;

            A[0] = 1983;

            for (int i = 1; i < N; i++)
                A[i] = (A[i - 1] * a + b) % 20090711;

            PriorityQueue<Long> lowers = new PriorityQueue<>((a1, b1) -> -1 * a1.compareTo(b1));

            PriorityQueue<Long> highers = new PriorityQueue<>();

            for (int i = 0; i < A.length; i++) {
                long number = A[i];
                addNumber(number, lowers, highers);
                rebalance(lowers, highers);
                sum = (sum + getMedian(lowers, highers)) % 20090711;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }

    private static long getMedian(PriorityQueue<Long> lowers, PriorityQueue<Long> highers) {
        PriorityQueue<Long> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Long> smallerHeap = lowers.size() > highers.size() ? highers : lowers;

        if (biggerHeap.size() == smallerHeap.size())
            return lowers.peek();
        else
            return biggerHeap.peek();
    }

    private static void addNumber(long number, PriorityQueue<Long> lowers, PriorityQueue<Long> highers) {
        if (lowers.isEmpty() || number < lowers.peek())
            lowers.add(number);
        else
            highers.add(number);
    }

    private static void rebalance(PriorityQueue<Long> lowers, PriorityQueue<Long> highers) {
        PriorityQueue<Long> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Long> smallerHeap = lowers.size() > highers.size() ? highers : lowers;

        if (biggerHeap.size() - smallerHeap.size() >= 2)
            smallerHeap.add(biggerHeap.poll());
    }

}

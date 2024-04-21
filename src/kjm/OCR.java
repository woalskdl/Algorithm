package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OCR {

    private static int m;
    private static int q;
    private static String[] words;
    private static double[] B;
    private static double[][] T;
    private static double[][] M;
    private static int n;
    private static int[] classified;
    private static double[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        m = Integer.parseInt(input.split(" ")[0]);
        q = Integer.parseInt(input.split(" ")[1]);

        input = br.readLine();

        words = input.split(" ");

        B = new double[m];
        T = new double[m][m];
        M = new double[m][m];

        input = br.readLine();

        for (int i = 0; i < m; i++)
            B[i] = Double.parseDouble(input.split(" ")[i]);

        for (int i = 0; i < m; i++) {
            input = br.readLine();

            for (int j = 0; j < m; j++)
                T[i][j] = Double.parseDouble(input.split(" ")[j]);
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine();

            for (int j = 0; j < m; j++)
                M[i][j] = Double.parseDouble(input.split(" ")[j]);
        }

        for (int i = 0; i < q; i++) {
            input = br.readLine();

            n = Integer.parseInt(input.split(" ")[0]);
            classified = new int[n];

            cache = new double[m][m];

            for (int j = 0; j < m; j++)
                Arrays.fill(cache[j], -1);

            for (int j = 1; j <= n; j++)
                classified[j - 1] = Arrays.asList(words).indexOf(input.split(" ")[j]);

            String bestSentence = "";
            double bestRate = 0;
            for (int j = 0; j < m; j++) {
                double curRate = rate(j, classified[0], -1) * getBestRate(1, j);

                if (bestRate < curRate) {
                    bestRate = curRate;
                    bestSentence = getBestSentence(1, j, words[j]);
                }
            }

            sb.append(bestSentence).append("\n");
        }

        System.out.println(sb);
    }

    private static String getBestSentence(int idx, int prevIdx, String sentence) {
        if (idx == n)
            return sentence;

        double bestRate = 0;
        String result = "";
        for (int i = 0; i < m; i++) {
            double curRate = rate(i, classified[idx], prevIdx) * getBestRate(idx + 1, i);

            if (curRate > bestRate) {
                bestRate = curRate;
                result = getBestSentence(idx + 1, i, sentence + " " + words[i]);
            }
        }

        return result;
    }

    private static double getBestRate(int idx, int prevIdx) {
        if (idx == n)
            return 1;

        if (cache[idx][prevIdx] != -1)
            return cache[idx][prevIdx];

        double bestRate = 0;
        for (int i = 0; i < m; i++) {
            double curRate = rate(i, classified[idx], prevIdx) * getBestRate(idx + 1, i);

            if (bestRate < curRate)
                bestRate = curRate;
        }

        return cache[idx][prevIdx] = bestRate;
    }

    private static double rate(int targetIdx, int resultIdx, int prevIdx) {
        if (prevIdx == -1)
            return rateFirst(targetIdx) * rateWord(targetIdx, resultIdx);
        else
            return rateNext(prevIdx, targetIdx) * rateWord(targetIdx, resultIdx);
    }

    private static double rateFirst(int idx) {
        return B[idx];
    }

    private static double rateWord(int idx, int resultIdx) {
        return M[idx][resultIdx];
    }

    private static double rateNext(int prevIdx, int idx) {
        return T[prevIdx][idx];
    }

}

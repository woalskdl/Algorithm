package test;

public class TOSS_20240427_3 {
    public boolean solution(String amountText) {

        char[] c = amountText.toCharArray();

        int first = c[0] - '0';

        if (first <= 0 || first >= 10)
            return false;

        int size = c.length;

        if (size == 1 && c[0] == '0')
            return true;

        int last = c[size - 1] - '0';

        if (!isNumber(last))
            return false;

        int numCount = 0;
        boolean containsCol = (size > 4 && c[size - 4] == ',');

        for (int i = size - 1; i >= 0; i--) {
            int input = c[i] - '0';

            if (!isNumber(input) && !isColon(input))
                return false;

            if (!containsCol && isColon(input))
                return false;

            if (isNumber(input))
                numCount += 1;

            if (numCount % 3 != 0 && !isNumber(input))
                return false;
        }

        return true;
    }

    private boolean isNumber(int number) {
        return number >= 0 && number < 10;
    }

    private boolean isColon(int input) {
        return input == (',' - '0');
    }
}

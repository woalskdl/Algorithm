package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FANMEETING {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {

            String input = br.readLine();
            StringBuilder membersInput = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'F') {
                    membersInput.append("1");
                } else {
                    membersInput.append("0");
                }
            }

            input = br.readLine();
            StringBuilder fansInput = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'F') {
                    fansInput.append("1");
                } else {
                    fansInput.append("0");
                }
            }

            int membersLength = membersInput.length();

            StringBuilder allHug = new StringBuilder();

            for (int i = 0; i < membersLength; i++)
                allHug.append("1");

            int allHugValue = Integer.parseInt(allHug.toString(), 2);

            int lengthDiff = fansInput.length() - membersLength;
            int membersBit = Integer.parseInt(membersInput.toString(), 2);

            int allHugCount = 0;
            for (int i = 0; i <= lengthDiff; i++) {
                String meetingFans = fansInput.substring(i, i + membersLength);
                int fansBit = Integer.parseInt(meetingFans, 2);

                if ((membersBit | fansBit) == allHugValue)
                    allHugCount += 1;
            }

            sb.append(allHugCount).append("\n");
        }

        System.out.println(sb);
    }
}

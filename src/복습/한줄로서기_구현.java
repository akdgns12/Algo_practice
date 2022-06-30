package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한줄로서기_구현 {
    static int numberOfPerson;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numberOfPerson = Integer.parseInt(br.readLine());

        int[] line = new int[numberOfPerson];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfPerson; i++) {
            int left = Integer.parseInt(st.nextToken());
            int position = searchPosition(line, left, numberOfPerson);
            line[position] = i + 1;
        }

        for (int i : line) System.out.print(i + " ");
    }

    static int searchPosition(int[] line, int numberOfLeft, int numberOfPerson) {
        for (int position = 0; position < numberOfPerson; position++) {
            if (numberOfLeft == 0 && line[position] == 0) {
                return position;
            }

            if(line[position] == 0) numberOfLeft--;
        }

        return -1;
    }
}

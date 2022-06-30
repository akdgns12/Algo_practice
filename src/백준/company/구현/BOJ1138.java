package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1138 {
    // 한 줄로 서기 / 실버2 / 구현
    /*
        사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만을 기억.
        각 사람들이 기억하는 정보가 주어질 때, 줄을 어떻게 서야 하는지 출력
     */
    static int numOfPerson;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfPerson = Integer.parseInt(br.readLine());

        int[] line = new int[numOfPerson];
        /**
         * 키가 1인 사람부터 차례대로 정보가 주어진다.
         */

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfPerson; i++) {
            int left = Integer.parseInt(st.nextToken());
            int position = searchPosition(line, left, numOfPerson);
            line[position] = i+1;
        }

        for (int i : line) System.out.print(i + " ");
    }

    static int searchPosition(int[] line, int numOfLeftPerson, int numOfPerson) {
        for (int position = 0; position < numOfPerson; position++) { // i+1번 사람의 왼쪽 사람이 더이상 없을때까지 알맞은 자리 찾아가도록 반복
            if (numOfLeftPerson == 0 && line[position] == 0) {
                return position;
            }
            if(line[position] == 0) numOfLeftPerson--;
        }

        return -1;
    }
}

package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7568 {
    // 덩치 / 실버5 / 구현
    // 각 사람의 덩치 등수는 자신보다 더 큰 덩치의 사람의 수로 정해짐
    // a가 b보다 덩치가 크다 = 몸무게와 키 모두 더 커야 덩치가 큰 것
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
        arr = new int[n][3]; // 사람 수 만큼 몸무게, 키, 등수 배열
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) { // 모든 사람을 기준으로 잡는 반복문
            cnt = 0;
            for (int j = 0; j < n; j++) { // 기준으로 잡은 사람과 다시 모든 사람을 비교 반복
                if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]){ // 기준으로 삼은 사람보다 키와 몸무게가 큰 사람을 만나면
                    cnt++;
                }
            }

            arr[i][2] = cnt+1; // 비교가 끝나면 카운트에 +1 후 등수 배열에 저장
        }

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i][2] + " ");
        }

    }
}

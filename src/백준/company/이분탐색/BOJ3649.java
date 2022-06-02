package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3649 {
    // 로봇 프로젝트 / 골드4 / 이진탐색
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = null;

        while ((t = br.readLine()) != null) {
            int x = Integer.parseInt(t) * 10000000; // 구멍의 너비, 센티미터 -> 나노미터
            int n = Integer.parseInt(br.readLine());
            int[] list = new int[n];

            // 레고 조각의 길이 리스트
            for (int i = 0; i < n; i++) {
                list[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(list);

            int lt = 0, rt = n - 1;
            boolean flag = false;

            while (lt < rt) {
                int sum = list[lt] + list[rt];

                if (sum == x) { // 두 조각의 합이 완벽하게 구멍에 맞으면 출력
                    System.out.println("yes" + " " + list[lt] + " " + list[rt]);
                    flag = true;
                    break;
                } else if (sum > x) { // 두 조각의 합이 구멍보다 길면, rt--
                    rt--;
                } else { // 두 조각의 합이 구멍보다 짧다면 lt++
                    lt++;
                }
            }

            if (!flag) { // 없는 경우는 danger
                System.out.println("danger");
            }

            t = null;
        }
    }
}

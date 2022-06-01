package 백준.company.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2467 {
    // 두 용액 / 골드 5 / 투포인터
    /*
        0과 n-1을 각 투포인터로 지정
        투포인터를 사용해 합이 0에 가까운 구간을 탐색
         1. min > Math.abs(arr[lt] + arr[rt]) 합이 0에 가장 가까운 값이 갱신되면 저장
         2. sum >= 0 합이 0보다 크거나 같다면 더 작아져야 하므로 오른쪽 포인터를 이동 rt--
         3. sum < 0 합이 0보다 작다면 더 커져야 하므로 왼쪽 포인터를 이동 lt++
     */
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int lt = 0;
        int rt = n - 1;
        long min = Long.MAX_VALUE;

        int ml = 0, mr = 0;

        while (lt < rt) {
            long sum = arr[lt] + arr[rt];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                ml = lt;
                mr = rt;
            }

            if (sum >= 0) {
                rt--;
            } else {
                lt++;
            }
        }

        System.out.println(arr[ml] + " " + arr[mr]);
    }
}

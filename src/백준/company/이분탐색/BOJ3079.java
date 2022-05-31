package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3079 {
    // 입국심사 / 골드 5 / 이진탐색
    /*
        사람 수 m, 입국 심사대 수 n
        각각의 심사대에서 심사하는 시간은 제각각
        모든 사람이 심사를 받는데 걸리는 시간의 최솟값
     */
    static int n, m;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 심사대 수
        m = Integer.parseInt(st.nextToken()); // 사람 수

        time = new int[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, time[i]);
        }

        /*
            로직을 짜보자
            시간을 중심으로 탐색해야 하니까, 해당 시간일때 몇 명을 심사대에서 통과시킬 수 있는가
            만약 mid초일 때, m명 이상의 사람을 통과시킬 수 있음 -> 탐색범위 줄여야함(0 ~ mid - 1)
            만약 mid초일 때, m명 이하의 사람을 통과시킬 수 있음 -> 탐색범위를 넓혀야함(mid + 1 ~ rt)
            처음 lt = 0, rt = 각 입국심사대에서 걸리는 시간의 최대값 * 심사 받으려는 사람의 수
         */

        long lt = 0L;
        long rt = max * m;

        long ans = 0L;
        while (lt <= rt) {
            long mid = (lt + rt) / 2;

            // mid초 일때 각 심사대에서 보낼 수 있는 수를 카운트 해줌
            long cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += (mid / time[i]);
            }

            // 비교 후 탐색 범위 변경
            if(cnt >= m){
                ans = mid;
                rt = mid - 1;
            } else if (cnt < m) {
                lt = mid + 1;
            }
        }

        System.out.println(ans);
    }
}

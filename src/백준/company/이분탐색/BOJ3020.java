package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3020 {
    // 개똥벌레 / 골드 5 / 이분탐색
    // n은 짝수, 첫번째 장애물은 항상 석순, 그다음 종유석과 석순이 번갈아 등장
    // 이때, 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하라

    static int n, h;
    static int[] up, down; // 석순, 종유석

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 동굴의 길이
        h = Integer.parseInt(st.nextToken()); // 높이

        up = new int[n / 2];
        down = new int[n / 2];

        int upIdx = 0;
        int downIdx = 0;

        for (int i = 0; i < n / 2; i++) {
            int num = Integer.parseInt(br.readLine());
            if(i % 2 == 0) down[downIdx++] = num;
            else up[upIdx++] = num;
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = Integer.MAX_VALUE; // 충돌하는 개수
        int cnt = 0; // 그러한개 몇개가 있는가

        for (int i = 1; i <= h; i++) { // 높이 1부터 h까지
            int downCrush = binarySearch(0, n / 2, i, down); // lt, rt, 현재높이, 배열
            int upCrush = binarySearch(0, n / 2 - 1, h - i + 1, up); // lt, rt, 현재높이, 배열

            int conflict = downCrush + upCrush;

            // 현재 min(충돌하는 개수)보다 작으면 min 바꿔주고 cnt = 1로 바꿔주기
            // 만약 같다면 cnt++
            if(min > conflict){
                min = conflict;
                cnt = 1;
            } else if (min == conflict) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }

    static int binarySearch(int lt, int rt, int height, int[] arr) {
        int min = Integer.MAX_VALUE;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (arr[mid] >= height) { // 만약 높이와 같거나 지금 높이보다 크면
                min = Math.min(min, mid);
                rt = mid - 1; // 왼쪽으로 가야됨
            } else {
                lt = mid + 1;
            }
        }

        // 왜 arr.length - min일까? --> 생각해보면 height보다 크거나 같은 첫번째 인덱스를 찾는
        // lower_bound 과정이므로 우리가 원하는 답인 hieght보다 크거나 같은 장애물의 개수는
        // arr.length - min이다!
        return min == Integer.MAX_VALUE ? 0 : (n/2) - min;
    }
}

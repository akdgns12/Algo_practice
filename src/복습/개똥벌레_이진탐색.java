package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 개똥벌레_이진탐색 {
    static int n, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[] up = new int[n / 2];
        int[] down = new int[n / 2];
        int downIdx = 0;
        int upIdx = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                down[downIdx++] = num;
            } else {
                up[upIdx++] = num;
            }
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = n; // 최소 충돌 갯수
        int cnt = 0; // 그러한 높이 갯수

        for (int i = 1; i <= h; i++) { // 각 높이마다 up,down의 경우에 충돌하는 횟수 구함
            int conflict = binarySearch(0, n/2, i, up) + binarySearch(0, n/2, h-i+1, down);

            if(min == conflict){
                cnt++;
                continue;
            }

            if (min > conflict) {
                min = conflict;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }

    static int binarySearch(int lt, int rt, int height, int[] arr) {
        while (lt < rt) {
            int mid = (lt + rt) / 2;

            if (arr[mid] >= height) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        return arr.length - rt;
    }
}

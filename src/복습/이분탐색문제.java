package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이분탐색문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] a = new int[n+1];
            int[] b = new int[m+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(b);

            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans += binarySearch(b, 1, m, a[i]);
            }
            System.out.println(ans);
        }
    }

    static int binarySearch(int[] arr, int l, int r, int target) {
        int ret = l - 1;

        while(l <= r){
            int mid = (l+r) / 2;
            if(arr[mid] < target){
                ret = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ret;
    }

}

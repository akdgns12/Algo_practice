package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두수의합 {
    static int t,n,k;
    static int min;
    static int count;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            System.out.println(two_pointer());
        }
    }

    static int two_pointer() {
        int left = 0;
        int right = n-1;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(sum >= k) right--;
            else left++;

            if (Math.abs(k - sum) < min) {
                min = Math.abs(k - sum);
                count=1;
            } else if (Math.abs(k - sum) == min) {
                count++;
            }
        }

        return count;
    }
}

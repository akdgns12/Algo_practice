package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    // 두 용액 / 골드 5 / 이분탐색
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int lt = 0;
        int rt = n-1;

        int gap = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        int sum;
        int temp;

        while(lt < rt){
            sum = arr[lt] + arr[rt];
            temp = Math.abs(sum);
            if(temp < gap){
                gap = temp;
                ans1 = arr[lt];
                ans2 = arr[rt];
            }
            if(sum > 0)
                rt--;
            else
                lt++;
        }

        System.out.println(ans1 + " " + ans2);
    }
}

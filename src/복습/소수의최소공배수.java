package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소수의최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }

        // 소수는 false
        boolean[] isPrime = new boolean[max+1];
        isPrime[0] = isPrime[1] = true;

        for (int i = 2; i * i < isPrime.length; i++) {
            if(isPrime[i]) continue;
            for (int j=i*i; j<isPrime.length; j+= i){
                isPrime[j] = true;
            }
        }

        long answer = 1;
        for (int i = 0; i < nums.length; i++) {
            if(isPrime[nums[i]]) continue;
            answer *= nums[i];
            isPrime[nums[i]] = true;
        }

        System.out.println(answer == 1 ? -1 : answer);
    }
}

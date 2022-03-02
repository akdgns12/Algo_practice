package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21919 {
    // 소수 최소공배수 / 실버 3 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //수열의 길이

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0; // isPrime배열의 범위 정하기 위해
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }

        // 소수는 false
        boolean[] isPrime = new boolean[max+1];
        isPrime[0] = isPrime[1] = true;

        for (int i = 2; i * i < isPrime.length; i++) {
            if(isPrime[i]) continue;
            for (int j = i * i; j < isPrime.length; j += i) { // 소수의 배수는 모두 소수가 아닌걸로 처리
                isPrime[j] = true;
            }
        }

        long answer = 1;
        for (int i = 0; i < nums.length; i++) {
            if(isPrime[nums[i]]) continue; //소수라면 skip
            answer *= nums[i];
            isPrime[nums[i]] = true; //중복해서 곱하는 경우 최소공배수가 안나오므로 한 번 처리한 수는 소수처리로 걸러질 수 있도록 처리
        }

        System.out.println(answer == 1 ? -1 : answer);
    }
}

package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
    // 예산 / 실버 3/ 이분탐색
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 지방의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] budget = new int[n];
        for (int i = 0; i < n; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
        }

        long total = Integer.parseInt(br.readLine());
        Arrays.sort(budget);

        /*
            int lt = budget[0]; 이라 생각해서 틀렸다.
            0 부터
         */
        long lt = 0;
        long rt = budget[n-1];
        long mid = 0;

        long answer = 0;
        while(lt <= rt){
            mid = (lt + rt) / 2; // 내야할 세금
            long sum = 0; // 모든 지방 세금 합

            for(int i=0; i<n; i++){
                if(budget[i] < mid){
                    sum += budget[i]; // 못내는 지방은 가진 최대 돈만 냄
                }else{
                    sum += mid; // 내라는 세금 낼 수 있는 지방
                }
            }

            if(sum > total){ // 내라는 세금보다 많은 경우 -> 세금을 줄여본다
                rt = mid - 1;
            }else{ // 내라는 세금보다 적은 경우 -> 세금을 높여서 더 최고의 경우의 수를 찾는다
                lt = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }
}

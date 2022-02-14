package 코테대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class 연속된자연수의합 { // 투포인터
    public static int solution(int n){
        int answer = 0, sum = 0, lt = 0;
        int m = n / 2 + 1;
        int[] arr = new int[m];

        for (int i = 0; i < m; i++)
            arr[i] = i+1;

        for (int rt = 0; rt < m; rt++) {
            sum += arr[rt];
            if(sum == n) answer++;
            while (sum >= n) {
                sum -= arr[lt++];
                if(sum == n) answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }


}

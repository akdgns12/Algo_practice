package 코테대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class 연속부분수열 { // 투포인터
    public static int solution(int n, int m, int[] arr){
        int answer = 0;
        int sum = 0;
        int left = 0;
        for (int rt = 0; rt < n; rt++) {
            sum += arr[rt];
            if(sum == m) answer++;
            while (sum >= m) {
                sum -= arr[left++];
                if(sum == m) answer++;
            }
        }

            return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n,m,arr));
    }


}

package 코테대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class 연속된자연수의합_수학 { // 수학
    public static int solution(int n){
        int answer = 0, cnt = 1;
        n--;
        while (n > 0) {
            cnt++; // 연속된 자연수의 갯수
            n = n - cnt;
            if(n % cnt == 0) answer++;
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }


}

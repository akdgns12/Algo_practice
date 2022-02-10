package 코테대비.배열;

import java.util.Scanner;

public class 소수_에라토스테네스의체 {
    public static int solution(int n){
        int answer = 0;
        int[] ch = new int[n+1]; // 동적배열 0으로 초기화
        for (int i = 2; i <= n; i++) {
            if (ch[i] == 0) { // 소수라면
                answer++;
            for (int j = i; j <= n; j += i) // 해당 소수의 배수라면 1과 자기자신 말고도 해당 소수의 배수이므로 소수가 아니다 그래서 1로 변경시켜줌
                    ch[j] = 1;
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

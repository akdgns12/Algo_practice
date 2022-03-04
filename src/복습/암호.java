package 복습;

import java.util.Scanner;

public class 암호 {
    public static String solution(int n, String s){
        String answer = "";
        for(int i=0; i<n; i++){
            String tmp = s.substring(0,7).replace('#', '1').replace('*','0');
            int num = Integer.parseInt(tmp, 2); // Integer.parseInt(Strign s,int radix) -> 숫자형의 문자열을 첫번째 인자값(String s)으로 받고 변환할 진수 (int radix)을 입력하면 해당 진에 맞춰 Integer형으로 변환
            answer += (char)num;
            s = s.substring(7); // 다음 7자리 검사를 위해 문자열 나누기
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();

        System.out.println(solution(n, str));
    }
}

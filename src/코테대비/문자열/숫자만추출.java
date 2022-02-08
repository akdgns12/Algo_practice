package 코테대비.문자열;

import java.util.Scanner;

public class 숫자만추출 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }

    public static int solution(String str){
        int answer = 0;
        for(char x : str.toCharArray()){
            if(x >= 48 && x<= 57) answer = answer * 10 + (x - 48);
        }

        return answer;
    }
}

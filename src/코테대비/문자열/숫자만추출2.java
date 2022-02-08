package 코테대비.문자열;

import java.util.Scanner;

public class 숫자만추출2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }

    public static int solution(String str){
        String answer = "";
        for(char x : str.toCharArray()){
            if(Character.isDigit(x)) answer += x;
        }

        return Integer.parseInt(answer);
    }
}

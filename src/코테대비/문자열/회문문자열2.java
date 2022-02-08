package 코테대비.문자열;

import java.util.Scanner;

public class 회문문자열2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(solution(str));
    }

    public static String solution(String str){
        String answer = "NO";
        str = str.toUpperCase();
        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equals(tmp)) answer = "YES";

        return answer;
    }
}

package 코테대비.문자열;

import java.util.Scanner;

public class 유효한팰린드롬 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(solution(str));
    }

    public static String solution(String str){
        String answer = "NO";
        // 알파벳 대문자가 아니면 -> ""로 바꾸는 정규식
        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equals(tmp)) answer = "YES";

        return answer;
    }
}

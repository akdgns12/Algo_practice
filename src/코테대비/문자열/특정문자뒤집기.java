package 코테대비.문자열;

import java.io.CharConversionException;
import java.util.Scanner;

public class 특정문자뒤집기 {
    public static String solution(String str){
        String answer = "";
        char[] c = str.toCharArray();
        int lt = 0, rt = str.length() - 1;
        while(lt < rt){
            if(!Character.isAlphabetic(c[lt])) lt++;
            else if(!Character.isAlphabetic(c[rt])) rt--;
            else{
                char tmp = c[lt];
                c[lt] = c[rt];
                c[rt] = tmp;
                lt++;
                rt--;
            }
        }
        answer = String.valueOf(c);

        return answer;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(solution(str));
    }
}

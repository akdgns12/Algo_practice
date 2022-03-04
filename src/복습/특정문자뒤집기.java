package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 특정문자뒤집기 {
    static String solution(String str){
        String answer = "";
        char[] c = str.toCharArray();
        int lt = 0, rt = str.length() - 1;
        while(lt < rt){
            if(!Character.isAlphabetic(c[lt])) lt++;
            else if(!Character.isAlphabetic(c[rt])) rt++;
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

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(solution(str));
    }
}

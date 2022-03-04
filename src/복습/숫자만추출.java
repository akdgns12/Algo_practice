package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 숫자만추출 {
    static int solution(String str){
        int answer = 0;
        for (char x : str.toCharArray()) {
            if(x >= 48 && x <= 57) answer = answer * 10 + (x - 48);
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(solution(str));
    }
}

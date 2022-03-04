package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문장속단어 {
    static String solution(String str){
        String answer = "";
        int max = Integer.MAX_VALUE;
        String[] split = str.split(" ");
        for (String x : split){
            int len = x.length();
            if(len > max){
                max = len;
                answer = x;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }
}

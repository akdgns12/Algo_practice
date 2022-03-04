package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 가장짧은거리_복습 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char c = br.readLine().charAt(0);

        for (int x : solution(str, c)){
            System.out.println(x + " ");
        }
    }

    static int[] solution(String str, char c){
        int[] answer = new int[str.length()];

        int p = 1000;

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == c){
                p = 0;
                answer[i] = p;
            }else{
                p++;
                answer[i] = p;
            }
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) == c){
                p = 0;
                answer[i] = p;
            }else{
                p++;
                answer[i] = Math.min(answer[i], p);
            }
        }

        return answer;
    }
}

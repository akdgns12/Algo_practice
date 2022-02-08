package 코테대비.문자열;

import java.util.ArrayList;
import java.util.Scanner;

public class 가장짧은문자거리 {
    public static int[] solution(String s, char t) {
        int[] answer = new int[s.length()];
        int p = 1000;

        // 왼쪽기준으로 왼쪽(target문자와)떨어진 거리 구하기
       for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == t) {
                p = 0;
                answer[i] = p;
            }else{
                p++;
                answer[i] = p;
            }
        }

       // 오른쪽 끝부터 오른쪽(taget문자와)떨어진 거리 구하기
        // 최종적으로 왼쪽, 오른쪽에서 각각 구한 거리중 더 작은 값을 갱신
       p = 1000;
       for(int i=s.length()-1; i>=0; i--){
           if(s.charAt(i) == t){
               p = 0;
           }else{
               p++;
               answer[i] = Math.min(answer[i], p);
           }
       }


        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char c = sc.next().charAt(0);

        for(int x : solution(str, c)){
            System.out.print(x + " ");
        }
    }

}

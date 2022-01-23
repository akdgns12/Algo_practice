package 코테대비.문자열;

import java.util.ArrayList;
import java.util.Scanner;

public class 단어뒤집기 {
    public static ArrayList<String> solution(int n, String[] str){
        ArrayList<String> answer = new ArrayList<>();
//        for(String x : str){
//            String tmp = new StringBuilder(x).reverse().toString();
//            answer.add(tmp);
//        }

        // 문자 하나하나 뒤집는 방법
        for(String x : str){
            char[] s = x.toCharArray();
            int lt = 0, rt = x.length()-1;
            while(lt < rt){
                char tmp = s[lt];
                s[lt] = s[rt];
                s[rt] = tmp;
                lt++;
                rt--;
            }
            String tmp = String.valueOf(s);
            answer.add(tmp);
        }


        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] str = new String[n];
        for(int i=0; i<n; i++){
            str[i] = sc.next();
        }
        for(String x : solution(n, str)){
            System.out.println(x);
        }
    }
}

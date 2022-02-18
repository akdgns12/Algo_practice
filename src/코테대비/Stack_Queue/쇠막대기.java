package 코테대비.Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

public class 쇠막대기 {
    // 레이저에 의해 잘려진 쇠막대기 조각의 총 개수
    public static int solution(String str){
        int answer = 0;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') st.push('(');
            else{
                st.pop();
                if(str.charAt(i-1) == '(') answer += st.size();
                else answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }


}

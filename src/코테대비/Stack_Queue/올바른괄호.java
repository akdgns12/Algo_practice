package 코테대비.Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

public class 올바른괄호 {
    public static String solution(String str){
        String answer = "YES";
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '('){
                st.push(c);
            }else{
                if (!st.isEmpty()) {
                    st.pop();
                }else{
                    return "NO";
                }
            }
        }

        if(!st.isEmpty()) answer = "NO";

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }
}

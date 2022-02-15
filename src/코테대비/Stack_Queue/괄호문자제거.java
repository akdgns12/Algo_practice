package 코테대비.Stack_Queue;

import java.io.CharArrayReader;
import java.util.Scanner;
import java.util.Stack;

public class 괄호문자제거 {
    public static String solution(String str){
        String answer = "";
        Stack<Character> st = new Stack<>();
        for (char x : str.toCharArray()) {
            if(x == ')'){
                while (st.pop() != '(');
            } else st.push(x);
        }

        for (int i = 0; i < st.size(); i++) answer += st.get(i);
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }
}

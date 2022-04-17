package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2504 {
    // 괄호의 값 // 실버2 / 구현
    // 생각
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> st = new Stack<>();

        int mul = 1;
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch(c){
                case '(' :
                    st.push(c);
                    mul *= 2;
                    break;
                case '[':
                    st.push(c);
                    mul *= 3;
                    break;
                case ')':
                    if(st.isEmpty() || st.peek() != '('){
                        result = 0;
                        break;
                    }

                    if(str.charAt(i-1) == '(') result += mul;
                    st.pop();
                    mul /= 2;
                    break;
                case ']':
                    if(st.isEmpty() || st.peek() != '['){
                        result = 0;
                        break;
                    }

                    if(str.charAt(i-1) == '[') result += mul;
                    st.pop();
                    mul /= 3;
                    break;
            }
        }

        System.out.println(!st.isEmpty() ? 0 : result);
    }
}



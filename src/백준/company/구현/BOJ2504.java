package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2504 {
    // 괄호의 값 // 실버2 / 구현
    // 생각
    /*
        1. 여는 괄호는 스택에 저장
        2. 닫는 괄호만날때
            2-1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        Stack<Character> st = new Stack<>();
        int result = 0;
        int val = 1;

        for (int i = 0; i < len; i++) {
            switch(str.charAt(i)){
                case '(' :
                    st.push(str.charAt(i));
                    val *= 2;
                    break;
                case '[' :
                    st.push(str.charAt(i));
                    val *= 3;
                    break;
                case ')':
                    if(st.isEmpty() || st.peek() != '('){
                        result = 0;
                        break;
                    }
                    else if(str.charAt(i-1) == '('){
                        result += val;
                    }
                    st.pop();
                    val /= 2;
                    break;
                case ']' :
                    if(st.isEmpty() || st.peek() != '['){
                        result = 0;
                        break;
                    }
                    else if(str.charAt(i-1) == '['){
                        result += val;
                    }
                    st.pop();
                    val /= 3;
                    break;
            }
        }

        if(!st.isEmpty()) sb.append(0).append("\n");
        else sb.append(result).append("\n");
        System.out.println(sb);
    }
}



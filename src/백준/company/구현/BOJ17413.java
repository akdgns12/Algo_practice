package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17413 {
    // 단어 뒤집기 2 / 실버 3 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '<'){
                while(!st.isEmpty()) sb.append(st.pop());
                while(s.charAt(i) != '>') sb.append(s.charAt(i++));
                sb.append('>');
            }else if(s.charAt(i) == ' '){
                while(!st.isEmpty()) sb.append(st.pop());
                sb.append(' ');
            }else {
                st.push(s.charAt(i));
            }
        }

        // 마지막에 남은 문자들 빼서 넣어주기
        while(!st.isEmpty()) sb.append(st.pop());
        System.out.println(sb);
    }
}

package Test;

import java.util.Stack;

/*
    단계 1: 괄호 짝 맞는 경우 TRUE
    아니면 FALSE
    
    단계 2: 
    주석 처리 된 문자열을 제외하고 괄호 짝 맞는 경우 TRUE 아니면 FALSE
    주석은 무조건 짝이 맞는 경우만 주어짐
 */
public class JOBDA {
    public static void main(String[] args) {
        JOBDA T = new JOBDA();
        String S = "))";
        System.out.println(T.solution(S));
    }
    public String solution(String S){
        Stack<Character> st = new Stack<>();
        int n = S.length();

        boolean flag = false;
        String str = "";
        for(int i=0; i<n; i++){
            char ch = S.charAt(i);
            if(ch == '/' && S.charAt(i+1) == '*' && !flag){
                flag = true;
            }else if(ch == '/' && S.charAt(i-1) == '*' && flag){
                flag = false;
                continue;
            }

            if(!flag) str += ch;
        }

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if (st.isEmpty()) {
                if(ch == '(' || ch == '{' || ch == '['){
                    st.push(ch);
                    continue;
                }else return "FALSE";
            }

            if(!st.isEmpty()){
                if(ch == '(' || ch == '{' || ch == '['){
                    st.push(ch);
                }else if(ch == ')'){
                    if(st.peek() == '(') st.pop();
                    else return "FALSE";
                }else if(ch == '}'){
                    if(st.peek()  == '{') st.pop();
                    else return "FALSE";
                }else if(ch == ']'){
                    if(st.peek() == '[') st.pop();
                    else return "FALSE";
                }else return "FALSE";
            }
        }

        if(st.size() != 0) return "FALSE";
        else return "TRUE";
    }
}

package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10773 {
    // 제로 / 실버4 / 구현
    /*
        생각
        0이 주어지면 최근의 수를 삭제
        stack으로 하면 될 듯?
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num != 0){
                st.push(num);
            }else{
                st.pop();
            }
        }

        int sum = 0;
        while(!st.isEmpty()){
            sum += st.pop();
        }

        System.out.println(sum);
    }
}

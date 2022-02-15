package 코테대비.Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

public class 후위식연산 {
    public static int solution(String str){
        int answer = 0;
        Stack<Integer> st = new Stack<>();
        for (char x : str.toCharArray()) {
            // 숫자일때
            if(Character.isDigit(x)) st.push(x - 48);
            else{ // 연산자 만났을 때
                int rt = st.pop();
                int lt = st.pop();
                if(x == '+') st.push(lt + rt);
                else if(x == '-') st.push(lt - rt);
                else if(x == '*') st.push(lt * rt);
                else if(x == '/') st.push(lt / rt);
            }
        }
        answer = st.get(0);
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }


}

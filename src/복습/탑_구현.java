package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑_구현 {
    static class Top{
        int num;
        int height;

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Stack<Top> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 탑을 push한다. 초기작업에 해당
                result.add(0);
                stack.push(new Top(i, height));
            } else {
                while (true) {
                    if (stack.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 스택에 탑을 push
                        result.add(0);
                        stack.push(new Top(i, height));
                        break;
                    }

                    Top top = stack.peek();

                    if (top.height > height) { // peek한 탑의 높이가 현재 탑의 높이보다 높다면
                        result.add(top.num); // peek한 탑의 번호를 출력하고 탑을 스택에 push
                        stack.push(new Top(i, height));
                        break;
                    } else { // peek한 탑의 높이가 현재탑의 높이보다 낮다면
                        stack.pop(); // 스택에서 pop하고 다시 반복문을 돌린다.
                    }
                }
            }
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

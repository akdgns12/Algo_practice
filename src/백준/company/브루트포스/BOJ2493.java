package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    // 탑 / 골드 5 / 구현
    /*
        수평선에 일렬로 세워진 탑들의 신호를 각각 어느 탑에서 수신하는지 알아내라.
        생각생각..
        스택 사용하면 될 듯?
        1. 스택이 비어있다면 0을 출력하고, 현재 탑을 스택에 push
        2. 스택이 비어있지 않다면, 아래 2가지 case 검사
         2-1. 스택에서 peek한 탑의 높이가 현재 탑의 높이보다 높다면, peek한 탑의 번호를 출력하고, 현재 탑을 스택에 push한다.
         2-2. 스택에 peek한 탑의 높이가 현재 탑의 높이보다 낮다면, peek한 탑을 pop하고 2번으로 다시 돌아간다.
     */
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        Stack<Top> stack = new Stack<>(); // 구조체를 이용하여 스택에 탑의 번호와, 높이 저장
        ArrayList<Integer> result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 탑을 push한다
                result.add(0);
                stack.push(new Top(i, height));
            } else {
                while(true){
                    if(stack.isEmpty()){ // 스택이 비어있다면, 0을 출력하고 탑을 push한다.
                        result.add(0);
                        stack.push(new Top(i, height));
                        break;
                    }

                    Top top = stack.peek();

                    if (top.height > height) { // peek한 탑의 높이가 현재 탑의 높이보다 높다면,
                        result.add(top.num); // peek한 탑의 번호를 출력하고,
                        stack.push(new Top(i, height)); // 현재 탑을 스택에 push한다.
                        break;
                    } else { // peek한 탑의 높이가 현재 탑의 높이보다 낮다면,
                        stack.pop(); // 스택에서 pop하고 다시 반복문을 돌린다.
                    }
                }
            }
        }

        for (int i : result){
            System.out.print(i + " ");
        }
    }

    static class Top{
        int num; // 탑의 번호
        int height; // 탑의 높이

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
}

package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493_탑 {
    static int N;
    static int[] tower;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 탑 수
        Stack<Node> stack = new Stack<>();
        tower = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){ // 각 탑의 높이
            int height = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()){ // 비어있을땐
                sb.append(0 + " ");
                stack.push(new Node(i, height));
            }else{
                while(true){
                    if(stack.isEmpty()){ // 진행하다가 스택이 비어있다면,
                        sb.append(0 + " ");
                        stack.push(new Node(i, height));
                        break;
                    }

                    Node pre = stack.peek();

                    if(pre.height > height){ // 앞의 탑의 높이가 현재보다 높다면
                        sb.append(pre.num + " ");
                        stack.push(new Node(i, height));
                        break;
                    }else{ // 현재 높이가 더 높다면
                        stack.pop();
                    }
                }
            }
        }

        // 0~N-1 탑들에서 발사한 신호를 수신할 탑들의 번호들을 차례로 출력

        System.out.println(sb);
    }

    static class Node{
        int num;
        int height;
        public Node(int num, int height){
            this.num = num;
            this.height = height;
        }
    }
}

package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16953 {
    // A -> B / 실버1 / BFS
    // a 를 b로 바꾸는데 필요한 연산의 최솟값에 1을 더한값 구해라
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        bfs(a,b);
    }

    // 가능한 연산 2가지
    static void bfs(long a, long b) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(a, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.num == b){
                System.out.println(now.cnt+1);
                return;
            }

            if(now.num * 2 <= b) q.offer(new Node(now.num * 2, now.cnt + 1));
            if(now.num * 10 + 1 <= b) q.offer(new Node(now.num * 10 + 1, now.cnt + 1));
        }
        System.out.println(-1);
    }

    static class Node{
        long num;
        int cnt;

        public Node(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

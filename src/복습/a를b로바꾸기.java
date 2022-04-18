package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class a를b로바꾸기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        bfs(a,b);
    }

    static void bfs(long a, long b) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(a,0));

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.a == b){
                System.out.println(node.cnt + 1);
                return;
            }

            if(node.a * 2 <= b) q.offer(new Node(node.a * 2, node.cnt + 1));
            if(node.a * 10 + 1 <= b) q.offer(new Node(node.a * 10 + 1, node.cnt + 1));
        }
        System.out.println(-1);
    }

    static class Node{
        long a;
        int cnt;

        public Node(long a, int cnt) {
            this.a = a;
            this.cnt = cnt;
        }
    }
}

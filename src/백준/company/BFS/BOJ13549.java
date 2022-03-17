package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13549 {
    // 숨바꼭질3 / 골드 5 / BFS
        static int n, k;
        static boolean[] visited;
        static int answer = Integer.MAX_VALUE;
        static int max = 100000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[max+1];
        bfs();
        System.out.println(answer);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            visited[node.x] = true;

            if(node.x == k) answer = Math.min(node.time, answer);

            if(node.x * 2 <= max && !visited[node.x * 2]) q.offer(new Node(node.x * 2, node.time));
            if(node.x - 1 >= 0 && !visited[node.x - 1]) q.offer(new Node(node.x - 1, node.time+1));
            if(node.x + 1 <= max && !visited[node.x + 1]) q.offer(new Node(node.x + 1, node.time+1));
        }
    }

    static class Node{
        int x, time;
        public Node(int x, int time){
            this.x = x;
            this.time = time;
        }
    }
}


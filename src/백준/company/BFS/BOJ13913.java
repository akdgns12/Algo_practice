package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13913 {
    //숨바꼭질4 / 골드 4 / BFS
    static int n, k;
    static int max = 100000;
    static boolean[] visited;
    static int[] route;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[max + 1];
        route = new int[max+1];
        Arrays.fill(route, -1);

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));
        visited[n] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == k) {
                System.out.println(node.time);

                sb = new StringBuilder();
                print(k);

                System.out.println(sb);
                return;
            }

            int next = node.x*2;
            if(next <= max && !visited[next]){
                q.offer(new Node(next, node.time + 1));
                visited[next] = true;
                route[next] = node.x;
            }

            next = node.x + 1;
            if(next <= max && !visited[next]) {
                q.offer(new Node(next, node.time + 1));
                visited[next] = true;
                route[next] = node.x;
            }

            next = node.x - 1;
            if(next >= 0 && !visited[next]) {
                q.offer(new Node(next, node.time + 1));
                visited[next] = true;
                route[next] = node.x;
            }


        }
    }

    static void print(int n){
        if(route[n] > -1){
            print(route[n]);
        }
        sb.append(n).append(" ");
    }

    static class Node{
        int x, time;
        public Node(int x, int time){
            this.x = x;
            this.time = time;
        }
    }
}

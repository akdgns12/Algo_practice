package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_DSLR {
    static Queue<Node> q;
    static boolean[] visited;
    static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 최소한의 명령어 사용으로 A -> B로
            q = new LinkedList<>();
            visited = new boolean[10001];
            ans = "";
            bfs(A,B);
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    static void bfs(int start, int end){
        q.offer(new Node(start,""));
        visited[start] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.num == end){
                ans = now.cmd;
                return;
            }

            // D
            int next = now.num * 2;
            if(next > 9999) next %= 10000;
            removeDuplicate(next, "D", now);
            // S
            next = now.num == 0 ? 9999 : now.num - 1;
            removeDuplicate(next, "S", now);
            // L
            next = (now.num % 1000) * 10 + now.num / 1000; // 왼편으로 회전
            removeDuplicate(next, "L", now);
            // R
            next = now.num % 10 * 1000 + now.num / 10; // 오른편으로 회전
            removeDuplicate(next, "R", now);
        }
    }

    static void removeDuplicate(int next, String cmd, Node now){
        if(!visited[next]){
            visited[next] = true;
            q.offer(new Node(next, now.cmd + cmd));
        }
    }

    static class Node{
        int num;
        String cmd;
        public Node(int num, String cmd){
            this.num = num;
            this.cmd = cmd;
        }
    }
}

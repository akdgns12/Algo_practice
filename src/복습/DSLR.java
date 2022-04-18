package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR {
    static boolean[] visited;
    static char[] oper = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bfs(a,b);
        }
    }

    static void bfs(int a, int b) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(a, ""));
        boolean[] visited = new boolean[10000];
        visited[a] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            int now = node.num;
            String command = node.command;

            if(now == b){
                System.out.println(command);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int res = calc(now, i);

                if(!visited[res]){
                    visited[res] = true;
                    q.offer(new Node(res, command + oper[i]));
                }
            }
        }
    }

    static int calc(int now, int i) {
        switch (i){
            case 0:
                return (now * 2) % 10000;
            case 1:
                return now == 0 ? 9999 : now - 1;
            case 2: // 왼편 회전 1234 -> 2341
                return (now % 1000) * 10 + (now / 1000);
            case 3: // 오른편 회전 1234 -> 4123
                return (now % 10) * 1000 + (now /10);
        }
        return 0;
    }

    static class Node{
        int num;
        String command;

        public Node(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }
}

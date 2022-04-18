package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {
    // DSLR / 골드5 / BFS
    // 각 명령어를 사용해 정수 a를 b로 최소한의 횟수로 바꿀 수 있도록
    static StringBuilder sb;
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

            sb = new StringBuilder();
            bfs(a,b);
        }
    }

    static void bfs(int a, int b){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        q.offer(new Node(a, ""));
        visited[a] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int now = node.num;
            String command = node.command;

            if(now == b){
                System.out.println(command);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int res = command(now, i);
                // 이미 확인한 숫자가 아닐경우
                if(!visited[res]){
                    visited[res] = true;
                    q.offer(new Node(res, command + oper[i]));
                }
            }
        }
    }

    static int command(int now, int i){
        switch(i){
            case 0: // D는 n을 두배로
                return (now * 2) % 10000;
            case 1: // S는 n에서 1을 뺀 결과. n이 0이라면 9999가 대신 들어감
                return now == 0 ? 9999 : now - 1;
            case 2: // L은 n의 각 자릿수를 왼편으로 회전(d2, d3, d4, d1)
                return (now % 1000) *10 + (now / 1000);
            case 3: // R은 n의 각 자릿수를 오른편으로 회전(d4, d1, d2, d3)
                return (now % 10) * 1000 + (now / 10);
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

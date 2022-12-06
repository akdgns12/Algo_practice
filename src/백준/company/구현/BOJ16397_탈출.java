package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16397_탈출 {
    static int N, T, G;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // led로 표현된 수
        T = Integer.parseInt(st.nextToken()); // 버튼을 누를 수 있는 최대 횟수
        G = Integer.parseInt(st.nextToken()); // 탈출을 위해 똑같이 만들어야 하는 수

        // N을 제한된 횟수인 T회안에 G와 같게 만들어야함

        ans = bfs();
        if (ans == -1) System.out.println("ANG");
        else System.out.println(ans);
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(N, 0)); // 현재수, 현재 진행횟수
        boolean[] visited = new boolean[100000];
        visited[N] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.time > T) return -1; // 제한횟수 초과하면 fail
            if (now.num == G) return now.time;
            // A
            int next = now.num + 1;
            if (next < 100000 && !visited[next]) {
                q.offer(new Node(next, now.time + 1));
                visited[next] = true;
            }

            // B
            next = now.num * 2;
            // 0이 아닌 가장 앞자리 -1,
            if (next > 99999) continue;
            String temp = Integer.toString(next);
            next -= (int) Math.pow(10, temp.length() - 1); // 가장 앞자리 수 -1
            if (next >= 0 && next < 100000 && !visited[next]) {
                q.offer(new Node(next, now.time + 1));
                visited[next] = true;
            }
        }

        return -1;
    }

    static class Node {
        int num, time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}

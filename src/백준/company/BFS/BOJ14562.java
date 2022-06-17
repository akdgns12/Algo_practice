package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14562 {
    // 태권왕 / 실버2 / BFS
    /*
        2가지 방법을 사용해
        S와 T가 같아지는 최소 연속 발차기 횟수 가하라
     */
    static int tc;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 태균이 점수
            int t = Integer.parseInt(st.nextToken()); // 상대 점수

            visited = new boolean[100];
            bfs(s,t);
        }
    }

    static void bfs(int s, int t) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s,0));
        visited[s] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.idx == t) {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 1; i <= t; i++) {
                int next = now.idx + (now.idx * i);
                t += 3;

                if (next < 1 ||next > t) continue;

                q.offer(new Node(next, now.cnt + 1));
                visited[next] = true;
            }

            for (int i = 1; i <= t; i++) {
                int next = now.idx + 1;

                if (next > t) continue;

                q.offer(new Node(next, now.cnt + 1));
                visited[next] = true;
            }
        }

        return;
    }

    static class Node{
        int idx;
        int cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}


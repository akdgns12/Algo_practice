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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 태균이 점수
            int t = Integer.parseInt(st.nextToken()); // 상대 점수

            bfs(s,t, 0);
        }
    }

    static void bfs(int s, int t, int cnt) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s, t, 0));

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.s == now.t) {
                System.out.println(now.cnt);
                return;
            }

            if(now.s > now.t) continue;

            q.offer(new Node(now.s * 2, now.t + 3, now.cnt + 1));
            q.offer(new Node(now.s + 1, now.t, now.cnt + 1));
        }

    }

    static class Node{
        int s, t;
        int cnt;

        public Node(int s, int t, int cnt) {
            this.s = s;
            this.t =t ;
            this.cnt = cnt;
        }
    }
}


package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9576_책나눠주기 {
    static int N, M;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Node(x, y));
            }

            Collections.sort(list);
            sb.append(solve()).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int solve() {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            Node now = list.get(i);
            int start = now.x;
            int end = now.y;

            for (int j = start; j <= end; j++) {
                if (visited[j]) continue;
                visited[j] = true;
                cnt++;
                break;
            }
        }

        return cnt;
    }

    static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

            @Override
            public int compareTo (Node o){
                if (this.y == o.y) return this.x - o.y;
                else return this.y - o.y;
        }
    }
}

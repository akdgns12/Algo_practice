package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1326 {
    // 폴짝폴짝
    // 징검다리에 쓰여있는 수의 배수만큼 점프
    // a -> b 로 가려할 때 최소 몇번 점프하는지
    static int n;
    static int[] arr;
    static boolean[] visited;
    static int minJump = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        bfs(a, b);
        if (minJump == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minJump);
        }
    }

    static void bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visited[start] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.idx == end) {
                if(now.jump < minJump)
                    minJump = now.jump;
                return;
            }

            int multiple = 1;

            // right
            while (true) {
                int next = now.idx + arr[now.idx] * multiple;
                if(next >= n) break;
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, now.jump + 1));
                }
                multiple++;
            }

            multiple = 1;

            // left
            while (true) {
                int next = now.idx - arr[now.idx] * multiple;
                if(next < 0) break;
                if (!visited[next]) {
                    q.offer(new Node(next, now.jump + 1));
                    visited[next] = true;
                }
                multiple++;
            }
        }

        return;
    }


    static class Node{
        int idx;
        int jump;

        public Node(int idx, int jump) {
            this.idx = idx;
            this.jump = jump;
        }
    }
}

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
    /*
        양의 방향, 음의 방향으로 움직이면서 방문체크하고
        이런 과정은 목적지에 도착하기 전까지 진행하지만
        결국 큐도 비게 된다는 것은 도달할 수 없다는 것이므로 -1을 반환
     */
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
    }

    static void bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visited[start] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.idx == end) { // 현재 위치가 목적지라면
                System.out.println(now.jump);
                return;
            }

            // 아직 도착안했다면 계속 이동
            // 양의 방향
            for (int i = 1; now.idx + (arr[now.idx] * i) < n; i++) {
                int next = now.idx + arr[now.idx] * i;
                if(visited[next]) continue;

                visited[next] = true;
                q.offer(new Node(next, now.jump + 1));
            }

            // 음의 방향
            for (int i = 1; now.idx - (arr[now.idx] * i) >= 0; i++) {
                int next = now.idx - arr[now.idx] * i;
                if(visited[next]) continue;

                visited[next] = true;
                q.offer(new Node(next, now.jump + 1));
            }
        }

        System.out.println(-1);
    }

    static class Node{
        int idx; // 몇 번째 노드인지
        int jump; // 점프 횟수

        public Node(int idx, int jump) {
            this.idx = idx;
            this.jump = jump;
        }
    }
}

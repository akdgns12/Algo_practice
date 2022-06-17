package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14248 {
    // 점프 점프 / 실버2 / BFS
    /*
        숫자가 적혀있는 만큼, 오른쪽이나 왼쪽으로 이동
        방문 가능한 돌들의 개수
     */
    static int n;
    static int[] bridge;
    static boolean[] visited;
    static int[] dx = {-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        bridge = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            bridge[i] = Integer.parseInt(st.nextToken());
        }

        int start = Integer.parseInt(br.readLine()) - 1; // 인덱스 처리를 위해
        System.out.println(bfs(start));
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 1; // 시작지점도 방문한 것이니 1로 시작 카운팅
        while (!q.isEmpty()){
            int now = q.poll();

            for (int i = 0; i < 2; i++) {
                int next = now + dx[i] * bridge[now];

                if(next < 0 || next >= n || visited[next]) continue;

                visited[next] = true;
                q.offer(next);
                cnt++;
            }
        }

        return cnt;
    }
}

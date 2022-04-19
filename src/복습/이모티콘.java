package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘 {
    static int S;
    static boolean[][] visited; // clipboard, total

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        visited = new boolean[10001][100];
        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 1, 0)); // 이미 화면에 이모티콘 1개 입력했다.
        visited[0][1] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if (cur.total == S) {
                System.out.println(cur.time);
                return;
            }

            // 1. 화면에 있는 이모티콘 클립보드에 저장
            q.offer(new Node(cur.total, cur.total, cur.time + 1));

            // 2. 클립보드에 있는 이모티콘 붙여넣기.
            // 클립보드 비어있는 상태에는 붙여넣기를 할 수 없음,
            if (cur.clipboard != 0 && cur.total + cur.clipboard <= S && !visited[cur.clipboard][cur.total + cur.clipboard]) {
                q.offer(new Node(cur.clipboard, cur.total + cur.clipboard, cur.time + 1));
                visited[cur.clipboard][cur.total + cur.clipboard] = true;
            }

            // 3. 화면에 있는 이모티콘 중 하나 삭제
            if (cur.total >= 1 && !visited[cur.clipboard][cur.total - 1]) {
                q.offer(new Node(cur.clipboard, cur.total - 1, cur.time - 1));
                visited[cur.clipboard][cur.total - 1] = true;
            }
        }
    }

    // 클립보드에 복사해서 저장한 이모티콘, 화면에 있는 이모티콘, 시간
    static class Node{
        int clipboard;
        int total;
        int time;

        public Node(int clipboard, int total, int time) {
            this.clipboard = clipboard;
            this.total = total;
            this.time = time;
        }
    }
}

package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이모티콘2 {
    static int S;
    static boolean[][] visited = new boolean[1001][1001]; // [clipboard][total]
    // 문제조건
    // 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어씌워짐
    // 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없음. 일부만 클립보드에 복사할 수 없음
    // 클립보드에 있는 이모티콘 중 일부를 삭제할 수 없음. 화면에 이모티콘을 붙여넣기 하면, 클립보드에 있는 이모티콘의 개수가 화면에 추가
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = Integer.parseInt(br.readLine());

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 1, 0));
        visited[0][1] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.total == S){
                System.out.println(node.time);
                return;
            }

            // 1. 복사
            q.offer(new Node(node.total, node.total, node.time + 1));

            // 2. 클립보드에 있는 이모티콘 화면에 붙여넣기
            // 클립보드가 비어있으면 안되고, 붙여넣은 후 개수가 총 개수보다 적어야 하며, 화면에 붙여넣으면 클립보드에 있는 이모티콘의 개수가 화면에 추가
            if (node.clipboard >= 1 && node.total + node.clipboard <= S && !visited[node.clipboard][node.total + node.clipboard]) {
                q.offer(new Node(node.clipboard, node.total + node.clipboard, node.time + 1));
                visited[node.clipboard][node.total + node.clipboard] = true;
            }

            // 3. 삭제
            // 화면에 있는 이모티콘 중 하나를 삭제한다.
            if (node.total >= 1 && !visited[node.clipboard][node.total - 1]) {
                q.offer(new Node(node.clipboard, node.total - 1, node.time + 1));
                visited[node.clipboard][node.total - 1] = true;
            }
        }
   }

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

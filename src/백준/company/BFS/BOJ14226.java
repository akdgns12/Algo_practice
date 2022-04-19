package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226 {
    // 이모티콘 / 골드 5 / BFS
    /*
        영선이가 S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값
        생각
        이미 화면에 이모티콘 1개를 입력해놓은 상태
        1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
        2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣음
        3. 화면에 있는 이모티콘 중 하나를 삭제
        클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어씌워짐 클립보드가 비어있는 상태에는 붙여넣을 수 없으며, 일부만 클립보드에 복사할 수 없음
     */
    /*
        BFS탐색을 하면서 조건에 맞게 1, 2, 3번을 진행하고 현재 개수가 S와같아진다면 BFS탐색을 종료하고 현재 시간을 출력하도록.

        탐색을 하면서 계속 같은 구간을 반복하지 않도록 visited 배열사용. 각각의 인덱스가 의미하는 바 [clipboard에 현재 복사된 이모티콘 개수][현재 화면에 출력된 총 이모티콘의 개수]
        1. 화면에 있는 이모티콘을 모두 복사해 클립보드에 저장한다.
         - 현재 total개수를 clipboard로 갱신하여 큐에 담아주면 된다. 시간은 현재시간 +1을 해준다.
        2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
         - clipboard 개수는 그대로 놔두고 현재 total개수를 total + clipboard로 갱신해 큐에 담아준다. 시간은 현재 시간 + 1을 해준다
        3. 화면에 있는 이모티콘 중 하나를 삭제한다.
         - clipboard 개수는 그대로 놔두고 현재 total개수를 total - 1로 갱신해 큐에 담아준다. 시간은 현재시간 + 1해준다.
     */
    static boolean[][] visited;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        visited = new boolean[1001][1001]; // 클립보드에 현재 복사된 이모티콘 개수, 현재 화면에 출력된 총 이모티콘 개수
        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 1, 0));
        visited[0][1] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.total == S){
                System.out.println(cur.time);
                return;
            }

            // 1. 화면에 있는 이모티콘 클립보드에 저장
            q.offer(new Node(cur.total, cur.total, cur.time + 1));

            // 2. 클립보드에 있는 이모티콘 붙여넣기.
            // 클립보드 비어있지 않아야하고, 붙여넣은 후 개수가 총 개수보다 적어야 하며, 이전에 방문한적 없어야 함.
            if (cur.clipboard != 0 && cur.total + cur.clipboard <= S && !visited[cur.clipboard][cur.total + cur.clipboard]) {
                q.offer(new Node(cur.clipboard, cur.total + cur.clipboard, cur.time + 1));
                visited[cur.clipboard][cur.total + cur.clipboard] = true;
            }

            // 3. 화면에 있는 이모티콘 중 하나 삭제.
            // 총 개수 1보다 크거나 같아야하고, 방문한적 없어야 함
            if(cur.total >= 1 && !visited[cur.clipboard][cur.total - 1]){
                q.offer(new Node(cur.clipboard, cur.total - 1, cur.time + 1));
                visited[cur.clipboard][cur.total - 1] = true;
            }
        }
    }

    static class Node {
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

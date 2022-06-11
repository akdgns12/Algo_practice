package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ피자오븐 {
    static int t,n;
    static int[] dt = {-1, 1, -10, 10, 60};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            visited = new boolean[61];
            int addHour = n/60;
            n %= 60;
            bfs(n, addHour);
        }
    }

    static void bfs(int targetTime, int addHour) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 0, 0, 0));
        visited[0] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.time == targetTime) {
                now.addh += addHour;
                System.out.print(now.addh + " " + now.addt + " " + now.mint + " " + now.addo + " " + now.mino + '\n');
                return;
            }

            for (int i = 0; i < 5; i++) {
                int next = now.time + dt[i];

                if(next < 0 || next > 60 || visited[next]) continue;

                visited[next] = true;
                if(i == 0) q.offer(new Node(next, now.addh, now.addt, now.mint, now.addo, now.mino + 1));
                else if(i == 1) q.offer(new Node(next, now.addh, now.addt, now.mint, now.addo + 1, now.mino));
                else if(i == 2) q.offer(new Node(next, now.addh, now.addt, now.mint + 1, now.addo, now.mino));
                else if(i == 3) q.offer(new Node(next, now.addh, now.addt + 1, now.mint, now.addo, now.mino));
                else q.offer(new Node(next, now.addh + 1, now.addt, now.mint, now.addo, now.mino));
            }
        }

    }


    static class Node{
        int time;
        int addh, addt, mint, addo, mino;

        public Node(int time, int addh, int addt, int mint, int addo, int mino) {
            this.time = time;
            this.addh = addh;
            this.addt = addt;
            this.mint = mint;
            this.addo = addo;
            this.mino = mino;
        }
    }
}

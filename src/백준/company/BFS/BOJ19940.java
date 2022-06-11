package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ19940 {
    // 피자오븐 / 골드5 / bfs
    // 설정해야 할 시간이 주어질 때, 시간에 맞추기 위해 누르는 버튼의 최소횟수
    static int t, n;
    static boolean[] visited;
    static int[] dt = {-1, 1, -10, 10, 60};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine()); // 설정해야 하는 시간 n(분 단위 정수)
            visited = new boolean[61];
            int addh = n/60; // 시간
            n %= 60; // 실질적으로 설정해야 하는 분
            bfs(n, addh);
        }
    }

    static void bfs(int end, int addh) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0, 0, 0, 0, 0));
        visited[0] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.time == end) {
                now.ADDH += addh; // 60분에 시간 더해주고(1시간 = 60분이기 때문에)
                System.out.print(now.ADDH + " " + now.ADDT + " " + now.MINT + " " + now.ADDO + " " + now.MINO + '\n');
                break;
            }

            for (int i = 0; i < 5; i++) {
                int nxt = now.time + dt[i];

                if(nxt < 0 || nxt > 60 || visited[nxt]) continue;

                visited[nxt] = true;
                // 문제에서 사전순으로 앞서는 순서대로 출력해야함(단위가 큰 수가 적은순)
                // 그래서 작은 단위부터 push하도록 순서를 진행해준다.
                // 반대로 해도 결과값엔 이상이 없지만(문제에서 요구하는 사전순으로 정렬하려면 이렇게 push 해줘야 함)
                if(i == 0) q.offer(new Node(nxt, now.ADDH, now.ADDT, now.MINT, now.ADDO, now.MINO+1));
                else if(i == 1) q.offer(new Node(nxt, now.ADDH , now.ADDT, now.MINT, now.ADDO+1, now.MINO));
                else if(i == 2) q.offer(new Node(nxt, now.ADDH , now.ADDT, now.MINT+1, now.ADDO, now.MINO));
                else if(i == 3) q.offer(new Node(nxt, now.ADDH, now.ADDT+1, now.MINT, now.ADDO, now.MINO));
                else q.offer(new Node(nxt, now.ADDH+1, now.ADDT, now.MINT, now.ADDO, now.MINO));
            }
        }
    }

    static class Node{
        int time;
        int ADDH, ADDT, MINT, ADDO, MINO;

        public Node(int time, int ADDH, int ADDT, int MINT, int ADDO, int MINO) {
            this.time = time;
            this.ADDH = ADDH;
            this.ADDT = ADDT;
            this.MINT = MINT;
            this.ADDO = ADDO;
            this.MINO = MINO;
        }
    }
}


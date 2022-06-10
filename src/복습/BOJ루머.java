package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ루머 {
    static int n, m;
    static int[] answer;
    static int[] turn;
    static Queue<Integer> q = new LinkedList<>();
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        answer = new int[n+1];
        Arrays.fill(answer, -1);

        turn = new int[n+1];
        Arrays.fill(turn, 0);

        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            while (true) {
                int num = Integer.parseInt(st.nextToken());
                list[i].add(num);
                if(num == 0) break;
            }
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.offer(num);
            answer[num] = 0;
        }


        bfs();
        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static void bfs() {
        // turn[i] : 몇 명 이상 감염되어야 i번사람이 감염되는(주변인 절반)
        for (int i = 1; i <= n; i++) {
            turn[i] = list[i].size() / 2;
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) { // 주변인 탐색
                if(next == 0) continue;

                turn[next] -= 1; // now는 감염상태이기 때문에 하나 빼줌
                if(answer[next] == -1 && turn[next] <= 0){ // 감염되지 않은 상태이고, turn[i]가 0이하일때
                    q.offer(next);
                    answer[next] = answer[now] + 1;
                }
            }

        }
    }
//
//    static boolean check(int node) {
//        int cnt = 0;
//        for (int next : list[node]){ // 해당 노드 주변 돌면서
//            if(answer[next] != -1) cnt++;
//        }
//
//        if(cnt >= turn[node]) return true;
//        else return false;
//    }
}

package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19538 {
    // 루머 / 골드4 / bfs
    /*
        사람들이 루머를 처음 믿기 시작하는 시간
        N명의 사람은 어떠한 루머를 자신과 연관된 주변인 중 절반이상이 믿는다면 자신또한 그 루머를 믿는다
        이때, 모든 사람이 루머를 믿기위해서 걸리는 시간을 구하라
        믿지 않을 경우 -1
     */
    /*
        큐에는 감염된 사람의 번호가 들어갈 수 있다.
        가장 처음에는 최초 감염자의 번호 1과 6
        큐에서 한사람(1번)을 뺀다. 이 사람은 최초 감염자이므로 0분에 감염된 사람. 이 사람은 주변인에게
        영향을 주었을 것이다. 감염된 사람의 모든 주변인들(2번과 3번)살펴보자
     */
    static int n, m;
    static Queue<Integer> q = new LinkedList<>();
    static int[] turn;
    static int[] answer; // 정답 배열
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); //사람 수

        turn = new int[n]; // 감염까지 남은 비감염 사람 수
        answer = new int[n];
        Arrays.fill(answer, -1); // -1로 초기화(처음 감염된 것인지 아닌지 구분위해)

        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // i번째 줄에 i번과 주변인의 번호가 주어짐(i번사람의 주변인)
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0)
                    break;

                list[i].add(num);
            }
        }

        m = Integer.parseInt(br.readLine()); // 루머 최초 유포자 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.offer(num); // 최초 유포자, 시간 : 0
            answer[num-1] = 0; // 최초유포자는 0분
        }

        bfs();
        for (int i : answer)
            System.out.print(i + " ");
    }

    // 큐에 감염된 사람들을 넣으며 주변인들의 감염여부 판별해나간다
    static void bfs() {
        // 주변인의 절반 이상이 루머를 믿을때 본인도 루머를 믿으므로
        // 몇 명이 감염되었을 떄 자신이 감염되는지에 대한 정보를
        // 사람 i의 주변인물 수 + 1/2의 몫으로 저장해둔다.
        for (int i = 1; i <= n; i++) {
            turn[i-1] = list[i].size() / 2;
        }

        while(!q.isEmpty()){
            int now = q.poll(); // 현재, 가장 먼저 감염된 사람의

            for (int next : list[now]) { // 주변인 탐색
                if(next == 0) continue;

                turn[next-1] -= 1; // 자신(주변인물)이 감염되기까지 남은 사람 수를 1빼고
                if(answer[next-1] == -1 && turn[next-1] <= 0){ // 만약 감염되지 않았고 주변인의 반 이상이 감염되었다면
                    q.offer(next); // 감염되었기에 큐에 넣어주고
                    answer[next-1] = answer[now-1] + 1; // 자신을 감염시킨 마지막 주변인의 감염된 시간 + 1분을 더함
                }
            }
        }
    }
}


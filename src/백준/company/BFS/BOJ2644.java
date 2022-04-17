package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2644 {
    // 촌수계산 / 실버 2 / BFS
    // 여러사람들에 대한 부모 자식들 간의 관계가 주어질 때, 주어진 두 사람의 촌수를 계산하라
    /*
        사람관의 관계를 잘 저장해줘야함.
        연결된 관계를 타고 가면서 촌수를 카운트해야 하므로
        다음 찾을 사람(a,b) 중 한사람을 기준으로 시작해서
        그 사람(a)부터 관계를 타고 가면서 촌수를 저장해주고
        대상(b)를 만나면 그 때의 촌수를 출력해주면 된다.
        BFS를 활용해서 이미 촌수를 확인한 사람은 스킵하고
        확인을 안한 사람들은 촌수를 증가시켜주면서 관계를 타고 가는 것
        촌수를 확인할 수 없을 때는
        result값이 갱신되지 않으므로 결국 -1을 출력하고 종료
     */
    static int n, m;
    static int[] dist;
    static ArrayList<Integer>[] relation;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 전체 사람의 수

        dist = new int[n+1];
        relation = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) { // 관계정보
            relation[i] = new ArrayList<>();
        }
        Arrays.fill(dist, -1);

        st = new StringTokenizer(br.readLine());
        int nodeA = Integer.parseInt(st.nextToken());
        int nodeB = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            relation[x].add(y);
            relation[y].add(x);
        }

        System.out.println(bfs(nodeA, nodeB));
    }

    static int bfs(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int now = q.poll();  // 확인할 사람을 큐에서 뺴고

            if(now == end){ // 비교대상을 찾으면 촌수(결과값)저장
                return result = dist[now];
            }

            // 해당 사람과 관계있는 사람들을 확인
            for (int i = 0; i < relation[now].size(); i++) {
                int next = relation[now].get(i);
                if(dist[next] != -1) continue; // 이미 촌수를 확인한 사람이면 pass
                dist[next] = dist[now] + 1; // 다음관계는 현재까지 촌수 +1
                q.offer(next);
            }
        }
        return -1;
    }
}

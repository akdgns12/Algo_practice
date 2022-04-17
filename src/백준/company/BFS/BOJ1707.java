package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {
    // 이분그래프 / 골드4 / BFS
    // 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프라 한다.
    // 판별하는 프로그램 작성
    // 이분그래프가 도대체 뭐임?
    // -> 모든 꼭짓점을 빨강,파랑으로 색칠하되 모든 변이 빨강과 파랑 꼭짓점을 포함하도록 색칠할 수 있는 그래
    // -> 즉, 그래프의 모든 정점을 빨강, 파랑으로 칠한다 했을 때 바로 인접한 정점끼리는 같은 색(그룹)을 갖지 않도록 -> 즉, 서로 다른 그룹의 정점이 간선으로 연결되어져 있는 그래프
    // 생각 그럼 BFS탐색으로 이전 노드와는 다른색을 칠해주고 만약 인접 노드와 같은 색일 경우 NO
    /*
        두 그룹으로 나누었을 때 자신의 그룹에는 접근할 수 없어야 한다.
        예로들어 a팀은 1번 노드의 인접한 2번 노드로 갔을 때 2번 노드가 a팀이면 안된다.
     */
    static int k;
    static int v, e;
    static int[] color;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine()); // 테스트 개수


        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken()); // 정점 개수
            e = Integer.parseInt(st.nextToken()); // 간선 개수

            list = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) {
                list[i] = new ArrayList<>();
            }

            // 간선 정보
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            color = new int[v+1];
            bfs(1);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) { // 모든 정점이 연결되어 있지않다 -> 문제를 잘 보면 정점이 서로 끊어져 있는 경우도 있음 다 체크해줘야함
            if(color[i] == 0){ // 아직 아무 그룹도 아니라면 -> 아직 방문하지 않았다면
                color[i] = 1; // 1그룹으로 매핑해주고 큐에 넣어준다
                q.offer(i);
            }

            while(!q.isEmpty()){
                int now = q.poll();
                // 인접노드 탐색
                for (int next : list[now]){
                    if(color[next] == color[now]){ // 인접노드가 같은 그룹(색깔)이라면 NO
                        System.out.println("NO");
                        return;
                    }

                    // 같은 그룹이 아니고 아직 방문하지 않았다면?
                    if(color[next] == 0){
                        q.offer(next); // 다음 노드를 큐에 넣어주고

                        // 이전 노드가 1이면 2로 매핑, 이전 노드가 2면 다음노드를 1로 매핑 이렇게 각각 다른 색깔로 매핑해준다
                        if(color[now] == 1){
                            color[next] = 2;
                        }else{
                            color[next] = 1;
                        }
                    }
                }
            }
        }
        System.out.println("YES");
    }
}

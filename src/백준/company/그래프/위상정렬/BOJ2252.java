package 백준.company.그래프.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 바킹독 - 위상정렬
 * 방향 그래프에서 간선으로 주어진 정점 간 선후관계를 위배하지 않도록 나열하는 정렬
 * #유의할 점 - 그래프가 순환하지 않아야 함(사이클 생성되면 X)
 *
 * indegree = 0 , 자신에게 들어오는 간선의 수가 0인 것 --> 즉, 제일 앞에 올 수 있는 노드(자신을 향한 노드가 없는 노드)
 */
public class BOJ2252 {
    // 줄 세우기 / 골드 3 / 그래프, 위상정렬
    static int N,M;
    // 위상정렬에 사용할 그래프 2차원 리스트로 구현
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    // 자신에게 들어오는 간선의 수(진입차수) 저장 배열
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수(노드 수)
        M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수(간선)

        indegree = new int[N+1];
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }
        /*
            답이 여러가지인 경우 아무거나 출력
         */
        // 두 학생의 비교 정보 -> A가 B의 앞에 서야한다는 뜻
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 정점 A에서 B로 이동가능
            graph.get(A).add(B);
            // 진입차수(간선이 생겼으므로) 1 증가
            indegree[B]++;
        }

        topological(indegree, graph);
    }

    static void topological(int[] indegree, ArrayList<ArrayList<Integer>> graph){
        Queue<Integer> q = new LinkedList<>(); // 진입차수가 0인 노드가 들어갈 큐
        Queue<Integer> result = new LinkedList<>(); // 위상정렬 결과가 들어갈 큐

        // indegree가 0인 노드 큐에 넣기
        for(int i=1; i<=N; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        // 진입차수가 0인 노드들 큐에서 하나씩 뺴며 연결된 노드의 indegree 감소
        // 반복해가며 indegree가 0이 된 노드 q큐에 넣기
        while(!q.isEmpty()){
            int node = q.poll();

            result.offer(node);

            for(int i : graph.get(node)) {
                indegree[i]--;

                if (indegree[i] == 0)
                    q.offer(i);
            }
        }// end while

        while(!result.isEmpty()){
            System.out.println(result.poll() + " ");
        }
    }
}


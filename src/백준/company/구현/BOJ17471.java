package 백준.company.구현;

import java.io.IOException;
import java.util.*;

public class BOJ17471 {
    // 게리맨더링 / 골드4 / 구현
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[] people;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 구역의 개수

        people = new int[N+1]; // 인구 수 저장
        for(int i=1; i<=N; i++){
            people[i] = sc.nextInt();
        }

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=1; i<=N; i++){ // 인접 구역 정보 -> 인접리스트 graph에 담는다
            int n = sc.nextInt();

            for(int j=0; j<n; j++){
                int num = sc.nextInt();

                // 입력이 중복해서 주어지기 때문에 양방향 방식으로 저장하지 않아도 됨
                graph.get(i).add(num); // i번에 인접한 노드(num)들 추가
            }
        }

        visited = new boolean[N+1];

        comb(1);
        System.out.println(answer== Integer.MAX_VALUE ? -1 : answer);
    }

    // N개의 구역을 두 그룹으로 나누는 함수 -> 조합 사용
    public static void comb(int depth){
        if(depth == N){
            ArrayList<Integer> a = new ArrayList<>(); // a 구역
            ArrayList<Integer> b = new ArrayList<>(); // b 구역

            for(int i=1; i<=N; i++){
                if(visited[i]){ // visited값이 true면 a구역
                    a.add(i);
                }else{ // false면 b구역
                    b.add(i);
                }
            }

            if(a.size() + b.size() != N) // 모든 구역이 포함되어 있지 않은 경우 X
                return;

            if(a.size() == 0 || b.size() == N) // 선거구는 적어도 1개의 구역은 포함해야 한다
                return;

            //각 선서구에 속한 구역들이 모두 이어져있다면 두 선거구의 인구수 차 갱신
            if(Connection(a,'a') && Connection(b, 'b')){
                int a_population = 0;
                for(int i=0; i<a.size(); i++){
                    a_population += people[a.get(i)];
                }

                int b_population = 0;
                for(int i=0; i<b.size(); i++){
                    b_population += people[b.get(i)];
                }

                answer = Math.min(answer, Math.abs(a_population - b_population));
            }
            return;
        }

        visited[depth] = true; // a구역에 속하는 경우(값이 true)
        comb(depth+1);
        visited[depth] = false; // b구역에 속하는 경우(값이 false)
        comb(depth+1);
    }

    // 각 그룹들이 이어져 있는지 확인하는 함순
    static boolean Connection(ArrayList<Integer> arr, char team){
        boolean[] visi = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr.get(0)); // 시작번호 삽입
        visi[arr.get(0)] = true; // 방문처리

        while(!q.isEmpty()){
            int now = q.poll();

            for(int i=0; i<graph.get(now).size(); i++){
                int next = graph.get(now).get(i);

                if(visi[next]) continue; // 이미 처리된 구역이면 skip

                // 연결된 구역이 같은 선거구에 속한 구역이라면 방문처리 & 큐에 삽입
                if((team == 'a' && visited[next] == true) || (team == 'b' && visited[next] == false)){
                    q.offer(next);
                    visi[next] = true;
                }
            }
        }
        // 모든 구역 중 연결되지 않은 구역이 있다면 해당 경우는 조건을 만족하는 경우가 X
        for(int i=0; i<arr.size(); i++){
            if(!visi[arr.get(i)])
                return false;
        }
        return true;
    }
}

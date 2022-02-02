package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {
    static int N,L,R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean isMove = false; // 연합이 일어났는지 체크하는 변수

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    // 이미 연합에 속한 나라는 무시
                    if(visited[i][j]) continue;
                    // 연합이 일어나는지 확인
                    if(union(i,j))
                        isMove = true;
                }
            }

            // 연합이 일어난다면 횟수 증가
            if(isMove == true){
                answer++;
                // 연합이 일어나지 않았다면 종료
            }else{
                System.out.println(answer);
                break;
            }
        }
    }

    // (x,y) 좌표의 나라를 기준으로 조건에 부합하는 나라들을 연합하는 함수
    static boolean union(int x, int y){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> group = new ArrayList<>(); // 연합된 나라들을 저장하는 연결리스트

        q.offer(new Node(x,y));
        group.add(new Node(x,y));
        visited[x][y] = true; // visited 배열을 통해 해당 좌표에 있는 나라가 연합에 속해있는지의 여부를 알 수 있다.

        int sum = map[x][y]; // 연합 국가들의 인구 총합
        // BFS 알고리즘을 사용하여 맞닿아 있는 나라들을 탐색한다
        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                // 배열 범위를 초과하거나 이미 연합에 속한 나라는 무시
                if(nx < 0 || ny < 0 || nx >= N || ny >= N ||
                visited[nx][ny]) continue;

                int dif = Math.abs(map[node.x][node.y] - map[nx][ny]); // 이웃한 나라와의 인구수 차이
                if(dif < L || dif > R) continue; // 연합할 수 없는 조건도 무시

                // 연합할 수 있는 나라라면
                sum += map[nx][ny]; // 인구수 누적
                visited[nx][ny] = true;
                q.offer(new Node(nx,ny));
                group.add(new Node(nx,ny));
            }
        }

        // 연합할 수 있는 나라가 없는 경우
        if(group.size() == 1)
            return false;
        else{
            // 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
            int people = sum / group.size();

            for(Node node : group)
                map[node.x][node.y] = people;
            return true;
        }
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2578 {
    // 빙고 / 실버 5 / 구현
    // 빙고판에 쓴 수들과, 사회자가 부르는 수의 순서가 주어질 때
    // 사회자가 몇 번쨰 수를 부른 후 철수가 빙고를 외치는지 출력
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[5][5];
        visited = new boolean[5][5];
        HashMap<Integer, Node> hash = new HashMap<>();

        for (int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                hash.put(map[i][j], new Node(i, j));
            }
        }


        // 사회자가 부르는 번호가 map의 어디 좌표(i,j)에 있는지 알기 위해 HashMap사용
        // key : 번호, value : Node로 i,j를 담는다
        // visited 배열을 생성해 사회자가 부르는 번호의 i,j에 true로 체크
        // 문제에서 3줄 이상 되어야 빙고를 외칠 수 있으므로, 사회자가 10번 이상 번호를 불렀을 경우 check() 메소드로 빙고인지 체크

        // 사회자가 외치는 번호
        int cnt = 0;
        int ans = 0;
        boolean flag = false;
        for (int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cnt++;
                int num = Integer.parseInt(st.nextToken());
                Node node = hash.get(num);
                visited[node.x][node.y] = true;
                if (!flag && cnt >= 10) {
                    flag = check();
                    ans = cnt;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean check(){
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            int w = 0;
            int l = 0;
            for (int j = 0; j < 5; j++) {
                if(visited[i][j])
                    w++;
                if(visited[j][i])
                    l++;
            }
            if(w == 5)
                cnt++;
            if(l == 5)
                cnt++;
        }
        if(visited[0][0] && visited[1][1] && visited[2][2] && visited[3][3] && visited[4][4])
            cnt++;
        if(visited[0][4] && visited[1][3] && visited[2][2] && visited[3][1] && visited[4][0])
            cnt++;
        if(cnt >= 3)
            return true;
        return false;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}

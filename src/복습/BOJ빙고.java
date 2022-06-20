package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ빙고 {
    static int[][] map;
    static boolean[][] visited;
    static HashMap<Integer, Node> hash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                hash.put(map[i][j], new Node(i, j));
            }
        }

        // 선이 세개 이상 그어지는순간 빙고
        int cnt = 0; // 완성된 빙고 수
        int result = 0; // 사회자가 몇번째 수를 부른 후 빙고가 완성되는지
        // 사회자가 외치는 빙고번호
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                result++; // 사회자가 몇 번째 수를 불렀는지
                Node node = hash.get(num);
                visited[node.x][node.y] = true; // 사회자가 부른 번호 방문처리 해주고

                // 그 숫자로부터 행 열 대각선 빙고 확인
                // 2,2는 대각선도 확인
                if(bingo1(node.x, node.y)) // 행 확인
                    cnt++;
                if(bingo2(node.x, node.y)) // 열 확인
                    cnt++;
                // 대각선 상에 있을 때
                if(node.x - node.y == 0){ // 왼쪽 대각선 체크
                    if(bingo3(node.x, node.y))
                        cnt++;
                }
                if(node.x + node.y == 4){ // 오른쪽 대각선 체크
                    if(bingo4(node.x, node.y))
                        cnt++;
                }

                if(cnt >= 3){ // 완성된 빙고가 3개 이상이면 답 출력
                    System.out.println(result);
                    return;
                }
            }
        }
    }

    // 빙고확인
    static boolean bingo1(int x, int y) {
        // 행
        for (int i = 0; i < 5; i++) {
            if(!visited[x][i])
                return false;
        }
        return true;
    }

    static boolean bingo2(int x, int y) {
        // 열
        for (int i=0; i<5; i++){
            if(!visited[i][y])
                return false;
        }
        return true;
    }

    static boolean bingo3(int x, int y) {
        // 왼쪽 대각선
        x = 0;
        y = 0;
        for (int i=0; i<5; i++){
            if(!visited[x++][y++])
                return false;
        }
        return true;
    }

    static boolean bingo4(int x, int y) {
        // 오른쪽 대각선
        x = 0;
        y = 4;
        for (int i = 0; i < 5; i++) {
            if(!visited[x++][y--])
                return false;
        }
        return true;
    }

    static class Node{
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

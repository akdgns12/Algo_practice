package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16954 {
    // 움직이는 미로 탈출 / 골드 4 / 그래프 탐색
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // 상, 하, 좌, 우, 왼쪽 위, 왼쪽 아래, 현재 위치, 오른쪽 위, 오른쪽 아래
    static int[] dx = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, -1, 0, 1, 0, -1, -1, 1, 1};

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];

        for (int i = 0; i <  8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs() ? 1 : 0);
    }

    public static boolean bfs() {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(7, 0)); // 욱제의 캐릭터 시작 위치 추가

        while (!q.isEmpty()) {
            int size = q.size();

            // 욱이의 캐릭터를 먼저 움직여야 하므로 큐의 크기만큼 반복
            /**
             * 문제에서 사람이 움직이고 벽을 움직인다고 했다
             * 그래서 사람을 8방향으로 모두 움직인 다음에 벽을 이동시키기 위해 현재 큐에 저장된 size만큼 돌리는 것
             *
             * 즉, size를 구해서 돌리는게 한 depth만큼 이동한 경우만 비교한다고 생각하시면 될 것 같아요
             */
            for (int i = 0; i < size; i++) {
                Node p = q.poll();
                int x = p.x;
                int y = p.y;

                // 가장 오른쪽 윗 칸으로 이동한 경우 종료
                if (x == 0 && y == 7) {
                    return true;
                }

                // 벽이 있는 경우 이동할 수 없기 때문에 넘어감
                if (map[x][y] == '#') {
                    continue;
                }

                // 모든 방향을 다 보면서 이동할 수 있는지 확인
                for (int j = 0; j < 9; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    // 범위를 벗어나지 않고, 빈 칸일 경우
                    if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && map[nx][ny] == '.') {
                        q.add(new Node(nx, ny));
                    }

                }
            }

            // 욱이의 캐릭터를 이동시킨 후 벽을 아래 행으로 한 칸씩 내림
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    // 처음에 가장 맨 위의 행에 벽이 있어도 1초 뒤에는 벽이 내려가므로 첫 번째 행은 무조건 '.'으로 바꿔줌
                    if (i == 0) {
                        map[i][j] = '.';
                    }
                    else {
                        // 이전 행에 있던걸 넣어줌
                        map[i][j] = map[i - 1][j];
                    }
                }
            }

        }

        return false;

    }

}
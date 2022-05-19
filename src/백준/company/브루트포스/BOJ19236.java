package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19236 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 0 ~ 8 : 북쪽부터 반시계방향
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    static class Fish {
        int x, y, num, dir;
        boolean isAlive = true;

        Fish() {

        }

        Fish(int x, int y, int num, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static class Shark {
        int x, y, dir, max;

        Shark() {

        }

        Shark(int x, int y, int dir, int max) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                Fish f = new Fish();
                f.num = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.num;
            }
        }

        // 물고기 미리 정렬
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.num - o2.num;
            }
        });

        // 초기 상어는 (0, 0)을 먹는다
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.num);
        // 상어에게 먹혀서 현재 비어있다.
        f.isAlive = false;
        arr[0][0] = -1;

        // solution
        solve(arr, shark, fishes);
        System.out.println(maxSum);
    }

    // 상어 식사 시간 재귀를 통해 상어가 이동할 수 없을 때까지 이동한다.
    static void solve(int[][] arr, Shark shark, List<Fish> fishes) {
        if (maxSum < shark.max) {
            maxSum = shark.max;
        }

        // 물고기 대 이동
        fishes.forEach(e -> moveFish(e, arr, fishes));

        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;
            if (OOB(nx, ny) || arr[nx][ny] <= 0) continue;

            // 물고기 잡아먹고 dfs 실행
            int[][] copyArr = copyMap(arr);
            List<Fish> copyFishes = copyList(fishes);

            copyArr[shark.x][shark.y] = 0;
            Fish f = copyFishes.get(arr[nx][ny] - 1);
            Shark newShark = new Shark(f.x, f.y, f.dir, shark.max + f.num);
            f.isAlive = false;
            copyArr[f.x][f.y] = -1;

            solve(copyArr, newShark, copyFishes);
        }
    }

    // 물고기 전체 이동
    // 이동가능한 칸은 빈칸 과 다른 물고기가 있는 칸
    // 45도 반시계방향으로 회전하면서 스캔
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int nDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nDir];
            int ny = fish.y + dy[nDir];

            if (OOB(nx, ny) || arr[nx][ny] <= -1) continue;

            arr[fish.x][fish.y] = 0;

            if (arr[nx][ny] == 0) {
                fish.x = nx;
                fish.y = ny;
            } else {
                Fish tmp = fishes.get(arr[nx][ny] - 1);
                tmp.x = fish.x;
                tmp.y = fish.y;
                arr[fish.x][fish.y] = tmp.num;

                fish.x = nx;
                fish.y = ny;
            }

            arr[nx][ny] = fish.num;
            fish.dir = nDir;
            return;
        }
    }

    static int[][] copyMap(int[][] arr) {
        int[][] tmp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    static List<Fish> copyList(List<Fish> fishes) {
        List<Fish> tmp = new ArrayList<>();
        fishes.forEach(e -> tmp.add(new Fish(e.x, e.y, e.num, e.dir, e.isAlive)));
        return tmp;
    }

    static boolean OOB(int x, int y) {
        return (x < 0 || x >= 4 || y < 0 || y >= 4);
    }
}
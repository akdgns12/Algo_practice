package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239 {
    // 스도쿠 게임 / 골드 4 / 구현
    // 완성되지 않은 스도쿠 퍼즐을 완성하라
    // 숫자가 채워지지 않은 칸은 0
    // 한 칸을 기준으로 가로, 세로, 3x3 범위에서 사용된 숫자를 제외한 나머지 숫자들을
    // 넣어보며 백트래킹을 이용해 해결

    /*. 1. 입력 받기
        2. 입력 받으면서 0인 칸은 list에 따로 저장해주기
        3. level이 go 함수가 재귀호출 될 때마다 1씩 증가하는데 list의 사이즈와 같아질 때까지 계속해서 go 함수 재귀호출. 같아지면 print 함수를 호출한다. 1부터 차례로 빈칸에 넣는 것이기 때문에 '사전식으로 앞서는 것을 출력'하라는 조건에도 맞게된다. 출력 후 프로그램 종료.
        4. 0인칸 하나씩 받아오기
        5. 1~9까지의 숫자 중 이미 쓰인 숫자 체크할 배열 생성
        6. 가로, 세로, 3x3 범위 탐색하며 1~9까지 쓰인 숫자 true 처리해주기
        7. 1~9까지 쓰이지 않은 숫자를 빈칸에 넣은 뒤 level 1 증가시켜 go 함수 재귀호출. 만약 1~9 중 쓸 숫자가 없으면 return 되어 그 전단계 칸으로 돌아와 다음 쓰이지 않은 숫자를 빈칸에 넣는 식으로 진행된다.
        8. 3에서 모든 빈칸이 채워져 print 함수가 호출되면 전체 판을 출력한다.
       */
    static int[][] map = new int[9][9];
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j) - '0';
                // 아직 채워지지 않은 칸 list에 넣어주기
                if(map[i][j] == 0) list.add(new Node(i, j));
            }
        }

        go(0);

        System.out.println();
    }

    // 3. level이 go함수가 재귀호출 될 때마다 1씩 증가하는데 list의 사이즈와
    // 같아질때까지 계속해서 go함수 재귀호출. 같아지면 print호출
    // 1부터 차례로 빈칸에 넣는 것이기 때문에 '사전식으로 앞서는 것을 출력'하라는 조건에도 맞다
    static void go(int level){
        // 3
        if (list.size() == level) {
            print();
            System.exit(0);
        }

        // 4. 0인칸 하나씩 받아오기
        int x = list.get(level).x;
        int y = list.get(level).y;

        // 1~9까지 이미 쓰인 숫자 체크할 배열
        boolean[] check = new boolean[10];

        // 가로, 세로 줄 에 쓰인 1~9까지의 숫자 체크해주고
        // 3x3범위 내에서도 이미쓰인 숫자 체크해준다.

        for (int i = 0; i < 9; i++) { // 가로
            if (map[x][i] != 0) {
                check[map[x][i]] = true;
            }
        }

        for (int i = 0; i < 9; i++) { // 세로
            if (map[i][y] != 0) {
                check[map[i][y]] = true;
            }
        }

        int startX = (x/3) * 3;
        int startY = (y/3) * 3;
        for (int i = startX; i < startX + 3; i++) { // 네모
            for (int j = startY; j < startY + 3; j++) {
                if (map[i][j] != 0) {
                    check[map[i][j]] = true;
                }
            }
        }

        for (int i = 1; i < 10; i++) {
            if (!check[i]) {
                map[x][y] = i;
                go(level + 1);
                map[x][y] = 0;
            }
        }
    }

    static void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

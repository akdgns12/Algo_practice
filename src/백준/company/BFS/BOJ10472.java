package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10472 {
    // 십자뒤집기 / 실버1 / BFS (아이디어가 중요한 문제)
    // 격자의 최종 상태가 주어질 때, 초기 상태에서 목표 상태에 도달하기 위한 최소 조작 횟수 구하는 문제
    // BFS를 이용해 격자의 모든 가능한 상태를 순회해준다.
    // 다만 중복된 상태 순회를 피하기 위해 보드가 검은색으로 칠해져 있을 경우 1로, 그렇지 않을 경우 0으로 변환하여
    // 이진수를 생성한 다음, 중복된 상태를 검증한다.
    static int p;
    // 3x3 격자를 0부터 차례대로 8까지 생각했을 때
    // 0이 뒤집어지면 -> 1,3도 뒤집어져야함
    // 1이 뒤집어지면 -> 0,2,4도 뒤집어져야함
    static int[][] direction = {
            {0, 1, 3}, {0, 1, 2, 4}, {1, 2, 5}, {0, 3, 4, 6}
            , {1, 3, 4, 5, 7}, {2, 4, 5, 8}, {3, 6, 7}, {4, 6, 7, 8}, {5, 7, 8}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        p = Integer.parseInt(br.readLine());

        // 초기 격자로 주어지는 격자는 모든칸이 흰색인 상태

        while (p-- > 0) {
            String str = "";
            // 보드가 검은색이면 1, 흰색이면 0
            for (int i = 0; i < 3; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < 3; j++) {
                    char c = tmp.charAt(j);
                    if (c == '*') {
                        str += "1";
                    } else {
                        str += "0";
                    }
                }
            }

            if (str.equals("000000000")) {
                System.out.println(0);
            } else {
                System.out.println(bfs(str));
            }
        }
    }

    static int bfs(String str) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();

        q.offer("000000000");
        set.add("000000000");
        int time = 1;

        while (!q.isEmpty()){
            int size = q.size();

            while (size-- > 0) {
                String now = q.poll();

                for (int i = 0; i < 9; i++) {
                    String next = now;
                    for (int j = 0; j < direction[i].length; j++) {
                        if (next.charAt(direction[i][j]) == '1') {
                            next = next.substring(0, direction[i][j]) + '0' + next.substring(direction[i][j] + 1, 9);
                        } else {
                            next = next.substring(0, direction[i][j]) + '1' + next.substring(direction[i][j] + 1, 9);
                        }
                    }

                    if(set.contains(next)) continue;

                    if(str.equals(next)) return time;

                    q.offer(next);
                    set.add(next);
                }
            }

            time++;
        }

        return time;
    }
}




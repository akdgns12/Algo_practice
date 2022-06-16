package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 십자뒤집기 {
    static int[][] direction = {
            {0, 1, 3}, {0, 1, 2, 4}, {1, 2, 5}, {0, 3, 4, 6}
            , {1, 3, 4, 5, 7}, {2, 4, 5, 8}, {3, 6, 7}, {4, 6, 7, 8}, {5, 7, 8}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String str = "";

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

            bfs(str);
        }
    }

    static void bfs(String str) {
        Queue<Node> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        q.offer(new Node("000000000", 0));
        visited.add("000000000");

        while (!q.isEmpty()){
            Node now = q.poll();

            if (str.equals(now.str)) {
                System.out.println(now.time);
                return;
            }

            for (int i = 0; i < 9; i++) {
                String next = str;

                for (int j = 0; j < direction[i].length; j++) {
                    if (next.charAt(direction[i][j]) == '1') {
                        next = next.substring(0, direction[i][j]) + '0' + next.substring(direction[i][j] + 1, 9);
                    } else {
                        next = next.substring(0, direction[i][j]) + '1' + next.substring(direction[i][j] + 1, 9);
                    }
                }

                if(visited.contains(next)) continue;

                q.offer(new Node(next, now.time + 1));
                visited.add(next);
            }
        }

        return;
    }

    static class Node{
        String str;
        int time;

        public Node(String str, int time) {
            this.str = str;
            this.time = time;
        }
    }
}

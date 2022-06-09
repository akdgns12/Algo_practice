package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4연산 {
    static long s, t;
    static char[] op = {'*', '+', '-', '/'};
    static HashSet<Long> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        if(s == t){
            System.out.println(0);
            return;
        }

        bfs();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s, ""));
        visited.add(s);

        while(!q.isEmpty()){
            Node o = q.poll();

            if (o.n == t) {
                System.out.println(o.result);
                return;
            }

            // 사전 순 연산 돌리기
            for (int i = 0; i < 4; i++) {
                long num = calculate(i, o.n); // 연산 결과값

                if(num <= 0) continue;
                if(visited.contains(num)) continue;

                visited.add(num);
                q.offer(new Node(num, o.result + op[i]));
            }
        }

        System.out.println(-1);
    }

    static long calculate(int op, long n) {
        if (op == 0) {
            n *= n;
        } else if (op == 1) {
            n += n;
        } else if (op == 2) {
            n -= n;
        } else {
            n /= n;
        }

        return n;
    }

    static class Node{
        long n;
        String result;

        public Node(long n, String result) {
            this.n = n;
            this.result = result;
        }
    }
}

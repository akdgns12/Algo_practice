package 백준.company.BFS;

import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;

import java.util.*;

public class BOJ14395 {
    // 4연산 / 골드5 / BFS
    /*
        s = s + s; (출력: +)
        s = s - s; (출력: -)
        s = s * s; (출력: *)
        s = s / s; (출력: /) (s가 0이 아닐때만 사용 가능)
     */
    static long s, t;
    static char[] op = {'*', '+', '-', '/'};
    // 목표는 s라는 정수가 t로 되기까지 최소 횟수를 구하는 것으로
    // 연산 중 같은 수가 중복되는 case를 제거해 시간초과 방지를 위해 set 사용(방문처리를 set으로)
    static HashSet<Long> visited = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        if (s == t) {
            System.out.println(0);
            return;
        }

        bfs();
    }

    static void bfs() {
        Queue<Operation> q = new LinkedList<>();
        q.offer(new Operation(s, ""));
        visited.add(s);

        while (!q.isEmpty()) {
            Operation o = q.poll();

            if(o.n == t){
                System.out.println(o.result);
                return;
            }

            // 사전 순 연산 돌리기
            for (int i = 0; i < 4; i++) {
                long num = calculate(i, o.n); // 연산 결과값

                if(num <= 0) continue; // 음수면 건너뛰기
                if(visited.contains(num)) continue;

                visited.add(num);
                q.offer(new Operation(num, o.result + op[i]));
            }
        }

        System.out.println(-1); // 바꿀 수 없는 경우 -1 출력
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

    static class Operation {
        long n;
        String result;

        public Operation(long n, String result) {
            this.n = n;
            this.result = result;
        }
    }
}

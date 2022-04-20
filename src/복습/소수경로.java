package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소수경로 {
    static int T;
    static boolean[] visited;
    static boolean[] isNotPrime = new boolean[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        checkPrime();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visited = new boolean[10001];
            bfs(a,b);
        }
    }

    static void bfs(int start, int target) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visited[start] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.num ==  target){
                System.out.println(now.cnt);
                return;
            }

            int[] pNum = {now.num / 1000, (now.num / 100) % 10, (now.num / 10) % 10, now.num % 10};

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if(i == 0 && j == 0) continue;

                    int tmp = pNum[i];
                    pNum[i] = j;
                    int next = changePassword(pNum);
                    pNum[i] = tmp;

                    if (!visited[next] && !isNotPrime[next]) {
                        q.offer(new Node(next, now.cnt + 1));
                        visited[next] = true;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    static int changePassword(int[] pNum) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            num += pNum[i] * (Math.pow(10, 3 - i));
        }
        return num;
    }

    static void checkPrime(){
        for (int i = 2; i < 10000; i++) {
            if(!isNotPrime[i]){ // i가 소수일때
                for (int j = i * i; j < 10000; j += i) {
                    isNotPrime[j] = true; // i의 배수인 j들은 모두 소수가 아님
                }
            }
        }
    }

    static class Node{
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

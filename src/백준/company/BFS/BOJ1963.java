package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1963 {
    // 소수 경로 / 골드4 / BFS
    // 네자리 소수 a가 한자리씩 바꿀 때 b로 바꾸는 과정의 최소 횟수
    // 0~9의 숫자를 가지는 네 개의 정점을 특정 조건(소수)을 만족하는 경우에만 탐색하여 원하는 목적지로 가는 최단경로! 를 구하는 문제
    /*
        생각
        1. 주어진 범위 이하의 수들 중 소수인 것들을 미리 판별해놓고
        2. 입력받은 네자리수를 한자리씩 0~9범위 탐색 b와 같아질때까지(bfs탐색)
     */
    static int T;
    static boolean[] visited;
    static boolean[] isNotPrime = new boolean[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        // 4자리까지의 소수 미리 구한다.
        // 이때, true는 소수가 아닌 수
        checkPrime();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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

            if (now.num == target) {
                System.out.println(now.cnt);
                return;
            }

            int[] pNum = {now.num / 1000, (now.num / 100) % 10, (now.num / 10) % 10, now.num % 10};

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    // 맨 앞자리인 천의자리가 0이면 1000을 초과하므로 pass
                    if(i == 0 && j == 0) continue;

                    // 비밀변호 변경, pNum[i]번째 자리를 j로 바꿔서 그 자리수에 맞게 넣어줘야 함
                    int tmp = pNum[i];
                    pNum[i] = j;
                    int next = changePassword(pNum);
                    // next가 하위 조건에 맞지 않을 경우 수 변환을 하지 말아야 하기 때문에 다시 원래대로 바꿔줌
                    pNum[i] = tmp;

                    // 디음 숫자를 방문하지 않았고 없고, 소수이면 큐에 추가
                    if (!visited[next] && !isNotPrime[next]) {
                        q.offer(new Node(next, now.cnt + 1));
                        visited[next] = true;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    // 자리수에 맞게 다시 배치해주는 함수
    static int changePassword(int[] pNum) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            num += pNum[i] * (Math.pow(10, 3 - i)); // 10의 (3-i)승 자리로 설정
        }
        return num;
    }

    static void checkPrime(){
        for (int i = 2; i < 10000; i++) {
            if(!isNotPrime[i]){ // i가 소수일경우
                for (int j = i * i; j < 10000; j += i) {
                    isNotPrime[j] = true; // 소수인 i의 배수들은 모두 소수가 아님
                }
            }
        }
    }

    static class Node{
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

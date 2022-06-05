package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16397 {
    // 탈출 / 골드 4 / bfs
    /*
        버튼 A를 누르면 N이 1 증가한다.
        버튼 B를 누르면 N에 2가 곱해진 뒤, 0이 아닌 가장 높은 자릿수의 숫자가 1 줄어든다. 예를 들어 123→146으로, 5→0으로, 3→5로 변한다. 단, N이 0이면 버튼 B를 눌러도 수가 변하지 않는다.
        LED가 다섯 자리까지밖에 없기 때문에 N이 99,999를 넘어가는 순간 탈출에 실패하게 된다.
        버튼 B를 눌러 N에 2를 곱한 순간 수가 99,999를 넘어간다면, 높은 자릿수의 수를 1 낮췄을때 99,999를 넘지 않는다고 해도 탈출에 실패하게 된다.
     */
    // 탈출에 필요한 최소 버튼 횟수
    static int N,T,G;
    static int[] click;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); // 버튼 클릭 최대 횟수
        G = Integer.parseInt(st.nextToken()); // 탈출을 위해 똑같이 만들어야 하는 수

        click = new int[100000];
        visited = new boolean[100000];

        bfs(N);
    }

    static void bfs(int n) {
        Queue<Node> q = new LinkedList<>();
        visited[n] = true;
        q.offer(new Node(n, 0));

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.t > T) break;
            if (node.n == G) {
                System.out.println(node.t);
                return;
            }

            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    int nx = node.n + 1;
                    if (nx > 99999 || visited[nx]) continue;
                    visited[nx] = true;
                    q.offer(new Node(nx, node.t + 1));
                } else {
                    int nx = node.n * 2;
                    if(nx > 99999 || visited[nx]) continue;

                    int idx = -1;

                    // 문제에서 정해진 자릿수 한계만큼 반복을 돌려서
                    for (int j = 1; j < 7; j++) {
                        // nx의 0이 아닌 가장 높은 자릿수는
                        // nx를 나눴을 때 nx가 그대로 나머지가 되는 10의 j승 -1이니까
                        if(nx % (int)Math.pow(10, j) == nx){
                            idx = j; // 해당 j를 idx에 기억해놓고
                            break;
                        }
                    }

                    if (idx != -1) {
                        nx -= (int) Math.pow(10, idx - 1); // nx에서 10의 idx-1승을 뺴준다 = nx에서 가장 높은 자릿수 -1하는 것과 동일
                        if(visited[nx]) continue;
                        visited[nx] = true;
                        q.offer(new Node(nx, node.t + 1));
                    }
                }
            }
        }

        System.out.println("ANG");
    }

    static class Node{
        int n;
        int t;

        public Node(int n, int t) {
            this.n = n;
            this.t = t;
        }
    }
}

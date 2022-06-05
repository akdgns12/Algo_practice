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
    static int n,t,g;
    static int[] click;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken()); // 버튼 클릭 최대 횟수
        g = Integer.parseInt(st.nextToken()); // 탈출을 위해 똑같이 만들어야 하는 수

        click = new int[100000];
        visited = new boolean[100000];

        int ans = bfs(n);

        System.out.println(ans != -1 ? ans : "ANG");
    }

    static int bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = true;
        int result = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == g) {
                result = click[cur];
            }
            // 버튼 클릭횟수가 t 초과하면 pass
            if(click[cur] + 1 > t) continue;

            int a = cur + 1;
            if(a < 100000 && !visited[a]){
                visited[a] = true;
                click[a] = click[cur] +  1;
                q.offer(a);
            }

            int b = cur * 2;

            System.out.println(b);

            System.out.println();

            if(b > 0 && b < 100000){
                b = B(b);
                System.out.println(b);
                if(!visited[b]){
                    visited[b] = true;
                    click[b] = click[cur] + 1;
                    q.offer(b);
                }
            }
        }

        return result;
    }

    // 0이 아닌 가장 높은 자리수를 1줄인다
    static int B(int x){
        int result = 0, idx = 10;
        int[] arr = new int[5];

        for (int i=0, k=10000; i<5; i++, k /= 10){
            arr[i] = (x / k) % 10;

            if(arr[i] != 0)
                idx = Math.min(idx, i);
        }

        arr[idx]--;

        for (int i = 0, k = 10000; i < 5; i++, k /= 10) {
            result += arr[i] * k;
        }

        return result;
    }
}

package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {
    // 숨바꼭질2 / 골드5 / BFS
    // 수빈이 초기위치 N, 동생 초기 위치 K, 걷거나 순간이동해서 가장빠르게 동생위치로 이동할 수 있는 시간
    // 그리고 그 방법의 가지수
    // 원래 bfs를 구현하게 되면 보통 최적화를 위해서 큐에 넣고 바로 방문처리를 한다.
    /*
        하지만 그것은 최적화, 즉 넣었다 뺐다 반복하지 않도록 하는 것
        이 문제는 가능한 모든 개수를 세야하기 때문에 그렇게 풀 수 없다.
        반례)
        1 4의 경우
        (1+1) x 2
        (1x2) x 2 이렇게 두가지가 가능한데 첫번재 경우에서 방문처리가 되버리면
        한가지만 탐색하게 된다.
         1. Node 구조체를 하나 만들어서 큐를 만들고 시작 값을 큐에 넣어주고 방문처리 해준다.
         2. 큐에서 값을 하나 꺼낸다. 그리고 방문처리를 한다.
            큐에 넣을 때 방문처리 하지 않고 꺼낼 때 방문처리를 한다.
            큐에 넣을 때 방문처리 하게 되면 가능한 모든 경우를 따질 수 없어 개수를 셀 수 없다
          3. 꺼낸 값이 동생의 위치와 같은 경우 min값과 시간 비교
             min보다 꺼낸 time값이 작다면 min값을 update해주고
             min값과 time값이 같다면 같은 시간으로 갈 수 있는 다른 경우이므로 cnt 값 증가
          4. 이동할 수 있는 세가지 경우 모두에 대해서 방문하지 않았고 이동이 가능한 경우 큐에 넣고 방문할 수 잇도록 한다.
          5. 큐가 빌때까지 반복
     */
    static int n, k;
    static boolean[] visited;
    static int min,cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈이 초기위치
        k = Integer.parseInt(st.nextToken()); // 동생 위치

        min = Integer.MAX_VALUE;
        cnt = 1;

        // n이 k보다 큰 경우에는 -1씩 n-k만큼 뒤로 가야 한다.
        // 이 방법 한가지뿐
        if(n > k){
            System.out.println(n - k);
            System.out.println(1);
        }else{
            bfs(n, k);
            System.out.println(min);
            System.out.println(cnt);
        }
    }

    static void bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[100001];
        int time = 0;

        visited[start] = true;
        q.offer(new Node(start, time));

        while(!q.isEmpty()){
            Node node = q.poll();

            int now = node.a;
            int curTime = node.time;
            visited[now] = true; // 큐에서 꺼내고 방문처리 해준다

            if(now == k){
                if(min > curTime)
                    min = curTime;
                else if(min == curTime)
                    cnt++;
                continue;
            }

            int nx1 = now - 1;
            int nx2 = now + 1;
            int nx3 = now * 2;

            if(nx1 >= 0 && !visited[nx1])
                q.offer(new Node(nx1, curTime + 1));
            if(nx2 <= 100000 && !visited[nx2])
                q.offer(new Node(nx2, curTime + 1));
            if(nx3 <= 100000 && !visited[nx3])
                q.offer(new Node(nx3, curTime + 1));
        }

    }

    static class Node{
        int a;
        int time;

        public Node(int a, int time) {
            this.a = a;
            this.time = time;
        }
    }
}

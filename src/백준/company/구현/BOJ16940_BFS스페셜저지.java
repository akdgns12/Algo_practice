package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16940_BFS스페셜저지 {
    static int N;
    static List<ArrayList<Integer>> list;
    static int[] parent, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        st = new StringTokenizer(br.readLine());
        parent = new int[N+1];
        answer = new int[N+1];
        for(int i=0 ;i<N; i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }

        bfs(1);
        System.out.println(1);
    }

    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        boolean[] visited = new boolean[N+1];
        visited[x] = true;

        while(!q.isEmpty()){
            int now = q.poll();

        }
    }
}

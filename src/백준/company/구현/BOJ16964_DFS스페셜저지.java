package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16964_DFS스페셜저지 {
    static int N;
    static List<ArrayList<Integer>> list;
    static boolean[] visited;
    static int[] answer, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.get(start).add(end);
            list.get(end).add(start);
        }

        st = new StringTokenizer(br.readLine());
        answer = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        for(int i=0; i<N; i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0);
        System.out.println(1);
    }

    static void dfs(int x, int idx){
        visited[x] = true;

        int cnt = 0;
        for(int next : list.get(x)){
            if(!visited[next]){
                visited[next] = true;
                parent[next] = x;
                cnt++;
            }
        }

        idx++;
        for(int i=0; i<cnt; i++){
            int next = answer[idx];
            if(parent[next] != x){
                System.out.println(0);
                System.exit(0);
            }
            dfs(next, idx);
        }
    }
}

package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이분그래프 {
    static int v,e;
    static int[] color;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        while(K --> 0){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            list = new ArrayList[v+1];
            for (int i = 1; i <= v; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            color = new int[v+1];
            bfs(1);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if(color[i] == 0){
                color[i] = 1;
                q.offer(i);
            }

            while(!q.isEmpty()){
                int now = q.poll();

                for (int next : list[now]){
                    if (color[next] == color[now]) {
                        System.out.println("NO");
                        return;
                    }

                    if(color[next] == 0){
                        q.offer(next);

                        if(color[now] == 1){
                            color[next] = 2;
                        }else{
                            color[next] = 1;
                        }
                    }
                }
            }
        }
        System.out.println("YES");
    }
}

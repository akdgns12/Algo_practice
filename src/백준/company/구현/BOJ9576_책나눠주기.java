package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9576_책나눠주기 {
    static int N, M;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int t=0; t<tc; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list.add(new Node(a,b));
            }

            Collections.sort(list);

            int ans = solve();
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int solve(){
        boolean[] visited = new boolean[N+1];

        int cnt = 0;
        for(int i=0; i<M; i++){
            Node now = list.get(i);
            int start = now.start;
            int end = now.end;

            for(int j=end; j>=start; j--){
                if(!visited[j]){
                    visited[j] = true;
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }

    static class Node implements Comparable<Node>{
        int start;
        int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o){
            if(o.end == this.end) return o.start - this.start;
            else return o.end - this.end;
        }
    }
}

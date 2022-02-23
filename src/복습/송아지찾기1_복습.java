package 복습;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 송아지찾기1_복습 {
    static int answer = 0;
    static int[] jump = {1, -1, 5};
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();
    
    static int bfs(int s, int e){
        visited = new boolean[100001];
        visited[s] = true;
        q.offer(s);
        int level = 0;
        while(!q.isEmpty()){
            int len = q.size();
            for (int i=0; i<len; i++){
                int x = q.poll();
                if(x == e) return level;
                for (int j=0; j<3; j++){
                    int nx = x + jump[j];
                    if(nx >= 1 && nx <= 10000 && !visited[nx]){
                        visited[nx] = true;
                        q.offer(nx);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        송아지찾기1_복습 T = new 송아지찾기1_복습();
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();

        System.out.println(T.bfs(s,e));
    }
}

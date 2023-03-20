package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12886_돌그룹 {
    static int A, B, C;
    static Queue<Node> q;
    static boolean[][][] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[3];
        visited = new boolean[501][501][501];
        q = new LinkedList<>();
        for(int i=0; i<3; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        q.offer(new Node(arr[0], arr[1], arr[2]));

        int answer = bfs();
        System.out.println(answer);
    }

    static int bfs(){
        while(!q.isEmpty()) {
            Node now = q.poll();

            int[] arr2 = new int[3];
            arr2[0] = now.x;
            arr2[1] = now.y;
            arr2[2] = now.z;
//            Arrays.sort(arr2);

            if(check(arr2[0], arr2[1], arr2[2])) return 1;

            for(int i=0; i<3; i++){
                int nx = now.x + arr2[i];
                int ny = now.y - arr2[i];
                int nz = now.z;

                System.out.println(nx + " " + ny + " " + nz);
                if(!isRange(nx, ny, nz)) continue;
                if(visited[nx][ny][nz]) continue;
                if(ny - nx < 0) continue;

                q.offer(new Node(nx, ny, nz));
                visited[nx][ny][nz] = true;
            }
        }

        return 0;
    }

    static boolean isRange(int x, int y, int z){
        if(x < 1 || y < 1 || z < 1 || x > 500 || y > 500 || z > 500) return false;
        else return true;
    }

    static class Node{
        int x, y, z;
        public Node(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static boolean check(int x, int y, int z){
        if(x == y && y == z) return true;
        else return false;
    }
}
/*
    10 15 35
    1. 20 5 35
    2. 10 15 35
    3. 20 5 35 -> 한 번 왔던 조합 visited 3차원
    3. 30 20 10
    4.
 */

package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현 {
    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(a == 0){
                union(x,y);
            }else{
                isSameParent(x,y);
            }
        }
    }

    // x의 부모를 찾는 연산
    static int find(int x){
        if(x == parent[x]){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    // y의 부모를 x의 부모로 치환하는 연산(x > y인 경우 반대)
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    static void isSameParent(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}

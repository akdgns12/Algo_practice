package 코테대비.Greedy;

import java.util.Scanner;

public class Union_Find_내방식 {
    static int n, m;
    static int[] parent;

    // x가 어떤 집합에 포함되어 있는지 찾는 연산
    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    // x,y가 포함되어 있는 집합을 합치는 연산
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        parent = new int[n+1];
        for (int i=1; i<=n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x,y);
        }
        int x = sc.nextInt();
        int y = sc.nextInt();
        int fx = find(x);
        int fy = find(y);
        if(fx == fy) System.out.println("YES");
        else System.out.println("NO");
    }
}

package 코테대비.Greedy;

import java.util.Scanner;

public class 친구인가_Union_Find {
    static int n,m;
    static int[] unf; // 집합을 표현할 배열 (인덱스 번호 : 학생번호, 배열 값 : 집합의 번호)

    //node가 속한 집합 번호를 return
    static int find(int node){
        if(node == unf[node]) return node;
        else return unf[node] = find(unf[node]);
    }

    static void union(int x, int y){
        int fx = find(x);
        int fy = find(y);
        if(fx != fy) unf[fx] = fy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        unf = new int[n+1];
        for (int i=1; i<=n; i++) unf[i] = i;
        for (int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x,y);
        }
        int x = sc.nextInt();
        int y = sc.nextInt();
        int fa = find(x);
        int fb = find(y);
        if(fa == fb) System.out.println("YES");
        else System.out.println("NO");
    }
}

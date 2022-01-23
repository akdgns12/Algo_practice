package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4256 {
    // 트리 / 골드3 / 분할정복_시뮬 /
    /*
        전위,중위 순회 결과를 가지고 후위 순회한 결과를 구하라
        로직
        전위 순회를 통해 root를 파악 후 중위 순회 왼쪽에 있는지 오른쪽에 있는지 범위를 좁히며
        위치를 파악한다. 만약에 양쪽에 없으면 부모 노드로 올라가 탐색한다.
        1) root를 찾는다
        전위 순회 : 3 6 5 4 8 7 1 2
        중위 순회 : (5 6 8 4 3 1 2 7)

        2) 다음은 6, 중위 순회에서 왼쪽입니다.
        전위 순회 : 3 6 5 4 8 7 1 2
        중위 순회 : (5 6 8 4) 3 1 2 7

        3) 다음은 5 중위 순회에서 왼쪽입니다.
        전위 순회 : 3 6 5 4 8 7 1 2
        중위 순회 : (5) 6 8 4 3 1 2 7

        4) 다음은 4 중위 순회에서 5기준 좌우에 없으므로 출력 후 올라갑니다.
        전위 순회 : 3 6 5 4 8 7 1 2
        중위 순회 : (5 6 8 4) 3 1 2 7

        5) 6의 오른쪽에 있습니다.

        6) 계속 반복합니다.
    */
    static int[] pre, in;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        while(t --> 0){
            int n = Integer.parseInt(br.readLine()); // 노드의 개수

            pre = new int[n+1];
            in = new int[n+1];

            // 전위 순회
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                pre[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                in[i] = Integer.parseInt(st.nextToken());
            }

            traversal(0,0,n);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void traversal(int root, int s, int e){
        int rootIdx = pre[root];
        for(int i=s; i<e; i++){
            if(in[i] == rootIdx){ // 중위순회 데이터에서 루트 노드의 위치를 찾는다
                traversal(root+1, s, i);
                traversal(root+i+1-s, i+1, e);
                sb.append(rootIdx + " ");
            }
        }
    }
}

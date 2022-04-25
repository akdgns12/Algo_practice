package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2660 {
    // 회장뽑기 / 골드5 / BFS
    /*
        생각
        회장선출, 어느 회원이 다른 모든 회원과 친구이면 이 회원의 점수는 1점. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말함
        어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말함.
        4점, 5점도 이런 방법으로 정해짐. 각 회원의 점수를 정할 때 주의할점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이.
        회장은 회원들 중에서 가장 점수가 작은 사람이 됨. 회장의 점수와 될 수 있는 모든 사람을 리턴

        모든 정점에 대하여 모든 정점의 최단거리를 구하는 플로이드 와샬 알고리즘
        문제에서 어떤 사람이 다른 모든 사람과 친구이면 1점, 다른 모든 사람이 친구이거나 친구의 친구이면 2점.
        => A라는 정점에서 B라는 정점으로 1번만으로 이동할 수 있으면 1점, 2번 이동해야하면 2점.
        따라서 모든 정점에서 모든 정점 사이의 최단거리를 구하고, 특정 정점에서 다른 정점으로의 최단거리가 가장 큰 값이 친구 점수가 된다.
        이렇게 모든 정점의 친구 점수를 구하고 그 중 점수가 가장 낮은 정점들이 회장 후보.
     */
    static int n;
    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 회원의 수

        int[][] arr = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i != j){
                    arr[i][j] = INF;
                }
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1) break;

            arr[a][b] = arr[b][a] = 1; // 친구 관계는 양방향
        }

        // 플로이드 와샬 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(arr[i][j] > arr[i][k] + arr[k][j]){
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int readerScore = INF;

        int[] scoreArr = new int[n+1]; // 각 정점의 친구 점수 목록

        for (int i = 1; i <= n; i++) { // 정점 i에 대하여
            int score = 0;
            for (int j = 1; j <= n; j++) { // 친구사이인 모든 정점을 탐색해서
                if (arr[i][j] != INF) {
                    score = Math.max(score, arr[i][j]); // 가장 높은 점수를 찾는다(이것이 친구점수이기 때문에)
                }
            }

            scoreArr[i] = score; // 그 친구 점수를 친구점수 목록 배열에 저장
            readerScore = Math.min(readerScore, score); // 그렇게 구한 각 정점의 친구점수 중 가장 낮은 점수를 찾음(회장 후보)
        }

        StringBuilder title = new StringBuilder();
        title.append(readerScore + " "); // 회장후보의 친구점수

        int readerNum = 0;

        StringBuilder body = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if(readerScore == scoreArr[i]){ // 회장후보 점수와 같은 정점이면
                readerNum++; // 회장후보 수 카운팅
                body.append(i + " "); // 회장후보가 될 수 있는 정점
            }
        }

        title.append(readerNum);

        System.out.println(title.toString());
        System.out.println(body.toString());
    }
}

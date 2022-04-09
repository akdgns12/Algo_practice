package 백준.company.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9466 {
    // 텀프로젝트 / 골드3 / 유니온 파인드
    // 싸이클이 존재하는 경우만 팀, 팀에 속하지 못한 사람의 수를 출력
    // 싸이클을 형성하지 못하는 노드 갯수를 찾는 문제
    /*
        학생이 팀이 되는 case
        1. 자기 혼자 1인팀
        2. 서로서로 link되어있는 팀

        자기 혼자 있는팀은 그냥 본인이 본인을 찍은 경우, 서로서로 link 되어있다는 것은 예를들어 2 > 4 > 5 > 2 처럼 한명도
        안끊기고 원을 그리는 것처럼의 형태를 띄어야 하나의 팀을 이룰 수 있다. 하나라도 이탈자가 나오면 불가능하다

        그럼 loop를 어떻게 돌려야할까?
        boolean배열을 두개 만들어서 하나는 dfs에서 방문했는지를 검토, 하나는 cycle이 돌아가는 와중에 또 방문되었는가를 확인하는지 검토
        예를들어 2 > 6 > 4 > 1 의 cycle을 돈다고 치면
        start : 2
        2 > 6		//visited[2] = true;
        6 > 4		//visited[6] = true;
        4 > 1		//visited[4] = true;
        1 > 2		//if(visited[2] == true) finished[2] = true; count++;
        2 > 6		//if(visited[6] == true) finished[6] = true; count++;
        ...
     */
    static int t;
    static int n;
    static int[] arr;
    static boolean[] visited, finished; // 방문 여부 체크, 방문한 노드에서 싸이클을 이미 뽑아냈었는가 체크
    static int teamCnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());
            finished = new boolean[n+1];
            visited = new boolean[n+1];
            teamCnt = 0;

            arr = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if(!finished[i])
                    dfs(i);
            }
            System.out.println(n - teamCnt);

        }

    }

    static void dfs(int now){
        if(visited[now]){ // cycle에서 두번째 방문이라면 이미 순환구조의 팀에 포함되어 있음
            finished[now] = true;
            teamCnt++; // 팀에 포함되어 있으므로 teamCnt++;
        }
        else{ // 첫 방문
            visited[now] = true;
        }

        int next = arr[now];
        if(!finished[next]) { // cycle에 두번째 방문을 한 node는 또 dfs를 돌릴 필요없음
            dfs(next);
        }

        visited[now] = false;
        finished[now] = true; // 만약 cycle에 포함되지 않았다면 cycle경험만 충족하고 count는 올라가지 않으며 dfs가 종료됨
    }
}


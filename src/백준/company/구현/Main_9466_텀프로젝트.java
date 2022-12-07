package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9466
public class Main_9466_텀프로젝트 {
    static int N, result; // 학생의 수, 팀을 구성한 학생 수
    static int[] arr; // 선택된 학생들의 번호
    static boolean[] visited, finished;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수 T

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            visited = new boolean[N+1]; // 방문 체크
            finished = new boolean[N+1]; // 확인 완료

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            result = 0; // 팀을 구성한 학생 수
            for(int i=1; i<=N; i++) {
                if(finished[i]) continue; // 이미 확인 했으면 넘어가기
                dfs(i);
            }

            sb.append(N-result).append("\n");
        }//while

        System.out.print(sb);
    }//main

    private static void dfs(int cur) {
        if(finished[cur]) return; // 이미 확인했으면 돌아가

        if(visited[cur]) { // 이미 방문했었다면
            result++; // 팀 구한거니까 학생수 증가
            finished[cur] = true; // 팀 구성 완료
        }

        visited[cur] = true; // 방문 체크
        dfs(arr[cur]); // 다음 학생 지목
        visited[cur] = false; // 방문 해제

        finished[cur] = true; // 팀 구성 완료
    }//dfs


}//class

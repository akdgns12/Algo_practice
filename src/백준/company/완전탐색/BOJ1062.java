package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062 {
    // 가르침 / 골드 4 / 완탐
    // 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지
    // 남극언어의 모든 단어는 anta로 시작되고, tica로 끝남, 남극언어에 단어는 N개밖에 없다.
    // 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램
    /*
        K개의 알파벳 조합으로 단어를 구성할 수 있는지 묻는 문제이기에, "조합"
        완점탐색 + 백트래킹
     */
    static int answer = Integer.MIN_VALUE;
    static int n, k;
    static String[] words;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        visited = new boolean[26];

        // 필수단어보다 적다면 읽을 수 있는 단어 0
        if(k < 5){
            System.out.println(0);
            return;
        } else if (k == 26) { // 26개의 모든 문자라면 n개 모두 읽을 수 있음
            System.out.println(n);
            return;
        }

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            words[i] = word.substring(4, word.length() - 4); // 접두사, 접미사 제외
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int idx, int cnt) {
        if (cnt == k - 5) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean canRead = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if(!visited[words[i].charAt(j) - 'a']){ // 만약 words에 들어있는 i번째 단어의 j번째 글자가 방문처리 되어있지 않다면
                        canRead = false;
                        break;
                    }
                }
                if(canRead) count++;
            }
            answer = Math.max(count, answer);
            return;
        }

        // 필수단어 제외한 k-5개의 단어 뽑는 백트래킹
        for (int i = idx; i < 26; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1 , cnt + 1);
                visited[i] = false;
            }
        }
    }
}

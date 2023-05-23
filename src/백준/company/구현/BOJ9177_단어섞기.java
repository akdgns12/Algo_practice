package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9177_단어섞기 {
    static int N, Total;
    static int[] alpha1, alpha2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            alpha1 = new int[st.nextToken().length()];
            alpha2 = new int[st.nextToken().length()];
            String last = st.nextToken();
            Total = last.length();

            if(check(last)) sb.append("Data set ").append(i+1 + ": yes");
            else sb.append("Data set ").append(i+1 + ": no");
        }

        System.out.println(sb.toString());
    }

    /*
        1. 마지막 단어를 origin으로
        2. origin 두 단어를 마지막 단어로 조합하는 경우에서 가지치기?
     */
    static boolean check(String str){
        Queue<Character> q = new LinkedList<>();
        for(int i=0; i<Total; i++) q.offer(str.charAt(i));

        while(!q.isEmpty()){
            char now = q.poll();

        }

        return true;
    }
}

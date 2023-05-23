package 백준.company.구현;

import sun.awt.image.ImageWatched;
import 코테대비.DP.가장높은탑쌓기_LIS응용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9177_단어섞어버려 {
    static int N, Total;
    static Queue<Character> q;
    static int[] alpha1, alpha2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            for(int j=0; j< str1.length(); j++) q.offer(str1.charAt(j));
            String str2 = st.nextToken();
            for(int k=0; k< str1.length(); k++) q.offer(str1.charAt(k));
            String last = st.nextToken();

            q = new LinkedList<>();
            if(check(str1, str2, last)) sb.append("Data set ").append(i+1 + ": yes");
            else sb.append("Data set ").append(i+1 + ": no");
        }

        System.out.println(sb.toString());
    }

    /*
        1. 마지막 단어를 origin으로
        2. origin 두 단어를 마지막 단어로 조합하는 경우에서 가지치기?
     */
    static boolean check(String str1, String str2, String last){
        while(!q.isEmpty()){
            char now = q.poll();


        }
        return true;
    }
}

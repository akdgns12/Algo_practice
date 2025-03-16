package 쩜튜브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_N과M11 {
    static int[] selected;
    static HashSet<String> set = new HashSet<String>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        selected = new int[M];
        dfs(arr, N, M, 0);
        Arrays.sort(selected);
        System.out.println(sb.toString());
    }

    static void dfs(int[] arr, int N, int M, int cnt){
        if(cnt == M) {
            StringBuilder temp = new StringBuilder();
            for(int i=0; i<M; i++){
                temp.append(selected[i]).append(" ");
            }
            temp.append("\n");
            String str = temp.toString();
            if(!set.contains(str)){
                set.add(str);
                sb.append(temp);
            }
            return;
        }

        for(int i=0; i<N; i++){
            selected[cnt] = arr[i];
            dfs(arr, N, M, cnt+1);
        }
    }
}

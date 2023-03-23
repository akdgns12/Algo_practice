package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863_스카이라인쉬운거 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[50001];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = y;
        }

        int cnt = 0;
        // 고도가 낮으면 건물 추가
        for(int i=0; i<=n; i++){
            while(!stack.isEmpty()){
                if(stack.peek() > arr[i]){
                    cnt++;
                    stack.pop();
                }else break;
            }

            if(!stack.isEmpty() && stack.peek() == arr[i]) continue;

            stack.push(arr[i]);
        }

        System.out.println(cnt);
    }
}

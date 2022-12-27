package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ5430_AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
            // R 수의 순서 뒤집기, D 첫번째 함수 버리기
            System.out.println(solve(command, n, arr));
        }
    }

    // 미리 command에 맞게 계산을 한 뒤 최종값을 출력만 하는 방식으로
    static String solve(String command, int n, String[] arr){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0; i<n; i++) dq.offer(Integer.parseInt(arr[i])); // 주어진 수 일단 넣고

        boolean dir = true; // true >, false <

        for(int i=0; i<command.length(); i++){
            char cmd = command.charAt(i);
            if(cmd == 'R'){ // 꺼낼 방향 바꿔주기
                dir = !dir;
            }else{ // D일때
                if(dq.isEmpty()){ // 비었는데 뒤집는다면
                    return "error";
                }
                // 현재 방향에 따라 꺼내주기
                if(dir) dq.pollFirst();
                if(!dir) dq.pollLast();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!dq.isEmpty()){
            if(dir){
                sb.append(dq.pollFirst());
            }else{
                sb.append(dq.pollLast());
            }
            if(!dq.isEmpty()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}

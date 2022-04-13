package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5430 {
    // AC / 골드5 / 구현
    // 생각 생각.....
    //
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().replace("[", "").replace("]","").split (",");
            sb.append(solve(command, n, arr));
            if(T != 0) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 'D'연산은 flag 방향 정보를 통해 앞 혹은 뒤에 있는 데이터를 제거해주면 된다.
    // stack과 Queue기능을 둘 다 가지고 있는 Deque자료구조 사용
    static String solve(String command, int n, String[] arr) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dq.offer(Integer.parseInt(arr[i]));
        }
        boolean dir = true; // true > , false <

        for (int i = 0; i < command.length(); i++) {
            char type = command.charAt(i);
            if(type == 'R') {
                dir = !dir;
            }
            else{
                if(dq.isEmpty()){
                    return "error";
                }
                if(dir){ // >
                    dq.pollFirst();
                }else{ // <
                    dq.pollLast();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!dq.isEmpty()){
            if (dir) {
                sb.append(dq.poll());
            }else{
                sb.append(dq.pollLast());
            }
            if(!dq.isEmpty()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}

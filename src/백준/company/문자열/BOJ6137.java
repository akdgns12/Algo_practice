package 백준.company.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6137 {
    // 문자열 생성 / 골4 / 그리디, 문자열
    /*
        문자열 S의 가장 앞의 문자 하나를 문자열 T에 추가
        문자열 S의 가장 뒤의 문자 하나를 문자열 T에 추가
        위 규칙으로 만들어진 T들 중 사전순으로 가장 앞서는 문자열 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().charAt(0);
        }

        int lt = 0;
        int rt = n-1;
        char[] t = new char[n];

        int idx = 0;
        while (lt <= rt) {
            if(arr[lt] < arr[rt]){ // 사전순으로 더 앞서는 문자 넣어준다
                t[idx++] = arr[lt++];
            } else if (arr[lt] > arr[rt]) {
                t[idx++] = arr[rt--];
            } else { // 사전순으로 같은 위치면
                // 더 깊게 파고들어 사전순으로 앞서는 문자 추가해준다.
                // 만약 더 파고들었는데, 문자가 양쪽 모두 같아서 결국 추가가 안됐다면 어느 한쪽이든 넣어준다.
                int l2 = lt, r2 = rt;
                boolean flag = false;
                while (l2 <= r2) {
                    if(arr[l2] < arr[r2]){
                        flag = true;
                        t[idx++] = arr[lt++];
                        break;
                    }else if(arr[l2] > arr[r2]){
                        flag = true;
                        t[idx++] = arr[rt--];
                        break;
                    }else {
                        l2++;
                        r2--;
                    }
                }
                if(!flag){ // 만약 끝까지 파고들었는데 모두 같은 사전순이라면
                    t[idx++] = arr[lt++]; // 앞, 뒤 중 아무거나 넣어준다.
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(t[i]);
            if(i != 0 && (i+1) % 80 == 0)
                System.out.println();
        }
    }
}

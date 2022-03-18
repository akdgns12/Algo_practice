package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
    static int n, m;
    static int[] arr;//수열을 나타내는 배열
    static boolean[] isUsed; // 해당 수가 쓰였는지 여부를 판단할 boolean배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[10];
        isUsed = new boolean[10];

        dfs(0);
    }

    static void dfs(int k){ // 현재 k개까지 수를 택했음
        if(k == m){ // m개를 모두 택했으면
            for (int i=0; i<m; i++)
                System.out.print(arr[i] + " "); //arr에 기록해둔 수를 추력
            System.out.println();
            return;
        }
        else{
            for(int i=1; i<=n; i++){ // 1부터 n까지의 수에 대해
                if(!isUsed[i]){ // 아직 i가 사용되지 않았다면
                    arr[k] = i; // k번째 수를 i로 정함
                    isUsed[i] = true; // i를 사용되었다고 표시
                    dfs(k+1); // 다음 수를 정하러 한 단계 더들어감
                    isUsed[i] = false; // k번째 수를 i로 정한 모든 경우에 대해 다 확인했으니 i를 이제 사용되지 않았다고 명
                }
            }
        }
    }


}

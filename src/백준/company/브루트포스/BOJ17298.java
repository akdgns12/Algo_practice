package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {
    // 오큰수 / 골드 4 / 구현
    static int n;
    /*
        우선 result정답 배열을 -1로 초기화
        스택을 하나 만들고, 이 스택에는 수열 nums의 원소의 인덱스가 들어간다.
        -nums[idx]가 아직 오큰수를 만나지 못했다면, idx스택에 계속 남아있게 된다.
        -nums[idx]가 오큰수를 만나면, 스택에서 idx가 pop되고, res[idx]에는 nums[idx]의 오큰수가 들어간다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            /*
                스택이 비어있지 않으면서
                현재 원소가 스택의 맨 위 원소가 가리키는 원소보다 큰 경우.
                해당 조건을 만족할 때까지 계속 stack의 맨 위 원소를 pop하면서
                해당 인덱스의 값을 현재 원소로 바꿔준다.
             */
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]){
                arr[stack.pop()] = arr[i];
            }

            stack.push(i);
        }

        // 남이있는 놈 처리
        // 스택의 모든 원소를 pop함과 동시에 해당 인덱스의 value를 -1로 초기화
        while(!stack.isEmpty()){
            arr[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr){
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}

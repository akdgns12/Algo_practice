package 코테대비.Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

public class 크레인인형뽑기_카카오 {
    public static int solution(int n, int m, int[][] map, int[] moves){
        int answer = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < n; j++) {
                if(map[j][moves[i]-1] != 0){
                    int tmp = map[j][moves[i]-1];
                    map[j][moves[i]-1] = 0;
                    if(!st.isEmpty() && tmp == st.peek()){ // 바구니에 같은 캐릭터 삭제
                        answer += 2;
                        st.pop();
                    }
                    else st.push(tmp);
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int m = sc.nextInt();
        int[] moves = new int[m];
        for (int i = 0; i < m; i++) {
            moves[i] = sc.nextInt();
        }
        System.out.println(solution(n, m, map, moves));
    }
}

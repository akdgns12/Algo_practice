package 코테대비.Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 교육과정설계 {
    public static String solution(String need, String plan){
        String answer = "YES";
        Queue<Character> q = new LinkedList<>();
        for (char x : need.toCharArray()) q.offer(x);
        for (char x : plan.toCharArray()) {
            if(q.contains(x)){ // 해당 필수과목을 포함한다면
                if(x != q.poll()) return "NO"; // 순서에 맞는지 체크
            }
        }
        // 필수과목 이수안할수도 있으니까 다 비어있는지 체크
        if(!q.isEmpty()) return "NO";
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        System.out.println(solution(a,b));
    }
}

package 복습;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 응급실 {
    static class Person{
        int idx;
        int priority;

        public Person(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
    public static int solution(int n, int m, int[] arr){
        int answer = 0;
        Queue<Person> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.offer(new Person(i, arr[i]));
        }
        while(!q.isEmpty()){
            Person node = q.poll();
            for (Person x : q){
                if(x.priority > node.priority){
                    q.offer(node);
                    node = null;
                    break;
                }
            }
            if(node != null){
                answer++;
                if(node.idx == m) return answer;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n,m,arr));
    }


}

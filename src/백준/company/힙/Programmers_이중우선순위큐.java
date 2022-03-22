package 백준.company.힙;

import java.util.*;

public class Programmers_이중우선순위큐 {
    public static void main(String[] args) {
        Programmers_이중우선순위큐 p = new Programmers_이중우선순위큐();
        String[] operations = {"I 16", "D 1"};
        System.out.println(p.solution(operations));
    }

    public int[] solution(String[] operations){
        int[] answer = new int[2];
        // 최소힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 최대힙
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        for(String op : operations){
            StringTokenizer st = new StringTokenizer(op);
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if(command.equals("I")){
                pq.offer(value);
                maxPq.offer(value);
                continue;
            }

            // 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시
            if(pq.size() < 1 && command.equals("D"))
                continue;

            if(value < 0){ // 최솟값 삭제
                int min = pq.poll();
                maxPq.remove(min);
                continue;
            }else{ // 최댓값 삭제
                int max = maxPq.poll();
                pq.remove(max);
            }
        }

        if(pq.size() > 0){
            answer[0] = maxPq.poll();
            answer[1] = pq.poll();
        }

        return answer;
    }
}

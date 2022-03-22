package 백준.company.힙;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_디스크컨트롤러 {
    public static void main(String[] args) {
        int[][] jobs = {{0,3},{1,9},{2,6}};
        Programmers_디스크컨트롤러 p = new Programmers_디스크컨트롤러();
        System.out.println(p.solution(jobs));
    }

    // [작업이 요청되는 시점, 작업의 소요시간]
    // 작업의 소요시간순 오름차순으로 정렬하묜?
    public int solution(int[][] jobs){
        int answer = 0;
        int count = 0; // 처리된 디스크 개수
        int end = 0; // 작업이 끝난 시간
        // 1. 요청시간을 기준으로 오름차순
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        // 2. 처리시간 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        int i = 0;
        // 3. while문을 이용하여 모든 요청이 처리될때까지 반복한다.
        while(count <jobs.length){
            // 4. 한번 더 while문을 사용하여 배열의 길이를 넘지 않으며 배열의 i번째 값이 end(작업이 끝난시간)
            // 을 넘지 않으면 우선순위 큐에 i번째 값을 넣어준다.
            while(i < jobs.length && jobs[i][0] <= end){
                pq.add(jobs[i++]);
            }

            // 5. 만약 큐가 비어있다면 현재의 작업이 끝날때까지 요청된 작업이 없는 것이므로
            // i가 가리키는 값의 첫번째 값을 넣어준다.
            if(pq.isEmpty()){
                end = jobs[i][0];
            }else{ // 6. 비어있지 않다면 tmp에 큐의 맨위의 값을 넣고 answer에
                // 처리시간 + 현재시간 - 요청시간을 더해주고 현재시간에는 처리시간을 더해주고 count(처리개수)를 1개 더해준다.
                int[] tmp = pq.poll();
                answer += tmp[1] + end - tmp[0];
                end += tmp[1];
                count++;
            }
        }

        return answer / jobs.length;
    }
}

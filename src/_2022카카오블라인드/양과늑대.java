package _2022카카오블라인드;

public class 양과늑대 {
    /*
        양과 늑대 이진트리 형태
        0은 양, 1은 늑대
        1. edges의 배열정보로 이진트리를 만들고
        2. 만든 이진트리를 탐색하며 조건에 맞게 누적된 양의 합이 늑대보다 작아야함.
        3. 그렇게 모은 양의 마리수가 최대 마리수를 return
     */
    public static int solution(int[] info, int[][] edges){
        int answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {
                {0,1},{1,2},{1,4},{0,8},
                {8,7},{9,10},{9,11},{4,3},
                {6,5},{4,6},{8,9}
        };

        System.out.println(solution(info, edges));
    }
}

//package 프로그래머스.lv2;
//
//public class 숫자변환하기 {
//    public static void main(String[] args) {
//        숫자변환하기 T = new 숫자변환하기();
//        int x = 10;
//        int y = 40;
//        int n = 5;
//        System.out.println(T.solution(x, y, n));
//    }
//
//    // 답 봐버림
//    class int solution(int x, int y, int n) {
//        int answer = 0;
//        int[] dp = new int[y+1];
//        // x = 10, y = 40, n = 5;
//        // dp[i] = x로 i를 만드는 최소횟수
//
//        for(int i=x+1; i<=y+1; i++){
////            if(i/3 >= x && )
//            if(i-n >= x){
//                int c = dp[i-n];
//            }else{
//                int c = Integer.MAX_VALUE;
//            }
//
//            dp[i] = Math.min(dp[i-n], Math.min(dp[i/2], dp[i/3]));
//        }
//
//        return answer;
//    }
//}

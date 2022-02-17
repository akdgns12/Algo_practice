package _2022카카오블라인드;

public class k진수에서소수개수구하기_2번 {
    public static int solution(int n, int k){
        int answer = 0;
        String temp = convert(n, k);

        String[] arr = temp.split("0");
        for (String str : arr){
            if(str.equals("")) continue;
            long num = Long.parseLong(str);
            if(isPrime(num))
                answer++;
        }

        return answer;
    }

    static boolean isPrime(long n){
        if(n < 2) return false;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }

        return true;
    }

    // 1. 진법 볍환
    static String convert(int n, int k){
        String temp = "";
        while(n != 0){
            temp = n % k + temp;
            n /= k;
        }

        return temp;
    }

    public static void main(String[] args) {
        int n = 437674;
        int k = 2;
        System.out.print(solution(n, k));
    }
}

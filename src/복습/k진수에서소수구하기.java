package 복습;

class K진수에서소수구하기 {
    public int solution(int n, int k) {
        int answer = 0;
        String temp = "";

        // k진법 변환
        while(n != 0){
            temp = n % k + temp;
            n /= k;
        }

        String[] arr = temp.split("0");

        for(String str : arr){
            if(str.equals("")) continue; // 1001 같은 경우 0 기준 split하면 ""가 발생 예외처리 해줘야됨
            long num = Long.parseLong(str);
            if(isPrime(num))
                answer++;
        }
        return answer;
    }

    static boolean isPrime(long n){
        if(n < 2) return false;
        for(int i=2; i<= Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }
}
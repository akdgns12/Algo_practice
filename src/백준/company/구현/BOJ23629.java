package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문자열 구현.......;;;;
 * 정답률이 낮은 이유가 있다..하
 * 거의 블로그에 있는 풀이를 베껴 풀었다..
 * 처음엔 Map<String,Integer> String(숫자), 숫자 로만 해결 가능할 줄 알았는데
 * 답 출력을 수식 전체와 답까지 도출해야해서 그냥 String, String으로 다 넣어주는게 편한듯
 * 문제에서 순서대로 계산이 이뤄진다 했으니 deque를 사용해 두 수를 계산하고 다시 큐앞에 삽입해
 * 순서대로 계산이 이뤄질 수 있도록 하는게 편함
 */
public class BOJ23629 {
    // 이 얼마나 끔직하고 무시무시한 수식이니 / 골드 5 / 문자열, 구현
    static String str;
    static HashMap<String, String> map = new HashMap<>();
    static ArrayList<Character> operator = new ArrayList<>();
    static Deque<Long> dq = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        map.put("ZERO", "0");
        map.put("ONE", "1");
        map.put("TWO", "2");
        map.put("THREE", "3");
        map.put("FOUR", "4");
        map.put("FIVE", "5");
        map.put("SIX", "6");
        map.put("SEVEN", "7");
        map.put("EIGHT", "8");
        map.put("NINE", "9");

        map.put("0", "ZERO");
        map.put("1", "ONE");
        map.put("2", "TWO");
        map.put("3", "THREE");
        map.put("4", "FOUR");
        map.put("5", "FIVE");
        map.put("6", "SIX");
        map.put("7", "SEVEN");
        map.put("8", "EIGHT");
        map.put("9", "NINE");

        for (char c : str.toCharArray()) {
            if(c == '+' || c == '-' || c == '/' || c == 'x' || c == '='){
                operator.add(c);
            }
        }

        solve(str);

        System.out.println(sb);
    }

    static void solve(String str){
        StringTokenizer st = new StringTokenizer(str, "+-x/=");

        if(st.countTokens() < operator.size()){ //숫자가 연산자보다 적다면 X
            sb.append("Madness!");
            return;
        }

        int opIdx = 0;
        long sum = 0L;

        // 1.
        while(st.hasMoreTokens()){
            String rel = StringToNum(st.nextToken());

            if (rel == null) {
                sb.append("Madness!");
                return;
            }

            sb.append(rel).append(operator.get(opIdx));
            opIdx++;
            dq.offer(Long.parseLong(rel));
        }

        sb.append("\n");

        //2. dq에 넣은 long타입 숫자를 계산하기
        opIdx = 0;
        //덱큐 사이즈가 2이상이라면 -> 연산할 숫자가 2개이상이면 연산가능이니까
        while(dq.size() >= 2){
            long l1 = dq.poll();
            long l2 = dq.poll();

            long rel = calculate(l1, l2, operator.get(opIdx));
            opIdx++;
            // 문제에서 순서대로 계산한다고 되어있기 때문에 이렇게
            // 앞에서 넣어줄 수 있는 deque사용해서 순서대로 계산이 이뤄질 수 있도록
            dq.offerFirst(rel);
        }

        //3. 최종값 문자열로 변환
        long l = dq.poll();
        sb.append(numToString(l));
    }

    static String numToString(long l){
        String strL = String.valueOf(l);
        String rel = "";
        for (int i = 0; i < strL.length(); i++) {
            //여기서 연산자 체크하는 이유 - 예제3번 같은 경우가 있기 때문
            if(strL.charAt(i) == '+' || strL.charAt(i) == '-' || strL.charAt(i) == 'x' ||
                    strL.charAt(i) == '/' || strL.charAt(i) == '='){
                rel += strL.charAt(i);
            }
            else rel += map.get(strL.charAt(i) + "");
        }
        return rel;
    }

    static long calculate(long l1, long l2, char opreator){
        long rel = 0L;
        switch (opreator) {
            case '+':
                rel = l1 + l2;
                break;
            case '-':
                rel = l1 - l2;
                break;
            case 'x':
                rel = l1 * l2;
                break;
            case '/':
                rel = l1 / l2;
                break;
        }
        return rel;
    }


    static String StringToNum(String str){
        String rel = "";
        String num = "";
        int s = 0, e = 0;

        while(true){
            e++;
            String mid = str.substring(s,e);
            if(map.get(mid) != null){
                rel += mid;
                num += map.get(mid);
                s = e;
            }
            //문자열 끝에 다다르면 break
            if(e == str.length()) break;
        }

        if(rel.length() == str.length()) return num;
        return null;
    }
}

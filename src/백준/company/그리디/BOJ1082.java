package 백준.company.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1082 {
    // 방번호 / 골드 3 / 그리디 or DP
    /*
        최대의 방번호를 만들려면
        1. 자릿수를 가능한 길게
        2. 맨 앞자리부터 최대한 큰 수가 들어가게

        1번을 만족하려면 무조건 수를 많이 모아야 하기 때문에
        비용이 가장 적은 숫자를 최대한 많이 산다.
        이때, 비용이 가장 적은 숫자가 0이라면
        맨 앞자리에 0이 오면 안되므로 비용이 두번째로 적은 숫자를 하나 사서
        맨 앞자리에 배치한 다음에 나머지는 비용이 가장 적은 숫자로 채운다.

        2번을 만족하기 위해서는 1번에서 만들어진 수를 가져와서
        맨 앞자리부터 맨 끝자리까지 최대한 큰 수로 교체해야 한다.
        맨 앞자리엔 0이 오지 않게 주의하고
        수용가능 가격 범위를 넘지않는 선에서 가장 큰 숫자부터 차례로 교체 시도
     */
    static class Number{
        int num, price;

        public Number(int num, int price) {
            this.num = num;
            this.price = price;
        }
    }
    static int n, m;
    static Number price[];
    static int[] result = new int[100];
    static HashMap<Integer, Integer> map = new HashMap<>(); // 숫자-비용 따로 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());  // 방번호로 사용할 수 있는 수 0~n-1

        st = new StringTokenizer(br.readLine());
        price = new Number[n];
        for (int i = 0; i < n; i++) { // 각 숫자의 가격
            price[i] = new Number(i, Integer.parseInt(st.nextToken()));
            map.put(i, price[i].price);
        }

        m = Integer.parseInt(br.readLine()); // 준비한 금액

        Arrays.sort(price, new Comparator<Number>(){ // 가격 기준 오름차순 정렬
            @Override
            public int compare(Number o1, Number o2) {
                if(o1.price == o2.price) return o1.num - o2.num;
                else return o1.price - o2.price;
            }
        });

        int cnt = 0;
        if(price[0].num == 0){ // 숫자 0이 가장 작은 수라서 맨 앞에 올 수 없기 때문에
            if(n == 1 || price[1].price > m) { // 0대신 넣을 그 다음 숫자의 비용을 감당할 수 없다면
                System.out.println(0); // 숫자 0으로밖에 할 수 없을 때 답은 0
                System.exit(0);
            }
            result[cnt++] = price[1].num; // 그 다음 숫자의 비용을 감당할 수 있으면 이 숫자로 맨 앞자리 채운다.
            m -= price[1].price;
        }

        while(m - price[0].price >= 0){ // 가장 비용이 작은 숫자로 최대한 많이 채움
            result[cnt++] = price[0].num;
            m -= price[0].price;
        }

        for (int i = 0; i < cnt; i++) {
            for (int j = n - 1; j >= 0; j--) { // 가장 큰 수부터
                if(i == 0 && j== 0) continue; // 맨 앞자리에 0이 들어가면 안됨
                // m + map.get(result[i]) - map.get(j);
                // 총 비용에서 가장 작은 비용들로 채우고 남은비용 + 현재 바꾸려는 자리의 비용 - 가능하다면 바꿀 비용
                int tmp = m + map.get(result[i]) - map.get(j);
                if (tmp >= 0) { // 가격 범위 안 넘어서 현재 숫자로 바꿀 수 있으면 바꿈(최대한 큰 수 이므로)
                    m = tmp; // 허용 가능한 비용 갱신
                    result[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < cnt; i++) System.out.println(result[i]);
    }
}

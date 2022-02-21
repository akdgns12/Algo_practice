package 백준.company.정규표현식;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ2870 {
    // 수학숙제 / 실버 4 / 정규표현식
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<String> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\d]+");

        // 각줄에서 숫자를 모두 찾아 리스트 numbers에 담는 동작을 수행
        /*
            1. n번을 돌며 문자열을 입력받는다
            2. 입력받은 문자열로 macther객체를 만든 뒤, find메소드를 호출해 문자열 내 모든 숫자를 찾는다.
            3. 숫자가 존재한다면, group 메소드를 통해 해당 부분 문자열에서 0으로 시작하는 숫자들을 모두 제거한다.
            예를 들어, 문자열 내에서 찾은 숫자가 00030이라면 앞에 0을 뗴어내 30으로 만든다.
            4. 가공된 숫자를 리스트에 담는다. 이 때, 숫자가 0 혹은 00과 같이 다 제거해서 빈 문자열이 되는 경우,
            리스트에는 0을 넣어준다.
         */
        while (n -- > 0) {
            String input = br.readLine();
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String num = matcher.group().replaceAll("^0+", "");
                numbers.add(num.length() == 0 ? "0" : num);
            }
        }

        // 리스트에 담긴 문자열 형태를 띄는 숫자들을 오름차순으로 정렬
        Collections.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String num : numbers) sb.append(num + "\n");

        System.out.println(sb);
    }
}

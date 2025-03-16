package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/*
     a b c d e f g h i j k l m n o p q r s t u v w x y z의 순서이지만,
     a b k d e g h i l m n ng o p r s t u w y

     20개의 알파벳 배열에서 순서대로 1~20 매핑하고
    그에 따라 정렬되게?
 */

public class BOJ1599_민식어 {
    static int N;
    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 단어 수

        map.put("a", "A");
        map.put("b", "B");
        map.put("k", "C");
        map.put("d", "D");
        map.put("e", "E");
        map.put("g", "F");
        map.put("h", "G");
        map.put("i", "H");
        map.put("l", "I");
        map.put("m", "J");
        map.put("n", "K");
        map.put("ng", "L");
        map.put("o", "M");
        map.put("p", "N");
        map.put("r", "O");
        map.put("s", "P");
        map.put("t", "Q");
        map.put("u", "R");
        map.put("w", "S");
        map.put("y", "T");

        String[] arr = new String[N];
        for(int i=0; i<N; i++){
            arr[i] = br.readLine();
        }

        String[][] narr = new String[N][2];
        for(int i=0; i<N; i++){
            narr[i][1] = arr[i];
            String str = "";
            for(int j=0; j<arr[i].length(); j++){
                if(arr[i].charAt(j) == 'n'){ // ng인 경우 체크
                    if(j + 1 == arr[i].length() || arr[i].charAt(j+1) != 'g') str += map.get("n") + " ";
                    else if(arr[i].charAt(j + 1) == 'g') {
                        str += map.get("ng") + " ";
                        j++;
                    }
                }else{
                    str += (map.get(String.valueOf(arr[i].charAt(j)))) + " ";
                }
            }

            narr[i][0] = str.trim();
        }


        for(int i=0; i<N; i++){
            narr[i][0] = narr[i][0].replaceAll(" ", "");
        }
        Arrays.sort(narr, (o1, o2) -> o1[0].compareTo(o2[0]));
//        Arrays.sort(narr, (o1, o2) -> Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
        for(int i=0; i<N; i++){
//            System.out.println(narr[i][0]);
            System.out.println(narr[i][1]);
        }
    }
}

/*
    4
    abakada  -> 0102031
    alpabet  -> 08'13'014'16 -> 이런 경우 -> 그냥 애초에 문자열로 저장할까
    tagalog  ->
    ako
 */

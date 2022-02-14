package 코테대비.HashMap_TreeSet;

import java.util.HashMap;
import java.util.Scanner;

public class 학급회장 {
     public static char solution(int n, String s){
         char answer = ' ';
         HashMap<Character, Integer> map = new HashMap<>();
         for (char x : s.toCharArray()) {
             map.put(x, map.getOrDefault(x, 0) + 1);
         }
//         System.out.println(map.containsKey('A'));
//         System.out.println(map.size());
//         System.out.println(map.remove('A'));
//         System.out.println(map.size());

         int max = Integer.MIN_VALUE;
         for (char key : map.keySet()) {
//             System.out.println(key+" "+ map.get(key));
             if (map.get(key) > max) {
                 max = map.get(key);
                 answer = key;
             }
         }

         return answer;
     }

     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         String str = sc.next();

         System.out.println(solution(n, str));
     }


}

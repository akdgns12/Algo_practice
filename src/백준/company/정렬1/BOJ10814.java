package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ10814 {
    // 나이순 정렬 / 실버 5 / 병합정렬하면 될 듯?
    // 총 3가지 방법
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Person[] p = new Person[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            p[i] = new Person(age, name);
        }

        // 타입을 Person으로 둘 것
        Arrays.sort(p, new Comparator<Person>(){
            // 나이순으로 정렬
            @Override
            public int compare(Person o1, Person o2){
                return o1.age - o2.age;
            }
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // 객체배열의 객체를 출력하면 해당 인덱스의 객체의 toString()이 출력 됨
            sb.append(p[i]);
        }

        System.out.println(sb);
    }

    static class Person{
        int age;
        String name;

        public Person(int age, String name){
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return age + " " + name + "\n";
        }
    }
}

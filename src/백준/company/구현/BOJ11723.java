package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11723 {
    // 집합 / 실버5 / 구현
    // 비트마스킹 연습 문제
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(br.readLine());
        int S = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;

            // 하나의 정수를 이진수로 바꿨을때, 특정 자리수의 비트가 1인지 0인지 판별하거나
            // 특정 비트를 더하거나 빼거나 바꾸는 것을 비트연산을 통해 할 수 있다.
            // *주의할점 - 20자리의 이진수가 있을 때, 각 비트의 자리수는 0~19이다.
            // 문제에서는 1~20에 대한 입력을 받기 때문에 -1을 해주어 0~19로 맞춰주어야 한다.
            switch (command) {
                case "add":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    S = S | (1 << num); // 집합에 특정원소 추가, 원소에 해당하는 비트만 켜야하기 때문에 해당 비트를 항상 1로 만드는 연산이 필요 OR연산
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken()) - 1;
                    S = S & ~(1 << num); // AND연산자 이용 1. (1 << num) : num번째가 켜진 상태, ~(1<<num) : num번째 꺼진상태, S & ~(1 << num) : num번째가 꺼진상태와 원래 S를 AND연산할 시 num번째는 항상 꺼짐
                case "check":
                    int temp = S & (1 << num); // num번째가 켜진상태와 S가 AND연산시 S에 num번째가 켜진상태로 존재했어야 temp 1, 없었다면 0
                    sb.append(((temp == 0) ? 0 : 1) + "\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken()) - 1; // S 집합에 해당 원소가 빠져있는 경우에는 추가하고, 들어있는 경우에는 삭제하는 방법이다. XOR 연산을 이용한다.
                    S = S ^ (1 << num);
                    break;
                case "all":
                    S = (1 << 21) - 1;
                    break;
                case "empty":
                    S = 0;
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}

/**
 * 구분	연산자	의미	 설명
 * 비트연산자	&	비트 단위의 AND	두 정수를 한 비트씩 비교하면서 양쪽 비트가 모두 1이면 결과도 1이고 나머지는 0을 반환
 * |	비트 단위의 OR	두 정수를 한 비트씩 비교하면서 양쪽 비트 중 하나라도 1이면 결과가 1이고 나머지는 0을 반환
 * ^	XOR(배타적 OR)	두 정수를 한 비트씩 비교하면서 양쪽 비트가 서로 다르면 1, 같으면 0을 반환
 * ~	NOT	정수 하나의 각 비트를 1이면 0, 0이면 1로 바꾸는 연산
 * 비트 이동연산자	<<	정수 a를 왼쪽으로 b비트 만큼 이동 (빈자리는 0으로 채워짐, MSB가 1이 되면 음수로 바뀜)
 * >>	정수 a를 오른쪽으로 b비트 만큼 이동 (빈자리는 정수 a의 MSB와 같은 값으로 채워짐)
 * >>>	정수 a를 오른쪽으로 b비트 만큼 이동 (빈자리는 0으로 채워짐)
 *
 * A를 10개의 집합의 상태를 나타내는 변수라고 가정하고 연산을 살펴보자. (0번째 ~ 9번째 원소)
 * 연산	사용 예시
 * 공집합과 꽉 찬 집합 구하기	A = 0; / A = (1 << 10) - 1;
 * 원소 추가	A |= (1 << k);
 * 원소 삭제	A &= ~(1 << k);
 * 원소의 포함 여부 확인 	if((A & (1 << k)) == (1 << k))
 * 원소의 토글(toggle)	A ^= (1 << k);
 * 두 집합에 대해서 연산	A | B       → A와 B의 합집합
 * A & B     → A와 B의 교집합
 * A & (~B) → A에서 B를 뺀 차집합
 * A ^ B     → A와 B중 하나에만 포함된 원소들의 집합
 * 집합의 크기 구하기	int bitCount(int A){
 *   if(A == 0) return 0;
 *   return A%2 + bitCount(A / 2);
 * }
 *
 * [내장 명령어]
 * gcc/g++ → __builtin_popcount(A)
 * visual C++ → __popcnt(A)
 * Java → Integer.bitCount(A)
 * 최소 원소 찾기	int first = A & (-A);
 * 최소 원소 지우기	A &= (A - 1);
 * 모든 부분 집합 순회하기	for (int subset = A ; subset>0; subset = ((subset - 1) & A)){ }
 *
 *
 * 공집합과 꽉 찬 집합 구하기
 *   ☛ 기본적으로 공집합은 bit가 모두 꺼진 상황이기 때문에 상수 0이 공집합을 표현한다. 반대로  꽉 찬 집합은 bit가 모두 켜진 상황이기 때문에 1111111111(2) 의 값으로 표현한다.
 *
 *
 *
 * 원소 추가
 *   ☛ A 집합에 특정 원소를 추가하는 방법이다. 원소에 해당하는 bit만 켜야 하기 때문에 해당 bit를 항상 1로 만드는 연산이 필요하기 때문에 따라서 OR 연산을 이용한다.
 *
 *
 *
 * 원소 삭제
 *   ☛ A 집합에 포함된 특정 원소를 삭제하는 방법이다. A에 k번째 원소의 포함 여부와 상관없이 해당 bit를 끄기 위해서는 AND 연산을 이용해야 한다.
 *
 * 1<<k : k번째가 켜진 상태
 *
 * ^(1<<k) : k번째만 꺼진 상태
 *
 * A &= ^(1<<k); : A 집합에 담긴 k번째 상태 off
 *
 *
 *
 *  +
 *
 * A -= (1<<k); :이는 A에 반드시 k번째 원소가 포함되어 있는 경우에만 가능하다. 만약 포함되어 있지 않은 상태에서 삭제 연산을 하게 되면 데이터가 망가진다.
 *
 *
 *
 * 원소의 포함 여부 확인
 *   ☛ A 집합에 특정 원소가 포함되어 있는지 확인하는 방법이다. k번째 원소가 포함되어 있는지 확인하고 싶다면, k번째 bit가 켜져 있는지만 확인하면 된다.
 *
 *
 *
 * 원소의 토글
 *   ☛ A 집합에 해당 원소가 빠져있는 경우에는 추가하고, 들어있는 경우에는 삭제하는 방법이다. XOR 연산을 이용한다.
 *
 *
 *
 * 두 집합에 대해 연산하기
 *   ☛ 두 집합을 A와 B라고 한다면 비트연산자들을 통해서 A와 B의 교집합, 합집합, 차집합 등을 구할 수 있다.
 *
 *
 *
 * 집합의 크기 구하기
 *   ☛ 집합에 포함된 원소의 크기를 구한다면 A에서 켜진 bit의 수를 구하면 될 것이다. 직접 모든 비트를 확인하면서 개수를 체크할 수도 있고, 내장 명령어를 이용할 수도 있다.
 *
 * * 내장 명령어는 최적화되어 구현되어 있기 때문에 직접 모든 비트를 탐색하는 것보다 효율적으로 작동한다. 단, 자바의 경우에는 이 연산이 표준 라이브러리에 포함되어 있지만, gcc와 C++에서 제공하는 함수들은 모두 컴파일러 의존적이므로 유의해야 한다.
 *
 *
 *
 * 최소 원소 찾기
 *   ☛ 집합에 포함된 가장 작은 원소 (index가 가장 작은 원소)를 찾는 방법이다. 켜져 있는 bit 중에서 가장 오른쪽에 있는 bit를 찾는 것이다. 비트마스크 뿐만 아니라 펜윅 트리 (Fenwick Tree)에서도 사용되는 기법이다.
 *
 *
 *
 * 비트 A가 있다고하자.
 *
 * 가장 오른쪽에 켜져있는 bit를 k라고 하면, 0~k-1의 bit는 모두 0이다.
 * 그렇다면 ~A에서는 k번째 bit는 0, 0~k-1의 bit는 모두 1이다.
 * ~A + 1을 하게 되면 k번째 bit는 1, 0~k-1의 bit는 모두 0이 된다. k이후의 비트는 아무 변화가 없다.
 * * ~A + 1 : 컴퓨터가 표현하는 A의 2의 보수 (-A)
 *
 *
 *
 * → 따라서, -A와 A를 AND연산을 시키면 k번째 bit만 켜진 상태로 남게 된다.
 *
 *  ex) int first = A & (-A);
 *
 *        A : 1010,
 *
 *     ~A+1 (-A) : 0110,
 *
 *       A&(-A) : 0010
 *
 *
 *
 * 최소 원소 지우기
 *   ☛ 가장 오른쪽에 켜져 있는 bit를 지우고 싶다면 A-1과 AND시키면 된다. A에서 1을 빼주게 되면 가장 오른쪽에 있던 bit는 0이 되고 그보다 오른쪽에 있는 모든 bit들이 1이 되기 때문이다.
 *
 *  ex) A &= (A-1);
 *
 *        A : 1010,
 *
 *        A-1 : 1001,
 *
 *       A&(A-1) : 1000
 *
 *
 *
 * 모든 부분 집합 순회하기
 *   ☛ A의 모든 부분 집합을 탐색하는 방법이다.
 *
 * ex) A: 1101 탐색
 *
 *      1) 1101    > subset-1 : 1100
 *
 *      2) 1100    > subset-1 : 1011
 *
 *      3) 1001     > subset-1 : 1000
 *
 *      4) 1000     > subset-1 : 0111
 *
 *      5) 0101    > subset-1 : 0100
 *
 *      6) 0100     > subset-1 : 0011
 *
 *      7) 0001    > subset-1 : 0000
 */
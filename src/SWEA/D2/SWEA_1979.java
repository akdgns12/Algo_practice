package SWEA.D2;/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class SWEA_1979
{
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        // 길이 k를 갖는 단어가 들어갈 수 있는 자리의 수 출력(예시를 보니 딱맞는 자리만 가능한듯)
        for(int test_case = 1; test_case <= T; test_case++)
        {

            int n = sc.nextInt(); // 가로, 세로 길이
            int k = sc.nextInt(); // 단어의 길이
            int answer = 0;

            int[][] arr = new int[n][n];

            for(int i=0; i<n; i++){
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // 0:검은색, 1:흰색
            // 행 찾기
            for (int i = 0; i < n; i++) {
                int rowcheck = 0;
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] == 0){ // 검은색이면 초기화
                        if (rowcheck == k) { // 이미 k길이의 단어가 들어올 수 있는상태면, 답 증가시키고 초기화
                            answer++;
                        }
                        rowcheck = 0;
                    }else{ // 흰색이면 길이 카운트 증가
                        rowcheck += 1;
                    }
                }

                // 가로길이가 k와 맞는경우
                if (rowcheck == k) {
                    answer++;
                }
            }

            // 열 찾기
            for (int i = 0; i < n; i++) {
                int colcheck = 0;
                for (int j = 0; j < n; j++) {
                    if (arr[j][i] == 0) { // 검은색이면 초기화, 흰색이면 카운팅
                        if(colcheck == k){
                            answer++;
                        }
                        colcheck = 0;
                    }else{
                        colcheck += 1;
                    }
                }

                if(colcheck == k) {
                    answer++;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        } // 테케 종료
    }
}
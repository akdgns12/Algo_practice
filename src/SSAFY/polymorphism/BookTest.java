package SSAFY.polymorphism;

public class BookTest {
    // 도서리스트를 관리하는 BookManager 객체 생성
    public static void main(String[] args) {
        BookManager bm = new BookManager();

        // BookManager 객체를 통해 도서 목록 추가, 삭제, 조회
        bm.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법"));
        bm.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 30000, "SW 모델링"));
        bm.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 2022, 3));

        System.out.println("**********************도서 전체 목록**********************");
        for (Book b : bm.getList()) {
            System.out.println(b);
        }
        System.out.println("**********************일반 도서 목록**********************");
        for (Book b : bm.getBooks()) {
            System.out.println(b);
        }
        System.out.println("**********************잡지 목록**********************");
        for (Magazine b : bm.getMagazines()) {
            System.out.println(b);
        }
        System.out.println("**********************도서 제목 포함검색:Java**********************");
        for (Book b : bm.searchByTitle("Java")) {
            System.out.println(b);
        }
        System.out.println("도서 가격 총합 : "+ bm.getTotalPrice());
        System.out.println("도서 가격 평균: "+ bm.getPriceAvg());
    }
}

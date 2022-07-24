package SSAFY.polymorphism;

import java.util.Arrays;
import java.util.Locale;

public class BookManager {
    int MAX_SIZE = 100; // 사용자 최대 개수
    Book[] books = new Book[MAX_SIZE]; // 도서 목록 리스트
    int size = 0; // 현재 등록된 도서 수

    // 도서목록에 사용자 추가 메서드
    public void add(Book book){
        if (size < MAX_SIZE) { // 최대 등록 제한 개수보다 등록된 수가 적다면 등록할 수 있으므로 등록
            books[size++] = book;
        }
    }

    // 고유번호로 도서목록 삭제 메서드
    public void remove(String isbn){
        for (int i = 0; i < size; i++) {
            if(books[i].getIsbn().equals(isbn)){ // 도서목록에 있는 고유번호와 파라미터로 들어온 고유번호가 같다면
                books[i] = books[size-1]; // 삭제한 위치에 도서목록에 끝에 있는 도서를 끌어옴
                books[size-1] = null; // 끌어온 위치는 다시 null 처리
                size--; // 사이즈 감소
            }
        }
    }

    // 현재 등록된 도서목록 반환 리스트
    public Book[] getList(){
        return Arrays.copyOfRange(books, 0, size);
    }

    // 고유번호로 해당 도서정보 반환하는 메서드
    public Book searchByIsbn(String isbn){
        for (int i = 0; i < size; i++) {
            if(books[i].getIsbn().equals(isbn)){
                return books[i];
            }
        }

        return null;
    }

    // 해당 도서 제목을 포함한 목록을 전부 반환하는 메서드
    public Book[] searchByTitle(String title){
        Book[] temp;
        int cnt = 0;

        for (int i = 0; i < size; i++) { // 도서 제목을 포함하는 개수를 카운팅
            if(books[i].getTitle().contains(title.toLowerCase())){
                cnt++;
            }
        }

        temp = new Book[cnt];
        int idx = 0;
        for (int i = 0; i < size; i++) { // 해당 제목을 포함하는 도서만 배열에 포함
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) { // 둘 다 소문자로 변환해줘서 포함될 수 있도록 처리
                temp[idx++] = books[i];
            }
        }

        return temp;
    }

    // 전체 도서정보 중 잡지 정보만 반환하는 메서드
    public Magazine[] getMagazines() {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if(books[i] instanceof Magazine){
                cnt++;
            }
        }

        Magazine[] result = new Magazine[cnt];
        int idx = 0;

        for (int i = 0; i < size; i++) {
            if (books[i] instanceof Magazine) {
                result[idx++]= (Magazine)books[i]; // 다운 캐스팅 필요함
            }
        }

        return result;
    }

    // 잡지가 아닌 일반도서 리스트를 반환하는 메서드
    public Book[] getBooks(){
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if(!(books[i] instanceof Magazine)){
                cnt++;
            }
        }

        Book[] result = new Book[cnt];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if(!(books[i] instanceof Magazine)){
                result[idx++] = books[i];
            }
        }

        return result;
    }

    // 도서리스트의 가격의 총합을 반환하는 메서드
    public int getTotalPrice(){
        int total = 0;

        for (int i = 0; i < size; i++) {
            total += books[i].getPrice();
        }

        return total;
    }

    // 도서가격의 평균을 반환하는 메서드
    public double getPriceAvg(){
        double avg = 0;
        return (double)getTotalPrice() / size;
    }
}

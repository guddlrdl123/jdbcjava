package dbutil.test;

import java.util.Optional;

import domain.users.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {

    // 단위 테스트
    // 기능 정의된 인터페이스를 통한 기능 호출
    private static Users repository = new UsersDAOImpl();

    public static void main(String[] args) {
        int testResult = repository.userAdd(
                new UserVO().builder()
                        .userId("testuser1")
                        .userPw("password")
                        .userName("testuser")
                        .userEmail("test202602@test.com")
                        .age(32)
                        .phone1("02")
                        .phone2("111-1111")
                        .address1("서울시 강남구")
                        .address2("역삼동")
                        .build());
        if (testResult != 0) {
            System.out.println("데이터 추가 성공");
        } else
            System.out.println("데이터 추가 실패");

        // 레코드 search 기능 테스트
        Optional<UserVO> searchResult = repository.userSearch("test202602@test.com");
        if (searchResult.isPresent()) {
            System.out.println("search 결과 성공");
        } else
            System.out.println("search 실패");

        // 레코드 수정(userMod)
        searchResult.get().setAddress2("수정된 주소");
        testResult = repository.userMod(searchResult.get());
        if (testResult != 0) {
            System.out.println("수정 성공");
        } else
            System.out.println("수정 실패");

        // 레코드 검색(userId, userName)
        // UserVO search2 = repository.userSearch(searchResult.get().getUserId(),
        // searchResult.get().getUserName()).get(0);
        // if (search2 != null) {
        // System.out.println("아이디 검색 성공");
        // } else
        // System.out.println("아이디 검색 실패");

        // 레코드 삭제
        testResult = repository.userDel(searchResult.get());
        if (testResult != 0) {
            System.out.println("삭제 성공");
        } else
            System.out.println("삭제 실패");

        repository.userAll().stream().forEach(System.out::println);
    }
}

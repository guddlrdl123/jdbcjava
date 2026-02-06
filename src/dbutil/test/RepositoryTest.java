package dbutil.test;

import domain.users.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {
    private static Users repository = new UsersDAOImpl();

    public static void main(String[] args) {
        UserVO testData = UserVO.builder()
                .userId("test111").userName("test111")
                .userPw("test11").userEmail("test111@naver.com")
                .build();

        if (repository.userAdd(testData) != 0) {
            System.out.println("성공");
        } else
            System.out.println("실패");

        if (repository.userDel(testData) != 0) {
            System.out.println("삭제 성공");
        } else
            System.out.println("삭제 실패");
    }
}

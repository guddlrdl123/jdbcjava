package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {
        // 1. 데이터 베이스 연결을 위한 Connection 객체 생성
        Connection conn = null;

        // 2. 데이터베이스 접속 테스트
        try {
            // 1. 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver"); // 오라클 드라이버

            // 2. 데이터베이스 접속 정보를 담은 Connection 객체 생성
            // jdbc:mysql:// -> jdbc로 mysql 접속
            // jdbc:mysql://localhost:3306/jdbc
            // localhost -> 서버 주소. Dokcer에 현재 DB 서버가 있다.
            // :3306 -> 포트 번호 (mysql은 포트번호 3306을 기본 포트로 사용)
            // /jdbc -> DB 이름
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:4306/jdbc", "jdbcuser", "jdbcuser");
            System.out.println(conn);
            System.out.println("데이터베이스 접속 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL 에러(데이터베이스 접속 실패)");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("데이터베이스 연결 해제 실패: " + e.getMessage());
            }
        }
    }
}

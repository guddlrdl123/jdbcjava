package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest2 {
    public static void main(String[] args) {
        // DB Connection 작업을 직접 해보기
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "jdbcuser", "jdbcuser");
            System.out.println("데이터베이스 접속 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("SQL 에러");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("실패");
            }
        }

        // try~ catch 구문의 finally를 사용해서 Connection 객체를 해제하지 않아도 됨.
        // try~ resource를 사용하는 방법
        // String url = "jdbc:mwql://localhost:3306/jdbc";
        // String user "jdbcuser";
        // String password = "jdbcuser";

        // try (Connection conn = DriverManager.getConnection(url, user, password)){
        // System.out.println("DB 연결 성공");
        // } catch (Exception e) {
        // System.out.println("DB 연결 실패");
        // System.out.println(e.getMessage());
        // }
    }
}

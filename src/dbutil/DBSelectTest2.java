package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest2 {
    public static void main(String[] args) {
        // Select 동작
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // 1. Connection 객체 생성
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            String sql = "select * from person";

            // 3. Statement 객체 생성, ResultSet 객체 선언(Query 결과 받을 객체)
            Statement stmt = conn.createStatement();
            ResultSet rs = null;

            // 4. SQL 실행
            // Select는 executeQuery() 사용
            // ResultSet 객체로 반환

            // 5. 결과 확인
            List<PersonVO> list = new ArrayList<>();
            rs = stmt.executeQuery(sql);
            while (rs.next()) { // rs.next() 반환값은 boolean
                PersonVO vo = new PersonVO(rs.getString("userId"),
                        rs.getString("userPw"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getInt("age"),
                        rs.getNString("address1"),
                        rs.getString("address2"));

                vo.setId(rs.getInt("id"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifyDate(rs.getTimestamp("modifyDate"));
                list.add(vo);
            }

            System.out.println("id :" + rs.getLong("id"));
            System.out.println("userId :" + rs.getString("userId"));
            System.out.println("userPw :" + rs.getString("userPw"));
            System.out.println("userEmail :" + rs.getString("userEmail"));
            System.out.println("phone :" + rs.getString("phone1")
                    + "-" + rs.getString("phone2"));
            System.out.println("age :" + rs.getInt("age"));
            System.out.println("address1 :" + rs.getString("address1"));
            System.out.println("address2 :" + rs.getString("address2"));
            System.out.println("regDate :" + rs.getTimestamp("regDate"));
            System.out.println("modifyDate :" + rs.getTimestamp("modifyDate"));
        }
        // else {
        // System.out.println("저장된 레코드가 없습니다.");
        // }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

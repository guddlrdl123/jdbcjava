package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO> list = new ArrayList<>();

        // DB작업
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select * from person where id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();
            // 결과처리
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
            list.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

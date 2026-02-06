package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO2;

public class DBSelectTest4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO2> list = new ArrayList<>();

        // DB작업
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select * from person where id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();
            // 결과처리
            while (rs.next()) { // rs.next() 반환값은 boolean
                list.add(new PersonVO2().builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEamil(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getInt("age"))
                        .address1(rs.getNString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());

            }
            list.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

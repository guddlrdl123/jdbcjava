package controller;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import dto.OrderDTO;
import dto.UserDTO;
import service.ordermanage.Ordermanage;
import service.ordermanage.OrdermanageImpl;
import service.usermanage.Usermanage;
import service.usermanage.UsermanageImpl;

public class OrderProgramController {

    private Ordermanage orderService = new OrdermanageImpl();
    private Usermanage userService = new UsermanageImpl();

    // 메서드 구현
    // 1. 회원가입
    public boolean join(String userId, String userPw, String userName, String userEmail, String userPhone, int age,
            String userAddress1, String userAddress2) {
        // 02-888-8888
        String phone1 = userPhone.substring(0, userPhone.indexOf('-'));
        String phone2 = userPhone.substring(userPhone.indexOf('-') + 1);
        UserDTO userDTO = UserDTO.builder()
                .userId(userId).userPw(userPw).userName(userName)
                .userEmail(userEmail).phone1(phone1).phone2(phone2)
                .age(age).address1(userAddress1).address2(userAddress2)
                .build();
        return userService.userRegister(userDTO);
    }

    // 2. 가입 정보 확인
    public UserDTO userInfo(String email) {
        return userService.searchOne(email);
    }

    // 3. 정보 수정
    public boolean userModify(int id, String userId, String userPw, String userName, String userEmail, String userPhone,
            int age,
            String userAddress1, String userAddress2) {
        String phone1 = userPhone.substring(0, userPhone.indexOf('-'));
        String phone2 = userPhone.substring(userPhone.indexOf('-') + 1);

        UserDTO userDTO = UserDTO.builder().id(id)
                .userId(userId).userPw(userPw).userName(userName)
                .userEmail(userEmail).phone1(phone1).phone2(phone2)
                .age(age).address1(userAddress1).address2(userAddress2)
                .build();

        return userService.userModify(userDTO);
    }

    // 4. 회원 탈퇴
    public boolean revokeUser(UserDTO userDTO) {
        // 기존 DB에 있는 사용자 정보 호출 : user
        UserDTO user = userService.searchOne(userDTO.getUserEmail());
        // DB에 있는 pw와 userDTO에 있는 pw를 비교해서 같으면 삭제
        if (user.getUserPw().equals(userDTO.getUserPw())) {
            return userService.userDelete(userDTO);
        }
        return false;
    }

    // 주문 처리는 회원 로그인 상태엣 처리.
    // 5. 주문 처리(생성)
    public boolean createOrder(UserDTO userDTO, String orderList, int price) {
        // num : 전체 주문에서 정보 얻기
        // DB에서 오늘 날짜의 주문 갯수 얻어와서
        // orderNum 생성
        // 지금은 임의로 생성
        int num = (int) (Math.random() * 100) + 1;
        OrderDTO orderDTO = OrderDTO.builder()
                .orderList(orderList).orderNum(num)
                .price(price)
                .build();
        return orderService.createOrder(orderDTO, userDTO);
    }

    // 6. 주문 조회
    public List<OrderDTO> getOrders(UserDTO userDTO) {
        return orderService.findList(userDTO);
    }

    // 7. 주문 수정
    public boolean modifyOrder(UserDTO userDTO, OrderDTO modify) {
        return orderService.modifyOrder(modify, userDTO);
    }

    // 8. 주문 삭제
    public boolean removeOrder(UserDTO userDTO, OrderDTO delete) {
        return orderService.deleteOrder(delete, userDTO);
    }

    // 9. 로그인
    public UserDTO login(String userId, String userPw) {
        return userService.login(userId, userPw);
    }
}

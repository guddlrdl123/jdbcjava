package dbutil.test;

import dto.OrderDTO;
import dto.UserDTO;
import service.ordermanage.Ordermanage;
import service.ordermanage.OrdermanageImpl;

public class ServiceOrderTest {
    private static Ordermanage ordermanage = new OrdermanageImpl();

    public static void main(String[] args) {
        ordermanage.createOrder(OrderDTO.builder()
                .orderList("탕수육")
                .price(35000)
                .orderNum(1)
                .userId("managetest")
                .build(),
                UserDTO.builder()
                        .userId("managetest")
                        .build());
    }
}

package dbutil.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import domain.orders.OrdersVO;
import repository.Orders;
import repository.OrdersDAOimpl;

public class RepositoryOrdersTest {

    // 테스트할 객체
    private static Orders ordersRepo = new OrdersDAOimpl();

    public static void main(String[] args) {
        // 입력
        OrdersVO order = OrdersVO.builder()
                .orderList("볶음밥").price(10000).orderNum(1).userId("testuser").build();

        if (insertTest(order)) {
            System.out.println("추가 성공!");
        } else
            System.out.println("추가 실패!");

        // 검색
        OrdersVO searchResult = searchOrdersVO(1);
        System.out.println(searchResult);

        // 수정
        searchResult.setOrderList("짬짜면");
        searchResult.setPrice(12000);
        if (modifyTest(searchResult)) {
            System.out.println("수정 성공!");
        } else
            System.out.println("수정 실패!");

        // 날짜로 검색
        Calendar cal = Calendar.getInstance(); // Calender 객체 생성
        cal.set(2026, 1, 9, 0, 0, 0); // 날짜 수정
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 문자 형식을 지정
        // 전달할 날짜 문자열 출력해서 확인
        System.out.println(sf.format(cal.getTime()));
        // 메서드에 날짜 문자열을 전달.
        List<OrdersVO> list = searchDate(sf.format(cal.getTime()));
        list.stream().forEach(System.out::println);

        // 삭제
        delOrderResult(12);
        if (delOrderResult(12)) {
            System.out.println("삭제 성공!");
        } else
            System.out.println("삭제 실패!");
    }

    public static boolean insertTest(OrdersVO order) {
        return ordersRepo.insertOrder(order);
    }

    public static OrdersVO searchOrdersVO(int orderNum) {
        return ordersRepo.ordersSearch(orderNum).get();
    }

    // 수정은 먼저 select를 통한 객체를 얻어온 다음에 수정.
    public static boolean modifyTest(OrdersVO modify) {
        return ordersRepo.modifyOrder(modify);
    }

    public static List<OrdersVO> searchDate(String dateStr) {
        return ordersRepo.ordersSearchDate(dateStr); // ordersRepo의 날짜 검색 메서드 호출
    }

    public static boolean delOrderResult(long id) {
        return ordersRepo.deleteOrder(id);
    }

}

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import controller.OrderProgramController;
import dto.OrderDTO;
import dto.UserDTO;
import service.usermanage.Usermanage;
import service.usermanage.UsermanageImpl;

public class App {
    // 사용자 입력을 위한 도구
    private static Scanner sc = new Scanner(System.in, "cp949");
    // Controller 레이어
    private static OrderProgramController controller = new OrderProgramController();

    // 로그인 정보를 저장한 변수
    private static UserDTO userInfo = null; // 로그인 하면 정보를 추가, 로그아웃하면 null로 변경

    public static void main(String[] args) throws Exception {
        System.out.println("[고객 주문 관리 프로그램]");
        menu();
    }

    public static void menu() { // 메인 메뉴(View)
        while (true) {

            System.out.println("1. 회원 관리");
            System.out.println("2. 주문 관리");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 가입, 로그인 정보를 출력하는 메뉴 메서드 호출
                    System.out.println("[회원 가입 하위 메뉴]");
                    userManageMenu();
                    break;

                case '2':
                    // 주문 처리(회원), 주문 처리(비회원)
                    System.out.println("[주문 관리 하위 메뉴]");
                    userOrder();
                    break;

                case '0':
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return; // 프로세스 종료

                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해 주세요.");
                    break;
            }

        }
    }

    public static void userManageMenu() {

        while (true) {
            System.out.println("1) 회원 가입");
            System.out.println("2) 로그인");
            System.out.println("0) 메인으로 이동");
            char choice = sc.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    // 회원 가입 정보 입력 메서드 호출
                    System.out.println("[회원 가입 정보 처리 메서드]");
                    joinUser();
                    break;

                case '2':
                    // 로그인 처리 메서드 호출
                    System.out.println("[로그인 처리 메서드]");
                    login();
                    break;

                case '0':
                    System.out.println("메인으로 이동합니다.");
                    return;

                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해 주세요.");
                    break;
            }
        }
    }

    // 회원가입
    public static void joinUser() {

        while (true) {
            System.out.println("[회원가입 정보 처리]");
            System.out.print("ID 입력 : ");
            String userId = sc.next();
            System.out.print("PW 입력 : ");
            String userPw = sc.next();
            sc.nextLine();
            System.out.print("이름 입력 : ");
            String userName = sc.nextLine();
            System.out.print("이메일 입력 : ");
            String userEmail = sc.next();
            sc.nextLine();
            System.out.print("전화번호 입력 : ");
            String userPhone = sc.nextLine();
            System.out.print("나이 입력 : ");
            int userAge = sc.nextInt();
            sc.nextLine();
            System.out.print("주소1 입력(시, 구, 동) : ");
            String userAddress1 = sc.nextLine();
            System.out.print("주소2 입력(상세 주소) : ");
            String userAddress2 = sc.nextLine();
            System.out.println("[입력한 정보 확인]");
            System.out.println("ID : " + userId);
            System.out.println("PW : " + userPw);
            System.out.println("이름 : " + userName);
            System.out.println("이메일 : " + userEmail);
            System.out.println("전화번호 : " + userPhone);
            System.out.println("나이 : " + userAge);
            System.out.println("주소 : " + userAddress1 + "(" + userAddress2 + ")");
            System.out.println("입력한 정보로 회원가입 하시겠습니까?(y/n)");
            char done = sc.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                // 회원 가입 처리(controller)
                boolean status = controller.join(userId, userPw, userName, userEmail, userPhone, userAge, userAddress1,
                        userAddress2);
                if (status)
                    return; // 회원가입 메뉴 나가고, 실패면 다시 while문으로
                else
                    System.out.println("회원가입 실패");
                return;
            }
        }
    }

    // 로그인
    public static void login() {
        while (true) {
            System.out.println("[로그인]");
            System.out.print("ID 입력 : ");
            String userId = sc.next();
            System.out.print("PW 입력 : ");
            String userPw = sc.next();
            System.out.println("로그인 하시겠습니까?(y/n)");
            sc.nextLine();
            char done = sc.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                userInfo = controller.login(userId, userPw);
                // controller를 통한 login 작업
                // 로그인 성공시 : 정보 확인, 수정, 탈퇴 메뉴를 연결 - 메서드
                // 로그인 실패시 : 아이디 또는 패스워드가 다릅니다.
                // 다시 입력 반복(계속여부확인)
                if (userInfo != null && !userInfo.getUserId().isEmpty()) {
                    userManage();
                } else {
                    System.out.println("아이디 또는 패스워드가 다릅니다.");
                }
            }
            System.out.print("이전 메뉴로 이동하겠습니까? (y/n) ");
            done = sc.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                return;
            }

        }
    }

    // 회원 정보 관리
    public static void userManage() {
        while (true) {

            // controller를 통해서 정보 출력

            System.out.println("1) 회원 정보 확인");
            System.out.println("2) 회원 정보 수정");
            System.out.println("3) 회원 탈퇴");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 정보 출력
                    System.out.println("[회원 정보 확인]");
                    System.out.println("ID : " + userInfo.getUserId());
                    System.out.println("이름 : " + userInfo.getUserName());
                    System.out.println("이메일 : " + userInfo.getUserEmail());
                    System.out.println("전화번호 : " + userInfo.getPhone1() + "-" + userInfo.getPhone2());
                    System.out.println("나이 : " + userInfo.getAge());
                    System.out.println("주소 : " + userInfo.getAddress1() + "(" + userInfo.getAddress2() + ")");
                    break;

                case '2':
                    // 회원 정보 출력 후 수정
                    System.out.println("[회원 정보 수정]");
                    System.out.printf("ID() : " + userInfo.getUserId());
                    System.out.println();
                    // 패스워드 수정은 별도의 로직으로 구성해야 한다.
                    System.out.printf("PW(%s) : ", userInfo.getUserPw());
                    String userPw = sc.next();
                    sc.nextLine();
                    System.out.printf("이름(%s) : ", userInfo.getUserName());
                    String userName = sc.nextLine();
                    System.out.printf("이메일(%s) : ", userInfo.getUserEmail());
                    String userEmaill = sc.nextLine();
                    System.out.printf("전화번호(%s-%s) : ", userInfo.getPhone1(), userInfo.getPhone2());
                    String userPhone = sc.nextLine();
                    System.out.printf("나이(%d) : ", userInfo.getAge());
                    int userAge = sc.nextInt();
                    sc.nextLine();
                    System.out.printf("주소1(%s) : ", userInfo.getAddress1());
                    String userAddress1 = sc.nextLine();
                    System.out.printf("주소2(%s) : ", userInfo.getAddress2());
                    String userAddress2 = sc.nextLine();
                    boolean status = controller.userModify(userInfo.getId(), userInfo.getUserId(), userPw, userName,
                            userEmaill,
                            userPhone, userAge, userAddress1, userAddress2);

                    if (status) {
                        System.out.println("회원 정보가 수정되었습니다.");
                        userInfo = controller.userInfo(userEmaill);
                    } else
                        System.out.println("수정 실패!");

                    break;

                case '3':
                    // 회원 정보 출력 후 삭제
                    System.out.println("[회원 탈퇴]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.print("회원 탈퇴하시겠습니까?(y/n)");
                    char done = sc.nextLine().toLowerCase().charAt(0);
                    if (done == 'y') {
                        System.out.print("사용자 PW() : ");
                        String pw = sc.next();
                        sc.nextLine();
                        // 회원 정보 넣어서 보낼 Pw
                        // 회원 정보 삭제를 위한 확인 처리할 패스워드.
                        userInfo.setUserPw(pw);
                        status = controller.revokeUser(userInfo);
                        if (status) { // 회원 탈퇴
                            System.out.println("회원 탈퇴 성공");
                            // 1. userInfo 정리 => null;
                            userInfo = null;
                            // 2. 이전 메뉴로 이동.
                            return;

                        } else { // 회원 탈퇴 실패
                            System.out.println("회원 탈퇴 실패!");
                        }
                    }
                    break;

                case '0':
                    // 이전 메뉴로 이동
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;

                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    // 주문
    public static void userOrder() {
        while (true) {
            System.out.println("1) 주문처리(회원)");
            System.out.println("2) 주문처리(비회원)");
            System.out.println("0) 이전 메뉴로 이동");
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("[주문 작업]");
                    orderManage();
                    // 주문 생성, 조회, 수정, 삭제
                    break;

                case '2':
                    System.out.println("작업X");
                    break;

                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;

                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    // 주문 생성
    public static void orderManage() {
        if (userInfo == null) {
            System.out.println("로그인 하셔야 합니다.");
            return;
        }
        // 주문 생성, 조회, 수정, 삭제 (회원인 경우 작업)
        boolean status = false;
        List<OrderDTO> list = null;
        while (true) {
            System.out.println("1) 주문 생성");
            System.out.println("2) 주문 조회");
            System.out.println("3) 주문 수정/삭제");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("[주문 생성]");
                    System.out.println("메뉴 입력(list) :");
                    String orderList = sc.nextLine();
                    System.out.println("가격 :");
                    int price = sc.nextInt();
                    sc.nextLine();
                    // controller에서 메뉴 처리
                    status = controller.createOrder(userInfo, orderList, price);
                    if (status) {
                        System.out.println("주문 생성 성공");
                    } else
                        System.out.println("주문 생성 실패!");
                    break;

                case '2':
                    // 주문 정보 읽어오기
                    // 반복문으로 처리. stream 또는 for문
                    System.out.println("[주문 조회]");
                    list = controller.getOrders(userInfo);
                    list.stream().forEach(System.out::println);
                    break;

                case '3':
                    // 주문 리스트 확인 -> 인덱스 번호 입력 -> 주문 삭제 처리(성공/실패)
                    System.out.println("[주문 삭제]");
                    System.out.println("============== 주문한 리스트 ==============");
                    list = controller.getOrders(userInfo);
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ":" + list.get(i));
                    }
                    // 주문삭제 처리
                    System.out.print("삭제할 번호(index)를 입력하세요");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    // 삭제 작업 진행
                    status = controller.removeOrder(userInfo, list.get(idx));
                    if (status) {
                        System.out.println("삭제 성공");
                    } else
                        System.out.println("삭제 실패!");

                    break;

                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;

                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }

        }
    }
}

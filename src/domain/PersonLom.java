package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Lombok에서 주로 사용하는 어노테이션
@Setter/@Getter : Getter/Setter 설정
@ToString : toString 메서드 생성.
@EqualsAndHashCode : equals()와 hashCode() 메서드를 자동 구현.
@Data : Getter, Setter, toString, equalsAndHashCode 어노테이션 기능을 모두 포함.
@Builder : 복잡한 객체 생성을 안정화 하는 Builder 패턴을 자동 생성.

@AllArgsConstructor : 멤버변수전체를 사용하는 생성자를 만들어줌.
@NoArgsConstructor : 기본 생성자를 만들어줌.

주의점 :
1. 무분별한 어노테이션의 사용으로 다른 기능과 연결되어 의도하지 않은 동작을 할 수 있음. (Data 어노테이션은 자중하는 것이 좋다.)
2. @Builder만 사용하면 기본 생성자는 생성되지 않는다.
    기본 생성자가 필요한 경우에는 @NoArgsConstructor를 사용한다.
    @AllArgsConstructor는 위에 @NoArgsConstructor를 사용하는 경우에 같이 사용한다.
3. Lombok에만 너무 의존하면 문제가 발생했을 때, 대처하기 어렵다. Lombok을 사용하지 못하는 경우에 대해서 대비할 필요가 있다.
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonLom {

    private int id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private int age;
    private String address1;
    private String address2;
    private Timestamp regDate;
    private Timestamp modifyDate;
}

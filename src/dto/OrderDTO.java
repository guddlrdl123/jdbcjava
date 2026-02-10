package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private long id;
    private String orderList;
    private int orderNum;
    private int price;
    private String date; // timestamp -> String
    private String userId;
}

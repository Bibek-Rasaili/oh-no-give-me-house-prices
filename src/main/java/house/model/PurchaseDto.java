package house.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PurchaseDto {
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private BigInteger transactionValue;

    static PurchaseDto from(Purchase purchase) {
        return new PurchaseDto(purchase.getDate(), purchase.getTransactionValue());
    }
}

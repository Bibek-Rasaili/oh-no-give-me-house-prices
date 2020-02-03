package house.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDto {
    private String ID;

    private String address;

    private String postCode;

    private List<PurchaseDto> purchases;

    private BigInteger price;

    public static HouseDto from (House house) {
        List<PurchaseDto> purchases = new ArrayList<>();
        house.getPurchases().forEach( purchase -> purchases.add(PurchaseDto.from(purchase)));

        return new HouseDto(
                house.getID(),
                house.getAddress(),
                house.getPostCode(),
                purchases,
                house.getPrice()
        );
    }
}

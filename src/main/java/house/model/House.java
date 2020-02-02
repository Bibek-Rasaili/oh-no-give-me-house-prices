package house.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "house_info")
public class House {
    @Id
    private String ID;

    @Indexed(unique=true)
    private String address;

    private String postCode;

    private List<Purchase> purchases;

    private BigInteger price;

    public House(String address, String postCode, List<Purchase> purchases, BigInteger price) {
        this.address = address;
        this.postCode = postCode;
        this.purchases = purchases;
        this.price = price;
    }
}


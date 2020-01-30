package house.info.houseinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "House-info")
public class House {
    @Id
    private BigInteger id;

    private String address;

    private String postCode;

    private Purchases purchases;

    private BigInteger prices;
}


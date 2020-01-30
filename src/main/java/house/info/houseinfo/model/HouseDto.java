package house.info.houseinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString // probably don't need to toString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

// https://github.com/khandelwal-arpit/springboot-starterkit
public class HouseDto {

    private String address;

    private String postCode;

    private Purchases purchases;

    private BigInteger prices;
}

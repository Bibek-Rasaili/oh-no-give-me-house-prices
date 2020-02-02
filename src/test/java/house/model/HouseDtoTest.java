package house.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseDtoTest {

    @BeforeEach
    void setUp() {
    }

    String houseID = "houseID";
    Date date = new Date(System.currentTimeMillis());
    private BigInteger bigIntegerValue = BigInteger.valueOf(98767897);
    String address = "address";
    String postCode = "postCode";
    private List<PurchaseDto> purchaseDtos = new ArrayList<>(){{
        add(new PurchaseDto(date,  bigIntegerValue));
    }};
    private List<Purchase> purchases = new ArrayList<>(){{
        add(new Purchase(date, bigIntegerValue));
    }};
    House house = new House(houseID, address, postCode, purchases, bigIntegerValue);
    HouseDto houseDto = new HouseDto(houseID, address, postCode, purchaseDtos, bigIntegerValue);

    @Test
    void fromShouldReturnAHouseDto() {
        String houseID = "houseID";

        House house = new House(houseID, address, postCode, purchases, bigIntegerValue);
        HouseDto houseDto = new HouseDto(houseID, address, postCode, purchaseDtos, bigIntegerValue);
        HouseDto result = HouseDto.from(house);

        assertEquals(houseDto.getAddress(), result.getAddress());
        assertEquals(houseDto.getPostCode(), result.getPostCode());
        assertEquals(houseDto.getPrice(), result.getPrice());
        assertEquals(houseDto.getPurchases().size(), result.getPurchases().size());
        assertEquals(houseDto.getPurchases().get(0).getDate(), result.getPurchases().get(0).getDate());
    }
}
package house.service;

import house.model.House;
import house.model.HouseDto;
import house.model.Purchase;
import house.model.PurchaseDto;
import house.repository.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class HouseServiceTest {
    @Mock
    HouseRepository houseRepository;

    HouseService houseService;

    Date date = new Date(System.currentTimeMillis());
    String houseID = "houseID";
    String address = "address";
    String postCode = "postCode";
    private List<Purchase> purchases = new ArrayList<>(){{
        add(new Purchase(date, bigIntegerValue));
    }};

    private BigInteger bigIntegerValue = BigInteger.valueOf(98767897);

    @BeforeEach
    void setUp() {
        this.houseService = new HouseService(houseRepository);
    }

    @Test
    void getHouseShouldReturnHouseDto() {
        House house = new House(houseID, address, postCode, purchases, bigIntegerValue);
        HouseDto houseDto = HouseDto.from(house);

        when(houseRepository.findByID(houseID)).thenReturn(house);

        HouseDto result = houseService.getHouse(houseID);
        assertEquals(houseDto, result);
    }

    @Test
    void addHouses() {
    }

    @Test
    void getHousesShouldReturnHouseDtos() {


    }

    @Test
    void getHousesSoldInDateRange() {
    }
}
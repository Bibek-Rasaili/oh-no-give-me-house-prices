package house.service;

import house.model.House;
import house.model.HouseDto;
import house.model.Purchase;
import house.repository.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseServiceTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    HouseRepository houseRepository;

    HouseService houseService;

    Date date = new Date(System.currentTimeMillis());
    String houseID = "houseID";
    String address = "address";
    String postCode = "postCode";
    private List<Purchase> purchases = new ArrayList<>(){{
        add(new Purchase(date, bigIntegerValue));
    }};
    BigInteger bigIntegerValue = BigInteger.valueOf(98767897);
    String date1;
    String date2;

    House house = new House(houseID, address, postCode, purchases, bigIntegerValue);
    HouseDto houseDto = HouseDto.from(house);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.houseService = new HouseService(houseRepository);
        this.date1 = "1994-10-08";
        this.date2 = "1994-10-10";
    }

    @Test
    void getHouseShouldReturnHouseDto() {
        when(houseRepository.findByID(houseID)).thenReturn(house);

        HouseDto result = houseService.getHouse(houseID);
        assertEquals(houseDto.getID(), result.getID());
        assertEquals(houseDto.getAddress(), result.getAddress());
        assertEquals(houseDto.getPostCode(), result.getPostCode());
        assertEquals(houseDto.getPurchases().size(), result.getPurchases().size());
        assertEquals(houseDto.getPrice(), result.getPrice());
    }

    @Test
    void getHousesShouldReturnHouseDtos() {
        List<House> houses = new ArrayList<>(){{
            add(house);
        }};
        when(houseRepository.findAll()).thenReturn(houses);

        List<HouseDto> result = houseService.getHouses();
        assertEquals(houseDto.getID(), result.get(0).getID());
        assertEquals(houseDto.getAddress(), result.get(0).getAddress());
        assertEquals(houseDto.getPostCode(), result.get(0).getPostCode());
        assertEquals(houseDto.getPurchases().size(), result.get(0).getPurchases().size());
        assertEquals(houseDto.getPrice(), result.get(0).getPrice());
    }

    @Test
    void getHousesSoldInDateRangewhenNoHousesareSoldWithinTheDatesProvided() throws ParseException {
        List<House> houses = new ArrayList<>(){{
            add(house);
        }};
        when(houseRepository.findAll()).thenReturn(houses);

        List<HouseDto> result = houseService.getHousesSoldInDateRange(date1, date2);
        assertEquals(0, result.size());
    }
}
package house.controller;

import house.model.HouseDto;
import house.model.PurchaseDto;
import house.service.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class HouseResourceControllerTest {

    @Mock
    HouseService houseService;

    private HouseResourceController houseResourceController;
    private String houseID;
    private HouseDto houseDto;
    private List<HouseDto> houseDtos;
    String date1;
    String date2;

    private Date date = new Date(System.currentTimeMillis());
    BigInteger bigIntegerValue = BigInteger.valueOf(98767897);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.houseResourceController = new HouseResourceController(houseService);
        this.houseID = "houseID";
        List<PurchaseDto> purchaseDtos = new ArrayList<>() {{
            add(new PurchaseDto(date, bigIntegerValue));
        }};
        this.houseDto = new HouseDto(houseID, "address", "postcode", purchaseDtos, bigIntegerValue);
        this.houseDtos = new ArrayList<>(){{
            add(houseDto);
        }};
        this.date1 = "1994-10-08";
        this.date2 = "1994-10-10";
    }

    @Test
    void getHouseShouldReturnHouseDto() {
        when(houseService.getHouse(houseID)).thenReturn(houseDto);

        ResponseEntity<HouseDto> result = houseResourceController.getHouse(houseID);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(houseDto, result.getBody());
    }

    @Test
    void getHousesShouldReturnHouseDtos() {
        when(houseService.getHouses()).thenReturn(houseDtos);

        ResponseEntity<List<HouseDto>> result = houseResourceController.getHouses();
        assertEquals(200, result.getStatusCode().value());
        assertEquals(houseDtos, result.getBody());
    }

    @Test
    void getHouseWithSalesWithinDatesShouldReturnHouseDtos() throws ParseException {
        when(houseService.getHousesSoldInDateRange(date1, date2)).thenReturn(houseDtos);

        ResponseEntity<List<HouseDto>> result = houseResourceController.getHouseWithSalesWithinDates(date1, date2);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(houseDtos, result.getBody());
    }

    @Test
    void getHouseWithSalesWithinDatesShouldThrowParseException() throws ParseException {
        Exception e = new ParseException("parse error", 500);
        when(houseService.getHousesSoldInDateRange(date1, date2))
                .thenThrow(e);

        ParseException exception = assertThrows(ParseException.class, () ->
                houseResourceController.getHouseWithSalesWithinDates(date1, date2));
        assertEquals("parse error", exception.getMessage());
    }
}
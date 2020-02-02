package house.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseDtoTest {

    @BeforeEach
    void setUp() {
    }

    Date date = new Date(System.currentTimeMillis());
    private BigInteger bigIntegerValue = BigInteger.valueOf(98767897);

    @Test
    void fromShouldReturnAPurchaseDto() {

        Purchase purchase = new Purchase(date, bigIntegerValue);
        PurchaseDto purchaseDto = new PurchaseDto(date, bigIntegerValue);
        PurchaseDto result = PurchaseDto.from(purchase);

        assertEquals(purchaseDto.getDate(), result.getDate());
        assertEquals(purchaseDto.getTransactionValue(), result.getTransactionValue());
    }
}
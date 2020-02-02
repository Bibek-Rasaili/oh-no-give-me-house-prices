package house.service;

import house.model.House;
import house.model.HouseDto;
import house.model.Purchase;
import house.model.PurchaseDto;
import house.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService (HouseRepository houseRepository) { this.houseRepository = houseRepository; }

    public HouseDto getHouse (String ID) { return HouseDto.from(houseRepository.findByID(ID)); }

    public void addHouses (List<HouseDto> houses) {
        if (houses.size() > 0) {
            List<Purchase> purchases = new ArrayList<>();
            houses.forEach(houseDto -> {
                houseDto.getPurchases().forEach(purchaseDto -> {
                    purchases.add(new Purchase(purchaseDto.getDate(), purchaseDto.getTransactionValue()));
                });
                houseRepository.insert(
                        new House(
                                houseDto.getAddress(),
                                houseDto.getPostCode(),
                                purchases,
                                houseDto.getPrice()));
            });
        }
    }

    public List<HouseDto> getHouses () {
        List<HouseDto> houseDtos = new ArrayList<>();
        houseRepository.findAll().forEach(house -> houseDtos.add(HouseDto.from(house)));
        return houseDtos;
    }

    public List<HouseDto> getHousesSoldInDateRange(String firstDate, String secondDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(firstDate);
        Date date2 = formatter.parse(secondDate);
        List<HouseDto> housesWithSalesInDateRange = new ArrayList<>();
        houseRepository.findAll().forEach( house -> {
            List<PurchaseDto> purchasesWithTheDateRange = new ArrayList<>();
            house.getPurchases().forEach(purchase -> {
                Date dateOfPurchase = purchase.getDate();
                if (date1.compareTo(dateOfPurchase) < 0 && date2.compareTo(dateOfPurchase) > 0) {
                    purchasesWithTheDateRange.add(new PurchaseDto(purchase.getDate(), purchase.getTransactionValue()));
                }
            });
            if (purchasesWithTheDateRange.size() > 0 ) {
                housesWithSalesInDateRange.add(
                        new HouseDto(house.getID(), house.getAddress(), house.getPostCode(), purchasesWithTheDateRange,house.getPrice()));
            }
        });
        return housesWithSalesInDateRange;
    }
}

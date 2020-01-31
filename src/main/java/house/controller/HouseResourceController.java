package house.controller;

import house.model.HouseDto;
import house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class HouseResourceController {

    private final HouseService houseService;

    @Autowired
    public HouseResourceController(HouseService houseService) { this.houseService = houseService; }

    @GetMapping(value = "/house/{houseID}")
    public ResponseEntity<HouseDto> getHouse (@PathVariable String houseID) {
        HouseDto house = houseService.getHouse(houseID);
        return ResponseEntity.ok(house);
    }

    @PostMapping(value = "/houses")
    public ResponseEntity addHouse (@RequestBody List<HouseDto> house) {
        houseService.addHouses(house);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/houses")
    public ResponseEntity<List<HouseDto>> getHouses () { return ResponseEntity.ok(houseService.getHouses()); }

    @GetMapping(value = "/houses/{date1}/{date2}")
    public ResponseEntity<List<HouseDto>> getHouseWithSalesWithinDates (@PathVariable String date1, @PathVariable String date2) throws ParseException {
        return ResponseEntity.ok(houseService.getHousesSoldInDateRange(date1, date2));
    }
}

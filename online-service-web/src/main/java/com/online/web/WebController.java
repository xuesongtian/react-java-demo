package com.online.web;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leifh
 * @date 2024/5/15
 * @description 前端控制器
 */
@RestController
@RequestMapping("file")
public class WebController {

    @GetMapping("mobile")
    public List<MobileFood> list() throws IOException {
        List<MobileFood> list = new ArrayList<>();
        InputStream in = this.getClass().getResourceAsStream("/static/mobile.csv");
        CSVUtils.readCSV(new InputStreamReader(in), records -> {
            for (CSVRecord record : records) {
                MobileFood mobileFood = new MobileFood();
                mobileFood.setLocationid(record.get("locationid"));
                mobileFood.setApplicant(record.get("Applicant"));
                mobileFood.setFacilitytype(record.get("FacilityType"));
                mobileFood.setCnn(record.get("cnn"));
                mobileFood.setLocationdescription(record.get("LocationDescription"));
                mobileFood.setAddress(record.get("Address"));
                mobileFood.setBlocklot(record.get("blocklot"));
                mobileFood.setBlock(record.get("block"));
                mobileFood.setLot(record.get("lot"));
                mobileFood.setPermit(record.get("permit"));
                mobileFood.setStatus(record.get("Status"));
                mobileFood.setFooditems(record.get("FoodItems"));
                mobileFood.setX(record.get("X"));
                mobileFood.setY(record.get("Y"));
                mobileFood.setLatitude(record.get("Latitude"));
                mobileFood.setLongitude(record.get("Longitude"));
                mobileFood.setSchedule(record.get("Schedule"));
                mobileFood.setDayshours(record.get("dayshours"));
                mobileFood.setNoisent(record.get("NOISent"));
                mobileFood.setApproved(record.get("Approved"));
                mobileFood.setReceived(record.get("Received"));
                mobileFood.setPriorpermit(record.get("PriorPermit"));
                mobileFood.setExpirationdate(record.get("ExpirationDate"));
                mobileFood.setLocation(record.get("Location"));
                mobileFood.setFire_prevention_districts(record.get("Fire Prevention Districts"));
                mobileFood.setPolice_districts(record.get("Police Districts"));
                mobileFood.setSupervisor_districts(record.get("Supervisor Districts"));
                mobileFood.setZip_codes(record.get("Zip Codes"));
                mobileFood.setNeighborhoods(record.get("Neighborhoods (old)"));
                list.add(mobileFood);
            }
            return true;
        });
        return list;
    }


}

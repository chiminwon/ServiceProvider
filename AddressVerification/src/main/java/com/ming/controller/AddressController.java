package com.ming.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ming.vo.AddVerReq;
import com.ming.vo.AddVerRes;
import com.ming.vo.AddressSuggestionVO;
import com.ming.vo.AddressVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

  public static final String EXACTMATCH = "ExactMatch";
  public static final String APPROXIMATEMATCH = "ApproximateMatch";
  public static final String NOMATCH = "NoMatch";
  public static final String GOOD = "GOOD";

  @GetMapping("/verify")
  public JSONObject addressVerification(@RequestParam(value = "Option") String option) {
    System.out.println("RequestParam is : " + option);
    JSONObject result;

    JSONObject exactMatch = new JSONObject();
    exactMatch.put("MessageCode", "ExactMatch");
    JSONObject addressJson = new JSONObject();
    addressJson.put("Addr1", "1 S Roselle Rd ");
    addressJson.put("City", "Schaumburg");
    addressJson.put("StateProvCd", "IL");
    addressJson.put("PostalCode", "60193");
    addressJson.put("Plus4", "1638");
    addressJson.put("County", "County");
    exactMatch.put("Addr", addressJson);

    JSONObject approximateMatch = new JSONObject();
    approximateMatch.put("MessageCode", "ApproximateMatch");
    JSONObject addSugJson1 = new JSONObject();
    addSugJson1.put("AddressRange", "1501");
    addSugJson1.put("PreDirection", "S");
    addSugJson1.put("StreetName", "WOLF");
    addSugJson1.put("StreetType", "RD");
    addSugJson1.put("SuiteName", "OFC");
    addSugJson1.put("SuiteRange", "1");
    addSugJson1.put("City", "Prospect heights");
    addSugJson1.put("StateProvCd", "IL");
    addSugJson1.put("PostalCode", "60070");
    addSugJson1.put("Plus4", "1373");

    JSONObject addSugJson2 = new JSONObject();
    addSugJson2.put("AddressRange", "1501");
    addSugJson2.put("PreDirection", "S");
    addSugJson2.put("StreetName", "WOLF");
    addSugJson2.put("StreetType", "RD");
    addSugJson2.put("SuiteName", "APT");
    addSugJson2.put("SuiteRange", "120 -  127");
    addSugJson2.put("City", "Prospect heights");
    addSugJson2.put("StateProvCd", "IL");
    addSugJson2.put("PostalCode", "60070");
    addSugJson2.put("Plus4", "1373");

    JSONArray jsonArray = new JSONArray();
    jsonArray.add(addSugJson1);
    jsonArray.add(addSugJson2);
    approximateMatch.put("AddressSuggestionVO", jsonArray);

    JSONObject noMatch = new JSONObject();
    noMatch.put("MessageCode", "NoMatch");
    noMatch.put("ResultCd", "GOOD");

    switch (option) {
      case "A":
        result = exactMatch;
        break;
      case "B":
        result = approximateMatch;
        break;
      case "C":
        result = noMatch;
        break;
      default:
        result = noMatch;
    }
    return result;
  }

  @PostMapping("/verify")
  public AddVerRes verify(@RequestBody AddVerReq addVerReq) {

    AddVerRes addVerRes = new AddVerRes();
    System.out.println("RequestBody is : " + addVerReq);
    if (null != addVerReq && null != addVerReq.getAddressVO()) {
      AddressVO addressVO = addVerReq.getAddressVO();
      String addr1 = addressVO.getAddr1();
      if (null != addr1) {
        switch (addr1) {
          case "1 S Roselle Rd":
            addVerRes.setMessageCode(EXACTMATCH);
            addVerRes.setAddressVO(addressVO);
            break;
          case "2 S Roselle Rd":
            List<AddressSuggestionVO> addSugVOList = new ArrayList<>();
            AddressSuggestionVO addSugVO = new AddressSuggestionVO();
            for (int i = 1; i < 6; i++) {
              addSugVO.setAddressRange("1501");
              addSugVO.setPreDirection("S");
              addSugVO.setStreetName("WOLF");
              addSugVO.setStreetType("RD");
              addSugVO.setStreetType("RD");
              addSugVO.setSuiteName("APT");
              addSugVO.setSuiteRange(i + "");
              addSugVO.setCity("Prospect heights");
              addSugVO.setStateProvCd("IL");
              addSugVO.setPostalCode("60070");
              addSugVO.setPlus4("1373");
              addSugVOList.add(addSugVO);
            }
            addVerRes.setMessageCode(APPROXIMATEMATCH);
            addVerRes.setAddSugVOList(addSugVOList);
            break;
          default:
            addVerRes.setMessageCode(NOMATCH);
            addVerRes.setResultCd(GOOD);
            break;
        }
      }
    }
    System.out.println("ResponseBody is : " + addVerRes);
    return addVerRes;
  }
}

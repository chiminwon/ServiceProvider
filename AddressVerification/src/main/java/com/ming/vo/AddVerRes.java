package com.ming.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddVerRes {

  @JsonProperty("MessageCode")
  private String messageCode;

  @JsonProperty("Addr")
  private AddressVO addressVO;

  @JsonProperty("AddressSuggestions")
  private List<AddressSuggestionVO> addSugVOList;

  @JsonProperty("ResultCd")
  private String resultCd;
}

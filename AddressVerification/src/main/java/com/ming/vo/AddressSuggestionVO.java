package com.ming.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressSuggestionVO {

  @JsonProperty("AddressRange")
  private String addressRange;

  @JsonProperty("PreDirection")
  private String preDirection;

  @JsonProperty("StreetName")
  private String streetName;

  @JsonProperty("StreetType")
  private String streetType;

  @JsonProperty("SuiteName")
  private String suiteName;

  @JsonProperty("SuiteRange")
  private String suiteRange;

  @JsonProperty("City")
  private String city;

  @JsonProperty("StateProvCd")
  private String stateProvCd;

  @JsonProperty("PostalCode")
  private String postalCode;

  @JsonProperty("Plus4")
  private String plus4;
}

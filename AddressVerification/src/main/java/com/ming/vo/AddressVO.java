package com.ming.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
// 序列化、反序列化忽略的属性，多个时用“,”隔开
// @JsonIgnoreProperties({"captcha"})
// 当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressVO {

  // 序列化、反序列化时，属性的名称
  @JsonProperty("Plus4")
  private String plus4;

  @JsonProperty("Addr1")
  private String addr1;

  @JsonProperty("PostalCode")
  private String postalCode;

  @JsonProperty("City")
  private String city;

  @JsonProperty("StateProvCd")
  private String stateProvCd;

  @JsonProperty("County")
  private String county;

  /*    // 为反序列化期间要接受的属性定义一个或多个替代名称，可以与@JsonProperty一起使用
  @JsonAlias({"pass_word", "passWord"})
  @JsonProperty("pwd")
  private String password;

  //序列化、反序列化时，格式化时间
  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  //序列化、反序列化忽略属性
  @JsonIgnore
  private String captcha;*/
}

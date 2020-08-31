/*
 * Anchor API
 * # Introduction 이 문서는 KAS\\(Klaytn API Service\\)의 Anchor API를 소개하는 문서입니다. Anchor API는 서비스 체인 데이터의 신뢰성을 보장하기 위해 데이터 신뢰성을 증명할 수 있는 메타데이터를 Klaytn 메인 체인에 전송하는 기능을 제공합니다.  자세한 사용 예시는 [튜토리얼](링크)를 확인하십시오.  
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api.anchor.v1.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Anchor 트랜잭션 목록
 */
@ApiModel(description = "Anchor 트랜잭션 목록")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-08-27T05:34:32.459Z")



public class AnchorTransactions {
  @SerializedName("cursor")
  private String cursor = null;

  @SerializedName("txs")
  private List<AnchorTransaction> txs = null;

  public AnchorTransactions cursor(String cursor) {
    this.cursor = cursor;
    return this;
  }

   /**
   * 이전 조회에서 마지막으로 조회된 문서
   * @return cursor
  **/
  @ApiModelProperty(required = true, value = "이전 조회에서 마지막으로 조회된 문서")
  public String getCursor() {
    return cursor;
  }

  public void setCursor(String cursor) {
    this.cursor = cursor;
  }

  public AnchorTransactions txs(List<AnchorTransaction> txs) {
    this.txs = txs;
    return this;
  }

  public AnchorTransactions addTxsItem(AnchorTransaction txsItem) {
    if (this.txs == null) {
      this.txs = new ArrayList<AnchorTransaction>();
    }
    this.txs.add(txsItem);
    return this;
  }

   /**
   * Get txs
   * @return txs
  **/
  @ApiModelProperty(value = "")
  public List<AnchorTransaction> getTxs() {
    return txs;
  }

  public void setTxs(List<AnchorTransaction> txs) {
    this.txs = txs;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnchorTransactions anchorTransactions = (AnchorTransactions) o;
    return Objects.equals(this.cursor, anchorTransactions.cursor) &&
        Objects.equals(this.txs, anchorTransactions.txs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cursor, txs);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnchorTransactions {\n");
    
    sb.append("    cursor: ").append(toIndentedString(cursor)).append("\n");
    sb.append("    txs: ").append(toIndentedString(txs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

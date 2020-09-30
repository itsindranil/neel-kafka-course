
package com.neel.kafkacourse.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "InventoryID",
    "NewsType",
    "custName",
    "custAddress",
    "reg_name",
    "subreg_name",
    "txnID",
    "custID",
    "regID",
    "txn_val"
})
public class AdOutSchema {

    @JsonProperty("InventoryID")
    private String inventoryID;
    @JsonProperty("NewsType")
    private String newsType;
    @JsonProperty("custName")
    private String custName;
    @JsonProperty("custAddress")
    private String custAddress;
    @JsonProperty("reg_name")
    private String regName;
    @JsonProperty("subreg_name")
    private String subregName;
    @JsonProperty("txnID")
    private String txnID;
    @JsonProperty("custID")
    private String custID;
    @JsonProperty("regID")
    private String regID;
    @JsonProperty("txn_val")
    private Object txnVal;

    @JsonProperty("InventoryID")
    public String getInventoryID() {
        return inventoryID;
    }

    @JsonProperty("InventoryID")
    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public AdOutSchema withInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
        return this;
    }

    @JsonProperty("NewsType")
    public String getNewsType() {
        return newsType;
    }

    @JsonProperty("NewsType")
    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public AdOutSchema withNewsType(String newsType) {
        this.newsType = newsType;
        return this;
    }

    @JsonProperty("custName")
    public String getCustName() {
        return custName;
    }

    @JsonProperty("custName")
    public void setCustName(String custName) {
        this.custName = custName;
    }

    public AdOutSchema withCustName(String custName) {
        this.custName = custName;
        return this;
    }

    @JsonProperty("custAddress")
    public String getCustAddress() {
        return custAddress;
    }

    @JsonProperty("custAddress")
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public AdOutSchema withCustAddress(String custAddress) {
        this.custAddress = custAddress;
        return this;
    }

    @JsonProperty("reg_name")
    public String getRegName() {
        return regName;
    }

    @JsonProperty("reg_name")
    public void setRegName(String regName) {
        this.regName = regName;
    }

    public AdOutSchema withRegName(String regName) {
        this.regName = regName;
        return this;
    }

    @JsonProperty("subreg_name")
    public String getSubregName() {
        return subregName;
    }

    @JsonProperty("subreg_name")
    public void setSubregName(String subregName) {
        this.subregName = subregName;
    }

    public AdOutSchema withSubregName(String subregName) {
        this.subregName = subregName;
        return this;
    }

    @JsonProperty("txnID")
    public String getTxnID() {
        return txnID;
    }

    @JsonProperty("txnID")
    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public AdOutSchema withTxnID(String txnID) {
        this.txnID = txnID;
        return this;
    }

    @JsonProperty("custID")
    public String getCustID() {
        return custID;
    }

    @JsonProperty("custID")
    public void setCustID(String custID) {
        this.custID = custID;
    }

    public AdOutSchema withCustID(String custID) {
        this.custID = custID;
        return this;
    }

    @JsonProperty("regID")
    public String getRegID() {
        return regID;
    }

    @JsonProperty("regID")
    public void setRegID(String regID) {
        this.regID = regID;
    }

    public AdOutSchema withRegID(String regID) {
        this.regID = regID;
        return this;
    }

    @JsonProperty("txn_val")
    public Object getTxnVal() {
        return txnVal;
    }

    @JsonProperty("txn_val")
    public void setTxnVal(Object txnVal) {
        this.txnVal = txnVal;
    }

    public AdOutSchema withTxnVal(Object txnVal) {
        this.txnVal = txnVal;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("inventoryID", inventoryID).append("newsType", newsType).append("custName", custName).append("custAddress", custAddress).append("regName", regName).append("subregName", subregName).append("txnID", txnID).append("custID", custID).append("regID", regID).append("txnVal", txnVal).toString();
    }

}

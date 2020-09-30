
package com.neel.kafkacourse.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "txnID",
    "InventoryID",
    "custID",
    "regID",
    "txn_val"
})
public class AdTxn {

    @JsonProperty("txnID")
    private String txnID;
    @JsonProperty("InventoryID")
    private String inventoryID;
    @JsonProperty("custID")
    private String custID;
    @JsonProperty("regID")
    private String regID;
    @JsonProperty("txn_val")
    private Object txnVal;

    @JsonProperty("txnID")
    public String getTxnID() {
        return txnID;
    }

    @JsonProperty("txnID")
    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public AdTxn withTxnID(String txnID) {
        this.txnID = txnID;
        return this;
    }

    @JsonProperty("InventoryID")
    public String getInventoryID() {
        return inventoryID;
    }

    @JsonProperty("InventoryID")
    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public AdTxn withInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
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

    public AdTxn withCustID(String custID) {
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

    public AdTxn withRegID(String regID) {
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

    public AdTxn withTxnVal(Object txnVal) {
        this.txnVal = txnVal;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("txnID", txnID).append("inventoryID", inventoryID).append("custID", custID).append("regID", regID).append("txnVal", txnVal).toString();
    }

}

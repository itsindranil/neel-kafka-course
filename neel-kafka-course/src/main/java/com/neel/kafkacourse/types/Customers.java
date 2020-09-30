
package com.neel.kafkacourse.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "custID",
    "name",
    "address"
})
public class Customers {

    @JsonProperty("custID")
    private String custID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;

    @JsonProperty("custID")
    public String getCustID() {
        return custID;
    }

    @JsonProperty("custID")
    public void setCustID(String custID) {
        this.custID = custID;
    }

    public Customers withCustID(String custID) {
        this.custID = custID;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Customers withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    public Customers withAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("custID", custID).append("name", name).append("address", address).toString();
    }

}


package com.neel.kafkacourse.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "regID",
    "reg_name",
    "subreg_name"
})
public class Regions {

    @JsonProperty("regID")
    private String regID;
    @JsonProperty("reg_name")
    private String regName;
    @JsonProperty("subreg_name")
    private String subregName;

    @JsonProperty("regID")
    public String getRegID() {
        return regID;
    }

    @JsonProperty("regID")
    public void setRegID(String regID) {
        this.regID = regID;
    }

    public Regions withRegID(String regID) {
        this.regID = regID;
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

    public Regions withRegName(String regName) {
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

    public Regions withSubregName(String subregName) {
        this.subregName = subregName;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("regID", regID).append("regName", regName).append("subregName", subregName).toString();
    }

}

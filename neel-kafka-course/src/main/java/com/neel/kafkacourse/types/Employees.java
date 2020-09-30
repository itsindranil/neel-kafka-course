
package com.neel.kafkacourse.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "empid",
    "name",
    "department",
    "salary"
})
public class Employees {

    @JsonProperty("empid")
    private String empid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("department")
    private String department;
    @JsonProperty("salary")
    private Integer salary;

    @JsonProperty("empid")
    public String getEmpid() {
        return empid;
    }

    @JsonProperty("empid")
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public Employees withEmpid(String empid) {
        this.empid = empid;
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

    public Employees withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String department) {
        this.department = department;
    }

    public Employees withDepartment(String department) {
        this.department = department;
        return this;
    }

    @JsonProperty("salary")
    public Integer getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employees withSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("empid", empid).append("name", name).append("department", department).append("salary", salary).toString();
    }

}

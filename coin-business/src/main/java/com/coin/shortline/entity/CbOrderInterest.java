package com.coin.shortline.entity;

import org.springframework.boot.actuate.endpoint.jmx.DataEndpointMBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


//币一轮盈亏统计
@Entity
@Table(name = "cb_order_interest")
public class CbOrderInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种
     */
    @Column
    private String symbol;


    /**
     * 持仓数量
     */
    @Column
    private BigDecimal position_count;

    /**
     * 成本单价
     */
    @Column
    private BigDecimal cost_amount;

    /**
     * 卖出单价
     */
    @Column
    private BigDecimal sell_amount;

    /**
     * 利润
     */
    @Column
    private BigDecimal interest_amount;

    @Column
    private Date create_at;

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPosition_count() {
        return position_count;
    }

    public void setPosition_count(BigDecimal position_count) {
        this.position_count = position_count;
    }

    public BigDecimal getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(BigDecimal cost_amount) {
        this.cost_amount = cost_amount;
    }

    public BigDecimal getSell_amount() {
        return sell_amount;
    }

    public void setSell_amount(BigDecimal sell_amount) {
        this.sell_amount = sell_amount;
    }

    public BigDecimal getInterest_amount() {
        return interest_amount;
    }

    public void setInterest_amount(BigDecimal interest_amount) {
        this.interest_amount = interest_amount;
    }
}

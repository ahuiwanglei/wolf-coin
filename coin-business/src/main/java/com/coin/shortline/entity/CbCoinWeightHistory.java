package com.coin.shortline.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cb_coin_weight_history")
public class CbCoinWeightHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种
     */
    @Column
    private String symbol;

    /**
     * 交易量比
     */
    @Column
    private BigDecimal business_amount_rate;

    /**
     * 交易量比  权重
     */
    @Column
    private BigDecimal business_amount_weight;


    /**
     * 买卖深度比
     */
    @Column
    private BigDecimal market_depth_rate;

    /**
     * 交易量比权重
     */
    @Column
    private BigDecimal market_depth_weight;

    /**
     * 价格比
     */
    @Column
    private BigDecimal price_rate;

    /**
     * 交易量比权重
     */
    @Column
    private BigDecimal price_weight;

    @Column
    private Date create_at;

    @Column
    private BigDecimal complex_weight;

    public BigDecimal getComplex_weight() {
        return complex_weight;
    }

    public void setComplex_weight(BigDecimal complex_weight) {
        this.complex_weight = complex_weight;
    }

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

    public BigDecimal getBusiness_amount_rate() {
        return business_amount_rate;
    }

    public void setBusiness_amount_rate(BigDecimal business_amount_rate) {
        this.business_amount_rate = business_amount_rate;
    }

    public BigDecimal getBusiness_amount_weight() {
        return business_amount_weight;
    }

    public void setBusiness_amount_weight(BigDecimal business_amount_weight) {
        this.business_amount_weight = business_amount_weight;
    }

    public BigDecimal getMarket_depth_rate() {
        return market_depth_rate;
    }

    public void setMarket_depth_rate(BigDecimal market_depth_rate) {
        this.market_depth_rate = market_depth_rate;
    }

    public BigDecimal getMarket_depth_weight() {
        return market_depth_weight;
    }

    public void setMarket_depth_weight(BigDecimal market_depth_weight) {
        this.market_depth_weight = market_depth_weight;
    }

    public BigDecimal getPrice_rate() {
        return price_rate;
    }

    public void setPrice_rate(BigDecimal price_rate) {
        this.price_rate = price_rate;
    }

    public BigDecimal getPrice_weight() {
        return price_weight;
    }

    public void setPrice_weight(BigDecimal price_weight) {
        this.price_weight = price_weight;
    }
}

package com.coin.shortline.entity.gate;

import javax.persistence.Column;
import java.math.BigDecimal;

public class CoinWeight {

    /**
     * 币种
     */
    private String symbol;

    /**
     * 交易量比
     */
    private BigDecimal business_amount_rate;

    /**
     * 交易量比  权重
     */
    private BigDecimal business_amount_weight;


    /**
     * 买卖深度比
     */
    private BigDecimal market_depth_rate;

    /**
     * 交易量比权重
     */
    private BigDecimal market_depth_weight;

    /**
     * 价格比
     */
    private BigDecimal price_rate;

    /**
     * 交易量比权重
     */
    private BigDecimal price_weight;

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

    public BigDecimal getRealWeight(){
        return this.price_rate.multiply(price_weight).add((market_depth_rate.multiply(market_depth_weight))).add((business_amount_rate.multiply(business_amount_weight)));
    }
}

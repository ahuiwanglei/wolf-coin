package com.coin.shortline.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cb_price_trends")
public class CbPriceTrends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种
     */
    @Column
    private String symbol;


    /**
     * 最新价格
     */
    @Column
    private BigDecimal amount;

    /**
     * 买方最高价
     */
    @Column
    private BigDecimal highestbid;

    /**
     * 卖方最高价
     */
    @Column
    private BigDecimal lowestask;

    /**
     * 交易量
     */
    @Column
    private BigDecimal base_volume;

    /**
     * 1分钟平均价格
     */
    @Column
    private BigDecimal amount1min;

    /**
     * 1分钟平均交易量
     */
    @Column
    private BigDecimal volume_1min;

    @Column
    private BigDecimal quote_volume;


    @Column
    private Date create_at;

    public BigDecimal getQuote_volume() {
        return quote_volume;
    }

    public void setQuote_volume(BigDecimal quote_volume) {
        this.quote_volume = quote_volume;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getHighestbid() {
        return highestbid;
    }

    public void setHighestbid(BigDecimal highestbid) {
        this.highestbid = highestbid;
    }

    public BigDecimal getLowestask() {
        return lowestask;
    }

    public void setLowestask(BigDecimal lowestask) {
        this.lowestask = lowestask;
    }

    public BigDecimal getBase_volume() {
        return base_volume;
    }

    public void setBase_volume(BigDecimal base_volume) {
        this.base_volume = base_volume;
    }

    public BigDecimal getAmount1min() {
        return amount1min;
    }

    public void setAmount1min(BigDecimal amount1min) {
        this.amount1min = amount1min;
    }

    public BigDecimal getVolume_1min() {
        return volume_1min;
    }

    public void setVolume_1min(BigDecimal volume_1min) {
        this.volume_1min = volume_1min;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}

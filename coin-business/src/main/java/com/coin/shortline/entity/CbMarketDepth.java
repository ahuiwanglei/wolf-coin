package com.coin.shortline.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cb_market_depths")
public class CbMarketDepth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种
     */
    @Column
    private String symbol;

    /**
     * 卖方深度
     */
    @Column
    private BigDecimal asks;
    /**
     * 买方深度
     */
    @Column
    private BigDecimal bids;

    /**
     * 1分钟深度平均比例
     */
    @Column
    private BigDecimal scale1min;

    @Column
    private Date create_at;

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

    public BigDecimal getAsks() {
        return asks;
    }

    public void setAsks(BigDecimal asks) {
        this.asks = asks;
    }

    public BigDecimal getBids() {
        return bids;
    }

    public void setBids(BigDecimal bids) {
        this.bids = bids;
    }

    public BigDecimal getScale1min() {
        return scale1min;
    }

    public void setScale1min(BigDecimal scale1min) {
        this.scale1min = scale1min;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}

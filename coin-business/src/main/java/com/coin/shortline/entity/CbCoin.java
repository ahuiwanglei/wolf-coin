package com.coin.shortline.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cb_coins")
public class CbCoin {

    /**
     * 0 :监听正常交易  1:暂停交易
     */
    public enum Status {
        Normal(0), Close(1);
        private int value;

        private Status(int v) {
            this.value = v;
        }

        public int getValue() {
            return value;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 平台
     */
    @Column
    private String platform;

    /**
     * 币种
     */
    @Column
    private String symbol;

    /**
     * 手续费
     */
    @Column
    private BigDecimal fees;

    /**
     * 持仓数量
     */
    @Column
    private BigDecimal position_count;

    /**
     * 成本单价
     */
    @Column
    private BigDecimal unit_amont;

    /**
     * 买入权重阀值
     */
    @Column
    private BigDecimal buy_threshold_rate;

    /**
     * 卖出权重阀值
     */
    @Column
    private BigDecimal sell_threshold_rate;

    /**
     * 止损阀值
     */
    @Column
    private BigDecimal all_sell_threshold_rate;

    /**
     * 0 :监听正常交易  1:暂停交易
     */
    @Column
    private Integer status;

    @Column
    private Date create_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BigDecimal getPosition_count() {
        return position_count;
    }

    public void setPosition_count(BigDecimal position_count) {
        this.position_count = position_count;
    }

    public BigDecimal getUnit_amont() {
        return unit_amont;
    }

    public void setUnit_amont(BigDecimal unit_amont) {
        this.unit_amont = unit_amont;
    }

    public BigDecimal getBuy_threshold_rate() {
        return buy_threshold_rate;
    }

    public void setBuy_threshold_rate(BigDecimal buy_threshold_rate) {
        this.buy_threshold_rate = buy_threshold_rate;
    }

    public BigDecimal getSell_threshold_rate() {
        return sell_threshold_rate;
    }

    public void setSell_threshold_rate(BigDecimal sell_threshold_rate) {
        this.sell_threshold_rate = sell_threshold_rate;
    }

    public BigDecimal getAll_sell_threshold_rate() {
        return all_sell_threshold_rate;
    }

    public void setAll_sell_threshold_rate(BigDecimal all_sell_threshold_rate) {
        this.all_sell_threshold_rate = all_sell_threshold_rate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}

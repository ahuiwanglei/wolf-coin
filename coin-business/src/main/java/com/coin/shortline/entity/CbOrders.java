package com.coin.shortline.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cb_orders")
public class CbOrders {

    public enum OrderType {
        Buy(0), Sell(1);
        private int value;

        private OrderType(int v) {
            this.value = v;
        }

        public int getValue() {
            return value;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种
     */
    @Column
    private String symbol;

    /**
     * 订单号
     */
    @Column
    private String order_number;

    /**
     * 手续费
     */
    @Column
    private BigDecimal rate;

    /**
     * 价格
     */
    @Column
    private BigDecimal amount;

    /**
     * 剩余数量
     */
    @Column
    private BigDecimal leftamount;

    /**
     * 成交数量
     */
    @Column
    private BigDecimal filledamount;

    /**
     * 成交价格
     */
    @Column
    private BigDecimal filledrate;

    /**
     * 0: 买入  1卖出
     */
    @Column
    private Integer type;

    /**
     * 交易时权重
     */
    @Column
    private BigDecimal business_weight;

    /**
     * open已挂单 cancelled已取消 closed已完成
     */
    @Column
    private String status;

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

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLeftamount() {
        return leftamount;
    }

    public void setLeftamount(BigDecimal leftamount) {
        this.leftamount = leftamount;
    }

    public BigDecimal getFilledamount() {
        return filledamount;
    }

    public void setFilledamount(BigDecimal filledamount) {
        this.filledamount = filledamount;
    }

    public BigDecimal getFilledrate() {
        return filledrate;
    }

    public void setFilledrate(BigDecimal filledrate) {
        this.filledrate = filledrate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getBusiness_weight() {
        return business_weight;
    }

    public void setBusiness_weight(BigDecimal business_weight) {
        this.business_weight = business_weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}

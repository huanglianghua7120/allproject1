package saaf.common.fmw.bean;

import java.util.Date;

public class OrderPayTwo {
    public OrderPayTwo() {
        super();
    }
    private Integer tid;
    private String orderCode;
    private Date orderTime;
    private Integer memberId;
    private String mobileNumber;

    public OrderPayTwo(Integer tid, String orderCode, Date orderTime, Integer memberId, String mobileNumber) {
        this.tid = tid;
        this.orderCode = orderCode;
        this.orderTime = orderTime;
        this.memberId = memberId;
        this.mobileNumber = mobileNumber;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }
}

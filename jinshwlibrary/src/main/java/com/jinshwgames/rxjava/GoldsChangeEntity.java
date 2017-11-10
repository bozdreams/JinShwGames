package com.jinshwgames.rxjava;

import java.io.Serializable;

/**
 * 功能说明:查看用户
 * 编写日期: 2017/10/19 下午5:20
 * 作者:zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class GoldsChangeEntity implements Serializable {


    /**
     * msg : 鍏戞崲鎴愬姛
     * code : 10310
     * data : {"orderNo":"20171108EXCHANGE57927806002","channelNo":"C1BF373010464B5FA04057831F218E53","spServiceNo":"76D75539D1544F0FB1B33298AA86B22F","userNo":"6","spServiceName":"閲戝暩娓告垙","remark":"12121","deviceNo":"240DC29BF853008","goldBalance":998716526,"createTimestamp":1510135065579,"storeNo":"2DC66E5C4B6F4A4F957DE62F923FBF24","exchangeGolds":40,"createTime":"2017-11-08 17:57:45","spServiceGolds":40,"id":25,"originalGolds":998716566}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoldsChangeEntity{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * orderNo : 20171108EXCHANGE57927806002
         * channelNo : C1BF373010464B5FA04057831F218E53
         * spServiceNo : 76D75539D1544F0FB1B33298AA86B22F
         * userNo : 6
         * spServiceName : 閲戝暩娓告垙
         * remark : 12121
         * deviceNo : 240DC29BF853008
         * goldBalance : 998716526
         * createTimestamp : 1510135065579
         * storeNo : 2DC66E5C4B6F4A4F957DE62F923FBF24
         * exchangeGolds : 40
         * createTime : 2017-11-08 17:57:45
         * spServiceGolds : 40
         * id : 25
         * originalGolds : 998716566
         */

        private String orderNo;
        private String channelNo;
        private String spServiceNo;
        private String userNo;
        private String spServiceName;
        private String remark;
        private String deviceNo;
        private int goldBalance;
        private long createTimestamp;
        private String storeNo;
        private int exchangeGolds;
        private String createTime;
        private int spServiceGolds;
        private int id;
        private int originalGolds;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(String channelNo) {
            this.channelNo = channelNo;
        }

        public String getSpServiceNo() {
            return spServiceNo;
        }

        public void setSpServiceNo(String spServiceNo) {
            this.spServiceNo = spServiceNo;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getSpServiceName() {
            return spServiceName;
        }

        public void setSpServiceName(String spServiceName) {
            this.spServiceName = spServiceName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getGoldBalance() {
            return goldBalance;
        }

        public void setGoldBalance(int goldBalance) {
            this.goldBalance = goldBalance;
        }

        public long getCreateTimestamp() {
            return createTimestamp;
        }

        public void setCreateTimestamp(long createTimestamp) {
            this.createTimestamp = createTimestamp;
        }

        public String getStoreNo() {
            return storeNo;
        }

        public void setStoreNo(String storeNo) {
            this.storeNo = storeNo;
        }

        public int getExchangeGolds() {
            return exchangeGolds;
        }

        public void setExchangeGolds(int exchangeGolds) {
            this.exchangeGolds = exchangeGolds;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getSpServiceGolds() {
            return spServiceGolds;
        }

        public void setSpServiceGolds(int spServiceGolds) {
            this.spServiceGolds = spServiceGolds;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOriginalGolds() {
            return originalGolds;
        }

        public void setOriginalGolds(int originalGolds) {
            this.originalGolds = originalGolds;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "orderNo='" + orderNo + '\'' +
                    ", channelNo='" + channelNo + '\'' +
                    ", spServiceNo='" + spServiceNo + '\'' +
                    ", userNo='" + userNo + '\'' +
                    ", spServiceName='" + spServiceName + '\'' +
                    ", remark='" + remark + '\'' +
                    ", deviceNo='" + deviceNo + '\'' +
                    ", goldBalance=" + goldBalance +
                    ", createTimestamp=" + createTimestamp +
                    ", storeNo='" + storeNo + '\'' +
                    ", exchangeGolds=" + exchangeGolds +
                    ", createTime='" + createTime + '\'' +
                    ", spServiceGolds=" + spServiceGolds +
                    ", id=" + id +
                    ", originalGolds=" + originalGolds +
                    '}';
        }
    }
}

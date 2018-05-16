package com.mex.GalaxyChain.mychart.bean;


import com.mex.GalaxyChain.mychart.BaseEntitiy;

import java.io.Serializable;
import java.util.List;

/**
 * @author LSJ
 * @Description:
 * @date 16/8/2
 * @time 下午3:36
 */
public class OminuteMsg extends BaseEntitiy implements Serializable {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private LatestBean latest;
        private List<QuotationsBean> quotations;
        private String tradeState;
        private String maxlast;
        private String minlast;
        private int lastsum;

        public int getLastsum() {
            return lastsum;
        }

        public void setLastsum(int lastsum) {
            this.lastsum = lastsum;
        }

        public String getMaxlast() {
            return maxlast;
        }

        public void setMaxlast(String maxlast) {
            this.maxlast = maxlast;
        }

        public String getMinlast() {
            return minlast;
        }

        public void setMinlast(String minlast) {
            this.minlast = minlast;
        }

        public String getTradeState() {
            return tradeState;
        }

        public void setTradeState(String tradeState) {
            this.tradeState = tradeState;
        }


        public LatestBean getLatest() {
            return latest;
        }

        public void setLatest(LatestBean latest) {
            this.latest = latest;
        }

        public List<QuotationsBean> getQuotations() {
            return quotations;
        }

        public void setQuotations(List<QuotationsBean> quotations) {
            this.quotations = quotations;
        }

        public static class LatestBean {
            /**
             * id : 54385
             * instID : Au(T+D)
             * name : 黄金延期
             * lastSettle : 269.81
             * lastClose : 269.96
             * open : 269.84
             * high : 270.60
             * low : 269.37
             * last : 270.32
             * close : 270.0
             * settle : 0.0
             * bid1 : 270.0
             * bidLot1 : 2
             * bid2 : 270.0
             * bidLot2 : 5
             * bid3 : 270.11
             * bidLot3 : 3
             * bid4 : 270.10
             * bidLot4 : 62
             * bid5 : 270.09
             * bidLot5 : 1
             * ask1 : 270.32
             * askLot1 : 22
             * ask2 : 270.35
             * askLot2 : 2
             * ask3 : 270.37
             * askLot3 : 2
             * ask4 : 270.39
             * askLot4 : 12
             * ask5 : 270.0
             * askLot5 : 105
             * volume : 37018
             * weight : 37018.000000
             * highLimit : 285.99
             * lowLimit : 253.62
             * upDown : 0.51
             * upDownRate : 0.001890
             * turnOver : 9998999860.00
             * average : 270.0
             * quoteDate : 20170705
             * quoteTime : 11:29:57
             * sequenceNo : 28627
             * years : 2017
             * months : 07
             * days : 05
             * hours : 11
             * minutes : 29
             * fiveminutes : 30
             * fiveteenminutes : 30
             * thirtyminutes : 30
             * forhours : 12
             * weeks : 27
             * posi : 263830
             */

            private int id;
            private String instID;
            private String name;
            private String lastSettle;
            private String lastClose;
            private String open;
            private String high;
            private String low;
            private String last;
            private String close;
            private String settle;
            private String bid1;
            private String bidLot1;
            private String bid2;
            private String bidLot2;
            private String bid3;
            private String bidLot3;
            private String bid4;
            private String bidLot4;
            private String bid5;
            private String bidLot5;
            private String ask1;
            private String askLot1;
            private String ask2;
            private String askLot2;
            private String ask3;
            private String askLot3;
            private String ask4;
            private String askLot4;
            private String ask5;
            private String askLot5;
            private String volume;
            private String weight;
            private String highLimit;
            private String lowLimit;
            private String upDown;
            private String upDownRate;
            private String turnOver;
            private String average;
            private String quoteDate;
            private String quoteTime;
            private String sequenceNo;
            private String years;
            private String months;
            private String days;
            private String hours;
            private String minutes;
            private String fiveminutes;
            private String fiveteenminutes;
            private String thirtyminutes;
            private String forhours;
            private String weeks;
            private String posi;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInstID() {
                return instID;
            }

            public void setInstID(String instID) {
                this.instID = instID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLastSettle() {
                return lastSettle;
            }

            public void setLastSettle(String lastSettle) {
                this.lastSettle = lastSettle;
            }

            public String getLastClose() {
                return lastClose;
            }

            public void setLastClose(String lastClose) {
                this.lastClose = lastClose;
            }

            public String getOpen() {
                return open;
            }

            public void setOpen(String open) {
                this.open = open;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getLast() {
                return last;
            }

            public void setLast(String last) {
                this.last = last;
            }

            public String getClose() {
                return close;
            }

            public void setClose(String close) {
                this.close = close;
            }

            public String getSettle() {
                return settle;
            }

            public void setSettle(String settle) {
                this.settle = settle;
            }

            public String getBid1() {
                return bid1;
            }

            public void setBid1(String bid1) {
                this.bid1 = bid1;
            }

            public String getBidLot1() {
                return bidLot1;
            }

            public void setBidLot1(String bidLot1) {
                this.bidLot1 = bidLot1;
            }

            public String getBid2() {
                return bid2;
            }

            public void setBid2(String bid2) {
                this.bid2 = bid2;
            }

            public String getBidLot2() {
                return bidLot2;
            }

            public void setBidLot2(String bidLot2) {
                this.bidLot2 = bidLot2;
            }

            public String getBid3() {
                return bid3;
            }

            public void setBid3(String bid3) {
                this.bid3 = bid3;
            }

            public String getBidLot3() {
                return bidLot3;
            }

            public void setBidLot3(String bidLot3) {
                this.bidLot3 = bidLot3;
            }

            public String getBid4() {
                return bid4;
            }

            public void setBid4(String bid4) {
                this.bid4 = bid4;
            }

            public String getBidLot4() {
                return bidLot4;
            }

            public void setBidLot4(String bidLot4) {
                this.bidLot4 = bidLot4;
            }

            public String getBid5() {
                return bid5;
            }

            public void setBid5(String bid5) {
                this.bid5 = bid5;
            }

            public String getBidLot5() {
                return bidLot5;
            }

            public void setBidLot5(String bidLot5) {
                this.bidLot5 = bidLot5;
            }

            public String getAsk1() {
                return ask1;
            }

            public void setAsk1(String ask1) {
                this.ask1 = ask1;
            }

            public String getAskLot1() {
                return askLot1;
            }

            public void setAskLot1(String askLot1) {
                this.askLot1 = askLot1;
            }

            public String getAsk2() {
                return ask2;
            }

            public void setAsk2(String ask2) {
                this.ask2 = ask2;
            }

            public String getAskLot2() {
                return askLot2;
            }

            public void setAskLot2(String askLot2) {
                this.askLot2 = askLot2;
            }

            public String getAsk3() {
                return ask3;
            }

            public void setAsk3(String ask3) {
                this.ask3 = ask3;
            }

            public String getAskLot3() {
                return askLot3;
            }

            public void setAskLot3(String askLot3) {
                this.askLot3 = askLot3;
            }

            public String getAsk4() {
                return ask4;
            }

            public void setAsk4(String ask4) {
                this.ask4 = ask4;
            }

            public String getAskLot4() {
                return askLot4;
            }

            public void setAskLot4(String askLot4) {
                this.askLot4 = askLot4;
            }

            public String getAsk5() {
                return ask5;
            }

            public void setAsk5(String ask5) {
                this.ask5 = ask5;
            }

            public String getAskLot5() {
                return askLot5;
            }

            public void setAskLot5(String askLot5) {
                this.askLot5 = askLot5;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getHighLimit() {
                return highLimit;
            }

            public void setHighLimit(String highLimit) {
                this.highLimit = highLimit;
            }

            public String getLowLimit() {
                return lowLimit;
            }

            public void setLowLimit(String lowLimit) {
                this.lowLimit = lowLimit;
            }

            public String getUpDown() {
                return upDown;
            }

            public void setUpDown(String upDown) {
                this.upDown = upDown;
            }

            public String getUpDownRate() {
                return upDownRate;
            }

            public void setUpDownRate(String upDownRate) {
                this.upDownRate = upDownRate;
            }

            public String getTurnOver() {
                return turnOver;
            }

            public void setTurnOver(String turnOver) {
                this.turnOver = turnOver;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public String getQuoteDate() {
                return quoteDate;
            }

            public void setQuoteDate(String quoteDate) {
                this.quoteDate = quoteDate;
            }

            public String getQuoteTime() {
                return quoteTime;
            }

            public void setQuoteTime(String quoteTime) {
                this.quoteTime = quoteTime;
            }

            public String getSequenceNo() {
                return sequenceNo;
            }

            public void setSequenceNo(String sequenceNo) {
                this.sequenceNo = sequenceNo;
            }

            public String getYears() {
                return years;
            }

            public void setYears(String years) {
                this.years = years;
            }

            public String getMonths() {
                return months;
            }

            public void setMonths(String months) {
                this.months = months;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getMinutes() {
                return minutes;
            }

            public void setMinutes(String minutes) {
                this.minutes = minutes;
            }

            public String getFiveminutes() {
                return fiveminutes;
            }

            public void setFiveminutes(String fiveminutes) {
                this.fiveminutes = fiveminutes;
            }

            public String getFiveteenminutes() {
                return fiveteenminutes;
            }

            public void setFiveteenminutes(String fiveteenminutes) {
                this.fiveteenminutes = fiveteenminutes;
            }

            public String getThirtyminutes() {
                return thirtyminutes;
            }

            public void setThirtyminutes(String thirtyminutes) {
                this.thirtyminutes = thirtyminutes;
            }

            public String getForhours() {
                return forhours;
            }

            public void setForhours(String forhours) {
                this.forhours = forhours;
            }

            public String getWeeks() {
                return weeks;
            }

            public void setWeeks(String weeks) {
                this.weeks = weeks;
            }

            public String getPosi() {
                return posi;
            }

            public void setPosi(String posi) {
                this.posi = posi;
            }
        }

        public static class QuotationsBean {

            private String id;
            private String last;
            private String high;
            private String instID;
            private String low;
            private String open;
            private String time;
            private String type;
            private String volume;
            private String ctime;
            private String years;
            private String months;
            private String days;
            private String weeks;
            private String hours;
            private String utime;
            private String rose;
            private String upDownRate;
            private String average;
            private String quoteDate;
            private String sequenceNo;
            private String quoteTime;

            public String getQuoteTime() {
                return quoteTime;
            }

            public void setQuoteTime(String quoteTime) {
                this.quoteTime = quoteTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLast() {
                return last;
            }

            public void setLast(String last) {
                this.last = last;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getInstID() {
                return instID;
            }

            public void setInstID(String instID) {
                this.instID = instID;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getOpen() {
                return open;
            }

            public void setOpen(String open) {
                this.open = open;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getYears() {
                return years;
            }

            public void setYears(String years) {
                this.years = years;
            }

            public String getMonths() {
                return months;
            }

            public void setMonths(String months) {
                this.months = months;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public String getWeeks() {
                return weeks;
            }

            public void setWeeks(String weeks) {
                this.weeks = weeks;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            public String getRose() {
                return rose;
            }

            public void setRose(String rose) {
                this.rose = rose;
            }

            public String getUpDownRate() {
                return upDownRate;
            }

            public void setUpDownRate(String upDownRate) {
                this.upDownRate = upDownRate;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public String getQuoteDate() {
                return quoteDate;
            }

            public void setQuoteDate(String quoteDate) {
                this.quoteDate = quoteDate;
            }

            public String getSequenceNo() {
                return sequenceNo;
            }

            public void setSequenceNo(String sequenceNo) {
                this.sequenceNo = sequenceNo;
            }
        }
    }
}
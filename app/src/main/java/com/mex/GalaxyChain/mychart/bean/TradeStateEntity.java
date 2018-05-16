package com.mex.GalaxyChain.mychart.bean;

/**
 * @author LSJ
 * @Description
 * @date 2017/8/17 下午5:15
 */
public class TradeStateEntity {

    /**
     * SequenceNo : 189
     * BeginString : GTP1.0
     * RspMsg : 交易成功
     * SequenceSeriesNo : 4
     * ApiName : onRecvRtnDeferInstStateUpdate
     * MsgType : B182
     * tradeState : A
     * ChainFlag : L
     * instID : mAu(T+D)
     * NodeType : 6
     * NodeID : 6031
     * CheckFlag : 0
     * DataType : TInstState
     * RspCode : RSP000000
     */

    private String SequenceNo;
    private String BeginString;
    private String RspMsg;
    private String SequenceSeriesNo;
    private String ApiName;
    private String MsgType;
    private String tradeState;
    private String ChainFlag;
    private String instID;
    private String NodeType;
    private String NodeID;
    private String CheckFlag;
    private String DataType;
    private String RspCode;

    public String getSequenceNo() {
        return SequenceNo;
    }

    public void setSequenceNo(String SequenceNo) {
        this.SequenceNo = SequenceNo;
    }

    public String getBeginString() {
        return BeginString;
    }

    public void setBeginString(String BeginString) {
        this.BeginString = BeginString;
    }

    public String getRspMsg() {
        return RspMsg;
    }

    public void setRspMsg(String RspMsg) {
        this.RspMsg = RspMsg;
    }

    public String getSequenceSeriesNo() {
        return SequenceSeriesNo;
    }

    public void setSequenceSeriesNo(String SequenceSeriesNo) {
        this.SequenceSeriesNo = SequenceSeriesNo;
    }

    public String getApiName() {
        return ApiName;
    }

    public void setApiName(String ApiName) {
        this.ApiName = ApiName;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getChainFlag() {
        return ChainFlag;
    }

    public void setChainFlag(String ChainFlag) {
        this.ChainFlag = ChainFlag;
    }

    public String getInstID() {
        return instID;
    }

    public void setInstID(String instID) {
        this.instID = instID;
    }

    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String NodeType) {
        this.NodeType = NodeType;
    }

    public String getNodeID() {
        return NodeID;
    }

    public void setNodeID(String NodeID) {
        this.NodeID = NodeID;
    }

    public String getCheckFlag() {
        return CheckFlag;
    }

    public void setCheckFlag(String CheckFlag) {
        this.CheckFlag = CheckFlag;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String DataType) {
        this.DataType = DataType;
    }

    public String getRspCode() {
        return RspCode;
    }

    public void setRspCode(String RspCode) {
        this.RspCode = RspCode;
    }
}

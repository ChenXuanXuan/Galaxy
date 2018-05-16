package com.mex.GalaxyChain.net.bean;

import com.google.gson.Gson;

/**
 * Created by LSJ on 18/3/13.
 */

public class EntrustData extends BaseBean {
	/**
	 * "order_id": "20180210000000011409",//订单id
	 * "base_asset": "YY",//交易币种
	 * "quote_asset": "ETH",//定价币种
	 * "type": 1,//交易类型：1-LIMIT 2-MARKET 3-STOP 4-STOP_LIMIT
	 * "side": 2,//方向：1-买2-卖
	 * "price": "0.00001850",//价格
	 * "origin_amount": "5000.00000000",//数量
	 * "executed_price": "0.00000000",//成交价格
	 * "executed_amount": "0.00000000",//成交数量
	 * "executed_quote_amount": "0.00000000",//成交额
	 * "status": 1,//状态1-新的委托2-已成交3-部分成交4-取消5-部分成交（部分成交已取消）
	 * "create_timestamp": 1518300110,//时间
	 * "price_decimal": 8,//价格精度
	 * "amt_decimal": 0,//数量精度
	 * "amount_decimal": 8//金额精度
	 */
	private String order_id;
	private String base_asset;
	private String quote_asset;
	private int type;
	private int side;
	private double price;
	private double origin_amount;
	private double executed_price;
	private double executed_amount;
	private double executed_quote_amount;
	private int status;
	private long create_timestamp;
	private int price_decimal;
	private int amt_decimal;
	private int amount_decimal;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getBase_asset() {
		return base_asset;
	}

	public void setBase_asset(String base_asset) {
		this.base_asset = base_asset;
	}

	public String getQuote_asset() {
		return quote_asset;
	}

	public void setQuote_asset(String quote_asset) {
		this.quote_asset = quote_asset;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOrigin_amount() {
		return origin_amount;
	}

	public void setOrigin_amount(double origin_amount) {
		this.origin_amount = origin_amount;
	}

	public double getExecuted_price() {
		return executed_price;
	}

	public void setExecuted_price(double executed_price) {
		this.executed_price = executed_price;
	}

	public double getExecuted_amount() {
		return executed_amount;
	}

	public void setExecuted_amount(double executed_amount) {
		this.executed_amount = executed_amount;
	}

	public double getExecuted_quote_amount() {
		return executed_quote_amount;
	}

	public void setExecuted_quote_amount(double executed_quote_amount) {
		this.executed_quote_amount = executed_quote_amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreate_timestamp() {
		return create_timestamp;
	}

	public void setCreate_timestamp(long create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	public int getPrice_decimal() {
		return price_decimal;
	}

	public void setPrice_decimal(int price_decimal) {
		this.price_decimal = price_decimal;
	}

	public int getAmt_decimal() {
		return amt_decimal;
	}

	public void setAmt_decimal(int amt_decimal) {
		this.amt_decimal = amt_decimal;
	}

	public int getAmount_decimal() {
		return amount_decimal;
	}

	public void setAmount_decimal(int amount_decimal) {
		this.amount_decimal = amount_decimal;
	}

	public static final String TEST_STR = "{\n" +
			"        \"order_id\": \"20180331000000332471\",\n" +
			"        \"base_asset\": \"MTC\",\n" +
			"        \"quote_asset\": \"ETH\",\n" +
			"        \"type\": 1,\n" +
			"        \"side\": 2,\n" +
			"        \"price\": \"0.00004095\",\n" +
			"        \"origin_amount\": \"4967.00000000\",\n" +
			"        \"executed_price\": \"0.00000000\",\n" +
			"        \"executed_amount\": \"0.00000000\",\n" +
			"        \"executed_quote_amount\": \"0.00000000\",\n" +
			"        \"status\": 1,\n" +
			"        \"create_timestamp\": 1522490453,\n" +
			"        \"price_decimal\": 8,\n" +
			"        \"amt_decimal\": 0,\n" +
			"        \"amount_decimal\": 8\n" +
			"      }";

	public static void main(String[] args) {
		EntrustData item = new Gson().fromJson(TEST_STR, EntrustData.class);

	}
}

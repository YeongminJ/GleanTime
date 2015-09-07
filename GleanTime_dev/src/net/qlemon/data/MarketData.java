package net.qlemon.data;

import java.util.List;

public class MarketData {
	private int categoryCnt;
	private String[] adURL;
	private List<GTCalendar> marketCal, searchCal;
	
	public int getCategoryCnt() {
		return categoryCnt;
	}
	public void setCategoryCnt(int categoryCnt) {
		this.categoryCnt = categoryCnt;
	}
	public String[] getAdURL() {
		return adURL;
	}
	public void setAdURL(String[] adURL) {
		this.adURL = adURL;
	}
	public List<GTCalendar> getMarketCal() {
		return marketCal;
	}
	public void setMarketCal(List<GTCalendar> marketCal) {
		this.marketCal = marketCal;
	}
	public List<GTCalendar> getSearchCal() {
		return searchCal;
	}
	public void setSearchCal(List<GTCalendar> searchCal) {
		this.searchCal = searchCal;
	}	
}

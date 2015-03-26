package idv.hsiehpinghan.stockweb.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialReportBalanceSheetDto implements
		Comparable<FinancialReportBalanceSheetDto> {
	private int year;
	private int season;
	private Date instantDate;
	// Asset
	private BigDecimal currentAssets;
	private BigDecimal noncurrentAssets;
	// Liability
	private BigDecimal currentLiabilities;
	private BigDecimal noncurrentLiabilities;
	// Equity
	private BigDecimal equity;

	public FinancialReportBalanceSheetDto(int year, int season,
			Date instantDate, BigDecimal currentAssets,
			BigDecimal noncurrentAssets, BigDecimal currentLiabilities,
			BigDecimal noncurrentLiabilities, BigDecimal equity) {
		super();
		this.year = year;
		this.season = season;
		this.instantDate = instantDate;
		this.currentAssets = currentAssets;
		this.noncurrentAssets = noncurrentAssets;
		this.currentLiabilities = currentLiabilities;
		this.noncurrentLiabilities = noncurrentLiabilities;
		this.equity = equity;
	}

	@Override
	public int compareTo(FinancialReportBalanceSheetDto o) {
		return instantDate.compareTo(o.getInstantDate());
	}

	public Date getInstantDate() {
		return instantDate;
	}

	public void setInstantDate(Date instantDate) {
		this.instantDate = instantDate;
	}

	public BigDecimal getCurrentAssets() {
		return currentAssets;
	}

	public void setCurrentAssets(BigDecimal currentAssets) {
		this.currentAssets = currentAssets;
	}

	public BigDecimal getNoncurrentAssets() {
		return noncurrentAssets;
	}

	public void setNoncurrentAssets(BigDecimal noncurrentAssets) {
		this.noncurrentAssets = noncurrentAssets;
	}

	public BigDecimal getCurrentLiabilities() {
		return currentLiabilities;
	}

	public void setCurrentLiabilities(BigDecimal currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public BigDecimal getNoncurrentLiabilities() {
		return noncurrentLiabilities;
	}

	public void setNoncurrentLiabilities(BigDecimal noncurrentLiabilities) {
		this.noncurrentLiabilities = noncurrentLiabilities;
	}

	public BigDecimal getEquity() {
		return equity;
	}

	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

}

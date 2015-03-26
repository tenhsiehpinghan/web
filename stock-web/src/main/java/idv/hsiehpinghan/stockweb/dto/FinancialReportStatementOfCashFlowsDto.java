package idv.hsiehpinghan.stockweb.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialReportStatementOfCashFlowsDto implements
		Comparable<FinancialReportStatementOfCashFlowsDto> {
	private int year;
	private int season;
	private Date startDate;
	private Date endDate;
	private BigDecimal cashFlowsFromUsedInOperatingActivities;
	private BigDecimal netCashFlowsFromUsedInInvestingActivities;
	private BigDecimal cashFlowsFromUsedInFinancingActivities;

	public FinancialReportStatementOfCashFlowsDto(int year, int season,
			Date startDate, Date endDate,
			BigDecimal cashFlowsFromUsedInOperatingActivities,
			BigDecimal netCashFlowsFromUsedInInvestingActivities,
			BigDecimal cashFlowsFromUsedInFinancingActivities) {
		super();
		this.year = year;
		this.season = season;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cashFlowsFromUsedInOperatingActivities = cashFlowsFromUsedInOperatingActivities;
		this.netCashFlowsFromUsedInInvestingActivities = netCashFlowsFromUsedInInvestingActivities;
		this.cashFlowsFromUsedInFinancingActivities = cashFlowsFromUsedInFinancingActivities;
	}

	@Override
	public int compareTo(FinancialReportStatementOfCashFlowsDto o) {
		int i = startDate.compareTo(o.getStartDate());
		if (i == 0) {
			i = endDate.compareTo(o.getEndDate());
		}
		return i;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getCashFlowsFromUsedInOperatingActivities() {
		return cashFlowsFromUsedInOperatingActivities;
	}

	public void setCashFlowsFromUsedInOperatingActivities(
			BigDecimal cashFlowsFromUsedInOperatingActivities) {
		this.cashFlowsFromUsedInOperatingActivities = cashFlowsFromUsedInOperatingActivities;
	}

	public BigDecimal getNetCashFlowsFromUsedInInvestingActivities() {
		return netCashFlowsFromUsedInInvestingActivities;
	}

	public void setNetCashFlowsFromUsedInInvestingActivities(
			BigDecimal netCashFlowsFromUsedInInvestingActivities) {
		this.netCashFlowsFromUsedInInvestingActivities = netCashFlowsFromUsedInInvestingActivities;
	}

	public BigDecimal getCashFlowsFromUsedInFinancingActivities() {
		return cashFlowsFromUsedInFinancingActivities;
	}

	public void setCashFlowsFromUsedInFinancingActivities(
			BigDecimal cashFlowsFromUsedInFinancingActivities) {
		this.cashFlowsFromUsedInFinancingActivities = cashFlowsFromUsedInFinancingActivities;
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

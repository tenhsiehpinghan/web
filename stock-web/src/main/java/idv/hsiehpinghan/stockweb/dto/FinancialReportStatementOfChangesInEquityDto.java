package idv.hsiehpinghan.stockweb.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialReportStatementOfChangesInEquityDto implements
		Comparable<FinancialReportStatementOfChangesInEquityDto> {
	private int year;
	private int season;
	private Date startDate;
	private Date endDate;
	private BigDecimal profitLoss;
	private BigDecimal otherComprehensiveIncome;

	public FinancialReportStatementOfChangesInEquityDto(int year, int season,
			Date startDate, Date endDate, BigDecimal profitLoss,
			BigDecimal otherComprehensiveIncome) {
		super();
		this.year = year;
		this.season = season;
		this.startDate = startDate;
		this.endDate = endDate;
		this.profitLoss = profitLoss;
		this.otherComprehensiveIncome = otherComprehensiveIncome;
	}

	@Override
	public int compareTo(FinancialReportStatementOfChangesInEquityDto o) {
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

	public BigDecimal getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(BigDecimal profitLoss) {
		this.profitLoss = profitLoss;
	}

	public BigDecimal getOtherComprehensiveIncome() {
		return otherComprehensiveIncome;
	}

	public void setOtherComprehensiveIncome(BigDecimal otherComprehensiveIncome) {
		this.otherComprehensiveIncome = otherComprehensiveIncome;
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

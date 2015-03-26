package idv.hsiehpinghan.stockweb.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialReportStatementOfComprehensiveIncomeDto implements
		Comparable<FinancialReportStatementOfComprehensiveIncomeDto> {
	private int year;
	private int season;
	private Date startDate;
	private Date endDate;
	private BigDecimal grossProfitLossFromOperationsNet;
	private BigDecimal netOperatingIncomeLoss;
	private BigDecimal nonoperatingIncomeAndExpenses;
	private BigDecimal incomeTaxExpenseContinuingOperations;
	private BigDecimal profitLossFromContinuingOperations;
	private BigDecimal dilutedEarningsLossPerShare;

	public FinancialReportStatementOfComprehensiveIncomeDto(int year,
			int season, Date startDate, Date endDate,
			BigDecimal grossProfitLossFromOperationsNet,
			BigDecimal netOperatingIncomeLoss,
			BigDecimal nonoperatingIncomeAndExpenses,
			BigDecimal incomeTaxExpenseContinuingOperations,
			BigDecimal profitLossFromContinuingOperations,
			BigDecimal dilutedEarningsLossPerShare) {
		super();
		this.year = year;
		this.season = season;
		this.startDate = startDate;
		this.endDate = endDate;
		this.grossProfitLossFromOperationsNet = grossProfitLossFromOperationsNet;
		this.netOperatingIncomeLoss = netOperatingIncomeLoss;
		this.nonoperatingIncomeAndExpenses = nonoperatingIncomeAndExpenses;
		this.incomeTaxExpenseContinuingOperations = incomeTaxExpenseContinuingOperations;
		this.profitLossFromContinuingOperations = profitLossFromContinuingOperations;
		this.dilutedEarningsLossPerShare = dilutedEarningsLossPerShare;
	}

	@Override
	public int compareTo(FinancialReportStatementOfComprehensiveIncomeDto o) {
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

	public BigDecimal getGrossProfitLossFromOperationsNet() {
		return grossProfitLossFromOperationsNet;
	}

	public void setGrossProfitLossFromOperationsNet(
			BigDecimal grossProfitLossFromOperationsNet) {
		this.grossProfitLossFromOperationsNet = grossProfitLossFromOperationsNet;
	}

	public BigDecimal getNetOperatingIncomeLoss() {
		return netOperatingIncomeLoss;
	}

	public void setNetOperatingIncomeLoss(BigDecimal netOperatingIncomeLoss) {
		this.netOperatingIncomeLoss = netOperatingIncomeLoss;
	}

	public BigDecimal getNonoperatingIncomeAndExpenses() {
		return nonoperatingIncomeAndExpenses;
	}

	public void setNonoperatingIncomeAndExpenses(
			BigDecimal nonoperatingIncomeAndExpenses) {
		this.nonoperatingIncomeAndExpenses = nonoperatingIncomeAndExpenses;
	}

	public BigDecimal getIncomeTaxExpenseContinuingOperations() {
		return incomeTaxExpenseContinuingOperations;
	}

	public void setIncomeTaxExpenseContinuingOperations(
			BigDecimal incomeTaxExpenseContinuingOperations) {
		this.incomeTaxExpenseContinuingOperations = incomeTaxExpenseContinuingOperations;
	}

	public BigDecimal getProfitLossFromContinuingOperations() {
		return profitLossFromContinuingOperations;
	}

	public void setProfitLossFromContinuingOperations(
			BigDecimal profitLossFromContinuingOperations) {
		this.profitLossFromContinuingOperations = profitLossFromContinuingOperations;
	}

	public BigDecimal getDilutedEarningsLossPerShare() {
		return dilutedEarningsLossPerShare;
	}

	public void setDilutedEarningsLossPerShare(
			BigDecimal dilutedEarningsLossPerShare) {
		this.dilutedEarningsLossPerShare = dilutedEarningsLossPerShare;
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

package idv.hsiehpinghan.stockweb.criteria;

import java.math.BigDecimal;

import idv.hsiehpinghan.stockdao.enumeration.ReportType;

public class AnalysisCriteria extends StockWebCriteriaBase {
	private Integer year;
	private Integer season;
	private ReportType reportType;
	private BigDecimal pValue;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public BigDecimal getPValue() {
		return pValue;
	}

	public void setPValue(BigDecimal pValue) {
		this.pValue = pValue;
	}

}

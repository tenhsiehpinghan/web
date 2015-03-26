package idv.hsiehpinghan.stockweb.criteria;

public class FinancialReportDetailCriteria extends StockWebCriteriaBase {
	private Integer year;
	private Integer season;

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

}

package idv.hsiehpinghan.stockweb.criteria;

import idv.hsiehpinghan.stockdao.enumeration.ReportType;

public class FinancialReportCriteria extends StockWebCriteriaBase {
	private ReportType reportType;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

}

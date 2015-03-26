package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.datetimeutility.utility.CalendarUtility;
import idv.hsiehpinghan.stockweb.criteria.FinancialReportDetailCriteria;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FinancialReportDetailAction extends StockActionBase {
	private static final long serialVersionUID = 1L;
	private static final int BEGIN_YEAR = 2013;
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	private FinancialReportDetailCriteria criteria;
	private Map<String, ObjectNode> financialReportDetailJsonMap;

	@Override
	public String execute() {
		if (criteria != null) {
			processCommonCriteria(criteria);
			getStockBag().setYear(criteria.getYear());
			getStockBag().setSeason(criteria.getSeason());
		}
		Locale locale = getLocale();
		financialReportDetailJsonMap = getStockBag()
				.getFinancialReportDetailJsonMap(locale);
		return SUCCESS;
	}

	public String query() {
		return execute();
	}

	public Map<String, ObjectNode> getFinancialReportDetailJsonMap() {
		return financialReportDetailJsonMap;
	}

	public String getBalanceSheetJson() {
		return financialReportDetailJsonMap.get(Presentation.Id.BalanceSheet)
				.toString();
	}

	public String getStatementOfComprehensiveIncomeJson() {
		return financialReportDetailJsonMap.get(
				Presentation.Id.StatementOfComprehensiveIncome).toString();
	}

	public String getStatementOfCashFlowsJson() {
		return financialReportDetailJsonMap.get(
				Presentation.Id.StatementOfCashFlows).toString();
	}

	public String getStatementOfChangesInEquityJson() {
		return replaceSpecialCharacter(financialReportDetailJsonMap.get(
				Presentation.Id.StatementOfChangesInEquity).toString());
	}

	public List<Integer> getYears() {
		int currYear = CalendarUtility.getCurrentYear();
		List<Integer> years = new ArrayList<Integer>();
		for (int i = BEGIN_YEAR; i <= currYear; ++i) {
			years.add(i);
		}
		return years;
	}

	public String[] getSeasons() {
		return new String[] { "1", "2", "3", "4" };
	}

	public FinancialReportDetailCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(FinancialReportDetailCriteria criteria) {
		this.criteria = criteria;
	}

	private String replaceSpecialCharacter(String str) {
		return str.replace("'", "&apos;");
	}
}

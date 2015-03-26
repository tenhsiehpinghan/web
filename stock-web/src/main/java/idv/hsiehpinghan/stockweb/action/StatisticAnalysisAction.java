package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.datetimeutility.utility.CalendarUtility;
import idv.hsiehpinghan.stockweb.criteria.AnalysisCriteria;
import idv.hsiehpinghan.stockweb.dto.StatisticAnalysisMainRatioAnalysisDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatisticAnalysisAction extends StockActionBase {
	static {
		int currYear = CalendarUtility.getCurrentYear();
		List<Integer> ys = new ArrayList<Integer>();
		for (int i = 2013; i <= currYear; ++i) {
			ys.add(i);
		}
		years = ys;
	}
	private static final long serialVersionUID = 1L;
	private static final List<Integer> years;
	private static final String[] seasons = new String[] { "1", "2", "3", "4" };
	private static final BigDecimal[] pValues = new BigDecimal[] {
			new BigDecimal("0.02"), new BigDecimal("0.05"),
			new BigDecimal("0.10") };
	private AnalysisCriteria criteria;

	@Override
	public String execute() {
		if (criteria != null) {
			processCommonCriteria(criteria);
			getStockBag().setYear(criteria.getYear());
			getStockBag().setSeason(criteria.getSeason());
			getStockBag().setPValue(criteria.getPValue());
		}
		return SUCCESS;
	}

	public String query() {
		return execute();
	}

	public TreeSet<StatisticAnalysisMainRatioAnalysisDto> getStatisticAnalysisMainRatioAnalysisDtos() {
		return getStockBag().getStatisticAnalysisMainRatioAnalysisDtos();
	}

	public List<Integer> getYears() {
		return years;
	}

	public String[] getSeasons() {
		return seasons;
	}

	public BigDecimal[] getPValues() {
		return pValues;
	}

	public AnalysisCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(AnalysisCriteria criteria) {
		this.criteria = criteria;
	}

}

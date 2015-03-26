package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.stockdao.entity.MonthlyOperatingIncome;
import idv.hsiehpinghan.stockweb.criteria.FinancialReportCriteria;
import idv.hsiehpinghan.stockweb.dto.FinancialReportBalanceSheetDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfCashFlowsDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfChangesInEquityDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfComprehensiveIncomeDto;

import java.util.NavigableSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FinancialReportAction extends StockActionBase {
	private static final long serialVersionUID = 1L;
	private FinancialReportCriteria criteria;

	@Override
	public String execute() {
		if (criteria != null) {
			processCommonCriteria(criteria);
			getStockBag().setReportType(criteria.getReportType());
		}
		return SUCCESS;
	}

	public NavigableSet<MonthlyOperatingIncome> getMonthlyOperatingIncomes() {
		return getStockBag().getMonthlyOperatingIncomes().descendingSet();
	}

	public NavigableSet<FinancialReportStatementOfChangesInEquityDto> getFinancialReportStatementOfChangesInEquityDtos() {
		return getStockBag().getFinancialReportStatementOfChangesInEquityDtos()
				.descendingSet();
	}

	public NavigableSet<FinancialReportStatementOfCashFlowsDto> getFinancialReportStatementOfCashFlowsDtos() {
		return getStockBag().getFinancialReportStatementOfCashFlowsDtos()
				.descendingSet();
	}

	public NavigableSet<FinancialReportBalanceSheetDto> getFinancialReportBalanceSheetDtos() {
		return getStockBag().getFinancialReportBalanceSheetDtos()
				.descendingSet();
	}

	public NavigableSet<FinancialReportStatementOfComprehensiveIncomeDto> getFinancialReportStatementOfComprehensiveIncomeDtos() {
		return getStockBag()
				.getFinancialReportStatementOfComprehensiveIncomeDtos()
				.descendingSet();
	}

	public FinancialReportCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(FinancialReportCriteria criteria) {
		this.criteria = criteria;
	}
}

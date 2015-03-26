package idv.hsiehpinghan.stockweb.bag;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.stockdao.entity.MainRatioAnalysis;
import idv.hsiehpinghan.stockdao.entity.MainRatioAnalysis.TTestFamily;
import idv.hsiehpinghan.stockdao.entity.MainRatioAnalysis.TTestFamily.TTestQualifier;
import idv.hsiehpinghan.stockdao.entity.MonthlyOperatingIncome;
import idv.hsiehpinghan.stockdao.entity.StockClosingCondition;
import idv.hsiehpinghan.stockdao.entity.StockInfo;
import idv.hsiehpinghan.stockdao.entity.Xbrl;
import idv.hsiehpinghan.stockdao.entity.Xbrl.ItemFamily;
import idv.hsiehpinghan.stockdao.enumeration.PeriodType;
import idv.hsiehpinghan.stockdao.enumeration.ReportType;
import idv.hsiehpinghan.stockservice.manager.ICompanyBasicInfoManager;
import idv.hsiehpinghan.stockservice.manager.IFinancialReportManager;
import idv.hsiehpinghan.stockservice.manager.IMonthlyOperatingIncomeHbaseManager;
import idv.hsiehpinghan.stockservice.manager.IStatisticAnalysisManager;
import idv.hsiehpinghan.stockservice.manager.IStockClosingConditionManager;
import idv.hsiehpinghan.stockservice.operator.FinancialReportDetailJsonMaker;
import idv.hsiehpinghan.stockweb.dto.FinancialReportBalanceSheetDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfCashFlowsDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfChangesInEquityDto;
import idv.hsiehpinghan.stockweb.dto.FinancialReportStatementOfComprehensiveIncomeDto;
import idv.hsiehpinghan.stockweb.dto.StatisticAnalysisMainRatioAnalysisDto;
import idv.hsiehpinghan.xbrlassistant.xbrl.Presentation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StockBag {
	private String stockCode = "2330";
	private StockInfo stockInfo;
	private MainRatioAnalysis mainRatioAnalysis;
	private ReportType reportType = ReportType.CONSOLIDATED_STATEMENT;
	// private Integer year = DateUtility.getYear();
	// private Integer season = DateUtility.getSeason();
	private Integer year = 2014;
	private Integer season = 3;
	private BigDecimal pValue = new BigDecimal("0.05");
	private TreeSet<StockClosingCondition> stockClosingConditions;
	private TreeSet<MonthlyOperatingIncome> monthlyOperatingIncomes;
	private TreeSet<Xbrl> xbrls;
	private Map<String, ObjectNode> financialReportDetailJsonMap;

	@Autowired
	private ICompanyBasicInfoManager comInfoManager;
	@Autowired
	private IStockClosingConditionManager closingCondManager;
	@Autowired
	private IMonthlyOperatingIncomeHbaseManager incomeManager;
	@Autowired
	private IFinancialReportManager reportManager;
	@Autowired
	private IStatisticAnalysisManager analysisManager;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		if (stockCode == null) {
			return;
		}
		this.stockCode = stockCode;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		if (reportType == null) {
			return;
		}
		this.reportType = reportType;
	}

	public StockInfo getStockInfo() {
		if (needUpdate(stockInfo) == true) {
			stockInfo = comInfoManager.getStockInfo(stockCode);
		}
		return stockInfo;
	}

	public MainRatioAnalysis getMainRatioAnalysis() {
		if (needUpdate(mainRatioAnalysis) == true) {
			mainRatioAnalysis = analysisManager.getMainRatioAnalysis(stockCode,
					reportType, year, season);
		}
		return mainRatioAnalysis;
	}

	public TreeSet<StockClosingCondition> getStockClosingConditions() {
		if (stockClosingConditions == null) {
			stockClosingConditions = closingCondManager.getAll(stockCode);
		} else if (stockClosingConditions.size() <= 0) {
			stockClosingConditions = closingCondManager.getAll(stockCode);
		} else if (stockCode
				.equals(getStockCode(stockClosingConditions.first())) == false) {
			stockClosingConditions = closingCondManager.getAll(stockCode);
		}
		return stockClosingConditions;
	}

	public TreeSet<MonthlyOperatingIncome> getMonthlyOperatingIncomes() {
		if (monthlyOperatingIncomes == null) {
			monthlyOperatingIncomes = incomeManager.getAll(stockCode, false);
		} else if (monthlyOperatingIncomes.size() <= 0) {
			monthlyOperatingIncomes = incomeManager.getAll(stockCode, false);
		} else if (stockCode.equals(getStockCode(monthlyOperatingIncomes
				.first())) == false) {
			monthlyOperatingIncomes = incomeManager.getAll(stockCode, false);
		}
		return monthlyOperatingIncomes;
	}

	public TreeSet<Xbrl> getXbrls() {
		if (xbrls == null) {
			xbrls = reportManager.getAll(stockCode, reportType);
		} else if (xbrls.size() <= 0) {
			xbrls = reportManager.getAll(stockCode, reportType);
		} else if (stockCode.equals(getStockCode(xbrls.first())) == false) {
			xbrls = reportManager.getAll(stockCode, reportType);
		} else if (reportType.equals(getReportType(xbrls.first())) == false) {
			xbrls = reportManager.getAll(stockCode, reportType);
		}
		return xbrls;
	}

	public TreeSet<FinancialReportStatementOfChangesInEquityDto> getFinancialReportStatementOfChangesInEquityDtos() {
		TreeSet<Xbrl> xbrls = getXbrls();
		TreeSet<FinancialReportStatementOfChangesInEquityDto> dtos = new TreeSet<FinancialReportStatementOfChangesInEquityDto>();
		PeriodType periodType = PeriodType.DURATION;
		for (Xbrl xbrl : xbrls) {
			int year = getYear(xbrl);
			int season = getSeason(xbrl);
			ItemFamily itemFam = xbrl.getItemFamily();
			Date startDate = getYearBeginDate(xbrl);
			Date endDate = getEndDate(xbrl);
			BigDecimal profitLoss = itemFam.get(
					Presentation.ElementId.PROFIT_LOSS, periodType, null,
					startDate, endDate);
			BigDecimal otherComprehensiveIncome = itemFam.get(
					Presentation.ElementId.OTHER_COMPREHENSIVE_INCOME,
					periodType, null, startDate, endDate);
			FinancialReportStatementOfChangesInEquityDto dto = new FinancialReportStatementOfChangesInEquityDto(
					year, season, startDate, endDate, profitLoss,
					otherComprehensiveIncome);
			dtos.add(dto);
		}
		return dtos;
	}

	public TreeSet<FinancialReportStatementOfCashFlowsDto> getFinancialReportStatementOfCashFlowsDtos() {
		TreeSet<Xbrl> xbrls = getXbrls();
		TreeSet<FinancialReportStatementOfCashFlowsDto> dtos = new TreeSet<FinancialReportStatementOfCashFlowsDto>();
		PeriodType periodType = PeriodType.DURATION;
		for (Xbrl xbrl : xbrls) {
			int year = getYear(xbrl);
			int season = getSeason(xbrl);
			ItemFamily itemFam = xbrl.getItemFamily();
			Date startDate = getYearBeginDate(xbrl);
			Date endDate = getEndDate(xbrl);
			BigDecimal cashFlowsFromUsedInOperatingActivities = itemFam
					.get(Presentation.ElementId.CASH_FLOWS_FROM_USED_IN_OPERATING_ACTIVITIES,
							periodType, null, startDate, endDate);
			BigDecimal netCashFlowsFromUsedInInvestingActivities = itemFam
					.get(Presentation.ElementId.NET_CASH_FLOWS_FROM_USED_IN_INVESTING_ACTIVITIES,
							periodType, null, startDate, endDate);
			BigDecimal cashFlowsFromUsedInFinancingActivities = itemFam
					.get(Presentation.ElementId.CASH_FLOWS_FROM_USED_IN_FINANCING_ACTIVITIES,
							periodType, null, startDate, endDate);
			FinancialReportStatementOfCashFlowsDto dto = new FinancialReportStatementOfCashFlowsDto(
					year, season, startDate, endDate,
					cashFlowsFromUsedInOperatingActivities,
					netCashFlowsFromUsedInInvestingActivities,
					cashFlowsFromUsedInFinancingActivities);
			dtos.add(dto);
		}
		return dtos;
	}

	public TreeSet<FinancialReportStatementOfComprehensiveIncomeDto> getFinancialReportStatementOfComprehensiveIncomeDtos() {
		TreeSet<Xbrl> xbrls = getXbrls();
		TreeSet<FinancialReportStatementOfComprehensiveIncomeDto> dtos = new TreeSet<FinancialReportStatementOfComprehensiveIncomeDto>();
		PeriodType periodType = PeriodType.DURATION;
		for (Xbrl xbrl : xbrls) {
			int year = getYear(xbrl);
			int season = getSeason(xbrl);
			ItemFamily itemFam = xbrl.getItemFamily();
			Date startDate = getYearBeginDate(xbrl);
			Date endDate = getEndDate(xbrl);
			BigDecimal grossProfitLossFromOperationsNet = itemFam
					.get(Presentation.ElementId.GROSS_PROFIT_LOSS_FROM_OPERATIONS_NET,
							periodType, null, startDate, endDate);
			BigDecimal netOperatingIncomeLoss = itemFam.get(
					Presentation.ElementId.NET_OPERATING_INCOME_LOSS,
					periodType, null, startDate, endDate);
			BigDecimal nonoperatingIncomeAndExpenses = itemFam.get(
					Presentation.ElementId.NONOPERATING_INCOME_AND_EXPENSES,
					periodType, null, startDate, endDate);
			BigDecimal incomeTaxExpenseContinuingOperations = itemFam
					.get(Presentation.ElementId.INCOME_TAX_EXPENSE_CONTINUING_OPERATIONS,
							periodType, null, startDate, endDate);
			BigDecimal profitLossFromContinuingOperations = itemFam
					.get(Presentation.ElementId.PROFIT_LOSS_FROM_CONTINUING_OPERATIONS,
							periodType, null, startDate, endDate);
			BigDecimal dilutedEarningsLossPerShare = itemFam.get(
					Presentation.ElementId.DILUTED_EARNINGS_LOSS_PER_SHARE,
					periodType, null, startDate, endDate);
			FinancialReportStatementOfComprehensiveIncomeDto dto = new FinancialReportStatementOfComprehensiveIncomeDto(
					year, season, startDate, endDate,
					grossProfitLossFromOperationsNet, netOperatingIncomeLoss,
					nonoperatingIncomeAndExpenses,
					incomeTaxExpenseContinuingOperations,
					profitLossFromContinuingOperations,
					dilutedEarningsLossPerShare);
			dtos.add(dto);
		}
		return dtos;
	}

	public TreeSet<FinancialReportBalanceSheetDto> getFinancialReportBalanceSheetDtos() {
		TreeSet<Xbrl> xbrls = getXbrls();
		TreeSet<FinancialReportBalanceSheetDto> dtos = new TreeSet<FinancialReportBalanceSheetDto>();
		PeriodType periodType = PeriodType.INSTANT;
		for (Xbrl xbrl : xbrls) {
			xbrl.getRowKey();
			ItemFamily itemFam = xbrl.getItemFamily();
			int year = getYear(xbrl);
			int season = getSeason(xbrl);
			Date instant = getInstantDate(xbrl);
			BigDecimal currentAssets = itemFam.get(
					Presentation.ElementId.CURRENT_ASSETS, periodType, instant,
					null, null);
			BigDecimal noncurrentAssets = itemFam.get(
					Presentation.ElementId.NONCURRENT_ASSETS, periodType,
					instant, null, null);
			BigDecimal currentLiabilities = itemFam.get(
					Presentation.ElementId.CURRENT_LIABILITIES, periodType,
					instant, null, null);
			BigDecimal noncurrentLiabilities = itemFam.get(
					Presentation.ElementId.NONCURRENT_LIABILITIES, periodType,
					instant, null, null);
			BigDecimal equity = itemFam.get(Presentation.ElementId.EQUITY,
					periodType, instant, null, null);
			FinancialReportBalanceSheetDto dto = new FinancialReportBalanceSheetDto(
					year, season, instant, currentAssets, noncurrentAssets,
					currentLiabilities, noncurrentLiabilities, equity);
			dtos.add(dto);
		}
		return dtos;
	}

	public TreeSet<StatisticAnalysisMainRatioAnalysisDto> getStatisticAnalysisMainRatioAnalysisDtos() {
		MainRatioAnalysis mainRatioAnalysis = getMainRatioAnalysis();
		TTestFamily tTestFam = mainRatioAnalysis.getTTestFamily();
		TreeSet<StatisticAnalysisMainRatioAnalysisDto> dtos = new TreeSet<StatisticAnalysisMainRatioAnalysisDto>();
		for (Entry<HBaseColumnQualifier, HBaseValue> ent : tTestFam
				.getLatestQualifierAndValueAsSet()) {
			TTestQualifier qual = (TTestQualifier) ent.getKey();
			String elementId = qual.getElementId();
			BigDecimal pValue = tTestFam.getPValue(elementId);
			if (this.pValue.compareTo(pValue) < 0) {
				continue;
			}
			String chineseName = tTestFam.getChineseName(elementId);
			String englishName = tTestFam.getEnglishName(elementId);
			BigDecimal statistic = tTestFam.getStatistic(elementId);
			BigDecimal degreeOfFreedom = tTestFam.getDegreeOfFreedom(elementId);
			BigDecimal confidenceInterval = tTestFam
					.getConfidenceInterval(elementId);
			BigDecimal sampleMean = tTestFam.getSampleMean(elementId);
			BigDecimal hypothesizedMean = tTestFam
					.getHypothesizedMean(elementId);
			StatisticAnalysisMainRatioAnalysisDto dto = new StatisticAnalysisMainRatioAnalysisDto(
					elementId, chineseName, englishName, statistic,
					degreeOfFreedom, confidenceInterval, sampleMean,
					hypothesizedMean, pValue);
			dtos.add(dto);
		}
		return dtos;
	}

	public Map<String, ObjectNode> getFinancialReportDetailJsonMap(Locale locale) {
		if (financialReportDetailJsonMap == null) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		} else if (stockCode.equals(getStockCode(financialReportDetailJsonMap)) == false) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		} else if (reportType
				.equals(getReportType(financialReportDetailJsonMap)) == false) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		} else if (year.equals(getYear(financialReportDetailJsonMap)) == false) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		} else if (season.equals(getSeason(financialReportDetailJsonMap)) == false) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		} else if (locale.equals(getLocale(financialReportDetailJsonMap)) == false) {
			financialReportDetailJsonMap = reportManager
					.getFinancialReportDetailJsonMap(stockCode, reportType,
							year, season, locale);
		}
		return financialReportDetailJsonMap;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		if (year == null) {
			return;
		}
		this.year = year;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		if (season == null) {
			return;
		}
		this.season = season;
	}

	public void setPValue(BigDecimal pValue) {
		if (pValue == null) {
			return;
		}
		this.pValue = pValue;
	}

	public BigDecimal getPValue() {
		return pValue;
	}

	private int getYear(Xbrl xbrl) {
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		return rowKey.getYear();
	}

	private int getSeason(Xbrl xbrl) {
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		return rowKey.getSeason();
	}

	private Date getInstantDate(Xbrl xbrl) {
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		int year = rowKey.getYear();
		int season = rowKey.getSeason();
		return DateUtility.getSeasonEndDate(year, season);
	}

	private Date getYearBeginDate(Xbrl xbrl) {
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		int year = rowKey.getYear();
		return DateUtility.getDate(year, 1, 1);
	}

	private Date getEndDate(Xbrl xbrl) {
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		int year = rowKey.getYear();
		int season = rowKey.getSeason();
		return DateUtility.getSeasonEndDate(year, season);
	}

	private String getStockCode(StockInfo stockInfo) {
		if (stockInfo == null) {
			return null;
		}
		StockInfo.RowKey rowKey = (StockInfo.RowKey) stockInfo.getRowKey();
		return rowKey.getStockCode();
	}

	private String getStockCode(Xbrl xbrl) {
		if (xbrl == null) {
			return null;
		}
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		return rowKey.getStockCode();
	}

	private String getStockCode(
			Map<String, ObjectNode> financialReportDetailJsonMap) {
		if (financialReportDetailJsonMap == null) {
			return null;
		}
		ObjectNode infoNode = financialReportDetailJsonMap
				.get(FinancialReportDetailJsonMaker.INFO);
		return infoNode.get(FinancialReportDetailJsonMaker.STOCK_CODE)
				.textValue();
	}

	private ReportType getReportType(
			Map<String, ObjectNode> financialReportDetailJsonMap) {
		if (financialReportDetailJsonMap == null) {
			return null;
		}
		ObjectNode infoNode = financialReportDetailJsonMap
				.get(FinancialReportDetailJsonMaker.INFO);
		String reportType = infoNode.get(
				FinancialReportDetailJsonMaker.REPORT_TYPE).textValue();
		return ReportType.valueOf(reportType);
	}

	private ReportType getReportType(Xbrl xbrl) {
		if (xbrl == null) {
			return null;
		}
		Xbrl.RowKey rowKey = (Xbrl.RowKey) xbrl.getRowKey();
		return rowKey.getReportType();
	}

	private Integer getYear(Map<String, ObjectNode> financialReportDetailJsonMap) {
		if (financialReportDetailJsonMap == null) {
			return null;
		}
		ObjectNode infoNode = financialReportDetailJsonMap
				.get(FinancialReportDetailJsonMaker.INFO);
		return infoNode.get(FinancialReportDetailJsonMaker.YEAR).asInt();
	}

	private Integer getSeason(
			Map<String, ObjectNode> financialReportDetailJsonMap) {
		if (financialReportDetailJsonMap == null) {
			return null;
		}
		ObjectNode infoNode = financialReportDetailJsonMap
				.get(FinancialReportDetailJsonMaker.INFO);
		return infoNode.get(FinancialReportDetailJsonMaker.SEASON).asInt();
	}

	private Locale getLocale(
			Map<String, ObjectNode> financialReportDetailJsonMap) {
		if (financialReportDetailJsonMap == null) {
			return null;
		}
		ObjectNode infoNode = financialReportDetailJsonMap
				.get(FinancialReportDetailJsonMaker.INFO);
		return new Locale(infoNode.get(FinancialReportDetailJsonMaker.LOCALE)
				.asText());
	}

	private String getStockCode(MonthlyOperatingIncome monthlyData) {
		if (monthlyData == null) {
			return null;
		}
		MonthlyOperatingIncome.RowKey rowKey = (MonthlyOperatingIncome.RowKey) monthlyData
				.getRowKey();
		return rowKey.getStockCode();
	}

	private String getStockCode(StockClosingCondition dailyData) {
		StockClosingCondition.RowKey rowKey = (StockClosingCondition.RowKey) dailyData
				.getRowKey();
		return rowKey.getStockCode();
	}

	private boolean needUpdate(MainRatioAnalysis mainRatioAnalysis) {
		if (mainRatioAnalysis == null) {
			return true;
		}
		MainRatioAnalysis.RowKey rowKey = (MainRatioAnalysis.RowKey) mainRatioAnalysis
				.getRowKey();
		if (stockCode.equals(rowKey.getStockCode()) == false) {
			return true;
		}
		if (reportType.equals(rowKey.getReportType()) == false) {
			return true;
		}
		if (year != rowKey.getYear()) {
			return true;
		}
		if (season != rowKey.getSeason()) {
			return true;
		}
		return false;
	}

	private boolean needUpdate(StockInfo stockInfo) {
		if (stockInfo == null) {
			return true;
		}
		if (stockCode.equals(getStockCode(stockInfo)) == false) {
			return true;
		}
		return false;
	}
}

package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.stockdao.entity.StockClosingCondition;
import idv.hsiehpinghan.stockdao.entity.StockInfo.CompanyFamily;
import idv.hsiehpinghan.stockdao.enumeration.ReportType;
import idv.hsiehpinghan.stockweb.bag.StockBag;
import idv.hsiehpinghan.stockweb.criteria.StockWebCriteriaBase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class StockActionBase extends ActionSupport implements
		SessionAware {
	private static final long serialVersionUID = 1L;
	private static final String STOCK_BAG = "stockBag";
	private Map<String, Object> session;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getActionName() {
		return ActionContext.getContext().getName();
	}

	public String getCompanyName() {
		Locale locale = getLocale();
		CompanyFamily companyFamily = getStockBag().getStockInfo()
				.getCompanyFamily();
		if (Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
			return companyFamily.getEnglishBriefName();
		} else {
			return companyFamily.getChineseName();
		}
	}

	public Date getDate() {
		StockClosingCondition stockClosingCondition = getLatestStockClosingConditions();
		if (stockClosingCondition == null) {
			return null;
		}
		StockClosingCondition.RowKey rowKey = (StockClosingCondition.RowKey) stockClosingCondition
				.getRowKey();
		return rowKey.getDate();
	}

	public BigDecimal getClosingPrice() {
		StockClosingCondition stockClosingCondition = getLatestStockClosingConditions();
		if (stockClosingCondition == null) {
			return null;
		}
		return stockClosingCondition.getClosingConditionFamily()
				.getClosingPrice();
	}

	public BigDecimal getChange() {
		StockClosingCondition stockClosingCondition = getLatestStockClosingConditions();
		if (stockClosingCondition == null) {
			return null;
		}
		return stockClosingCondition.getClosingConditionFamily().getChange();
	}

	public String getStockCode() {
		return getStockBag().getStockCode();
	}

	public ReportType[] getReportTypes() {
		return ReportType.values();
	}

	public StockBag getStockBag() {
		StockBag stockBag = (StockBag) session.get(STOCK_BAG);
		if (stockBag == null) {
			stockBag = applicationContext.getBean(StockBag.class);
			session.put(STOCK_BAG, stockBag);
		}
		return (StockBag) session.get(STOCK_BAG);
	}

	protected void processCommonCriteria(StockWebCriteriaBase criteria) {
		getStockBag().setStockCode(criteria.getStockCode());
	}

	protected Map<String, Object> getSession() {
		return this.session;
	}

	private StockClosingCondition getLatestStockClosingConditions() {
		TreeSet<StockClosingCondition> stockClosingConditions = getStockBag()
				.getStockClosingConditions();
		if (stockClosingConditions.size() <= 0) {
			return null;
		}
		return stockClosingConditions.last();
	}

}

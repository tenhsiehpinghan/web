package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.stockdao.entity.StockClosingCondition;
import idv.hsiehpinghan.stockweb.criteria.TransactionInfoCriteria;

import java.util.TreeSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionInfoAction extends StockActionBase {
	private static final long serialVersionUID = 1L;
	private TransactionInfoCriteria criteria;

	@Override
	public String execute() {
		if (criteria != null) {
			processCommonCriteria(criteria);
		}
		return SUCCESS;
	}

	public StockClosingCondition[] getStockClosingConditions() {
		TreeSet<StockClosingCondition> stockClosingConditions = getStockBag()
				.getStockClosingConditions();
		StockClosingCondition[] arr = new StockClosingCondition[stockClosingConditions
				.size()];
		return stockClosingConditions.toArray(arr);
	}

	public TransactionInfoCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(TransactionInfoCriteria criteria) {
		this.criteria = criteria;
	}
}

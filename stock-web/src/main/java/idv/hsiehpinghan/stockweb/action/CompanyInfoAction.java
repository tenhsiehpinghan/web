package idv.hsiehpinghan.stockweb.action;

import idv.hsiehpinghan.stockdao.entity.StockInfo.CompanyFamily;
import idv.hsiehpinghan.stockweb.criteria.CompanyInfoCriteria;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CompanyInfoAction extends StockActionBase {
	private static final long serialVersionUID = 1L;
	private CompanyInfoCriteria criteria;

	@Override
	public String execute() {
		if (criteria != null) {
			processCommonCriteria(criteria);
		}
		return SUCCESS;
	}

	public CompanyFamily getCompanyFamily() {
		return getStockBag().getStockInfo().getCompanyFamily();
	}

	public CompanyInfoCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(CompanyInfoCriteria criteria) {
		this.criteria = criteria;
	}

}

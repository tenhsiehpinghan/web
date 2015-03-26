package idv.hsiehpinghan.stockweb.dto;

import java.math.BigDecimal;
import java.util.Locale;

public class StatisticAnalysisMainRatioAnalysisDto implements
		Comparable<StatisticAnalysisMainRatioAnalysisDto> {
	private String elementId;
	private String chineseName;
	private String englishName;
	private BigDecimal statistic;
	private BigDecimal degreeOfFreedom;
	private BigDecimal confidenceInterval;
	private BigDecimal sampleMean;
	private BigDecimal hypothesizedMean;
	private BigDecimal pValue;

	public StatisticAnalysisMainRatioAnalysisDto(String elementId,
			String chineseName, String englishName, BigDecimal statistic,
			BigDecimal degreeOfFreedom, BigDecimal confidenceInterval,
			BigDecimal sampleMean, BigDecimal hypothesizedMean,
			BigDecimal pValue) {
		super();
		this.elementId = elementId;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.statistic = statistic;
		this.degreeOfFreedom = degreeOfFreedom;
		this.confidenceInterval = confidenceInterval;
		this.sampleMean = sampleMean;
		this.hypothesizedMean = hypothesizedMean;
		this.pValue = pValue;
	}

	@Override
	public int compareTo(StatisticAnalysisMainRatioAnalysisDto o) {
		int result = pValue.compareTo(o.getPValue());
		if(result == 0) {
			return chineseName.compareTo(o.getChineseName());
		}
		return result;
	}

	public String getName(Locale locale) {
		if (Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
			return getEnglishName();
		} else {
			return getChineseName();
		}
	}

	public String getElementId() {
		return elementId;
	}

	public String getChineseName() {
		return chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public BigDecimal getStatistic() {
		return statistic;
	}

	public BigDecimal getDegreeOfFreedom() {
		return degreeOfFreedom;
	}

	public BigDecimal getConfidenceInterval() {
		return confidenceInterval;
	}

	public BigDecimal getSampleMean() {
		return sampleMean;
	}

	public BigDecimal getHypothesizedMean() {
		return hypothesizedMean;
	}

	public BigDecimal getPValue() {
		return pValue;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public void setStatistic(BigDecimal statistic) {
		this.statistic = statistic;
	}

	public void setDegreeOfFreedom(BigDecimal degreeOfFreedom) {
		this.degreeOfFreedom = degreeOfFreedom;
	}

	public void setConfidenceInterval(BigDecimal confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

	public void setSampleMean(BigDecimal sampleMean) {
		this.sampleMean = sampleMean;
	}

	public void setHypothesizedMean(BigDecimal hypothesizedMean) {
		this.hypothesizedMean = hypothesizedMean;
	}

	public void setPValue(BigDecimal pValue) {
		this.pValue = pValue;
	}

}

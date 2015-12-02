package cn.concar.service.model.stat;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class KeyBigDecimalValModel {
	
	private String category;;
	private BigDecimal value;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
    public String toString() {
        return "[KeyBigDecimalValModel] category: " + category + " value: " + value;
    }
	
}
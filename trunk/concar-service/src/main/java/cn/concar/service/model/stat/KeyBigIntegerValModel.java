package cn.concar.service.model.stat;

import java.math.BigInteger;

import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * 
 * @author sen
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class KeyBigIntegerValModel {

	private String category;
	private BigInteger value;
	private String type;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return "[PieKeyValModel] category: " + category + " value: " + value + " type: " + type;
    }
}

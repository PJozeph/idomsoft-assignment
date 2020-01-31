package home.assignment.idomsoft.documentservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardWrapper {
	
	@JsonProperty("dictname")
	private String dictName;
	private List <Card> rows;
	
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public List<Card> getRows() {
		return rows;
	}
	public void setRows(List<Card> rows) {
		this.rows = rows;
	}
	
}

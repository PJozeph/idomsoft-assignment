package home.assignment.idomsoft.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NationalityWrapper {

	@JsonProperty("dictname")
	private String dictName;
	private List<Nationality> rows;
	
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public List<Nationality> getRows() {
		return rows;
	}
	public void setRows(List<Nationality> rows) {
		this.rows = rows;
	}
	
}

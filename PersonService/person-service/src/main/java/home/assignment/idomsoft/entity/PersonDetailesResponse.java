package home.assignment.idomsoft.entity;

public class PersonDetailesResponse {
	
	private SzemelyDTO szemelyDTO;

	public PersonDetailesResponse(SzemelyDTO szemelyDTO) {
		this.szemelyDTO = szemelyDTO;
	}

	public SzemelyDTO getSzemelyDTO() {
		return szemelyDTO;
	}

	public void setSzemelyDTO(SzemelyDTO szemelyDTO) {
		this.szemelyDTO = szemelyDTO;
	}

}

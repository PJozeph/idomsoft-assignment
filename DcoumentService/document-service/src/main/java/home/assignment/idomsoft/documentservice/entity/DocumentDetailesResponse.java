package home.assignment.idomsoft.documentservice.entity;

public class DocumentDetailesResponse {
	
	private OkmanyDTO okmanyDTO;

	public DocumentDetailesResponse(OkmanyDTO okmanyDTO) {
		this.okmanyDTO = okmanyDTO;
	}

	public OkmanyDTO getOkmanyDTO() {
		return okmanyDTO;
	}

	public void setOkmanyDTO(OkmanyDTO okmanyDTO) {
		this.okmanyDTO = okmanyDTO;
	}

}

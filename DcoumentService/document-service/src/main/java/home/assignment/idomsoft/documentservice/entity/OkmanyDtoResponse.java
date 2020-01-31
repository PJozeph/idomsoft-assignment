package home.assignment.idomsoft.documentservice.entity;

import java.io.Serializable;
import java.util.List;

public class OkmanyDtoResponse implements Serializable {
	
	private List<String> errorMessages;
	private OkmanyDTO okmanyDTO;
	
	public OkmanyDtoResponse() {}
	
	public OkmanyDtoResponse(List<String> errorMessages, OkmanyDTO okmanyDTO) {
		this.errorMessages = errorMessages;
		this.okmanyDTO = okmanyDTO;
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public OkmanyDTO getOkmanyDTO() {
		return okmanyDTO;
	}
	public void setOkmanyDTO(OkmanyDTO okmanyDTO) {
		this.okmanyDTO = okmanyDTO;
	}

}

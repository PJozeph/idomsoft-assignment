package home.assignment.idomsoft.entity;

import java.io.Serializable;
import java.util.List;

public class OkmanyDtoResponse implements Serializable{
	
	private List<String> errorMessages;
	private List<OkmanyDTO> okmanyDTO;
	
	public OkmanyDtoResponse() {}
	
	public OkmanyDtoResponse(List<String> errorMessages, List<OkmanyDTO> okmanyDTO) {
		this.errorMessages = errorMessages;
		this.okmanyDTO = okmanyDTO;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void setOkmanyDTO(List<OkmanyDTO> okmanyDTO) {
		this.okmanyDTO = okmanyDTO;
	}
	
	public List<OkmanyDTO> getOkmanyDTO() {
		return okmanyDTO;
	}
	
}

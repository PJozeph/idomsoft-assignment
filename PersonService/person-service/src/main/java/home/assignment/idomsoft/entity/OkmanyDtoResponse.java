package home.assignment.idomsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OkmanyDtoResponse implements Serializable{
	
	private List<String> errorMessages;
	private ArrayList<OkmanyDTO> okmanyDTO;
	
	public OkmanyDtoResponse() {}
	
	public OkmanyDtoResponse(List<String> errorMessages, ArrayList<OkmanyDTO> okmanyDTO) {
		this.errorMessages = errorMessages;
		this.okmanyDTO = okmanyDTO;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void setOkmanyDTO(ArrayList<OkmanyDTO> okmanyDTO) {
		this.okmanyDTO = okmanyDTO;
	}
	
	public ArrayList<OkmanyDTO> getOkmanyDTO() {
		return okmanyDTO;
	}
	
}

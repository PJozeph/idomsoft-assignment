package home.assignment.idomsoft.documentservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import home.assignment.idomsoft.documentservice.entity.OkmanyDTO;

@Service
public class CardNumberValidator {

	private List<String> errosMessages = new ArrayList<>();

	public List<String> isvalid(List<OkmanyDTO> okmanyDtoList) {

		okmanyDtoList.forEach(e -> {
			if (e.getOkmTipus().equals("Személyazonosító igazolvány")) {
				if (!e.getOkmanySzam().matches("^[a-zA-Z]{6}\\d{2}$")) {
					errosMessages.add("ID car number has to be 6 numbers and 2 digit");
				}
			} else if (e.getOkmTipus().equals("Útlevél")) {
				if (!e.getOkmanySzam().matches("^[a-zA-Z]{2}\\d{7}$")) {
					errosMessages.add("Passport number has to be 2 numbers and 7 digit");
				}
			} else if (e.getOkmTipus().equals("Vezetői engedély")) {
				if (e.getOkmanySzam().length() > 10) {
					errosMessages.add("Driver Card number can not be loner than 10 charter");
				}
			}
		});
		return errosMessages;
	}

	public List<String> getErrosMessages() {
		return errosMessages;
	}
}

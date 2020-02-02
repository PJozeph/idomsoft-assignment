package home.assignment.idomsoft.documentservice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.assignment.idomsoft.documentservice.entity.Card;
import home.assignment.idomsoft.documentservice.entity.CardWrapper;
import home.assignment.idomsoft.documentservice.entity.OkmanyDTO;

@Service
public class CardService {

	private List<Card> cards;

	@Autowired
	private ObjectMapper objectMapper;
	
	
	public String getOkmanyTipus(String cardId) {
		 Optional<Card> card = cards
				 .stream()
				 .filter(document -> document.getKod().equals(cardId))
				 .findFirst();
		 return card.isPresent() ? card.get().getErtek() : null;
	}

	public List<String> isCardNumbervalid(List<OkmanyDTO> okmanyDtoList) {
		List<String> errosMessages = new ArrayList<>();
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

	@PostConstruct
	public void readJson() {
		try {
			CardWrapper cardWrapper
				= objectMapper.readValue(
					new File(
							getClass()
							.getClassLoader()
							.getResource("kodszotar46_okmanytipus.json")
							.getFile()),CardWrapper.class);
			this.cards = cardWrapper.getRows();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

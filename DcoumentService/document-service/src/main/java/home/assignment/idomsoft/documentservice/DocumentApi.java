package home.assignment.idomsoft.documentservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.assignment.idomsoft.documentservice.entity.OkmanyDTO;
import home.assignment.idomsoft.documentservice.entity.OkmanyDtoResponse;
import home.assignment.idomsoft.documentservice.service.CardService;

@RestController
public class DocumentApi {
	
	@Autowired
	private CardService cardService;

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(method = RequestMethod.POST, value = "/documentApi")
	@ResponseBody
	public ResponseEntity<OkmanyDtoResponse> getPersonDocuments(@RequestBody String okmanyDtos) {
		List<String> errosMessages = new ArrayList<>();
		List<OkmanyDTO> cards;
		try {
			cards = Arrays.asList(objectMapper.readValue(okmanyDtos, OkmanyDTO[].class));
		} catch (IOException e) {
			errosMessages.add(e.getMessage());
			return new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()),HttpStatus.OK);
		}

		cards.forEach(card -> {
			String okmanyTipus = cardService.getOkmanyTipus(card.getOkmTipus());
			if (okmanyTipus == null) {
				errosMessages.add(card.getOkmTipus() + " no such a card type");
			}
			else if (card.getLejarDat() == null) {
				errosMessages.add("expiration date is required");
			}
			else {
				card.setOkmTipus(okmanyTipus);
				card.setErvenyes(new Date().getTime() < card.getLejarDat().getTime());
			}
		});

		List<String> cardNumbersValidationMessages = cardService.isCardNumbervalid(cards);
		if (!cardNumbersValidationMessages.isEmpty()) {
			errosMessages.addAll(cardNumbersValidationMessages);
		}

		return errosMessages.isEmpty()
				? new ResponseEntity<>(new OkmanyDtoResponse(new ArrayList<String>(), cards), HttpStatus.OK)
				: new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
	}

}

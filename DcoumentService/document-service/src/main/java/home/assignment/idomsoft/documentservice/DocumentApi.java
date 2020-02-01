package home.assignment.idomsoft.documentservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.assignment.idomsoft.documentservice.entity.Card;
import home.assignment.idomsoft.documentservice.entity.OkmanyDTO;
import home.assignment.idomsoft.documentservice.entity.OkmanyDtoResponse;
import home.assignment.idomsoft.documentservice.service.CardNumberValidator;
import home.assignment.idomsoft.documentservice.service.CardService;

//crossorigin 
@RestController
public class DocumentApi {
	final String uri = "http://localhost:8080/documentApi";

	@Autowired
	private CardService cardService;
	
	@Autowired 
	private CardNumberValidator cardNumberValidator;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(method = RequestMethod.POST, value = "/documentApi")
	@ResponseBody
	public ResponseEntity<OkmanyDtoResponse> getPersonDetaile(@RequestBody List<String> okmanyDtos) {
		
			ArrayList<OkmanyDTO> personCards = new ArrayList<>();
		for (String okmanyDTO : okmanyDtos) {
			try {
				personCards.add(objectMapper.readValue(okmanyDTO, OkmanyDTO.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		if (bindingResult.hasErrors()) {
//			List<ObjectError> allErrors = bindingResult.getAllErrors();
//			List<String> errosMessages = new ArrayList<>();
//			allErrors.forEach(e -> errosMessages.add(e.getDefaultMessage()));
//			return new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
//		}
//		
//		ArrayList<OkmanyDTO> personCards = new ArrayList<>();
//		for (String  cardId : cardList.split(",")) {
//			personCards.add(cardService.getOkmanyDto(cardId));
//		}
//		
		List<String> errosMessages = cardNumberValidator.isvalid(personCards);
		
		return errosMessages.isEmpty() ?  new ResponseEntity<>(new OkmanyDtoResponse(new ArrayList<String>(),personCards), HttpStatus.OK) :
			new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
	}

}

package home.assignment.idomsoft.documentservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import home.assignment.idomsoft.documentservice.entity.Card;
import home.assignment.idomsoft.documentservice.entity.OkmanyDTO;
import home.assignment.idomsoft.documentservice.entity.OkmanyDtoResponse;
import home.assignment.idomsoft.documentservice.service.CardService;

//crossorigin 
@RestController
public class DocumentApi {
	final String uri = "http://localhost:8080/documentApi";

	@Autowired
	private CardService cardService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/documentApi")
	public ResponseEntity<OkmanyDtoResponse> getPersonDetaile(@RequestBody @Valid OkmanyDTO okmanyDto ,BindingResult bindingResult ,
			@RequestParam(name = "cardList") String cardList) {
		
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			List<String> errosMessages = new ArrayList<>();
			allErrors.forEach(e -> errosMessages.add(e.getDefaultMessage()));
			return new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
		}
		
		ArrayList<OkmanyDTO> personCards = new ArrayList<>();
		for (String  cardId : cardList.split(",")) {
			personCards.add(cardService.getOkmanyDto(cardId));
		}
		return new ResponseEntity<>(new OkmanyDtoResponse(new ArrayList<String>(),personCards), HttpStatus.OK);
	}

}

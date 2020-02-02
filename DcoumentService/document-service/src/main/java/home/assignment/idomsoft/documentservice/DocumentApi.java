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
	public ResponseEntity<OkmanyDtoResponse> getPersonDetaile(@RequestBody String okmanyDtos) {
			List<OkmanyDTO> okmanyDtoList = null;
			List<String> errosMessages = new ArrayList<>();
			try {
				 okmanyDtoList =  Arrays.asList(objectMapper.readValue(okmanyDtos, OkmanyDTO[].class));
			} catch (IOException e) {
				errosMessages.add(e.getMessage());
				return new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
			}
			
			List<String> isvalid = cardNumberValidator.isvalid(okmanyDtoList);
			if (!isvalid.isEmpty()) {
				errosMessages.addAll(isvalid);
			}
		
		return errosMessages.isEmpty()
				? new ResponseEntity<>(new OkmanyDtoResponse(new ArrayList<String>(), okmanyDtoList), HttpStatus.OK)
				: new ResponseEntity<>(new OkmanyDtoResponse(errosMessages, new ArrayList<OkmanyDTO>()), HttpStatus.OK);
	}

}

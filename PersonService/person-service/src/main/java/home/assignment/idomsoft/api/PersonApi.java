package home.assignment.idomsoft.api;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import home.assignment.idomsoft.entity.OkmanyDTO;
import home.assignment.idomsoft.entity.OkmanyDtoResponse;
import home.assignment.idomsoft.entity.PersonDetailesResponse;
import home.assignment.idomsoft.entity.SzemelyDTO;
import home.assignment.idomsoft.entity.ValidationErrorMessage;
import home.assignment.idomsoft.service.NationalityService;


@RestController
public class PersonApi {
	
	@Autowired
	private NationalityService nationalityService;
	
	private final String uri = "http://localhost:8082/documentApi?cardList={cardList}";

	@RequestMapping("/validate-person")
	public ResponseEntity<Object> getPersonDetaile(@Valid SzemelyDTO szemelyDto, BindingResult bindingResult ,OkmanyDTO okmanyDto ) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			List<String> errosMessages = new ArrayList<>();
			allErrors.forEach(e -> errosMessages.add(e.getDefaultMessage()));
			return new ResponseEntity<>(new ValidationErrorMessage(errosMessages), HttpStatus.BAD_REQUEST);
		}
		RestTemplate restTemplate = new RestTemplate();
		szemelyDto.setAllampDekod(nationalityService.getNationality(szemelyDto.getAllampKod()));
		
		ResponseEntity<OkmanyDtoResponse> exchange 
						= restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(okmanyDto,createHeader()), OkmanyDtoResponse.class,"1,2,3");
		
		OkmanyDtoResponse body = exchange.getBody();
		
		
		return exchange.getBody().getErrorMessages().size() >= 1 ? 
				  new ResponseEntity<>(new ValidationErrorMessage(body.getErrorMessages()), HttpStatus.BAD_REQUEST):
				  new ResponseEntity<>(new PersonDetailesResponse(szemelyDto), HttpStatus.OK);
	}

	private HttpHeaders createHeader() {
		HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
		return headers;
	}
	
}

package home.assignment.idomsoft.service.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private ObjectMapper objectMapper;
	
	final String uri = "http://localhost:8082/documentApi";

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
		
		
		HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<OkmanyDTO> request = new HttpEntity<>(okmanyDto, headers);
		ResponseEntity<OkmanyDtoResponse> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, OkmanyDtoResponse.class);
		
		OkmanyDtoResponse body = exchange.getBody();
		body.getErrorMessages().forEach(e-> System.out.println(e));
		
		return new ResponseEntity<>(new PersonDetailesResponse(szemelyDto), HttpStatus.OK);
	}
	

	
	
}

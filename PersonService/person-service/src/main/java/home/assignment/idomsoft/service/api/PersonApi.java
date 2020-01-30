package home.assignment.idomsoft.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.assignment.idomsoft.entity.Nationality;
import home.assignment.idomsoft.entity.OkmanyDTO;
import home.assignment.idomsoft.entity.PersonDetailesResponse;
import home.assignment.idomsoft.entity.SzemelyDTO;
import home.assignment.idomsoft.entity.ValidationErrorMessage;
import home.assignment.idomsoft.service.NationalityService;


@RestController
public class PersonApi {
	
	@Autowired
	private NationalityService nationalityService;
	
	
	 final String uri = "http://localhost:8080/documentApi";

	@RequestMapping("/validate-person")
	public ResponseEntity<Object> getPersonDetaile(@Valid SzemelyDTO szemelyDto, BindingResult bindingResult  ,OkmanyDTO okmanyDto ) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			List<String> errosMessages = new ArrayList<>();
			allErrors.forEach(e -> errosMessages.add(e.getDefaultMessage()));
			return new ResponseEntity<>(new ValidationErrorMessage(errosMessages), HttpStatus.BAD_REQUEST);
			
			
		}
		
		System.out.println(okmanyDto);
		szemelyDto.setAllampDekod(nationalityService.getNationality(szemelyDto.getAllampKod()));
		return new ResponseEntity<>(new PersonDetailesResponse(szemelyDto), HttpStatus.OK);
	}
}

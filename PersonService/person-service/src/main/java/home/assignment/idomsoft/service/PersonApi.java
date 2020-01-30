package home.assignment.idomsoft.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import home.assignment.idomsoft.entity.OkmanyDTO;
import home.assignment.idomsoft.entity.SzemelyDTO;

@RestController
public class PersonApi {
	
	@Autowired
	private NationalityService nationalityService;
	
	@Autowired 
	private RestTemplate restTemplete;
	
	 final String uri = "http://localhost:8080/documentApi";

	@RequestMapping("/validate-person")
	public SzemelyDTO getPersonDetaile(@Valid SzemelyDTO szemelyDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			allErrors.forEach(e -> System.out.println(e));
		}
		
		
		szemelyDto.setAllampDekod(nationalityService.getNationality(szemelyDto.getAllampKod()));
		
//		ListOkmanyDTO forObject = restTemplete.getForObject(uri, OkmanyDTO.class);
		
		return szemelyDto;
	}

}

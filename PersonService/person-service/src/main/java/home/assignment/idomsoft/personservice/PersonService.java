package home.assignment.idomsoft.personservice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.assignment.idomsoft.personservice.dto.SzemelyDTO;

@RestController
public class PersonService {

	@RequestMapping("/validate-person")
	public SzemelyDTO getPersonDetaile(@Valid SzemelyDTO okmanyDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.err.println("HAS ERRORS@@@@@@@@@@@@@@@");
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			allErrors.forEach(e -> System.out.println(e));
		}
		return null;
	}

}

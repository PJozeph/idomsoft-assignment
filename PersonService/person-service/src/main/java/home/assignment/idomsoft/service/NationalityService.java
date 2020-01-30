package home.assignment.idomsoft.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.assignment.idomsoft.entity.Nationality;
import home.assignment.idomsoft.entity.NationalityWrapper;

@Service
public class NationalityService {

	private List<Nationality> nationalities;

	@Autowired
	private ObjectMapper objectMapper;
	
	public boolean isNationalityCodePresent(String nationalityCode) {
		 return nationalities.stream().filter(nationality -> nationality.getKod().equals(nationalityCode)).findAny().isPresent();
	}
	
	
	public String getNationality(String nationalityCode) {
		return nationalities.stream().filter(nationality -> nationality.getKod().equals(nationalityCode)).findAny().get().getAllampolgarsag();
	}

	@PostConstruct
	public void readJson() {
		try {
			NationalityWrapper nationalityWrapper = objectMapper.readValue(new File("C:\\Users\\ZZ01N1740\\git\\IdomSoft\\idomsoft-assignment\\PersonService\\person-service\\kodszotar21_allampolg.json"),NationalityWrapper.class);
			this.nationalities = nationalityWrapper.getRows();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

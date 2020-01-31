package home.assignment.idomsoft.documentservice.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import home.assignment.idomsoft.documentservice.entity.Card;
import home.assignment.idomsoft.documentservice.entity.CardWrapper;

@Service
public class CardService {

	private List<Card> cards;

	@Autowired
	private ObjectMapper objectMapper;
	

	@PostConstruct
	public void readJson() {
		try {
			CardWrapper cardWrapper
				= objectMapper.readValue(
					new File(
							getClass()
							.getClassLoader()
							.getResource("kodszotar46_okmanytipus.json")
							.getFile()),CardWrapper.class);
			this.cards = cardWrapper.getRows();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

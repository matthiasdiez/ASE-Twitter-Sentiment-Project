package model.dto;

import java.util.ArrayList;
import java.util.List;

public class TermDto {

	public String name;
	public List<SentimentResultDto> results = new ArrayList<SentimentResultDto>();

	public TermDto(String name) {
		this.name = name;
	}

	public void addResult(SentimentResultDto result) {
		results.add(result);
	}
}

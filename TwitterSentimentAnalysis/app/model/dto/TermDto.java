package model.dto;

import java.util.ArrayList;
import java.util.List;

public class TermDto {

	public String name;
	public double overallResult;
	public List<SentimentResultDto> results = new ArrayList<SentimentResultDto>();

	public TermDto(String name, double overallResult) {
		this.name = name;
		this.overallResult = overallResult;
	}

	public void addResult(SentimentResultDto result) {
		results.add(result);
	}
}

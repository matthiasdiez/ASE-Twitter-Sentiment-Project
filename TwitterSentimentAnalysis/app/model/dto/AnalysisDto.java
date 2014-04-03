package model.dto;

import java.util.ArrayList;
import java.util.List;

public class AnalysisDto {

	public String name;
	public List<TermDto> terms = new ArrayList<TermDto>();

	public AnalysisDto(String name) {
		this.name = name;
	}

	public void addTerm(TermDto term) {
		terms.add(term);
	}
}

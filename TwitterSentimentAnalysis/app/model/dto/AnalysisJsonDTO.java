package model.dto;

import java.util.ArrayList;
import java.util.List;

public class AnalysisJsonDTO {

	public String name;
	public List<TermJsonDTO> terms = new ArrayList<TermJsonDTO>();

	public AnalysisJsonDTO(String name) {
		this.name = name;
	}

	public void addTerm(TermJsonDTO term) {
		terms.add(term);
	}
}

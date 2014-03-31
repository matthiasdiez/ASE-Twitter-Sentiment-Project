package model.dto;

import java.util.ArrayList;
import java.util.List;

public class TermJsonDTO {

	public String name;
	public List<ResultJsonDTO> results = new ArrayList<ResultJsonDTO>();

	public TermJsonDTO(String name) {
		this.name = name;
	}

	public void addResult(ResultJsonDTO result) {
		results.add(result);
	}
}

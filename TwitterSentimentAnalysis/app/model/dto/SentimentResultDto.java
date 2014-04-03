package model.dto;

public class SentimentResultDto {

  public String timestamp;
  public double value;

  public SentimentResultDto(final String dateTime, final double value) {
    this.timestamp = dateTime;
    this.value = value;
  }
}

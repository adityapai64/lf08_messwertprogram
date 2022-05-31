package messwertprogram;

import java.sql.Timestamp;

public class Messwert {
	private Double temperature;
	private Timestamp measuredAt;
	
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Timestamp getMeasuredAt() {
		return measuredAt;
	}
	public void setMeasuredAt(Timestamp measuredAt) {
		this.measuredAt = measuredAt;
	}
	
	@Override
	public String toString() {
		return "Temperature: " + temperature + ", Measured on: " + measuredAt;
	}
}

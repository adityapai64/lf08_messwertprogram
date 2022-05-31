package messwertprogram;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;

public class Controller {
	private static final String FILEPATH = "C:\\Users\\adity\\eclipse-workspace\\messwertprogram\\src\\main\\resources\\recordedTemps.json";
	private ObjectMapper objectMapper = new ObjectMapper();
	
	Components components = JSensors.get.components();
	List<Cpu> cpus = components.cpus;
	List<Temperature> temps = null;
	
	private void saveToFile(MesswertListe messwertListe) {
		try {
			File file = new File(FILEPATH);
			if (!file.exists()) {
				file.createNewFile();
			}
			objectMapper.writeValue(file, messwertListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void readTemps() {
		if(cpus != null) {
			Cpu cpu = cpus.get(cpus.size()-1);
				if (cpu.sensors != null) {
					temps = cpu.sensors.temperatures;
				}
				if (temps != null) {
					for (int i = 0; i < 4; i++) {
						MesswertListe messwertListe = loadMesswertListe();
						Messwert messwert = new Messwert();
						Timestamp time = new Timestamp(System.currentTimeMillis());
						messwert.setTemperature(temps.get(temps.size() - 1).value);
						messwert.setMeasuredAt(time);
						if (messwertListe.getMesswertListe() == null) {
							messwertListe.setMesswertListe(new ArrayList <>());
						}
						messwertListe.getMesswertListe().add(messwert);
						saveToFile(messwertListe);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Werte eingelesen!");
				} else {
					System.out.println("Fehler beim Lesen der Werte");
				}
			}
	}
	
	public MesswertListe loadMesswertListe () {
		MesswertListe messwertListe = new MesswertListe();
		try {
			File file = new File(FILEPATH);
			if (file.exists()) {
				messwertListe = objectMapper.readValue(new File(FILEPATH), MesswertListe.class);
			}
			objectMapper.writeValue(file, messwertListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messwertListe;
	}
	
	public void printTemps() {
		MesswertListe messwertsliste = loadMesswertListe();
		if (messwertsliste.getMesswertListe() != null && !messwertsliste.getMesswertListe().isEmpty()) {
			for (Messwert messwert : messwertsliste.getMesswertListe()) {
				System.out.println(messwert);
			}
		} else {
			System.out.println("Die WertListe ist leer. Bitte lesen Sie die Werte zuerst ein");
		}
	}
	
	public void calculateMedian() {
		MesswertListe messwertsliste = loadMesswertListe();
		double result = 0;
		if (messwertsliste.getMesswertListe() != null && !messwertsliste.getMesswertListe().isEmpty()) {
			for (Messwert messwert : messwertsliste.getMesswertListe()) {
				result += messwert.getTemperature();
			}
		System.out.println("Median: " + result/messwertsliste.getMesswertListe().size());
		} else {
			System.out.println("Die WertListe ist leer. Bitte lesen Sie die Werte zuerst ein");
		}
		
	}
	
}

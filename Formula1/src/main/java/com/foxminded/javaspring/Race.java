package com.foxminded.javaspring;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class Race {

	static List<String> readAbbrevations() {
		String fileName = "abbrevations.txt";
		List<String> abbrevations = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			abbrevations = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return abbrevations;
	}

	static List<String> readStartTimes() {
		String fileName = "start.txt";
		List<String> startTimesList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			startTimesList = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return startTimesList;
	}

	static List<String> readEndTimes() {
		String fileName = "end.txt";
		List<String> endTimesList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			endTimesList = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return endTimesList;
	}

	static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	static Map<String, LocalTime> startTimes() {
		return readStartTimes().stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalTime.from(fmt.parse(s.substring(14)))));
	}

	static Map<String, LocalTime> endTimes() {
		return readEndTimes().stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalTime.from(fmt.parse(s.substring(14)))));
	}

	static Map<String, Duration> results() {
		return readStartTimes().stream().collect(Collectors.toMap((s -> s.substring(0, 3)),
				s -> Duration.between((startTimes().get(s.substring(0, 3))), (endTimes().get(s.substring(0, 3))))));
	}

	static List<Racer> racersList() {

		List<String[]> abbrevationsToArray = readAbbrevations().stream().map(s -> s.split("_"))
				.collect(Collectors.toList());
		return abbrevationsToArray.stream().map(s -> new Racer(s[0], s[1], s[2], results().get(s[0])))
				.sorted((Racer r1, Racer r2) -> (int) (r1.getResult().toMillis() - (int) r2.getResult().toMillis()))
				.collect(Collectors.toList());

	}

	static String output() {

		LocalTime base = LocalTime.of(0, 0, 0, 0);

		List<StringBuilder> unnumberedList = racersList().stream()
				.map((r) -> new StringBuilder()
						.append(". " + r.getName() + String.format("%" + (20 - r.getName().length()) + "s", " ") + "| "
								+ r.getTeam() + String.format("%" + (28 - r.getTeam().length()) + "s", " ") + "| "
								+ base.plusNanos(r.getResult().toNanos())))
				.collect(Collectors.toList());

		// Here I don't know how to add the number of the racer by stream so I use forEach:

		int number = 1;
		List<StringBuilder> numberedList = new ArrayList<>(); 
		for (StringBuilder line : unnumberedList) {
			numberedList.add(new StringBuilder().append(number).append(line));
			number++;
		}
		
		List<StringBuilder> top15 = numberedList.stream().limit(15).collect(Collectors.toList());
		List<StringBuilder> theRest = numberedList.stream().skip(15).collect(Collectors.toList());
		
		String outputTop15 = String.join("\n", top15);
		String outputTheRest = String.join("\n", theRest);
		return new StringBuilder().append(outputTop15 + "\n--------------------------------------------------------------------\n" 
		+ outputTheRest).toString();

	}

	public static void main(String[] args) {
		
		System.out.println(output());

	}

}

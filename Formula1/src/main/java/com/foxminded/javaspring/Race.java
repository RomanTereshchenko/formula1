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

	static String abbrevationsFilePath = "abbrevations.txt";
	static String startFilePath = "start.txt";
	static String endFilePath = "end.txt";
	static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public static void main(String[] args) {

		System.out.println(racersResultsPrintout());

	}

	static String racersResultsPrintout() {

		LocalTime base = LocalTime.of(0, 0, 0, 0);

		List<String> unnumberedList = racersList().stream()
				.map((r) -> r.getName() + String.format("%" + (20 - r.getName().length()) + "s", " ") + "| "
						+ r.getTeam() + String.format("%" + (28 - r.getTeam().length()) + "s", " ") + "| "
						+ base.plusNanos(r.getResult().toNanos()))
				.collect(Collectors.toList());

		List<String> numberedList = IntStream.rangeClosed(1, unnumberedList.size())
				.mapToObj(number -> number + ". " + unnumberedList.get(number - 1)).collect(Collectors.toList());

		List<String> top15 = numberedList.stream().limit(15).collect(Collectors.toList());
		List<String> theRest = numberedList.stream().skip(15).collect(Collectors.toList());

		String outputTop15 = String.join("\n", top15);
		String outputTheRest = String.join("\n", theRest);
		return new StringBuilder().append(outputTop15
				+ "\n--------------------------------------------------------------------\n" + outputTheRest)
				.toString();

	}

	static List<Racer> racersList() {

		Map<String, Duration> resultPerRacer = results();

		List<String[]> racersParameters = getFileLines(abbrevationsFilePath).stream().map(s -> s.split("_"))
				.collect(Collectors.toList());
		final int abbevation = 0;
		final int name = 1;
		final int team = 2;

		return racersParameters.stream()
				.map(racer -> new Racer(racer[abbevation], racer[name], racer[team],
						resultPerRacer.get(racer[abbevation])))
				.sorted((Racer r1, Racer r2) -> (int) (r1.getResult().toMillis() - (int) r2.getResult().toMillis()))
				.collect(Collectors.toList());

	}

	static List<String> getFileLines(String filePath) {

		List<String> linesFromFile = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

			linesFromFile = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linesFromFile;
	}

	static Map<String, Duration> results() {
		Map<String, LocalTime> abbrPerStartTime = startTimes();
		Map<String, LocalTime> abbrPerEndTime = endTimes();
		return abbrPerStartTime.entrySet().stream()
				.collect(Collectors.toMap(abbrAndStartTime -> abbrAndStartTime.getKey(), abbrAndStartTime -> Duration
						.between(abbrAndStartTime.getValue(), abbrPerEndTime.get(abbrAndStartTime.getKey()))));
	}

	static Map<String, LocalTime> startTimes() {
		return getFileLines(startFilePath).stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalTime.from(fmt.parse(s.substring(14)))));
	}

	static Map<String, LocalTime> endTimes() {
		return getFileLines(endFilePath).stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> LocalTime.from(fmt.parse(s.substring(14)))));
	}

}

package com.foxminded.javaspring;

import java.time.Duration;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

class Racer {

	String abbr;
	String name;
	String team;
	Duration result;


}

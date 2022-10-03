package com.foxminded.javaspring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RaceTest {

	@Test
	void racersResultsPrintout_returnsCorrectPrintout_givenFiles() {
		assertEquals("1. Sebastian Vettel    | FERRARI                     | 00:01:04.415\n"
				+ "2. Daniel Ricciardo    | RED BULL RACING TAG HEUER   | 00:01:12.013\n"
				+ "3. Valtteri Bottas     | MERCEDES                    | 00:01:12.434\n"
				+ "4. Lewis Hamilton      | MERCEDES                    | 00:01:12.460\n"
				+ "5. Stoffel Vandoorne   | MCLAREN RENAULT             | 00:01:12.463\n"
				+ "6. Kimi Raikkonen      | FERRARI                     | 00:01:12.639\n"
				+ "7. Fernando Alonso     | MCLAREN RENAULT             | 00:01:12.657\n"
				+ "8. Sergey Sirotkin     | WILLIAMS MERCEDES           | 00:01:12.706\n"
				+ "9. Charles Leclerc     | SAUBER FERRARI              | 00:01:12.829\n"
				+ "10. Sergio Perez        | FORCE INDIA MERCEDES        | 00:01:12.848\n"
				+ "11. Romain Grosjean     | HAAS FERRARI                | 00:01:12.930\n"
				+ "12. Pierre Gasly        | SCUDERIA TORO ROSSO HONDA   | 00:01:12.941\n"
				+ "13. Carlos Sainz        | RENAULT                     | 00:01:12.950\n"
				+ "14. Esteban Ocon        | FORCE INDIA MERCEDES        | 00:01:13.028\n"
				+ "15. Nico Hulkenberg     | RENAULT                     | 00:01:13.065\n"
				+ "--------------------------------------------------------------------\n"
				+ "16. Brendon Hartley     | SCUDERIA TORO ROSSO HONDA   | 00:01:13.179\n"
				+ "17. Marcus Ericsson     | SAUBER FERRARI              | 00:01:13.265\n"
				+ "18. Lance Stroll        | WILLIAMS MERCEDES           | 00:01:13.323\n"
				+ "19. Kevin Magnussen     | HAAS FERRARI                | 00:01:13.393", Race.racersResultsPrintout());
	}
	
	@Test
	void getFileLines_returnsLinesFromTestFile_testFile() {
		
		assertEquals("[line1, line2, line3]", Race.getFileLines("testFile.txt").toString());
	}
	
	
}

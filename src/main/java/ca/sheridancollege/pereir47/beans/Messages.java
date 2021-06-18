package ca.sheridancollege.pereir47.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Messages {
	
	private Long id;
	private Long threadId;
	private String message;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dates;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime times;
	private String employee;

}

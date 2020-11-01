package tw.com.BeMet.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		if (source.strip().isBlank()) {
			return null;
		}
		try {
			return LocalDateTime.parse(source);
		} catch (DateTimeParseException e) {
			return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
	}

}

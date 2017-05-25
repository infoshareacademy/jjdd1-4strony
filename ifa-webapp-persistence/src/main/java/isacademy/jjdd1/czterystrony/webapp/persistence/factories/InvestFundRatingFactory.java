package isacademy.jjdd1.czterystrony.webapp.persistence.factories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvestFundRatingFactory {
    private static final String DELIMITER = ",";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final int DATE_COLUMN_INDEX_IN_DATA_FILE = 1;
    private static final int OPEN_COLUMN_INDEX_IN_DATA_FILE = 2;
    private static final int HIGH_COLUMN_INDEX_IN_DATA_FILE = 3;
    private static final int LOW_COLUMN_INDEX_IN_DATA_FILE = 4;
    private static final int CLOSE_COLUMN_INDEX_IN_DATA_FILE = 5;

    public static InvestFundRating create(String record, InvestFund investFund) {
        String[] splittedRecord = record.split(DELIMITER);

        return new InvestFundRating.Builder()
                .withDate(parseDateFrom(splittedRecord[DATE_COLUMN_INDEX_IN_DATA_FILE]))
                .withOpen(parseValueFrom(splittedRecord[OPEN_COLUMN_INDEX_IN_DATA_FILE]))
                .withHigh(parseValueFrom(splittedRecord[HIGH_COLUMN_INDEX_IN_DATA_FILE]))
                .withLow(parseValueFrom(splittedRecord[LOW_COLUMN_INDEX_IN_DATA_FILE]))
                .withClose(parseValueFrom(splittedRecord[CLOSE_COLUMN_INDEX_IN_DATA_FILE]))
                .withInstrument(investFund)
                .build();
    }

    private static LocalDate parseDateFrom(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private static BigDecimal parseValueFrom(String value) {
        return new BigDecimal(Double.parseDouble(value)).setScale(2, RoundingMode.HALF_UP);
    }
}

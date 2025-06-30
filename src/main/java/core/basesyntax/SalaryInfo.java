package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate newDateFrom = parseDate(dateFrom);
        LocalDate newDateTo = parseDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(newDateFrom.format(FORMATTER))
                .append(" - ")
                .append(newDateTo.format(FORMATTER));
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] parts = line.split("\s+");
                LocalDate partDate = parseDate(parts[DATE_INDEX]);
                if (parts[NAME_INDEX].trim().equals(name) && !partDate.isBefore(newDateFrom)
                        && !partDate.isAfter(newDateTo)) {
                    salary += Integer.parseInt(parts[HOURS_INDEX])
                            * Integer.parseInt(parts[INCOME_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}

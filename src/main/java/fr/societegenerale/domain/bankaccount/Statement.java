package fr.societegenerale.domain.bankaccount;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Statement {

  private final LinkedList<StatementLine> statementLines = new LinkedList<>();

  private final Printer printer = new Printer();

  private final Screen screen = new Screen();

  private static final String HEADER = "DATE|CREDIT|DEBIT|BALANCE";

  private static final String TOTAL_BALANCE_TEXT = "TOTAL BALANCE :";

  private static final String FORMAT_DATE = "yyyy-MM-dd";

  private static final String SEPARATOR = "|";

  public List<StatementLine> getStatementLines() {
    return statementLines;
  }

  public void addStatementLine(final BigDecimal balance, final BigDecimal amount, final Date date) {
    final StatementLine statementLine = new StatementLine(date, amount, balance);
    statementLines.add(statementLine);
  }

  public Printer getPrinter() {
    return printer;
  }

  public Screen getScreen() {
    return screen;
  }

  public void printStatement(final BigDecimal balance) {
    printer.print(buildStatement(balance));
  }

  public void showStatement(final BigDecimal balance) {
    screen.show(buildStatement(balance));
  }

  private LinkedList<String> buildStatement(final BigDecimal balance) {
    final LinkedList<String> lines = new LinkedList<>();
    lines.add(HEADER);

    for (final StatementLine statementLine : statementLines) {
      String date = convertDateToString(statementLine.getDate());
      String credit = statementLine.getAmount().signum() >= 0 ? statementLine.getAmount().abs().toString() : "";
      String debit = statementLine.getAmount().signum() < 0 ? statementLine.getAmount().abs().toString() : "";
      String balanceLine = statementLine.getBalance().toString();

      lines.add(new StringBuilder(date).append(SEPARATOR).append(credit).append(SEPARATOR).append(debit)
        .append(SEPARATOR).append(balanceLine).toString());
    }

    lines.add(new StringBuilder(TOTAL_BALANCE_TEXT).append(balance).toString());

    return lines;
  }

  private String convertDateToString(final Date date) {
    final DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
    return dateFormat.format(date);
  }
}

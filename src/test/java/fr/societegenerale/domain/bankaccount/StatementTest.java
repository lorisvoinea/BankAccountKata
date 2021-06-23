package fr.societegenerale.domain.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatementTest {

  private Account account;

  private static final String HEADER = "DATE|CREDIT|DEBIT|BALANCE";

  private static final String TOTAL_BALANCE_TEXT = "TOTAL BALANCE :";

  private static final String FORMAT_DATE = "yyyy-MM-dd";

  private static final String SEPARATOR = "|";

  @BeforeEach
  public void setUp() throws Exception {
    account = new Account();
  }

  @Test
  @DisplayName("handle statement print")
  public void should_print_all_statement() {

    account.deposit(new BigDecimal(10000));
    account.withdraw(new BigDecimal(1000));
    account.deposit(new BigDecimal(200));

    account.printStatement();

    Iterator<StatementLine> it = account.getStatement().getStatementLines().iterator();

    StringBuilder strBuilder = new StringBuilder("-----Print------");
    strBuilder.append("\n");
    strBuilder.append(HEADER);
    strBuilder.append("\n");

    StatementLine statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append("10000").append(SEPARATOR)
      .append(SEPARATOR).append("10000");
    strBuilder.append("\n");

    statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append(SEPARATOR).append("1000")
      .append(SEPARATOR).append("9000");
    strBuilder.append("\n");

    statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append("200").append(SEPARATOR)
      .append(SEPARATOR).append("9200");
    strBuilder.append("\n");

    strBuilder.append(TOTAL_BALANCE_TEXT).append("9200");
    strBuilder.append("\n");

    strBuilder.append("-----End print------");
    strBuilder.append("\n");

    assertEquals(account.getStatement().getPrinter().getPrintedStr(), strBuilder.toString());

  }

  @Test
  @DisplayName("handle show statement")
  public void should_show_all_statement() {

    account.deposit(new BigDecimal(10000));
    account.withdraw(new BigDecimal(1000));
    account.deposit(new BigDecimal(200));

    account.showStatement();

    Iterator<StatementLine> it = account.getStatement().getStatementLines().iterator();

    StringBuilder strBuilder = new StringBuilder("-----Show------");
    strBuilder.append("\n");
    strBuilder.append(HEADER);
    strBuilder.append("\n");

    StatementLine statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append("10000").append(SEPARATOR)
      .append(SEPARATOR).append("10000");
    strBuilder.append("\n");

    statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append(SEPARATOR).append("1000")
      .append(SEPARATOR).append("9000");
    strBuilder.append("\n");

    statementLine = it.next();
    strBuilder.append(convertDateToString(statementLine.getDate())).append(SEPARATOR).append("200").append(SEPARATOR)
      .append(SEPARATOR).append("9200");
    strBuilder.append("\n");

    strBuilder.append(TOTAL_BALANCE_TEXT).append("9200");
    strBuilder.append("\n");

    strBuilder.append("-----End show------");
    strBuilder.append("\n");

    assertEquals(account.getStatement().getScreen().getShownStr(), strBuilder.toString());
  }

  @Test
  @DisplayName("handle add statement")
  public void should_get_all_statement() {

    Statement statement = account.getStatement();

    statement.addStatementLine(new BigDecimal(10000), new BigDecimal(10000), new Date());
    statement.addStatementLine(new BigDecimal(9000), new BigDecimal(-1000), new Date());
    statement.addStatementLine(new BigDecimal(9200), new BigDecimal(200), new Date());

    Iterator<StatementLine> statementLines = statement.getStatementLines().iterator();

    StatementLine statementLine = statementLines.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(10000));
    assertEquals(statementLine.getBalance(), new BigDecimal(10000));
    assertEquals(convertDateToString(statementLine.getDate()), convertDateToString(new Date()));

    statementLine = statementLines.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(-1000));
    assertEquals(statementLine.getBalance(), new BigDecimal(9000));
    assertEquals(convertDateToString(statementLine.getDate()), convertDateToString(new Date()));

    statementLine = statementLines.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(200));
    assertEquals(statementLine.getBalance(), new BigDecimal(9200));
    assertEquals(convertDateToString(statementLine.getDate()), convertDateToString(new Date()));
  }

  private String convertDateToString(final Date date) {
    final DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
    return dateFormat.format(date);
  }

}

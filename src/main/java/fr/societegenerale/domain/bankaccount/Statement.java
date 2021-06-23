package fr.societegenerale.domain.bankaccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Statement {

  private final LinkedList<StatementLine> statementLines = new LinkedList<>();

  public List<StatementLine> getStatementLines() {
    return statementLines;
  }

  public void addStatementLine(final BigDecimal balance, final BigDecimal amount, final Date date) {
    final StatementLine statementLine = new StatementLine(date, amount, balance);
    statementLines.add(statementLine);
  }
}

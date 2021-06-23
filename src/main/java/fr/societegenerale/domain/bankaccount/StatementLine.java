package fr.societegenerale.domain.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

public class StatementLine {

  private Date date;

  private BigDecimal amount;

  private BigDecimal balance;

  public StatementLine(final Date date, final BigDecimal amount, final BigDecimal balance) {
    this.date = date;
    this.amount = amount;
    this.balance = balance;
  }

  public Date getDate() {
    return date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getBalance() {
    return balance;
  }

}

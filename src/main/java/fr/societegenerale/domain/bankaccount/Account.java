package fr.societegenerale.domain.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

  private Statement statement = new Statement();

  private BigDecimal balance = BigDecimal.ZERO;

  public Statement getStatement() {
    return statement;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void deposit(BigDecimal amount) {
    //TODO
  }

  public void withdraw(BigDecimal amount) {
    if (amount == null)
      amount = BigDecimal.ZERO;
    balance = balance.subtract(amount.abs());
    statement.addStatementLine(balance, amount.negate(), new Date());
  }

  public void printStatement() {
    //TODO
  }

  public void showStatement() {
    //TODO
  }

}

package fr.societegenerale.domain.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class AccountTest {

  private Account account;

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach
  public void setUp() throws Exception {
    account = new Account();
  }

  @Test
  @DisplayName("withdraw should work")
  public void should_return_balance_and_statement_witdraw() {
    account.withdraw(new BigDecimal(50));

    LinkedList<StatementLine> statementlines = (LinkedList<StatementLine>) account.getStatement().getStatementLines();
    assertEquals(account.getBalance(), new BigDecimal(-50));
    assertEquals(statementlines.getFirst().getAmount(), new BigDecimal(-50));
    assertEquals(FORMAT.format(statementlines.getFirst().getDate()), FORMAT.format(new Date()));
    assertEquals(statementlines.getFirst().getBalance(), new BigDecimal(-50));
  }

  @Test
  @DisplayName("withdraw null")
  public void should_return_balance_and_statement_witdraw_null() {
    account.withdraw(null);

    LinkedList<StatementLine> statementlines = (LinkedList<StatementLine>) account.getStatement().getStatementLines();
    assertEquals(account.getBalance(), new BigDecimal(0));
    assertEquals(statementlines.getFirst().getAmount(), new BigDecimal(0));
    assertEquals(FORMAT.format(statementlines.getFirst().getDate()), FORMAT.format(new Date()));
    assertEquals(statementlines.getFirst().getBalance(), new BigDecimal(0));
  }

  @Test
  @DisplayName("deposit should work")
  public void should_return_balance_and_statement_deposit() {
    account.deposit(new BigDecimal(50));

    LinkedList<StatementLine> statementlines = (LinkedList<StatementLine>) account.getStatement().getStatementLines();
    assertEquals(account.getBalance(), new BigDecimal(50));
    assertEquals(statementlines.getFirst().getAmount(), new BigDecimal(50));
    assertEquals(FORMAT.format(statementlines.getFirst().getDate()), FORMAT.format(new Date()));
    assertEquals(statementlines.getFirst().getBalance(), new BigDecimal(50));
  }

  @Test
  @DisplayName("deposit null")
  public void should_return_balance_and_statement_deposit_null() {
    account.deposit(null);

    LinkedList<StatementLine> statementlines = (LinkedList<StatementLine>) account.getStatement().getStatementLines();
    assertEquals(account.getBalance(), new BigDecimal(0));
    assertEquals(statementlines.getFirst().getAmount(), new BigDecimal(0));
    assertEquals(FORMAT.format(statementlines.getFirst().getDate()), FORMAT.format(new Date()));
    assertEquals(statementlines.getFirst().getBalance(), new BigDecimal(0));
  }

  @Test
  @DisplayName("deposit and withdraw should work")
  public void should_return_balance_and_statement_deposit_withdraw() {
    account.deposit(new BigDecimal(1000000));
    account.withdraw(new BigDecimal(1000));
    account.deposit(new BigDecimal(500));

    Iterator<StatementLine> it = account.getStatement().getStatementLines().iterator();

    StatementLine statementLine = it.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(1000000));
    assertEquals(FORMAT.format(statementLine.getDate()), FORMAT.format(new Date()));
    assertEquals(statementLine.getBalance(), new BigDecimal(1000000));

    statementLine = it.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(-1000));
    assertEquals(FORMAT.format(statementLine.getDate()), FORMAT.format(new Date()));
    assertEquals(statementLine.getBalance(), new BigDecimal(999000));

    statementLine = it.next();
    assertEquals(statementLine.getAmount(), new BigDecimal(500));
    assertEquals(FORMAT.format(statementLine.getDate()), FORMAT.format(new Date()));
    assertEquals(statementLine.getBalance(), new BigDecimal(999500));

  }

}

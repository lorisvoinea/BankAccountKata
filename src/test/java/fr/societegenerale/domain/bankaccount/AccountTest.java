package fr.societegenerale.domain.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import fr.societegenerale.domain.bankaccount.Account;
import fr.societegenerale.domain.bankaccount.StatementLine;

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

}

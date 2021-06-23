package fr.societegenerale.domain.bankaccount;

import java.util.List;

public class Printer {

  private String printedStr = "";

  public String getPrintedStr() {
    return printedStr;
  }

  public void print(final List<String> lines) {

    final StringBuilder toPrint = new StringBuilder();
    toPrint.append("-----Print------");
    toPrint.append("\n");

    for (final String line : lines) {
      toPrint.append(line);
      toPrint.append("\n");
    }

    toPrint.append("-----End print------");
    toPrint.append("\n");

    printedStr = toPrint.toString();
  }
}

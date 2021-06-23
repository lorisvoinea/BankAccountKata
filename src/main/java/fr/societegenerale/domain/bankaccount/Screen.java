package fr.societegenerale.domain.bankaccount;

import java.util.List;

public class Screen {

  private String shownStr = "";

  public String getShownStr() {
    return shownStr;
  }

  public void show(final List<String> lines) {

    final StringBuilder toShow = new StringBuilder();
    toShow.append("-----Show------");
    toShow.append("\n");

    for (final String line : lines) {
      toShow.append(line);
      toShow.append("\n");
    }

    toShow.append("-----End show------");
    toShow.append("\n");

    shownStr = toShow.toString();
  }

}

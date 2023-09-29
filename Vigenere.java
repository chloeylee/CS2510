import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

// represents the vigenere code problem
class Vigenere {
  ArrayList<Character> alphabet;
  ArrayList<ArrayList<Character>> table;

  public Vigenere() {
    this.alphabet = new ArrayList<Character>(Arrays.asList(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
        't', 'u', 'v', 'w', 'x', 'y', 'z'));
    this.table = initVigenere();
  }

  // initialize vigenere table 
  public ArrayList<ArrayList<Character>> initVigenere() {
    ArrayList<ArrayList<Character>> vigTable = new ArrayList<ArrayList<Character>>();

    for (int index = 0; index < 26; index++) {
      ArrayList<Character> row = new ArrayList<Character>();
      for (int rowIndex = 0; rowIndex < 26; rowIndex++) {
        row.add(this.alphabet.get((index + rowIndex) % 26));
      }
      vigTable.add(row);
    }
    return vigTable;
  }

  // consumes the encoded message and keyword and uses table to decipher
  public String decode(String msg, String keyword) {
    String key = getKey(msg, keyword);
    StringBuilder result = new StringBuilder();

    for (int index = 0; index < msg.length(); index++) {
      int row = this.alphabet.indexOf(key.charAt(index));
      int col = this.table.get(row).indexOf(msg.charAt(index));

      result.append(this.alphabet.get(col));
    }
    return result.toString();
  }

  // consume the message and keyword and uses table to produce the encoded message
  public String encode(String msg, String keyword) {
    String key = getKey(msg, keyword);
    StringBuilder encodedMsg = new StringBuilder();

    for (int index = 0; index < msg.length(); index++) {
      int row = this.alphabet.indexOf(key.charAt(index));
      int col = this.alphabet.indexOf(msg.charAt(index));
      encodedMsg.append(this.table.get(row).get(col));
    }
    return encodedMsg.toString();
  }

  // retrieves the key using the message and keyword
  String getKey(String msg, String keyword) {
    StringBuilder key = new StringBuilder();

    for (int index = 0; index < msg.length(); index++) {
      key.append(keyword.charAt(index % keyword.length()));
    }
    return key.toString();
  }
}

class ExamplesVigenere {
  ExamplesVigenere(){}

  ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList(
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
      'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
      't', 'u', 'v', 'w', 'x', 'y', 'z'));

  Vigenere vigenere = new Vigenere();

  void testInitVigenere(Tester t) {
    t.checkExpect(vigenere.initVigenere().size(), 26);
  }

  void testDecode(Tester t) {
    t.checkExpect(vigenere.decode("oijss", "hey"), "hello");
    t.checkExpect(vigenere.decode("glmtekwty", "yay"), "ilovemyta");
    t.checkExpect(vigenere.decode("vtntpu", "pls"), "giveec");
  }

  void testEncode(Tester t) {
    t.checkExpect(vigenere.encode("hello", "hey"), "oijss");
    t.checkExpect(vigenere.encode("ilovemyta", "yay"), "glmtekwty");
    t.checkExpect(vigenere.encode("giveec", "pls"), "vtntpu");
  }

  void testGenerateKey(Tester t) {
    t.checkExpect(vigenere.getKey("ilovemyta", "yay"), "yayyayyay");
    t.checkExpect(vigenere.getKey("giveec", "pls"), "plspls");
    t.checkExpect(vigenere.getKey("hello", "hey"), "heyhe");
  }

}
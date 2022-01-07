package ch.ibw.clientServer.server.dateReply;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
* Lasse jeden Unit-Test einzeln laufen.
* Zuvor musst du den DateServer manuell starten.
* */
class DateServerTest {
  private Client client;

  @BeforeEach
  void createClient() {
    client = new Client();
    client.startConnection("127.0.0.1", 6060);
  }

  @AfterEach
  void stopClient(){
    this.client.stopConnection();
  }

  @Test
  void singleRequest_respondsWithDate(){
    client.readFromServer();
    String response = client.sendMessage("date");
    Assertions.assertTrue(response.contains("CET 2022"));
  }

  @Test
  void twoRequestsInSequence_respondWithDate() {
    client.readFromServer();
    String response1 = client.sendMessage("date");
    Assertions.assertTrue(response1.contains("CET 2022"));

    // geht nicht, nach erster antwort ist das "protokoll" beendet.
    client.readFromServer();
    String response2 = client.sendMessage("date");
    Assertions.assertTrue(response2.contains("CET 2022"));
  }
}

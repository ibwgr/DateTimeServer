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
    String response = client.sendMessage("");
    Assertions.assertTrue(response.contains("CET 2019"));
  }

  @Test
  void twoRequestsInSequence_respondWithDate(){
    String response1 = client.sendMessage("");
    Assertions.assertTrue(response1.contains("CET 2019"));

    String response2 = client.sendMessage("");
    Assertions.assertTrue(response2.contains("CET 2019"));
  }
}
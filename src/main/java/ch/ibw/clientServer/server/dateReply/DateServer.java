package ch.ibw.clientServer.server.dateReply;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple TCP server. When a client connects, it sends the client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write. Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 *
 * @see <https://cs.lmu.edu/~ray/notes/javanetexamples/>
 */
public class DateServer {
    private static SimpleDateFormat time = new SimpleDateFormat("'Es ist gerade 'H'.'mm' Uhr.'");
    private static SimpleDateFormat date = new SimpleDateFormat("'Heute ist 'EEEE', der 'dd.MM.yy");

    public static void main(String[] args) throws IOException {



        try (ServerSocket listener = new ServerSocket(6060)) {
            System.out.println("DateServer läuft");


            try (Socket socket = listener.accept()) {   // Warte auf Clientverbindung
                System.out.println("[Server] verbunden.");

            while (true){
                    PrintWriter zumClient = new PrintWriter(socket.getOutputStream(), true);
                    zumClient.println("Fuer Zeit = TIME / Fuer Datum = DATE");
                    System.out.println("[Server] Nachricht gesendet.");

                    BufferedReader vomClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String clientWunsch = vomClient.readLine();
                    Date jetzt = new Date();
                    ObjectOutputStream objectZumClient = new ObjectOutputStream(socket.getOutputStream());


                    if (clientWunsch.equalsIgnoreCase("TIME")) {
                        objectZumClient.writeObject(time.format(jetzt));

                    } else if (clientWunsch.equalsIgnoreCase("DATE")) {
                        objectZumClient.writeObject(date.format(jetzt));
                    } else {
                        zumClient.println(clientWunsch + " ist unzulaessig");
                        // zumClient.println(clientWunsch);
                    }
                    // objectZumClient.flush();


                    //out.println(new Date().toString());     // Sende Antwort an Client

                }

            }
        }
    }
}
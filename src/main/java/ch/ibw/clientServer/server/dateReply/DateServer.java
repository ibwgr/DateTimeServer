package ch.ibw.clientServer.server.dateReply;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(6060)) {
            System.out.println("DateServer läuft");
            try (Socket socket = listener.accept()) {   // Warte auf Clientverbindung
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(new Date()); // Sende Antwort an Client
            }
        }
    }
}

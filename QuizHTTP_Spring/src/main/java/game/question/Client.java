package game.question;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
    private final InputStream response;
    private final OutputStream request;
    private final String host;
    private final int port;
    /**
     * Constructor for the Client class that creates a new socket and initializes input and output streams.
     *
     * @param site the website to connect to
     * @param port the port number to use for the connection
     * @throws IOException if an I/O error occurs while creating the socket
     */
    public Client(String site, int port) throws IOException {
        Socket socket = new Socket(site, port);
        this.host = site;
        this.port = port;
        response = socket.getInputStream();
        request = socket.getOutputStream();
    }

    /**
     * Sends a GET request to the server to retrieve a random question.
     *
     * @param site the path to the API endpoint that returns a random question
     * @return a string representation of the JSON object containing the question and answer
     * @throws IOException if an I/O error occurs while sending the request or reading the response
     */
    public String getQuestion(String site) throws IOException {
        byte[] data = ("GET " + site + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: Mozilla/5.0\r\n" +
                "Accept: */*\r\n\r\n").getBytes();

        request.write(data);

        // Read the HTTP response headers
        StringBuilder headers = new StringBuilder();
        int c;
        while ((c = response.read()) != -1) {
            headers.append((char) c);
            if (headers.toString().endsWith("\r\n\r\n")) {
                break;
            }
        }

        // Parse the Content-Length header
        int contentLength = -1;
        String[] headerLines = headers.toString().split("\r\n");
        for (String headerLine : headerLines) {
            if (headerLine.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(headerLine.substring("Content-Length:".length()).trim());
                break;
            }
        }

        // Read the HTTP response body
        StringBuilder result = new StringBuilder();
        int bytesRead = 0;
        while (bytesRead < contentLength || contentLength == -1) {
            c = response.read();
            if (c == -1) {
                break;
            }
            result.append((char) c);
            bytesRead++;
        }

        return result.toString();
    }

}

package ChatRoom.Client;

import ChatRoom.Server.Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Server server;
    private Client client;


    @BeforeEach
    void setUp() {
        server = new Server();

        Server.SocketServer clientHandler = new Server.SocketServer();

        Thread clientHandlerThread = new Thread(clientHandler);

        clientHandlerThread.start();

        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client.closeConnection();
        server.stopServer();

    }



    // TODO: Change the greeting message from the server on established connection
    // You can change the greeting string of the server and/or the unit test to your liking.
    @Test
    public void testGreetingMessage() throws IOException {
        // Arrange
        String expectedGreeting = "\"Welcome to the server client!\"";

        // Act
        client.sendMessage(expectedGreeting);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedGreeting, receivedMessage);
    }




    // TODO: Implement a server reply to the client
    // Make the server reply to a message sent from the client. You can change the string of the server and/or the unit test to your liking.
    @Test
    public void testReplyFromServer() throws IOException {
        // Arrange
        String expectedMessage = "Test message";

        // Act
        client.sendMessage(expectedMessage);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedMessage, receivedMessage);
    }



    // TODO: Implement a method in the server that checks if an incoming messages is a number.
    // If the message has numbers ONLY, the server should reply to the client the square root of the number in the message. If you wish you can change up the method to do any other arithmetic operation.
    @Test
    public void testSquareNumber() throws IOException {
        // Arrange
        String numberString = "5";
        String expectedResponse = "The square of 5 is 25.";

        // Act
        client.sendMessage(numberString);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }



    // TODO: Implement a method in the server that checks an incoming messages from a client is a lowercase string.
    // If the message has lower case letters only, the server should broadcast to the client the same message but in reverse.
    @Test
    public void testStringReversal() throws IOException {
        // Arrange
        String message = "hello world";
        String expectedResponse = "dlrow olleh";

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }
}
~~ On the server, the program will create a server socket with a port calculated based on the ASCII number of keywords entered by the user. Later, the program will continue to accept connections from clients and add them to the list. For each connection from the client, the program will create a new thread to manage that user.

On each thread, the program will read the userID from the client and continue to read messages sent by that user. If the received message is empty, then the user will be removed from the list. If the message is not empty, the program will send the message to all connected clients.

For each message sent, the program will add the current time (in HH:mm:ss format) before the message is sent. The message sent to the client has the format [userID]'[current time]' > [message].

In the client application, there are two roles that can be chosen, namely as a sender or receiver. If the user selects the sender role, the user is prompted to enter a keyword. Then, the program will make a connection to the server using the port number which is calculated based on the ASCII number of each character in the keyword. The user is also asked to enter a user ID and send it to the server. Furthermore, users can send messages to the server by entering messages on the keyboard. If the user sends the message "---", the program will stop and the connection will be closed.

If the user selects the role of recipient, the user is prompted to enter the same password used by the sender. Then, the program will establish a connection to the server with the port number calculated based on the ASCII number of each character in the keyword. The program will continue to read messages received from the server and display them on the screen.
There are two roles that can be chosen, namely as a sender or receiver.

If the role as a sender (sender), then the user is asked to enter a keyword. After that, the program will make a connection to the server using the port number which is calculated based on the ASCII number of each character in the keyword. Then, the user is asked to enter the user ID and send it to the server. Furthermore, users can send messages to the server by entering messages on the keyboard. If the user sends a message ---, the program stops and the connection is closed.

If the role is as a receiver, then the user is asked to enter the same keyword used by the sender. After that, the program will establish a connection to the server with the port number calculated based on the ASCII number of each character in the keyword. Then, the program will continue to read messages received from the server and display them on the screen. ~~

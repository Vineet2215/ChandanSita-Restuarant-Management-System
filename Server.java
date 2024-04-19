import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;


public class Server {


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for connections...");

            // Continuously accept connections
            while (true) {
                Socket socket = serverSocket.accept();

                // Handle each connection in a separate thread
                Thread clientThread = new Thread(new ClientHandler(socket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Read username from client
                String username = in.readLine();

                // Read list size from client
                int listSize = Integer.parseInt(in.readLine());

                // Read items from client
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < listSize; i++) {
                    list.add(in.readLine());
                }

                System.out.println("Username from client: " + username);
                System.out.println("Items from client: ");
                for (String item : list) {
                    System.out.println(item);
                }

                ArrayList<String[]> items = parseInput(list);

                // Close resources
                in.close();
                socket.close();

                // Add items to the database
                try (Connection connection = initializeConnection()) {
                    addToDatabase(connection, username, items);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static Connection initializeConnection() throws SQLException {
        // Connecting to the database
        String url = "jdbc:mysql://localhost:3306/java_chandansita_project";
        String dbUsername = "root";
        String dbPassword = "";
        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }

    static ArrayList<String[]> parseInput(ArrayList<String> input) {
        ArrayList<String[]> items = new ArrayList<>();

        for (String itemString : input) {
            String[] parts = itemString.split(",\\s*");

            if (parts.length == 3) {
                String[] itemData = new String[3];
                itemData[0] = parts[0].trim(); // Item name
                itemData[1] = parts[1].trim(); // Item amount
                itemData[2] = parts[2].trim(); // Item price
                items.add(itemData);
            } else {
                System.err.println("Invalid input format: " + itemString);
            }
        }

        return items;
    }

    static void addToDatabase(Connection connection, String username, ArrayList<String[]> items) throws SQLException {
       
       // Finding the userId for the username ! 
        int userId = getUserId(connection, username);

        if (userId != -1) {
            String sql = "INSERT INTO order_table (user_id, item_name, item_quantity, item_price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (String[] item : items) {
                    statement.setInt(1, userId);
                    statement.setString(2, item[0]); // Item name
                    try {
                        int quantity = Integer.parseInt(item[1]); // Item amount
                        double price = Double.parseDouble(item[2]); // Item price

                        statement.setInt(3, quantity);
                        statement.setDouble(4, price);
                        statement.executeUpdate();
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing quantity or price: " + e.getMessage());
                        // Handle parsing error (e.g., skip this item or log the error)
                    }
                }
                statement.close();
                System.out.println("Data added to the database successfully !.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Finding userId with the help of Username
    static int getUserId(Connection connection, String username) throws SQLException 
    {
        int userId = -1;
        String sql = "SELECT user_id FROM user_table WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
            }
        }
        return userId;
    }
}





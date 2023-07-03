//mengimport package java.io dan java.net
import java.io.*;
import java.net.*;

//mendefinisikan nama class sebagai comServer
public class comServer {
    //menetapkan konstanta dengan nama TESTPORT dan nilai 5000
    public final static int TESTPORT = 5000;

    //method utama
    public static void main(String args[]) {
        //membuat instance objek ServerSocket bernama checkServer dan menetapkan nilainya sebagai null
        ServerSocket checkServer = null;
        //mendefinisikan variable dengan nama line
        String line;
        //membuat instance objek BufferedReader bernama is dan menetapkan nilainya sebagai null
        BufferedReader is = null;
        //membuat instance objek DataOutputStream bernama os dan menetapkan nilainya sebagai null
        DataOutputStream os = null;
        //membuat instance objek Socket bernama clientSocket dan menetapkan nilainya sebagai null
        Socket clientSocket = null;

        try {
            //mencoba membuat instance objek ServerSocket dengan port yang ditentukan sebelumnya
            checkServer = new ServerSocket(TESTPORT);
            //memperlihatkan output pada console bahwa aplikasi server telah hidup
            System.out.println("Aplikasi Server hidup...");
        } catch (IOException e) {
            //menampilkan pesan error yang terjadi pada saat mencoba membuat instance ServerSocket
            System.out.println(e);
        }

        try {
            //mencoba membuat instance objek Socket dan BufferedReader serta DataOutputStream ketika client berhasil terhubung
            //(menerima permintaan koneksi dari client)
            clientSocket = checkServer.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception ei) {
            //menampilkan pesan error yang terjadi ketika mencoba membuat koneksi
            ei.printStackTrace();
        }

        //melakukan looping untuk menerima pesan dari client
        while (true) {
            try {
                //membaca input dari client
                line = is.readLine();
                //menampilkan pesan yang diterima pada console
                System.out.println("pesan masuk: " + line);

                //jika pesan yang diterima merupakan "exit", maka akan memutuskan koneksi dari client
                if (line.equals("exit")) {
                    os.writeBytes("koneksi diputuskan oleh client");
                    break;
                }

                //membalas pesan dari client dengan mengirimkan pesan yang di-reverse
                os.writeBytes("Balasan: " + new StringBuffer(line).reverse().toString() + "\n");

            } catch (IOException e) {
                //menampilkan pesan error yang terjadi pada saat membaca input dari client
                e.printStackTrace();
            }
        }

        try {
            //menutup koneksi socket, BufferedReader dan ServerSocket
            os.close();
            is.close();
            clientSocket.close();
            checkServer.close();
            //menampilkan pesan pada console bahwa aplikasi server selesai
            System.out.println("Aplikasi Server selesai...");
        } catch (IOException ic) {
            //menampilkan pesan error yang terjadi pada saat menutup koneksi
            ic.printStackTrace();
        }
    }
}

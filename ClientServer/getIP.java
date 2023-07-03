import java.net.*;  

public class getIP {  //mendefinisikan kelas public bernama getIP
    public static void main(String args[]) throws Exception {  //mendefinisikan method yang akan dijalankan pertama kali saat program dijalankan
        InetAddress host = null;  //mendefinisikan variabel host bertipe InetAddress dan diinisialisasi dengan null
        host = InetAddress.getLocalHost(); //mengambil alamat IP host saat ini

        byte ip[] = host.getAddress(); //mendefinisikan variabel ip bertipe byte[] dan diinisialisasi dengan alamat IP host

        for (int i=0; i<ip.length; i++) {  //mengulang sebanyak panjang array ip
            if (i > 0) {  //jika i lebih dari 0
                System.out.print(".");  //mencetak "."
            }
            System.out.print(ip[i] & 0xff);  //mencetak nilai dari elemen array ip yang telah dikonversi ke int dan dibagi 255
        }
        System.out.println();  //mencetak baris baru
    }
}
import java.net.*; //mengimpor library java.net untuk menggunakan class InetAddress

public class getName { //membuat kelas getName
    public static void main(String args[]) throws Exception { //method main sebagai entry point program dan menggunakan Exception untuk men-handle error
        InetAddress host = null; //membuat variabel host dengan tipe InetAddress yang diinisialisasi dengan null
        host = InetAddress.getLocalHost(); //mendapatkan HostAddress dari komputer local dan menyimpannya ke variabel host
        System.out.println("Nama komputer Anda: " + host.getHostName()); //menampilkan nama hostname dari komputer dan disimpan di variabel host dengan method getHostName()
    }
}

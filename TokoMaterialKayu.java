import java.util.ArrayList;
import java.util.Scanner;


public class TokoMaterialKayu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Material> daftarMaterial = new ArrayList<>();
        
  
        daftarMaterial.add(new Material("Papan Kayu Jati", 150000, 50, "lembar"));
        daftarMaterial.add(new Material("Paku Kayu 5cm", 2000, 200, "pack"));
        daftarMaterial.add(new Material("Lem Kayu", 35000, 30, "kaleng"));
        daftarMaterial.add(new Material("Cat Kayu Mahoni", 75000, 20, "kaleng"));
        
        System.out.println("=== SELAMAT DATANG DI TOKO MATERIAL KAYU ===");
        System.out.println("Daftar Material Tersedia:");
        System.out.println("===========================================");
        

        for (int i = 0; i < daftarMaterial.size(); i++) {
            System.out.println((i+1) + ". " + daftarMaterial.get(i).getInfo());
        }
        

        ArrayList<Pesanan> keranjang = new ArrayList<>();
        boolean lanjutBeli = true;
        
        while (lanjutBeli) {
            System.out.print("\nMasukkan nomor material yang ingin dibeli: ");
            int pilihan = scanner.nextInt() - 1;
            
            if (pilihan >= 0 && pilihan < daftarMaterial.size()) {
                Material materialDipilih = daftarMaterial.get(pilihan);
                System.out.println("Anda memilih: " + materialDipilih.getNama());
                System.out.println("Stok tersedia: " + materialDipilih.getStok() + " " + materialDipilih.getSatuan());
                
                System.out.print("Masukkan jumlah yang ingin dibeli: ");
                int jumlah = scanner.nextInt();
                
                if (jumlah <= materialDipilih.getStok()) {
                    keranjang.add(new Pesanan(materialDipilih, jumlah));
                    materialDipilih.kurangiStok(jumlah);
                    System.out.println("Material berhasil ditambahkan ke keranjang!");
                } else {
                    System.out.println("Maaf, stok tidak mencukupi!");
                }
            } else {
                System.out.println("Nomor material tidak valid!");
            }
            
            System.out.print("\nApakah ingin membeli material lain? (y/n): ");
            lanjutBeli = scanner.next().equalsIgnoreCase("y");
        }
        
        System.out.println("\n=== STRUK PEMBELIAN ===");
        System.out.println("No. Nama Material\tJumlah\tHarga Satuan\tTotal");
        System.out.println("------------------------------------------------");
        
        double totalPembelian = 0;
        for (int i = 0; i < keranjang.size(); i++) {
            Pesanan pesanan = keranjang.get(i);
            Material material = pesanan.getMaterial();
            double subtotal = material.getHarga() * pesanan.getJumlah();
            
            System.out.printf("%d. %s\t%d %s\tRp%,.0f\tRp%,.0f\n", 
                (i+1), 
                material.getNama(), 
                pesanan.getJumlah(), 
                material.getSatuan(), 
                material.getHarga(), 
                subtotal);
            
            totalPembelian += subtotal;
        }
        
        System.out.println("------------------------------------------------");
        System.out.printf("TOTAL PEMBELIAN: Rp%,.0f\n", totalPembelian);
        System.out.println("Terima kasih telah berbelanja di Toko Material Kayu Kami!");
        
        scanner.close();
    }
}


class Material {
    private String nama;
    private double harga;
    private int stok;
    private String satuan;
    
    public Material(String nama, double harga, int stok, String satuan) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.satuan = satuan;
    }
    

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }
    

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
    public String getSatuan() { return satuan; }
    

    public String getInfo() {
        return String.format("%-20s Rp%,.0f/%s (Stok: %d %s)", 
            nama, harga, satuan, stok, satuan);
    }
}


class Pesanan {
    private Material material;
    private int jumlah;
    
    public Pesanan(Material material, int jumlah) {
        this.material = material;
        this.jumlah = jumlah;
    }
    

    public Material getMaterial() { return material; }
    public int getJumlah() { return jumlah; }
}
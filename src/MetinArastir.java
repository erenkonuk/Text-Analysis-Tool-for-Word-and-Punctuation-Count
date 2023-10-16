import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MetinArastir {
    private File dosya;
    public MetinArastir(){
        dosya = new File("metin.txt");
        if(!dosya.exists()){
            try {
                dosya.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String metin = dosyaOku();
        Kelime[] kelimeler = kelimeBul(metin);
        for (int i = 0; i < kelimeler.length; i++) {
            System.out.println("Kelime : "+kelimeler[i].kelime
                    + " sayisi : "+kelimeler[i].kelimeSayisi);
        }
    }

    private Kelime[] kelimeBul(String metin) {
        String[] dizi = metin.split(" ");
        int kelimeSayisi = farkliKelimeSay(dizi);
        Kelime[] kelimeler = new Kelime[kelimeSayisi];
        int sayacK = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(!dizi[i].isBlank()){
                if(varMiKelime(kelimeler,dizi[i],sayacK) == -1){
                    kelimeler[sayacK] = new Kelime(dizi[i]);
                    sayacK++;
                }
                else{
                    int indis = varMiKelime(kelimeler,dizi[i],sayacK);
                    kelimeler[indis].kelimeSayisi++;
                }
            }
        }
        return kelimeler;
    }
    private int varMiKelime(Kelime[] kelimeler, String s,int sayi) {
        for (int i = 0; i < sayi; i++) {
            if(kelimeler[i].kelime.equals(s)) return i;
        }
        return -1;
    }
    private int farkliKelimeSay(String[] dizi) {
        // dizi = { "asdf", "uyıasd", "", "asd"   }
        int sayac = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(!dizi[i].isBlank()) sayac++;
        }
        String[] farkliKelimeler = new String[sayac];
        int farkliKelimeSayisi = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(!dizi[i].isBlank()){
                if( varMi(farkliKelimeler,dizi[i],farkliKelimeSayisi) == -1){
                    farkliKelimeler[farkliKelimeSayisi] = dizi[i];
                    farkliKelimeSayisi++;
                }
            }
        }
        return farkliKelimeSayisi;
    }

    private int varMi(String[] farkliKelimeler, String s, int sayi) {
        if(farkliKelimeler.length ==  0) return  -1;
        for (int i = 0; i < sayi; i++) {
            if(farkliKelimeler[i].equals(s)) return i;
        }
        return -1;
    }

    private String dosyaOku() {
        String metin = "";
        try {
            Scanner scanner = new Scanner(dosya);
            while (scanner.hasNextLine()){
                if(metin.isBlank()) metin = scanner.nextLine();
                else metin += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return metin;
    }
}

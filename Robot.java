/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author silaneroglu
 */
public abstract class Robot {
    
    private int konumX;    // X koordinatı (0-9 arası)
    private int konumY;    // Y koordinatı (0-9 arası)
    
    // Robotun durum bilgileri
    private int pilSeviyesi;   // Mevcut pil seviyesi (0-100 arası)
    private String durum;      // Robotun durumu ("Aktif", "Pil Düşük" vb.)
    
    // Sabit değerler
    public static final int IZGARA_BOYUTU = 10;         // 10x10'luk ızgara
    public static final int MINIMUM_PIL_SEVIYESI = 5;   // Minimum çalışma pil seviyesi
    
    // Kurucu metod
    public Robot(int baslangicPilSeviyesi) {
        this.konumX = 0;  // Robot (0,0) noktasından başlar
        this.konumY = 0;
        this.pilSeviyesi = baslangicPilSeviyesi;
        this.durum = "Aktif";
    }
    
    // Soyut metodlar - Alt sınıflar tarafından uygulanacak
    public abstract boolean hareketEt(String yon, int adimSayisi);
    public abstract String cevreKontrol();
    
    // Şarj işlemleri
    public void sarjEt() {
        this.pilSeviyesi = 100;
        this.durum = "Aktif";
    }
    
    public boolean pilDusuk() {
        return pilSeviyesi <= MINIMUM_PIL_SEVIYESI;
    }
    
    // Getter metodları
    public int[] mevcutKonum() {
        return new int[]{konumX, konumY};
    }
    
    public int getPilSeviyesi() {
        return pilSeviyesi;
    }
    
    public String getDurum() {
        return durum;
    }
    
    // Setter metodları
    public void setKonumX(int yeniX) {
        this.konumX = yeniX;
    }
    
    public void setKonumY(int yeniY) {
        this.konumY = yeniY;
    }
    
    public void setPilSeviyesi(int yeniPilSeviyesi) {
        this.pilSeviyesi = yeniPilSeviyesi;
    }
    
    public void setDurum(String yeniDurum) {
        this.durum = yeniDurum;
    }
    
    // Hareket kontrolü
    public boolean hareketGecerliMi(String yon, int adimSayisi) {
        int yeniX = konumX;
        int yeniY = konumY;
        
        switch(yon.toLowerCase()) {
            case "yukari":
                yeniY -= adimSayisi;
                break;
            case "asagi":
                yeniY += adimSayisi;
                break;
            case "sol":
                yeniX -= adimSayisi;
                break;
            case "sag":
                yeniX += adimSayisi;
                break;
            default:
                return false;
        }
        
        return yeniX >= 0 && yeniX < IZGARA_BOYUTU && 
               yeniY >= 0 && yeniY < IZGARA_BOYUTU;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author silaneroglu
 */
import java.util.ArrayList;

public class IntelligentRobot extends Robot {
    private ArrayList<String> hareketGecmisi;
    private static final int ADIM_PIL_TUKETIMI = 1;
    private static final int[][] SARJ_ISTASYONLARI = {{2, 2}, {5, 5}, {8, 8}};
    
    public IntelligentRobot(int baslangicPilSeviyesi) {
        super(baslangicPilSeviyesi);
        this.hareketGecmisi = new ArrayList<>();
    }
    
    @Override
    public boolean hareketEt(String yon, int adimSayisi) {
        if (!hareketGecerliMi(yon, adimSayisi)) {
            System.out.println("Geçersiz hareket!");
            return false;
        }
        
        if (pilDusuk()) {
            setDurum("Pil Düşük");
            System.out.println("Pil seviyesi çok düşük!");
            return false;
        }
        
        int gerekliPil = adimSayisi * ADIM_PIL_TUKETIMI;
        if (getPilSeviyesi() < gerekliPil) {
            System.out.println("Yetersiz pil seviyesi!");
            return false;
        }
        
        switch(yon.toLowerCase()) {
            case "yukari":
                setKonumY(mevcutKonum()[1] - adimSayisi);
                break;
            case "asagi":
                setKonumY(mevcutKonum()[1] + adimSayisi);
                break;
            case "sol":
                setKonumX(mevcutKonum()[0] - adimSayisi);
                break;
            case "sag":
                setKonumX(mevcutKonum()[0] + adimSayisi);
                break;
        }
        
        setPilSeviyesi(getPilSeviyesi() - gerekliPil);
        hareketGecmisi.add(String.format("%s yönünde %d adım hareket edildi", yon, adimSayisi));
        sarjIstasyonuKontrol();
        return true;
    }
    
    @Override
    public String cevreKontrol() {
        StringBuilder sonuc = new StringBuilder("Tarama sonuçları:\n");
        for (int[] istasyon : SARJ_ISTASYONLARI) {
            int uzaklik = Math.abs(istasyon[0] - mevcutKonum()[0]) + 
                         Math.abs(istasyon[1] - mevcutKonum()[1]);
            if (uzaklik <= 2) {
                sonuc.append(String.format("Şarj istasyonu tespit edildi: (%d,%d)\n", 
                    istasyon[0], istasyon[1]));
            }
        }
        return sonuc.toString();
    }
    
    private void sarjIstasyonuKontrol() {
        for (int[] istasyon : SARJ_ISTASYONLARI) {
            if (mevcutKonum()[0] == istasyon[0] && mevcutKonum()[1] == istasyon[1]) {
                sarjEt();
                System.out.println("Şarj istasyonuna ulaşıldı. Robot şarj ediliyor...");
                break;
            }
        }
    }
    
    public ArrayList<String> hareketGecmisiniGetir() {
        return new ArrayList<>(hareketGecmisi);
    }
}
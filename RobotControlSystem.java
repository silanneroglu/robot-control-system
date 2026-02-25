/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author silaneroglu
 */

public class RobotControlSystem {
    // Ana metod - Programın başlangıç noktası
    public static void main(String[] args) {
        RobotControlSystem sistem = new RobotControlSystem();
        sistem.baslat();
    }

    // Sınıf değişkenleri
    private final IntelligentRobot robot;
    private final MovementTracker takipci;
    private final Scanner giris;
    
    // Kurucu metod
    public RobotControlSystem() {
        giris = new Scanner(System.in);
        System.out.print("Başlangıç pil seviyesini girin (0-100): ");
        int pilSeviyesi = giris.nextInt();
        robot = new IntelligentRobot(pilSeviyesi);
        takipci = new MovementTracker();
    }
    
    // Ana menüyü gösteren metod
    private void menuGoster() {
        System.out.println("\nAkıllı Robot Kontrol Sistemi");
        System.out.println("1. Robotu Hareket Ettir");
        System.out.println("2. Mevcut Konumu Göster");
        System.out.println("3. Hareket Geçmişini Göster");
        System.out.println("4. Bir Hareketi Güncelle");
        System.out.println("5. Hareket Geçmişini Sıfırla");
        System.out.println("6. Çevreyi Tara");
        System.out.println("7. Pili Şarj Et");
        System.out.println("8. Çıkış");
        System.out.print("Seçiminiz: ");
    }
    
    // Sistemi başlatan metod
    public void baslat() {
        while (true) {
            izgaraGoster();
            System.out.println("Pil Seviyesi: " + robot.getPilSeviyesi());
            menuGoster();
            
            int secim = giris.nextInt();
            giris.nextLine(); // Buffer'ı temizle
            
            switch (secim) {
                case 1:
                    robotuHareketEttir();
                    break;
                case 2:
                    konumuGoster();
                    break;
                case 3:
                    gecmisiGoster();
                    break;
                case 4:
                    hareketGuncelle();
                    break;
                case 5:
                    gecmisiSifirla();
                    break;
                case 6:
                    cevreyiTara();
                    break;
                case 7:
                    sarjEt();
                    break;
                case 8:
                    cikis();
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
    
    // Izgarayı gösteren metod
    private void izgaraGoster() {
        int[] konum = robot.mevcutKonum();
        System.out.println("\nRobot Konumu:");
        
        for (int i = 0; i < Robot.IZGARA_BOYUTU; i++) {
            for (int j = 0; j < Robot.IZGARA_BOYUTU; j++) {
                if (i == konum[1] && j == konum[0]) {
                    System.out.print("R ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    
    // Robot kontrol metodları
    private void robotuHareketEttir() {
        System.out.print("Yön (yukari/asagi/sag/sol): ");
        String yon = giris.nextLine();
        System.out.print("Adım sayısı: ");
        int adim = giris.nextInt();
        
        if (robot.hareketEt(yon, adim)) {
            takipci.hareketEkle(yon + " yönünde " + adim + " adım");
        }
    }
    
    private void konumuGoster() {
        int[] konum = robot.mevcutKonum();
        System.out.printf("Robot'un konumu: (%d,%d)\n", konum[0], konum[1]);
    }
    
    private void gecmisiGoster() {
        ArrayList<String> gecmis = takipci.gecmisiGetir();
        if (gecmis.isEmpty()) {
            System.out.println("Hareket geçmişi boş!");
            return;
        }
        
        System.out.println("\nHareket Geçmişi:");
        for (int i = 0; i < gecmis.size(); i++) {
            System.out.println((i+1) + ". " + gecmis.get(i));
        }
    }
    
    private void hareketGuncelle() {
        gecmisiGoster();
        System.out.print("Güncellenecek hareketin numarası: ");
        int index = giris.nextInt() - 1;
        giris.nextLine();
        
        System.out.print("Yeni hareket açıklaması: ");
        String yeniHareket = giris.nextLine();
        
        if (takipci.hareketGuncelle(index, yeniHareket)) {
            System.out.println("Hareket güncellendi.");
        } else {
            System.out.println("Geçersiz hareket numarası!");
        }
    }
    
    private void gecmisiSifirla() {
        System.out.print("Hareket geçmişini silmek istediğinize emin misiniz? (E/H): ");
        String cevap = giris.nextLine();
        if (cevap.equalsIgnoreCase("E")) {
            takipci.gecmisiTemizle();
            System.out.println("Hareket geçmişi temizlendi.");
        }
    }
    
    private void cevreyiTara() {
        System.out.println(robot.cevreKontrol());
    }
    
    private void sarjEt() {
        robot.sarjEt();
        System.out.println("Robot şarj edildi. Yeni pil seviyesi: " + robot.getPilSeviyesi());
        takipci.hareketEkle("Robot manuel olarak şarj edildi");
    }
    
    private void cikis() {
        System.out.println("Program sonlandırılıyor...");
        giris.close();
    }
}
            
            

            
    

    


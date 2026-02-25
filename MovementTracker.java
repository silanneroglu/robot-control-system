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

public class MovementTracker {
    private ArrayList<String> hareketler;
    
    public MovementTracker() {
        this.hareketler = new ArrayList<>();
    }
    
    public void hareketEkle(String hareket) {
        hareketler.add(hareket);
    }
    
    public void gecmisiTemizle() {
        hareketler.clear();
    }
    
    public ArrayList<String> gecmisiGetir() {
        return new ArrayList<>(hareketler);
    }
    
    public boolean hareketGuncelle(int index, String yeniHareket) {
        if (index >= 0 && index < hareketler.size()) {
            hareketler.set(index, yeniHareket);
            return true;
        }
        return false;
    }
}
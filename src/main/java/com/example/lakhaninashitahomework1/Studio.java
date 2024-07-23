/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lakhaninashitahomework1;

/**
 *
 * @author NASHITA LAKHANI
 */
public class Studio {
    int SID;
    String Sname;
    
    public Studio(int SID, String Sname){
        
        this.SID=SID;
        this.Sname=Sname;
    }


    Studio() {
        
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }
    
    
}

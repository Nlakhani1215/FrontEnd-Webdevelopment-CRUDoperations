/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lakhaninashitahomework1;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 *
 * @author NASHITA LAKHANI
 */
public class Movie {
    @NotBlank
    @Digits(integer = 4, fraction=0)
    @Size(min = 4, max = 4, message = "MID must be a 4-digit number")
    private int MID;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Genre cannot be empty")
    private String genre;
    @Positive(message = "Price must be a positive number")
    private double price;
    @NotBlank(message = "Rating cannot be empty")
    @Pattern(regexp = "G|PG|PG-13|R|NC-17", message = "Invalid rating")
    private String rating;
    private int studio;

    public int getStudio() {
        return studio;
    }

    public void setStudio(int studio) {
        this.studio = studio;
    }
    
   //getters and setters
    public int getMID(){
        return MID;
    }
    
    public void setMID(int MID){
        this.MID=MID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    
    public String getRating(){
        return rating;
    }
    public void setRating(String rating){
        this.rating=rating;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.MID;
        return hash;
    }
    
    //Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final Movie other= (Movie) obj;
        return this.MID == other.MID;
    }  

    @Override
    public String toString() {
        return "Movie{" + "MID=" + MID + ", name=" + name + ", genre=" + genre + ", price=" + price + ", rating=" + rating + ", studio=" + studio + '}';
    }

    int getStudioSID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
  
}

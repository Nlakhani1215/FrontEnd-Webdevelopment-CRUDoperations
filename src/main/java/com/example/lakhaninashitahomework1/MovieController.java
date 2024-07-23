/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lakhaninashitahomework1;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
;

/**
 *
 * @author NASHITA LAKHANI
 */
@RestController
public class MovieController {
    
    private final ArrayList<Movie> randomMovies= new ArrayList();
    private final ArrayList<Studio> studios= new ArrayList<>();
    Random random=new Random();
    public MovieController(){
            initializeStudios();
            initializeMovies();
            
        }
    
    private void initializeStudios() {
        studios.add(new Studio(1, "StudioA"));
        studios.add(new Studio(2, "StudioB"));
        studios.add(new Studio(3, "StudioC"));
        studios.add(new Studio(4, "StudioD"));
        studios.add(new Studio(5, "StudioE"));
  
    }
        private void initializeMovies(){
            String[] genres= {"Action", "Comedy", "Drama", "Romance", "Sci-Fi", "Horror", "Thriller"};
            String[] ratings = {"G", "PG", "PG-13", "R", "NC-17"};
            String[] names = {
            "John Smith",
            "Emily Johnson",
            "Michael Williams",
            "Emma Jones",
            "William Brown",
            "Olivia Davis",
            "James Miller",
            "Sophia Wilson",
            "Alexander Moore",
            "Isabella Taylor",
            "Daniel Anderson",
            "Charlotte Martinez",
            "David Thompson",
            "Amelia Garcia",
            "Joseph Robinson",
            "Mia Rodriguez",
            "Andrew Lee",
            "Grace Hernandez",
            "Lucas Walker",
            "Ava Young",
            "Matthew King",
            "Chloe Lopez",
            "Ethan Scott",
            "Ella Adams",
            "Benjamin Green",
            "Lily Hall",
            "Christopher Martinez",
            "Madison Lewis",
            "Jacob Clark",
            "Sophie White"
            };
        
        for(int i=0; i<30; i++){
            Movie movie=new Movie();
            movie.setMID(random.nextInt(1000, 9999));
            movie.setName(names[random.nextInt(names.length)]);
            movie.setPrice(random.nextDouble()*100);
            movie.setGenre(genres[random.nextInt(genres.length)]+ ",");
            movie.setRating(ratings[random.nextInt(ratings.length)]+ ","); 
            movie.setStudio(getRandomStudioSID());
          //  System.out.println("output movies --"+movie.getName()+movie.getStudio());
            randomMovies.add(movie);
        }
        }
        
      private int getRandomStudioSID() {
        if (!studios.isEmpty()) {
            int randomIndex = random.nextInt(studios.size());
            return studios.get(randomIndex).getSID();
        } else {
            return 0; // or any default value indicating no studio
        }
    }
        
       
        
       //Get all movies
       @GetMapping ("/movies")
        public ArrayList<Movie> getMovies(){
            return randomMovies;
        }
        
        //.Get a movie with a given MID
        @GetMapping ("/movies/{MID}")
        public ResponseEntity<Movie> getMovieMID(@PathVariable ("MID")int MID){
            Movie movie=new Movie();
            movie.setMID(MID);
            if (randomMovies.contains(movie)){
                return new ResponseEntity(randomMovies.get(randomMovies.indexOf(movie)), HttpStatus.OK);
            }
            else{
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }      
        }
        
        //with rating
        
    @GetMapping("/movies/rating/{rating}")
    public ResponseEntity<ArrayList<Movie>> getMoviesByRating(@PathVariable("rating") String rating) {
        ArrayList<Movie> moviesWithRating = new ArrayList<>();
        for (Movie movie : randomMovies) {
            if (movie.getRating().contains(rating)) {
                moviesWithRating.add(movie);
            }
        }
        if (!moviesWithRating.isEmpty()) {
            return new ResponseEntity<>(moviesWithRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
        //with a given genre
        @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<ArrayList<Movie>> getMoviesByGenre(@PathVariable("genre") String genre) {

        ArrayList<Movie> moviesWithGenre = new ArrayList<>();
        for (Movie movie : randomMovies) {
            if (movie.getGenre().contains(genre)) {
                moviesWithGenre.add(movie);
            }
        }

        if (!moviesWithGenre.isEmpty()) {
            return new ResponseEntity<>(moviesWithGenre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    //price between low and high range
    @GetMapping("/movies/price/{low}/{high}")
    public ArrayList<Movie> getMoviesBetweenPrices(@PathVariable ("low") double low, @PathVariable("high") double high ) {
        ArrayList<Movie> moviesBetweenPrices = new ArrayList();
        for (Movie movie : randomMovies) {
            if (movie.getPrice() >= low && movie.getPrice() <= high) {
                moviesBetweenPrices.add(movie);
            }

        }
        return moviesBetweenPrices;
    }
    
    @GetMapping("/studios")
    public ArrayList<Studio> getAllStudios(){
        return studios;
    }
    
    //Getting a movie using the studio name
    @GetMapping("/movies/studio/{studioName}")
    public ResponseEntity<ArrayList<Movie>> getMovieByStudioName(@PathVariable("studioName") String studioName) {
        ArrayList<Movie> moviesWithStudio = new ArrayList<>();
        //boolean studioFound = false;
        for (Studio studio : studios) {
            if (studio.getSname().equalsIgnoreCase(studioName)) {
       //         studioFound = true;
                for (Movie movie : randomMovies) {
                    if (movie.getStudio() == studio.getSID()) {
                        moviesWithStudio.add(movie);
                        return new ResponseEntity<>(moviesWithStudio, HttpStatus.OK);
                    }
                }
               
            }
            
            

        }
        
            return new ResponseEntity<>(moviesWithStudio, HttpStatus.NOT_FOUND);

        }
    

   //POST endpoints
   //Add a movie
    @PostMapping(value = "/movie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if(randomMovies.contains(movie)){
            return new ResponseEntity(movie, HttpStatus.FOUND);
        } else {
            randomMovies.add(movie);
            return new ResponseEntity(movie, HttpStatus.OK);

        }
    }
    
    @PostMapping(value = "/studio", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Studio> addStudio(@RequestBody Studio studio) {
        if(studios.contains(studio)){
            return new ResponseEntity(studio, HttpStatus.FOUND);
        } else {
            studios.add(studio);
            return new ResponseEntity(studio, HttpStatus.OK);

        }
    }

    
    @PutMapping(value = "/movie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> editMovie(@Valid @RequestBody Movie movie) {

        if (randomMovies.contains(movie)) {
            Movie editedMovie = randomMovies.get(randomMovies.indexOf(movie));

            if (movie.getName() != null) {
                editedMovie.setName(movie.getName());
            }
            if (movie.getRating() != null) {
                editedMovie.setRating(movie.getRating());
            }

            if (movie.getGenre() != null) {
                editedMovie.setGenre(movie.getGenre());
            }
            editedMovie.setPrice(movie.getPrice());

            return new ResponseEntity(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/studios/{SID}")
    public ResponseEntity<Studio> updateStudio(@PathVariable("SID") int SID, @RequestBody Studio updatedStudio) {
    
         for(Studio studio:studios){
             if(studio.getSID()==SID){
                 studio.setSname(updatedStudio.getSname());
                 return new ResponseEntity(studio, HttpStatus.OK);
             }
             
         }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
             
    }
    
    //Delete End Points
    @DeleteMapping("/movie/{MID}")
    public ResponseEntity<Movie> deleteByMID(@PathVariable("MID") int MID){
        Movie movie= new Movie();
        movie.setMID(MID);
        if(randomMovies.contains(movie)){
            Movie deleteMovie=randomMovies.get(randomMovies.indexOf(movie));
            randomMovies.remove(deleteMovie);
            return new ResponseEntity(deleteMovie, HttpStatus.OK);
            
        }
        else{
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/studio/{SID}")
    public ResponseEntity<Studio> deleteBySID(@PathVariable("SID") int SID){
      
  
        for (Studio studio : studios) {
            if (studio.getSID() == SID) {
                studios.remove(studio);
                return new ResponseEntity(studio, HttpStatus.OK);
            }
        }

        return new ResponseEntity(null, HttpStatus.OK);

    }

}
        

    
    
    
    

      
        
        
    
        
    
    
    

 
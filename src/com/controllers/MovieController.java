package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Movie;
import com.services.MovieService;

@CrossOrigin
@Controller
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	//-----GET ALL MOVIES-----
	@ResponseBody
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public List<Movie> getAllMovies() {
		List<Movie> allMovies = movieService.getAll();
		return allMovies;
	}
	
	//-----GET MOVIE BY ID-----
	@ResponseBody
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public Movie getMovieById(@PathVariable int id) {
		Movie thisMovie = movieService.getById(id);
		return thisMovie;
	}
	
	//-----ADD NEW MOVIE-----
	@ResponseBody
	@RequestMapping(value = "/movie", method = RequestMethod.POST)
	public String addMovie(@RequestBody Movie movie) {
		movie.setTitle(movie.getTitle());
		movie.setDirector(movie.getDirector());
		movie.setDescription(movie.getDescription());
		movie.setImgUrl(movie.getImgUrl());
		movie.setRating(movie.getRating());
		movieService.create(movie);
		return "NEW MOVIE POSTED";
	}
	
	//-----UPDATE MOVIE BY ID-----
	@ResponseBody
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
	public String updateMovie(@PathVariable int id, @RequestBody Movie movie) {
		movieService.updateMovie(id, movie);
		return "MOVIE UPDATED";
	}
	
	//-----DELETE MOVIE BY ID-----
	@ResponseBody
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
	public Movie deleteMovie(@PathVariable int id) {
		Movie deletedMovie = movieService.delete(id);
		return deletedMovie;
	}
	
}

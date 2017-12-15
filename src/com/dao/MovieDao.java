package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.entities.Movie;

@Repository("MovieDao")
public class MovieDao {

	public Movie get(int id, EntityManager em) {
		return em.find(Movie.class, id);
	}

	public List<Movie> getAll(EntityManager em) {
		TypedQuery<Movie> query = em.createQuery("SELECT e FROM Movie e", Movie.class);
		List<Movie> allMovies = query.getResultList();
		return allMovies;
	}

	public Movie delete(int id, EntityManager em) {
		Movie thisMovie = em.find(Movie.class, id);
		em.remove(thisMovie);
		return thisMovie;
	}

	public Movie create(Movie movie, EntityManager em) {
		em.persist(movie);
		return movie;
	}

	public Movie update(int id, Movie movie, EntityManager em) {
		Movie updatedMovie = em.find(Movie.class, id);
		updatedMovie.setTitle(movie.getTitle());
		updatedMovie.setDescription(movie.getDescription());
		updatedMovie.setDirector(movie.getDirector());
		updatedMovie.setImgUrl(movie.getImgUrl());
		updatedMovie.setRating(movie.getRating());
		
		return updatedMovie;
	}

}

package com.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MovieDao;
import com.entities.Movie;

@Service("MovieService")
public class MovieService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Default_JPA");

	@Autowired
	MovieDao movieDao;

	public Movie create(Movie movie) {
		Movie newMovie = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction(); // Creates a new transaction

		try {
			transaction.begin(); // Starts the transaction
			newMovie = movieDao.create(movie, em);
			transaction.commit(); // Commits the transaction
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}

		return newMovie;
	}

	public List<Movie> getAll() {
		List<Movie> allMovies = null;
		EntityManager em = emf.createEntityManager();
		
		try {
			allMovies = movieDao.getAll(em);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return allMovies;
	}

	public Movie getById(int id) {
		Movie foundMovie = null;
		EntityManager em = emf.createEntityManager();

		try {
			foundMovie = movieDao.get(id, em);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return foundMovie;

	}

	public Movie updateMovie(int id, Movie movie) {
		Movie updatedMovie = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			updatedMovie = movieDao.update(id, movie, em);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		
		return updatedMovie;
	}

	public Movie delete(int id) {
		Movie deletedMovie = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin(); // Starts the transaction
			deletedMovie = movieDao.delete(id, em);
			transaction.commit(); // Commits the transaction
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
		
		return deletedMovie;
	}

}

// -----COMMENTS ARE OLD CODE USED BEFORE IMPLEMENTING DATABASE-----
// private static int i = 0;

// public static Movie create(Movie movie) {
//// movie.setId(i++);
//// movies.add(movie);
//// return movie;
// }
//
// public static List<Movie> getAll() {
//// return movies;
// }
//
// public static Movie getById(int id) {
//// Movie thisMovie = null;
////
//// for (int i=0; i<movies.size(); i++) {
//// if (movies.get(i).getId() == id) {
//// thisMovie = movies.get(i);
//// break;
//// }
//// }
////
//// return thisMovie;
// }
//
// public static Movie updateMovie(int id, Movie movie) {
//// Movie thisMovie = movies.get(id);
////
//// thisMovie.setTitle(movie.getTitle());
//// thisMovie.setDescription(movie.getDescription());
//// thisMovie.setDirector(movie.getDirector());
//// thisMovie.setImgUrl(movie.getImgUrl());
//// thisMovie.setRating(movie.getRating());
////
//// return thisMovie;
// }
//
// public static Movie delete(int id) {
//// Movie deletedMovie = null;
////
//// for (int i=0; i<movies.size(); i++) {
//// if (movies.get(i).getId() == id) {
//// deletedMovie = movies.remove(i);
//// break;
//// }
//// }
////
//// return deletedMovie;
//// }
package com.book.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.api.entitis.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Book findById(int id);


	void deleteByTitle(String title);
}

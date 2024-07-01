package com.book.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.api.entitis.Book;
import com.book.api.repository.BookRepository;

import jakarta.transaction.Transactional;

@Component
public class BookService {

	@Autowired
	private BookRepository bookRepository;

//	private static List<Book> books = new ArrayList<>();

//	static {
//		books.add(new Book(10, "Java Book", "James"));
//		books.add(new Book(15, "C++ Book", "John"));
//		books.add(new Book(20, "Python Book", "Guido"));
//		books.add(new Book(25, "C Book", "Denis"));
//	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(int id) {
		/*
		 * try { for (Book book : books) { if (id == book.getId()) { return book; } } }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		Book book = null;
		try {
//			book = books.stream().filter(b -> b.getId() == id).findFirst().get();
			book = bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public Book addBook(Book book) {
		Book savedBook = bookRepository.save(book);
//		books.add(book);
		return savedBook;
	}

	public List<Book> addBooks(List<Book> newBooks) {
		List<Book> savedBooks = bookRepository.saveAll(newBooks);
//		books.addAll(newBooks);
		return savedBooks;
	}

	public Book deleteBookById(int bookid) {
//		AtomicReference<Book> temp = new AtomicReference<Book>(null);

		/*
		 * Book[] temp = new Book[1]; books.removeIf(book -> { if (book.getId() ==
		 * bookid) { temp[0] = book; return true; } else { return false; } });
		 */
//		return temp.get();
//		return temp[0];

		Book deletedBook = bookRepository.findById(bookid);
		bookRepository.deleteById(bookid);
		return deletedBook;
	}

	@Transactional
	public void deleteBookByName(String bookName) {
//		AtomicReference<Book> temp = new AtomicReference<Book>(null);

//		/* by using removeIf method of list collection */
//		books.removeIf(book -> {
//			if (book.getTitle().equals(bookName)) {
//				temp.set(book);
//				return true;
//			} else {
//				return false;
//			}
//		});

//		/* by using java stream features */
//		books = books.stream().filter(book -> {
//			if (book.getTitle().equals(bookName)) {
//				temp.set(book);
//				return false;
//			} else {
//				return true;
//			}
//		}).collect(Collectors.toList());
//
//		return temp.get();

		bookRepository.deleteByTitle(bookName);

	}

	public Book updateBookById(Book book, int bookId) {
//		AtomicReference<Book> updatedBook = new AtomicReference<Book>(null);
//		books = books.stream().map(b -> {
//			if (b.getId() == bookId) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//				updatedBook.set(b);
//			}
//			return b;
//		}).collect(Collectors.toList());
//		return updatedBook.get();
		book.setId(bookId);
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}
}

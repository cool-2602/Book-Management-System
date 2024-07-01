package com.book.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.api.entitis.Book;
import com.book.api.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

//	/*  Handler method for get all books */
//	/*  @RequestMapping(value="/books", method=RequestMethod.GET) */
//	@GetMapping("/books")
//	public List<Book> getAllBooks() {
//		return bookService.getAllBooks();
//	}

	/* handler method for get all books by sending status code */
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> list = bookService.getAllBooks();

		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}

//	/*  Handler method for get book by id  */
//	@GetMapping("/books/{id}")
//	public Book getBookById(@PathVariable("id") int id) {
//		return bookService.getBookById(id);
//	}

	/* handler method for get book by id by sending http status code */
	@GetMapping("/books/id/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {

		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));

	}

//	handler method for add new book
	@PostMapping("/books")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
//		@RequestBody annotation use to convert json format data to java object because in rest api data is in json format only
		Book temp=null;
		try {
			temp = bookService.addBook(book);
			System.out.println("New Book is Added");
			System.out.println(temp);
			return ResponseEntity.of(Optional.of(temp));

		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/* handler method for add new list of books */
	@PostMapping("/books/b")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books){
		List<Book> temp = null;
		try {
			temp = bookService.addBooks(books);
			System.out.println("New books are added :");
			for(Book b:temp) {
				System.out.println(b);
			}
			return ResponseEntity.of(Optional.of(temp));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	handler method to delete book by id
	@DeleteMapping("/books/id/{bookId}")
	public Book deleteBookById(@PathVariable("bookId") int bookid) {
		Book book = bookService.deleteBookById(bookid);
		System.out.println("Deleted Book by ID is :");
		System.out.println(book);
		return book;
	}

//	handler method to delete book by name
	@DeleteMapping("/books/name/{bookName}")
	public void deleteBookByName(@PathVariable("bookName") String bookName) {
		bookService.deleteBookByName(bookName);
//		System.out.println("Deleted Book by NAME is :");
//		System.out.println(book);
//		return book;
	}

//	handler method to update book by id
	@PutMapping("books/{bookid}")
	public Book updateBookById(@RequestBody Book book, @PathVariable("bookid") int bookid) {
		Book updatedBook = bookService.updateBookById(book, bookid);
		System.out.println("Updated Book is :");
		System.out.println(updatedBook);
		return updatedBook;
	}
}

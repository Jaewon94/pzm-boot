package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    // 1. 전체리스트 가져오기 비즈니스 메서드
    public List<Book> getAllList() {
        return bookRepository.findAll();
    }

    // 2. 데이터 등록하기(저장하기)
    public Book register(Book book) {
        return bookRepository.save(book);
    }

    // 3. 특정 데이터 가져오기
    public Book getById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }

    // 4. 특정 데이터 수정하기
    public Book update(Long id, Book reqBook) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get(); // 영속성 메모리에 있는 BOOK
            book.setTitle(reqBook.getTitle());
            book.setAuthor(reqBook.getAuthor());
            book.setPrice(reqBook.getPrice());
//            return book; // 자동 더티체킹을 한 후 update SQL 실행 (속도지연, 구현쉽다.) / update SQL이 인나옴
            return bookRepository.save(book); // 이렇게 해도 id를 보고 있으면 업데이트 실행 함 / update SQL이 나옴
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }

    // 5. 특정 데이터 삭제하기
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

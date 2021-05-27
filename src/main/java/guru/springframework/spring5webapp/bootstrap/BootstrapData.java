package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publ = new Publisher();
        publ.setName("O'really");
        publ.setCity("San Francisco");
        publ.setState("California");
        publisherRepository.save(publ);

        Author dam = new Author("Damiano","Leonardi");
        Book first_book = new Book("Domain driven", "342352353535");
        dam.getBooks().add(first_book);
        first_book.getAuthors().add(dam);

        authorRepository.save(dam);
        bookRepository.save(first_book);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("java dev", "454656565656");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        publ.getBooks().add(noEJB);
        publisherRepository.save(publ);

        System.out.println("Started in bootstrap");
        System.out.println("Number of publisher: "+publisherRepository.count());
        System.out.println("publisher number of books: "+publ.getBooks().size());
        System.out.println("Numbers of books: "+ bookRepository.count());


    }
}

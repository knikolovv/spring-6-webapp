package guru.springframework.spring6webapp.boostrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.repository.BookRepository;
import guru.springframework.spring6webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        Author george = new Author();
        george.setFirstName("George");
        george.setLastName("Bush");

        Book ddd = new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("12345");

        Publisher publisher = new Publisher();
        publisher.setName("Golden Books");
        publisher.setCity("California");

        Author georgeSaved = authorRepository.save(george);
        Book dddSaved = bookRepository.save(ddd);
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        georgeSaved.getBooks().add(dddSaved);

        bookRepository.save(dddSaved);
        authorRepository.save(georgeSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count " + authorRepository.count());
        System.out.println("Publisher count " + publisherRepository.count());
        System.out.println("Book publisher = " + dddSaved.getPublisher());
        System.out.println("Author books " + georgeSaved.getBooks());


    }
}

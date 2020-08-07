package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    public void run(String... args) {
        User admin = new User("admin", "super@domain.com", "admin",
                "Super", "Hero", true);

        Role adminRole1 = new Role("admin", "ROLE_ADMIN");
        Role adminRole2 = new Role("admin", "ROLE_USER");
        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);

        Category category = new Category();
        category.setName("Fiction");
        categoryRepository.save(category);
        Book book1 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("Poetry");
        categoryRepository.save(category);
        Book book2 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("Drama");
        categoryRepository.save(category);
        Book book3 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("Prose");
        categoryRepository.save(category);
        Book book4 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("Nonfiction");
        categoryRepository.save(category);
        Book book5= new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("fantasy");
        categoryRepository.save(category);
        Book book = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("romance");
        categoryRepository.save(category);
        Book book6 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        category = new Category();
        category.setName("science fiction");
        categoryRepository.save(category);

        Book book7 = new Book("9867664", "The Hunger Games (The Hunger Games, #1)",
                "Suzanne Collins", 456, 2015,"", false, category);

        bookRepository.save(book);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);

    }


}

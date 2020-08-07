package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

//  Home Page
    @RequestMapping("/")
    public String index(Principal principal, Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "index";
    }

//  Login Page
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//  Logout Page
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }

//  User Profile
    @RequestMapping("/account/profile")
    public String admin(Principal principal, Model model) {
        model.addAttribute("currentUser", principal.getName());
        return "profile";
    }

//  Registration
    @GetMapping("/account/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
//  Process Registration
    @PostMapping("/account/processregister")
    public String processRegister(@Valid @ModelAttribute("user") User user,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "New user account created");

        user.setEnabled(true);
        userRepository.save(user);

        Role role = new Role(user.getUsername(), "ROLE_USER");
        roleRepository.save(role);

        return "redirect:/";

    }

    //    Page to add a Book
    @RequestMapping("/book/add")
    public String addBook(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("submit", "Add");
        model.addAttribute("categories", categories);

        return "addBook";
    }
    //    Page to Update a Book
    @RequestMapping("/book/update/{id}")
    public String updateBook(@PathVariable long id, Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        Book book = bookRepository.findById(id).get();

        model.addAttribute("book", book);
        model.addAttribute("submit", "Update");
        model.addAttribute("categories", categories);

        return "addBook";
    }

    //    Processing added Book
    @RequestMapping("/book/process")
    public String process(@Valid @ModelAttribute Book book, BindingResult result,
                          @RequestParam("bookImage") MultipartFile file ) {
//        First we check if the file submitted is empty
        if (file.isEmpty() || result.hasErrors()) {
            return "redirect:/book/add";
        }

//        Then we upload the file to cloudinary
        try {
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            book.setImage(uploadResult.get("url").toString());

            bookRepository.save(book);

//        We check if there was any error during upload; if so, redirect to the /add opage
        } catch (IOException e) {
            e.getStackTrace();
            return "redirect:/book/add";
        }

//        If Everything went okay \, we redirect to the home page
        return "redirect:/";
    }

    //    Page to view Book details
    @RequestMapping("/book/details")
    public String viewBook(@RequestParam("id") long id,  Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);

        return "bookDetails";
    }

//
    @GetMapping("/book/outOfStock/{id}")
    public String outOfStock(@PathVariable long id, Model model, @RequestParam("details") boolean returnToDetailPage){

        Book book = bookRepository.findById(id).get();
    //       Here: I am negating whatever the isRented property holds
        book.setOutOfStock(!book.getOutOfStock());

        bookRepository.save(book);
        model.addAttribute("employee", book);

        if (returnToDetailPage) {
            return "redirect:/book/details?id="+id;
        } else {
            return "redirect:/";
        }

    }




    //    Add Category
    @GetMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("submit", "Add");

        return "addCategory";
    }
    //    Update Category
    @GetMapping("/category/update/{id}")
    public String updateCategory(@PathVariable long id, Model model) {
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);
        model.addAttribute("submit", "Update");

        return "addCategory";
    }
    //    Process Category
    @PostMapping("/category/process")
    public String processCategory(@Valid @ModelAttribute Category category, BindingResult result,
                                  Model model) {
        if(result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("submit", "Update");
            return "addCategory";
        }
        categoryRepository.save(category);


        return "redirect:/";
    }

    //    Page to view Book details
    @RequestMapping("/category/details/{id}")
    public String viewCate(@PathVariable long id,  Model model) {
        Category category =categoryRepository.findById(id).get();
        model.addAttribute("category", category);

        return "categoryDetails";
    }


}

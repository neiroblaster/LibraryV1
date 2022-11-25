package com.shchayuk.mvcProject1.config.controllers;

import com.shchayuk.mvcProject1.config.dao.BookDAO;
import com.shchayuk.mvcProject1.config.dao.PersonDAO;
import com.shchayuk.mvcProject1.config.models.Book;
import com.shchayuk.mvcProject1.config.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people",personDAO.index());
        model.addAttribute("owner",personDAO.showOwner(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook (@ModelAttribute("book") Book book){
        return "/books/new";
    }

    @PostMapping()
    public String create(@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }

        bookDAO.update(id, book);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/lend")
    public String lendTheBook(@PathVariable("id") int id,
                              @ModelAttribute("person") Person person){
        bookDAO.lendTheBook(id, person.getId());
        return "redirect:/books/" + id;
    }

        @PatchMapping("/{id}/release")
    public String releaseTheBook(@PathVariable("id") int id){
        bookDAO.releaseTheBook(id);
        return "redirect:/books/" + id;
    }
}

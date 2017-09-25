package com.bookstore.resource;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by DG on 9/10/17.
 */
@RestController
@RequestMapping("/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Book addBookPost(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/add/image",method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("id") Long id,
                                 HttpServletResponse response,
                                 HttpServletRequest request)
    {
        try{
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
            Iterator<String> it = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(it.next());
            String fileName = id+".png";


            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
            stream.write(bytes);
            stream.close();

            return  new ResponseEntity("Upload Success!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/bookList")
    public List<Book> getBookList(){
        return bookService.findAll();
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public Book updateBookPost(@RequestBody Book book){
        return bookService.save(book);
    }

    @RequestMapping(value = "/update/image",method = RequestMethod.POST)
    public ResponseEntity updateImagePost(@RequestParam("id") Long id,
                                         HttpServletResponse response,
                                         HttpServletRequest request)
    {
        try{
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
            Iterator<String> it = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(it.next());
            String fileName = id+".png";

            Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
            stream.write(bytes);
            stream.close();

            return  new ResponseEntity("Upload Success!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/{id}")
    public Book getBook(@PathVariable("id") Long id){
        Book book = bookService.findOne(id);
        return book;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity remove(@RequestBody String id){
        bookService.removeOne(Long.parseLong(id));

        return new ResponseEntity("Remove Success!",HttpStatus.OK);
    }
}

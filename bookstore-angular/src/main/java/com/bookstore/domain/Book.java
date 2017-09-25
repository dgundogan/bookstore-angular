package com.bookstore.domain;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by DG on 9/10/17.
 */
@Entity
@Data
public class Book implements Serializable{

    private static final long serialVersionUID=42352423L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;
    private String publisher;
    private String publicationDate;
    private String language;
    private String category;
    private int numberOfPages;
    private String format;
    private String isbn;
    private double shippingWeight;
    private double listPrice;
    private double ourPrice;
    private boolean active=true;

    @Column(columnDefinition = "text")
    private String description;
    private int inStockNumber;

    private Blob bookImage;

}

package com.kcode.quote.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;


@Getter
@Setter
@Table(name = "quotes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "please enter author")
//    @Size(min = 3, max = 20,  message = "{quote.author.invalid}")
    private String author;

    @Lob
    @NotBlank(message = "please enter content")
    @Size(max = 250,  message = "{quote.content.invalid}")
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "quote_has_username", referencedColumnName = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserDao user;
}
package com.kcode.quoteapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quote  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @NotNull
    @Size(max = 250)
    private String content;

    @Lob
    private String authorname;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_quote", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}

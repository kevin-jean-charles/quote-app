package com.kcode.quote.app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "please enter username")
    @Size(min = 3, max = 20, message = "{user.username.invalid}")
    private String username;

    @NotBlank(message = "please enter password")
    @Size(min = 6, max = 120 , message = "{user.username.invalid}")
    @JsonIgnore
    private String password;
}


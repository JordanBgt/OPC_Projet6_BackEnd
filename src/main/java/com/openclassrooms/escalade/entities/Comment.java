package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = -3892513819842144293L;

    @Id
    private Long id;

    private String description;

    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Spot spot;

}

package com.openclassrooms.escalade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "longueur")
public class Longueur implements Serializable {

    private static final long serialVersionUID = 878099787940406601L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "cotation_min_id")
    private Cotation cotationMin;

    @ManyToOne
    @JoinColumn(name = "cotation_max_id")
    private Cotation cotationMax;

    @ManyToOne
    private User user;
}

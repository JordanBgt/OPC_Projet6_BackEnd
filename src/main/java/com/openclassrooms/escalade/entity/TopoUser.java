package com.openclassrooms.escalade.entity;

import com.openclassrooms.escalade.model.EBookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topo_user")
public class TopoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topo_id")
    private Topo topo;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    private boolean available;

    @Column(name = "booking_state")
    @Enumerated(EnumType.STRING)
    private EBookingState bookingState;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
}

package com.openclassrooms.escalade.dto;

import com.openclassrooms.escalade.model.EBookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopoUserDto {

    private Long id;
    private boolean available;
    private Date bookingDate;
    private EBookingState bookingState;
    private TopoLightDto topo;
    private UserLightDto owner;
    private UserLightDto tenant;
}

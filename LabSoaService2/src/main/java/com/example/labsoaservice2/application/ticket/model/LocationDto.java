package com.example.labsoaservice2.application.ticket.model;

import lombok.Data;

@Data
public class LocationDto {
    //    @Schema(name = "Координата х")
    private long x;
    //    @Schema(name = "Координата y")
    private Double y;
    //    @Schema(name = "Координата z")
    private float z;
    //    @Schema(name = "Название места")
    private String name;
}

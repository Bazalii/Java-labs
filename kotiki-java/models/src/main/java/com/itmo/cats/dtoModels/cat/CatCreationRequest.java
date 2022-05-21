package com.itmo.cats.dtoModels.cat;

import com.itmo.cats.coreModels.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class CatCreationRequest {
    private String name;

    private Date birthDate;

    private String breed;

    private Color color;

    private int ownerId;
}

package org.mock.persistence.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {
    private Long id;
    private String name;
    private String team;
    private String position;
}

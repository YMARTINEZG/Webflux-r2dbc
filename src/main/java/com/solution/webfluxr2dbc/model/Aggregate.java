package com.solution.webfluxr2dbc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table("aggregate")
public class Aggregate {
    @Id
    @Column("id")
    private long id;
    @Column("request_id")
    private Integer requestId;
    @Column("request_message")
    private String requestMessage;
}

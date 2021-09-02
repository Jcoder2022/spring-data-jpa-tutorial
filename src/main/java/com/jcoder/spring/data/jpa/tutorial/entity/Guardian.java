package com.jcoder.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable // As I don't want seperate table, want in same student table
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name="guardian_name")),
        @AttributeOverride(name = "email", column = @Column(name="guardian_email")),
        @AttributeOverride(name = "mobile", column = @Column(name="guardian_mobile"))
}
)
public class Guardian implements Serializable {
    private String name;
    private String email;
    private String mobile;
}

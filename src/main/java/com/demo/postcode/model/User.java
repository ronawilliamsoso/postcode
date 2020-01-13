package com.demo.postcode.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "t_user")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    @Length(min=2,max = 30)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;

    @Column(length=30,nullable = false)
    private String lastName;

    @Column(length =2)
    private Integer age;

    @Column
    private BigDecimal height;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(nullable = false)
    private Boolean active;

    @Column( insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastModified;

}

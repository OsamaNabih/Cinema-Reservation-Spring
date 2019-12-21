
package application;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String password;
    
    private Integer userType;
    
    private Date birthDate;


}

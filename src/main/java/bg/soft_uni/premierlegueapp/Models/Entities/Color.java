<<<<<<< HEAD
package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "colors")
public class Color extends BaseEntity{
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
=======
//package bg.soft_uni.premierlegueapp.Models.Entities;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//@Table
//@Entity(name = "colors")
//public class Color extends BaseEntity{
//    @Column(nullable = false)
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
>>>>>>> 9824e931688916421edbf2b5354024868cf6c5d1

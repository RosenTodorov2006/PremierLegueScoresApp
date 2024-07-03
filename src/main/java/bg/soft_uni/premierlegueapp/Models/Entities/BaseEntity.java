<<<<<<< HEAD
package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
=======
//package bg.soft_uni.premierlegueapp.Models.Entities;
//
//import jakarta.persistence.*;
//
//@MappedSuperclass
//public class BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//}
>>>>>>> 9824e931688916421edbf2b5354024868cf6c5d1

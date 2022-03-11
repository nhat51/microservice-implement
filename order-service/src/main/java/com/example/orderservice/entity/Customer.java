package com.example.orderservice.entity;

import com.example.orderservice.entityDTO.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String phone;

   /* @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
*/
    public static Customer convertDTOtoEntity(CustomerDTO dto){
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(entity.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}

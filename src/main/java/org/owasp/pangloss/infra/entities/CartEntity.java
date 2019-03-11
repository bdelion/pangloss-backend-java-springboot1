package org.owasp.pangloss.infra.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Entity(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ElementCollection
    private List<ItemEntity> items = emptyList();

    private String address = "";

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.project.wineshop.model;

import com.project.wineshop.model.enums.OrderPayment;
import com.project.wineshop.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    @ToString.Exclude
    private Map<Product,Integer> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_time")
    private LocalDateTime orderDate;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private OrderStatus.Status orderStatus;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private OrderPayment.Payment payment;

    private Boolean isGift;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package shop.app.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.app.models.orders.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query("from Invoice where customer.id=:customerId")
    List<Invoice> findAllByCustomer(long customerId);
}

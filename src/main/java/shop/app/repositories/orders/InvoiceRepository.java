package shop.app.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.orders.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}

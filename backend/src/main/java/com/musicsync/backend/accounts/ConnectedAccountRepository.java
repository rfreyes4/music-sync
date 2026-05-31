package com.musicsync.backend.accounts;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ConnectedAccountRepository extends JpaRepository<ConnectedAccount, Long> {
    
    //Metodo para encontrar cuentas conectadas por proveedor
    List<ConnectedAccount> findByProvider(Provider provider);
    
}

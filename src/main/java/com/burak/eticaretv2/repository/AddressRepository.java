package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findAddressesByCustomerId(Integer customerId);

}

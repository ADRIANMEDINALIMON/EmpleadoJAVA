
package com.adrian.proyecto.dao;

import com.adrian.proyecto.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface IPersonaDao  extends CrudRepository<Persona, Long>{
    
}

package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.CV;

public interface ICVRepo extends CrudRepository<CV, Integer>
{

}

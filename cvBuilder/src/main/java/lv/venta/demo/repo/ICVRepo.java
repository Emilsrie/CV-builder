package lv.venta.demo.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.CV;

public interface ICVRepo extends CrudRepository<CV, Integer>
{

}

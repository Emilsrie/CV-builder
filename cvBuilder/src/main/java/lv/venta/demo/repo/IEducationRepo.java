package lv.venta.demo.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Education;

public interface IEducationRepo extends CrudRepository<Education, Integer>{

}

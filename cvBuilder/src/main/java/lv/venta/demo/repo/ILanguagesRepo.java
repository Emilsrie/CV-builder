package lv.venta.demo.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Languages;

public interface ILanguagesRepo extends CrudRepository<Languages, Integer>
{

}

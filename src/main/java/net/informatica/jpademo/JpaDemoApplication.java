package net.informatica.jpademo;

import net.informatica.jpademo.model.Categoria;
import net.informatica.jpademo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
//    @Autowired
//    private CategoriaRepository repo;
    @Autowired
    private CategoriaRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(repo);
        //guardar();
        //buscarPorId();
        //modificar();
        //conteo();
        //encontrarPorId();
        //eliminarTodos();
        //conteo();
        //eliminar();
        //buscarTodos();
        //exixteId();
        //guardartodas();
        //buscarTodosJPA();
        //borrarTodoEnBloqueJPA();
        //buscarTodosOrdenados();
        buscarTodosPaginacion();
    }

    ///////Metodos JPArepository//////
    private void buscarTodosPaginacionOrdenados(){

    }
    private void buscarTodosPaginacion(){
     Page<Categoria> page= repo.findAll(PageRequest.of(2,5));//PAGEABLE
        System.out.println("TOTAL DE ELEMENTOS "+ page.getTotalElements());
        System.out.println("TOTAL DE REGISTROS "+ page.getTotalPages());

     for(Categoria c : page.getContent()){
         System.out.println(c.getId()+" "+c.getNombre());
     }
    }
    private void buscarTodosOrdenados(){
        //el sort by debe de ser el nombre de la propiedad que se tiene en la categoria
        List<Categoria> categoria=repo.findAll(Sort.by("nombre").descending());
        for (Categoria c : categoria){
            System.out.println(c.getId()+" "+c.getNombre());
        }
    }
    private void borrarTodoEnBloqueJPA(){
        //con esto elimino de manera masiva los datos de la base
        repo.deleteAllInBatch();
    }
    private void buscarTodosJPA(){
        List<Categoria>  categorias=repo.findAll();
        for(Categoria c : categorias){
            System.out.println(c.getId() + " " + c.getNombre());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////
    private void guardar(){
        Categoria categoria=new Categoria();
        categoria.setNombre("COMPUTACION");
        categoria.setDescripcion("CATEGORIAS RELACIONADAS CON COMPUTACION");
        repo.save(categoria);
        System.out.println(categoria);
    }

    /*
    *METODO FINDID-Interfaz CrudRepository
    */
    private void buscarPorId(){
        Optional<Categoria> optional=repo.findById(1);
        if (optional.isPresent()==true){
            System.out.println(optional.get());
        }else{
            System.out.println("CATEGORIA NO ENCONTRADA");
        }
    }
/*
*metodo findById - Interfaz CrudRepository
*/
    private void modificar(){
        Optional<Categoria> optional=repo.findById(1);
        if (optional.isPresent()==true){
            Categoria catTmp=optional.get();
            catTmp.setNombre("Ing. de Software");
            catTmp.setDescripcion("Desarrollo de Sistemas");
            repo.save(catTmp);
            System.out.println(optional.get());
        }else{
            System.out.println("CATEGORIA NO ENCONTRADA");
        }
    }

    private void eliminar(){
        int idCategoria=2;
        repo.deleteById(idCategoria);
        System.out.println("ELIMINANDO REGISTRO");
    }

    private void conteo(){
        long count=repo.count();
        System.out.println("TOTAL DE CATEGORIAS: "+count);
    }

    private void eliminarTodos(){
        repo.deleteAll();;
    }

    private void encontrarPorId(){
        List<Integer> ids=new LinkedList<Integer>();
        ids.add(1);
        ids.add(4);
        ids.add(10);
        Iterable<Categoria> categorias=repo.findAllById(ids);
        for(Categoria cat : categorias){
            System.out.println(cat);
        }
        repo.findAllById(ids);
    }

    private void buscarTodos(){
       Iterable<Categoria> categorias= repo.findAll();
        for (Categoria cat :categorias){
            System.out.println(cat);
        }
    }

    private void exixteId(){
        boolean existe =repo.existsById(5);
        System.out.println("LA CATEGORIA EXISTE:"+existe);
    }

    private void guardartodas(){
        List<Categoria> categorias=getListaCategorias();
        repo.saveAll(categorias);
    }

    private List<Categoria>getListaCategorias(){
        List<Categoria>lista=new LinkedList<Categoria>();
        Categoria cat1=new Categoria();
        cat1.setNombre("PORGRAMADOR BLOCK CHAIN");
        cat1.setDescripcion("RELACIONADO CON BLOCCHAIN");
        Categoria cat2=new Categoria();
        cat2.setNombre("PORGRAMADOR BLOCK CHAIN2");
        cat2.setDescripcion("RELACIONADO CON BLOCCHAIN2");

        lista.add(cat1);
        lista.add(cat2);
        return  lista;
    }

}

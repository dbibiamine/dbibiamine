package cat.controllers;

import java.util.List;

import org.TP1.dao.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.dao.IProduitRepository;

@RestController
public class CatalogueController {
@Autowired
private IProduitRepository produitRepository;

@RequestMapping("/save")
public Produit saveProduit(Produit p) {
	 produitRepository.save(p);
	return p;
}
@RequestMapping("/all")
public List<Produit> getProduits(){
	return produitRepository.findAll();
}
@RequestMapping("/produits")
public Page<Produit> getProduits(int page){
	return produitRepository.findAll(new PageRequest(page, 5));
}
@RequestMapping("/produitsParMC")
public Page<Produit> getProduits(String mc,int page){
	return produitRepository.produitPartMC("%"+mc+"%", new PageRequest(page, 5));	
}


@RequestMapping("/delete")
public boolean delete(Long ref) {
	produitRepository.deleteById(ref);
	return true;
}
@RequestMapping("/update")
public Produit update(Produit p) {
	produitRepository.saveAndFlush(p);
	return p;
}
}

package com.projeto.sgts.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projeto.sgts.crudspring.model.Atividade;
import com.projeto.sgts.crudspring.repository.AtividadeRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AtividadeRepository atividadeRepository){
		return args -> {
			atividadeRepository.deleteAll();
			Atividade a = new Atividade();
			a.setTipo("Bug");
			a.setResumo("Erro no login com usuario X");
			//a.setDescricao("Erro no login com usuario X, ao acessar a pagina inserindo dados XXXXX houve erro de acesso");
			a.setSprint("68");
			a.setStatus("Em progresso");
			atividadeRepository.save(a);
		};
	}
}

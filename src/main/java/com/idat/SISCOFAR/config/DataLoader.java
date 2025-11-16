package com.idat.SISCOFAR.config;

import com.idat.SISCOFAR.entity.Usuario;
import com.idat.SISCOFAR.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (usuarioRepository.count() == 0) {
			System.out.println("No hay usuarios, creando datos de prueba para SICOFAR...");

			Usuario farmaceutico = new Usuario();
			farmaceutico.setUsername("farmaceutico");
			farmaceutico.setPassword(passwordEncoder.encode("12345"));
			farmaceutico.setRol("USER");
			usuarioRepository.save(farmaceutico);

			Usuario admin = new Usuario();
			admin.setUsername("admin_sicofar");
			admin.setPassword(passwordEncoder.encode("12345"));
			admin.setRol("ADMIN");
			usuarioRepository.save(admin);

			System.out.println("Datos de prueba creados.");
		}
	}
}
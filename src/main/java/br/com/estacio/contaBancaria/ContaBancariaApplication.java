package br.com.estacio.contaBancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SpringBootApplication
public class ContaBancariaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ContaBancariaApplication.class, args);
		LocaleContextHolder.setDefaultLocale(new Locale("pt", "BR"));
	}

}

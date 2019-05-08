package br.com.estacio.contaBancaria.controller;

import br.com.estacio.contaBancaria.dto.ContaBancariaDTO;
import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import br.com.estacio.contaBancaria.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta-bancaria")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping()
    public @ResponseBody ContaBancariaDTO novo(@RequestBody ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaBancaria = this.contaBancariaService.novo(contaBancariaDTO);

        return new ContaBancariaDTO(contaBancaria);
    }
}

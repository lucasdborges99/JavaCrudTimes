package com.template.service;

import com.template.model.dao.TimesDAO;
import com.template.model.dto.TimesDTO;
import java.util.ArrayList;
import static com.template.validator.TimesValidator.validarExcluir;
import static com.template.validator.TimesValidator.validarTime;

public class TimesService {

    private final TimesDAO timesDAO;

    public TimesService() {
        timesDAO = new TimesDAO();
    }

    public ArrayList<TimesDTO> listarTimes() {
        return timesDAO.listarTimes();
    }

    public void cadastrarTime(String nome, String anoFund, String estado, String brasileiros) {
        if (validarTime(nome, anoFund, estado, brasileiros)) {
            TimesDTO linhaTime = new TimesDTO();
            linhaTime.setNome(nome);
            linhaTime.setAnoFundacao(Integer.parseInt(anoFund));
            linhaTime.setEstado(estado);
            linhaTime.setTitulosBrasileiros(Integer.parseInt(brasileiros));
            timesDAO.cadastrarTime(linhaTime);
        }
    }

    public void alterarTime(String id, String nome, String anoFund, String estado, String brasileiros) {
        if (validarTime(nome, anoFund, estado, brasileiros)) {
            TimesDTO linhaTime = new TimesDTO();
            linhaTime.setId(Integer.parseInt(id));
            linhaTime.setNome(nome);
            linhaTime.setAnoFundacao(Integer.parseInt(anoFund));
            linhaTime.setEstado(estado);
            linhaTime.setTitulosBrasileiros(Integer.parseInt(brasileiros));
            timesDAO.alterarTime(linhaTime);
        }
    }

    public void excluirTime(String id) {

        if (validarExcluir(id)) {
            timesDAO.excluirTime(Integer.parseInt(id));
        }
    }
}
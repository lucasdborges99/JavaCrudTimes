package com.template.service;

import com.template.model.dao.TimesDAO;
import com.template.model.dto.TimesDTO;
import java.util.ArrayList;

public class TimesService {

    private final TimesDAO timesDAO = new TimesDAO();

    public ArrayList<TimesDTO> listarTimes() {
        return timesDAO.listarTimes();
    }

    public void cadastrarTime(String nome, String anoFund, String estado, String brasileiros) {
        TimesDTO linhaTime = new TimesDTO();
        linhaTime.setNome(nome);
        linhaTime.setAnoFundacao(Integer.parseInt(anoFund));
        linhaTime.setEstado(estado);
        linhaTime.setTitulosBrasileiros(Integer.parseInt(brasileiros));
        timesDAO.cadastrarTime(linhaTime);
    }

    public void alterarTime(String id, String nome, String anoFund, String estado, String brasileiros) {
        TimesDTO linhaTime = new TimesDTO();
        linhaTime.setId(Integer.parseInt(id));
        linhaTime.setNome(nome);
        linhaTime.setAnoFundacao(Integer.parseInt(anoFund));
        linhaTime.setEstado(estado);
        linhaTime.setTitulosBrasileiros(Integer.parseInt(brasileiros));
        timesDAO.alterarTime(linhaTime);
    }

    public void excluirTime(String id) {
        timesDAO.excluirTime(Integer.parseInt(id));
    }
}
package com.template.validator;

import com.template.util.DialogUtil;
import java.util.ArrayList;
import java.util.List;

public class TimesValidator {

    public static boolean validarTime(String nome, String anoFund, String estado, String brasileiros) {
        List<Validator<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidator("Nome", nome));
        validadores.add(new CampoObrigatorioValidator("Estado", estado));
        validadores.add(new CampoObrigatorioValidator("Ano de Fundação", anoFund));
        validadores.add(new CampoObrigatorioValidator("Títulos Brasileiros", brasileiros));

        validadores.add(new AnoValidator(anoFund));
        validadores.add(new BrasileirosValidator(brasileiros));

        for (Validator<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                DialogUtil.showWarning(validador.getMensagemErro());
                return false;
            }
        }

        return true;
    }

    public static boolean validarExcluir(String id) {
        if (id == null || id.trim().isEmpty()) {
            DialogUtil.showWarning("Selecione um time na tabela para excluir.");
            return false;
        }
        return true;
    }
}
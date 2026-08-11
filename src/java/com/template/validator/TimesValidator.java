package com.template.validator;

import com.template.util.DialogUtil;

public class TimesValidator {

    public static boolean validarTime(String nome, String anoFund, String estado, String brasileiros) {
        if (nome.isEmpty() || anoFund.isEmpty() || estado.isEmpty() || brasileiros.isEmpty()) {
            DialogUtil.showWarning("Preencha todos os campos necessários para prosseguir");
            return false;
        }
        return true;
    }

    public static boolean validarExcluir(String id) {
        if (id.isEmpty()) {
            DialogUtil.showWarning("Selecione um time na tabela para excluir");
            return false;
        }
        return true;
    }
}
package com.template.validator;

import com.template.util.DialogUtil;

public class TimesValidator {

    public static boolean validarTime(String nome, String anoFund, String estado, String brasileiros) {

        CampoObrigatorioValidator nomeValidator =
                new CampoObrigatorioValidator("Nome", nome);

        CampoObrigatorioValidator estadoValidator =
                new CampoObrigatorioValidator("Estado", estado);

        if (!nomeValidator.validar(nome)) {
            DialogUtil.showWarning(nomeValidator.getMensagemErro());
            return false;
        }

        if (!estadoValidator.validar(estado)) {
            DialogUtil.showWarning(estadoValidator.getMensagemErro());
            return false;
        }

        AnoValidator anoValidator = new AnoValidator(anoFund);

        if (!anoValidator.validar(anoFund)) {
            DialogUtil.showWarning(anoValidator.getMensagemErro());
            return false;
        }

        BrasileirosValidator brasileirosValidator =
                new BrasileirosValidator(brasileiros);

        if (!brasileirosValidator.validar(brasileiros)) {
            DialogUtil.showWarning(brasileirosValidator.getMensagemErro());
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
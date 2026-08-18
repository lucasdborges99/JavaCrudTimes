package com.template.validator;

public class BrasileirosValidator implements Validator<String> {

    private final String valor;

    public BrasileirosValidator(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(valor.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "A quantidade de títulos brasileiros deve ser um número inteiro.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}
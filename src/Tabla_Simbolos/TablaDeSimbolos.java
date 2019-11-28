/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import java.util.LinkedList;

/**
 *
 * @author Andree
 */
public class TablaDeSimbolos extends LinkedList<Simbolo> {

    private TablaDeSimbolos padre;

    public TablaDeSimbolos() {
        this.padre = null;
    }

    public void setPadre(TablaDeSimbolos tablapadre) {
        this.padre = tablapadre;
    }

    public void setValor(String id, Object valor) {
        setValor(id, valor, this);
    }

    private void setValor(String id, Object valor, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getId().equalsIgnoreCase(id)) {
                switch (item.getTipo().getAsignado()) {
                    case Entero:
                        item.setValor((int) valor);
                        return;
                    case Decimal:
                        item.setValor((double) valor);
                        return;
                    case Float:
                        item.setValor((float) valor);
                        return;
                    case Char:
                        item.setValor((char) valor);
                        return;
                    case Cadena:
                        item.setValor((String) valor);
                        return;
                    case Bool:
                        item.setValor((boolean) valor);
                        return;
                    default:
                        //deberia tirar error ya que no existe el tipo asignado
                        return;
                }
            }
        }
    }

    public boolean asignValor(String id, Object valor) {
        return asignValor(id, valor, this);
    }

    private boolean asignValor(String id, Object valor, TablaDeSimbolos tsPadre) {

        try {
            Object val_aux = null;
            for (Simbolo item : tsPadre) {
                if (item.getId().equalsIgnoreCase(id)) {
                    switch (item.getTipo().getTipo()) {
                        case Numero:
                            switch (item.getTipo().getAsignado()) {
                                case Entero:
                                    val_aux = (int) valor;
                                    return true;
                                case Decimal:
                                    val_aux = (double) valor;
                                    return true;

                                case Float:
                                    val_aux = (float) valor;
                                    return true;
                                default:
                                    return false;
                            }
                        case Cadena:
                            val_aux = valor.toString();
                            return true;
                        case Char:
                            val_aux = (char) valor;
                            return true;
                        case Bool:
                            val_aux = (boolean) valor;
                            return true;
                        default:
                            break;
                    }
                }
            }
            return val_aux != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Object getValor(String id) {
        return getValor(id, this);
    }

    private Object getValor(String id, TablaDeSimbolos tsPadre) {
        for (Simbolo item : tsPadre) {
            if (item.getId().equals(id)) {
                return item.getValor();
            }
        }
        if (tsPadre.getPadre() != null) {
            return getValor(id, tsPadre.getPadre());
        }
        return null;
    }

    public boolean existeSimbolo(String id) {
        return existeSimbolo(id, this);
    }

    private boolean existeSimbolo(String id, TablaDeSimbolos tsPadre) {
        return tsPadre.stream().anyMatch((item) -> (item.getId().equalsIgnoreCase(id)));
    }

    public TablaDeSimbolos getPadre() {
        return this.padre;
    }

}

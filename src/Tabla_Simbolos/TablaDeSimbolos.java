/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolos;

import Instrucciones.Tipo;
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
                if (item.getTipo().getAsignado() == Tipo.Entero) {
                    item.setValor((int) valor);
                }
            }
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

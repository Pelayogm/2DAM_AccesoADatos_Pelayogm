package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Exportar {

    @Autowired
    Menus menu;

    public void ExportarTorneo (Usuario usuario) {
        if (usuario instanceof AdminTorneos) {
            AdminTorneos adminTorneos = (AdminTorneos) usuario;
        } else {
            System.out.println("No dispones de los permisos necesarios para acceder a este menú.");
            menu.menuInicial();
        }
    }

    public void ExportarCarnet (Entrenador entrenador) {

    }

    public void CrearElementoXML (String tipoDatoEtiqueta, String datoEtiqueta, Document document, Element raiz) {
        Element element = document.createElement(tipoDatoEtiqueta);
        Text text = document.createTextNode(datoEtiqueta);
        raiz.appendChild(element);
        element.appendChild(text);
    }
}

package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Exportar {

    public static void ExportarTorneo (Usuario usuario) {
        if (usuario instanceof AdminTorneos) {
            AdminTorneos adminTorneos = (AdminTorneos) usuario;
        } else {
            System.out.println("No dispones de los permisos necesarios para acceder a este menú.");
            Menus.menuInicial();
        }
    }

    public static void ExportarCarnet (Entrenador entrenador) {

    }

    public static void CrearElementoXML (String tipoDatoEtiqueta, String datoEtiqueta, Document document, Element raiz) {
        Element element = document.createElement(tipoDatoEtiqueta);
        Text text = document.createTextNode(datoEtiqueta);
        raiz.appendChild(element);
        element.appendChild(text);
    }
}

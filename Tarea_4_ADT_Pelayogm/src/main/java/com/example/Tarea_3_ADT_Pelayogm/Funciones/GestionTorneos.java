package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.*;
import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import com.example.Tarea_3_ADT_Pelayogm.MongoDB.MongoDBConectar;
import com.example.Tarea_3_ADT_Pelayogm.MongoDB.TorneoMongoDAO;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CarnetServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CombateEntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class GestionTorneos {

    @Autowired
    TorneoServiciosImplementacion torneoServiciosImplementacion;
    @Autowired
    EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;
    @Autowired
    CombateEntrenadorServiciosImplementacion combateEntrenadorServiciosImplementacion;
    @Autowired
    Menus menus;
    @Autowired
    CarnetServiciosImplementacion carnetServiciosImplementacion;

    public boolean inscribirEntrenador(Usuario usuario) {
        Scanner entrada = new Scanner(System.in);
        List<Torneo> torneosDisponibles = torneoServiciosImplementacion.listaDeTorneosSinGanador();
        //Contador para en caso de que no haya torneos disponibles darle una opción al usuario para salir.
        int contador = 0;

        if (!torneosDisponibles.isEmpty()) {
            System.out.println("Torneos disponibles:");
            if (usuario instanceof AdminTorneos) {
                for (int i = 0; i < torneosDisponibles.size(); i++) {
                    if (torneosDisponibles.get(i).getIdAdminTorneo() == usuario.getIdUsuarioInterfaz()) {
                        System.out.println( i + " - " + "Nombre torneo: " + torneosDisponibles.get(i).getNombreTorneo() + " | Region: " + torneosDisponibles.get(i).getCodigoTorneo());
                        contador++;
                    }
                }
            } else {
                for (int i = 0; i < torneosDisponibles.size(); i++) {
                    System.out.println( i + " - " + "Nombre torneo: " + torneosDisponibles.get(i).getNombreTorneo() + " | Region: " + torneosDisponibles.get(i).getCodigoTorneo());
                    contador++;
                }
            }

            if (contador == 0) {
                //Si contador es 0 entonces esto es la escapatoria para evitar que se quede en un bucle.
                System.out.println("No hay torneos disponibles");
                System.out.println("Volviendo...");
                if (usuario instanceof AdminTorneos) {
                    menus.menuAdminTorneos((AdminTorneos) usuario);
                } else {
                    menus.menuInicial();
                }
            }

            System.out.println("Seleccione un torneo.");
            try {
                int opcionUsuario = entrada.nextInt();
                try {
                    //Al torneo escogido se le coge la lista de combates que tiene y se itera sobre ella
                    System.out.println("Torneo escogido: " + torneosDisponibles.get(opcionUsuario).getNombreTorneo());
                    List<Combate> combatesDelTorneo = torneosDisponibles.get(opcionUsuario).getCombates();
                    for (int i = 0; i < combatesDelTorneo.size(); i++) {
                        CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                        Combate combate = combatesDelTorneo.get(i);
                        //Por cada combate se sacan los CombateEntrenador para sacar el ID y buscar el entrenador por ID. Y así poner su nombre
                        String nombreEntrenador1 = "plazaLibre";
                        String nombreEntrenador2 = "plazaLibre";
                            if (combateEntrenador.getIdEntrenador1() != 0) {
                                Entrenador entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador1());
                                nombreEntrenador1 = entrenador1.getNombreEntrenador();
                            }

                            if (combateEntrenador.getIdEntrenador2() != 0) {
                                Entrenador entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador2());
                                nombreEntrenador2 = entrenador2.getNombreEntrenador();
                            }
                            //Si están vacíos los huecos se pone plazaLibre
                        System.out.println("Combate " + combate.getIdCombate() + " | " + nombreEntrenador1 + " Contra " + nombreEntrenador2 + " | "
                            + "Fecha: " + combate.getFechaCombate());
                    }
                    if (usuario instanceof AdminTorneos) {
                        Torneo torneoSeleccionado = torneosDisponibles.get(opcionUsuario);
                        List<Entrenador> entrenadoresQueNoEstan = entrenadorServiciosImplementacion.entrenadoresQueNoEstanElTorneo(torneoSeleccionado.getIdTorneo());
                        System.out.println("Entrenadores disponibles para el torneo " + torneoSeleccionado.getNombreTorneo());
                        System.out.println("------------------------------------------------------------------");
                        for (int i = 0; i < entrenadoresQueNoEstan.size(); i++) {
                            System.out.println(i + " - " + entrenadoresQueNoEstan.get(i).getNombreEntrenador());
                        }
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("Escoge a un entrenador");
                        try {
                            int entrenadorEscogidoScanner = entrada.nextInt();
                            Entrenador entrenadorElegido = entrenadoresQueNoEstan.get(entrenadorEscogidoScanner);
                            System.out.println("¿Quieres inscribir a " + entrenadorElegido.getNombreEntrenador() + " en el torneo " + torneoSeleccionado.getNombreTorneo() + "?");
                            System.out.println("1. Si | 2. No");
                            try {
                                int confirmacion = entrada.nextInt();
                                if (confirmacion != 1) {
                                    System.out.println("Entendido, no se inscribirá en el torneo");
                                    inscribirEntrenador(usuario);
                                }
                                //Este contador sirve para cuantas veces se ha inscrito si son 2 veces, se sale del bucle
                                int contadorCombates = 0;
                                //Esta sección es para el entrenador que escoge este torneo como su torneo inicial
                                //Se le muestran todos los combates menos el último (el último es el combate final).
                                for (int i = 0; i < combatesDelTorneo.size(); i++) {
                                    CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                                    CombateEntrenador ultimoCombate = combatesDelTorneo.get(combatesDelTorneo.size() - 1).getCombateEntrenador();

                                    if (combateEntrenador.getIdEntrenador1() == 0) {
                                        combateEntrenador.setIdEntrenador1(entrenadorElegido.getIdEntrenador().intValue());
                                        combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                        contadorCombates++;
                                        //Esto sirve para poner al el entrenador en el último combate y evitar combinaciones sin fin.
                                        if (ultimoCombate.getIdEntrenador1() == 0) {
                                            ultimoCombate.setIdEntrenador1(entrenadorElegido.getIdEntrenador().intValue());
                                            combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(ultimoCombate);
                                            contadorCombates++;
                                        }
                                    } else if (combateEntrenador.getIdEntrenador2() == 0 && combateEntrenador.getIdEntrenador1() != entrenadorElegido.getIdEntrenador()) {
                                        combateEntrenador.setIdEntrenador2(entrenadorElegido.getIdEntrenador().intValue());
                                        combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                        // La lista tiene 3 huecos, pero solo se recorre el primer y segundo hueco, por tanto, se tiene que hacer "-2" porque "i" empieza en 0
                                        // y solo se dará como máximo 2 vueltas (0, 1 y 2)
                                        if (i == combatesDelTorneo.size() - 1) {
                                            //Esto sirve para poner el torneo a "-1" indicando que el torneo ya está lleno.
                                            Torneo torneo = torneoServiciosImplementacion.obtenerTorneoPorId(
                                                    combatesDelTorneo.get(i).getTorneo().getIdTorneo());
                                            torneo.setIdGanador(-1);
                                            //Con los datos actualizados lo insertamos en la base de datos para que se guarden los cambios.
                                            torneoServiciosImplementacion.insertarTorneo(torneo);
                                        }
                                        contadorCombates++;
                                    }

                                    if (contadorCombates == 2) {
                                        System.out.println("Se ha inscrito a " + entrenadorElegido.getNombreEntrenador() + " al torneo");
                                        //Se actualizan los datos del entrenador para la tabla entrenador_torneo que refleje que ya está en el torneo y no salga más en la lista
                                        Torneo torneoEscogido = torneoServiciosImplementacion.obtenerTorneoPorId(combatesDelTorneo.get(i).getTorneo().getIdTorneo());
                                        entrenadorElegido.getListaTorneos().add(torneoEscogido);
                                        entrenadorServiciosImplementacion.insertarEntrenador(entrenadorElegido);
                                        return true;
                                    }
                                }
                                System.out.println("¿Quieres volver a iniciar el proceso de inscripción? 1. Si | 2. No");
                                try {
                                    int confirmacionFinal = entrada.nextInt();
                                    if (confirmacionFinal == 1) {
                                        inscribirEntrenador(usuario);
                                    } else {
                                        menus.menuAdminTorneos((AdminTorneos) usuario);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Entrada no valida, volviendo...");
                                    menus.menuAdminTorneos((AdminTorneos) usuario);
                                }

                            } catch (Exception e) {
                                System.out.println("Entrada no valida, volviendo...");
                                inscribirEntrenador(usuario);
                            }
                        } catch (Exception e) {
                            System.out.println("No se ha podido escoger al entrenador");
                            inscribirEntrenador(usuario);
                        }
                    } else {
                        //Esto sirve para el primer torneo al registrar un entrenador nuevo
                        Entrenador entrenador = new Entrenador();
                        if (usuario instanceof Entrenador) {
                             entrenador = (Entrenador) usuario;
                        }
                        //Este contador sirve para cuantas veces se ha inscrito si son 2 veces, se sale del bucle
                        int contadorCombates = 0;
                        //Esta sección es para el entrenador que escoge este torneo como su torneo inicial
                        //Se le muestran todos los combates menos el último (el último es el combate final).
                        for (int i = 0; i < combatesDelTorneo.size(); i++) {
                            CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                            CombateEntrenador ultimoCombate = combatesDelTorneo.get(combatesDelTorneo.size() - 1).getCombateEntrenador();

                            if (combateEntrenador.getIdEntrenador1() == 0) {
                                combateEntrenador.setIdEntrenador1(usuario.getIdUsuarioInterfaz());
                                combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                contadorCombates++;
                                //Esto sirve para poner al el entrenador en el último combate y evitar combinaciones sin fin.
                                if (ultimoCombate.getIdEntrenador1() == 0) {
                                    ultimoCombate.setIdEntrenador1(usuario.getIdUsuarioInterfaz());
                                    combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(ultimoCombate);
                                    contadorCombates++;
                                }
                            } else if (combateEntrenador.getIdEntrenador2() == 0 && combateEntrenador.getIdEntrenador1() != entrenador.getIdEntrenador()) {
                                combateEntrenador.setIdEntrenador2(usuario.getIdUsuarioInterfaz());
                                combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                // La lista tiene 3 huecos, pero solo se recorre el primer y segundo hueco, por tanto, se tiene que hacer "-2" porque "i" empieza en 0
                                // y solo se dará como máximo 2 vueltas (0, 1 y 2)
                                if (i == combatesDelTorneo.size() - 1) {
                                    //Esto sirve para poner el torneo a "-1" indicando que el torneo ya está lleno.
                                    Torneo torneo = torneoServiciosImplementacion.obtenerTorneoPorId(
                                            combatesDelTorneo.get(i).getTorneo().getIdTorneo());
                                    torneo.setIdGanador(-1);
                                    //Con los datos actualizados lo insertamos en la base de datos para que se guarden los cambios.
                                    torneoServiciosImplementacion.insertarTorneo(torneo);
                                }
                                contadorCombates++;
                            }

                            if (contadorCombates == 2) {
                                Torneo torneoEscogido = torneoServiciosImplementacion.obtenerTorneoPorId(combatesDelTorneo.get(i).getTorneo().getIdTorneo());
                                entrenador.getListaTorneos().add(torneoEscogido);
                                System.out.println("Inscrito con éxito");
                                return true;
                            }
                        }
                        //Esto informa al usuario de que el torneo está lleno en caso de que el "-1" no se inserte y evitar la excepción.
                        System.out.println("El torneo esta lleno");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Opción fuera de límites");
                    inscribirEntrenador(usuario);
                }
            } catch (Exception e) {
                System.out.println("Entrada no reconocida, vuelve al menu");
                inscribirEntrenador(usuario);
            }
        } else {
            System.out.println("Actualmente no hay ningún torneo disponible para inscribir a un entrenador");
        }
        return false;
    }

    public void pelear(Usuario usuario) {
        //Los torneos listos para combatir son los que su idGanador es -1
        List<Torneo> torneosDisponibles = torneoServiciosImplementacion.listaDeTorneosParaCombatir();
        //Este boolean sirve para saber si es el combate final de un torneo
        boolean combateFinalBoolean = false;
        Scanner entrada = new Scanner(System.in);

        if (!torneosDisponibles.isEmpty()) {
            System.out.println("Lista de torneos:");
            for (int i = 0; i < torneosDisponibles.size(); i++) {
                Torneo torneo = torneosDisponibles.get(i);
                if (torneo.getIdAdminTorneo() == usuario.getIdUsuarioInterfaz()) {
                    System.out.println(i + " - " + torneo.getNombreTorneo() + " está listo.");
                    System.out.println("---------------------------------------------------------");
                }
            }
            System.out.println("Selecciona el torneo.");
            try {
                int opcionUsuario = entrada.nextInt();
                Torneo torneoSeleccionado = torneosDisponibles.get(opcionUsuario);
                List<Combate> combatesDelTorneo = torneoSeleccionado.getCombates();
                Entrenador entrenador1;
                Entrenador entrenador2;
                    for (int i = 0; i < combatesDelTorneo.size(); i++) {
                        CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                        if (combateEntrenador.getIdGanador() == 0) {
                            if (combateEntrenador.getIdEntrenador1() != 0 && combateEntrenador.getIdEntrenador2() != 0) {
                                entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador1());
                                entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador2());
                                System.out.println(i + " - " + entrenador1.getNombreEntrenador() + " CONTRA " + entrenador2.getNombreEntrenador());
                            }
                        }
                    }

                System.out.println("¿Iniciar Torneo? 1. Sí | 2. No");
                    try {
                        int contadorV1 = 0;
                        int contadorV2 = 0;
                        int contadorV3 = 0;

                        int idEntrenador2 = 0;

                        int iniciarTorneoScanner = entrada.nextInt();
                        if (iniciarTorneoScanner == 1) {
                            Entrenador entrenadorGanador = new Entrenador();
                            int idGanador;

                            for (int i = 0; i < combatesDelTorneo.size(); i++) {
                                CombateEntrenador combateEntrenadorActual = combatesDelTorneo.get(i).getCombateEntrenador();
                                entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenadorActual.getIdEntrenador1());
                                entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenadorActual.getIdEntrenador2());

                                if (combateEntrenadorActual.getIdCombateEntrenador() == combatesDelTorneo.get(combatesDelTorneo.size() - 1).getCombateEntrenador().getIdCombateEntrenador()) {
                                    System.out.println("- ÚLTIMO COMBATE -");
                                    combateFinalBoolean = true;
                                }

                                //Si el idEntrenador1 + 1 es mayor que el del entrenador 2 gana entrenador 1
                                if (combateEntrenadorActual.getIdEntrenador1() + 1 >= combateEntrenadorActual.getIdEntrenador2()) {
                                    System.out.println("Ha ganado " + entrenador1.getNombreEntrenador());
                                    idGanador = entrenador1.getIdEntrenador().intValue();
                                    combateEntrenadorActual.setIdGanador(idGanador);
                                    combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenadorActual);
                                    if (combateEntrenadorActual.getIdCombateEntrenador() == combatesDelTorneo.get(0).getCombateEntrenador().getIdCombateEntrenador()) {
                                        contadorV1++;
                                        System.out.println(entrenador1.getNombreEntrenador() + " lleva " + contadorV1 + " victorias");
                                    } else if (combateEntrenadorActual.getIdCombateEntrenador() == combatesDelTorneo.get(combatesDelTorneo.size() - 2).getCombateEntrenador().getIdCombateEntrenador()){
                                        contadorV2++;
                                        System.out.println(entrenador1.getNombreEntrenador() + " lleva " + contadorV2 + " victorias");
                                        idEntrenador2 = entrenador1.getIdEntrenador().intValue();
                                    } else {
                                        contadorV1++;
                                        System.out.println(entrenador1.getNombreEntrenador() + " lleva " + contadorV1 + " victorias");
                                    }

                                } else {
                                    System.out.println("Ha ganado " + entrenador2.getNombreEntrenador());
                                    idGanador = entrenador2.getIdEntrenador().intValue();
                                    combateEntrenadorActual.setIdGanador(idGanador);
                                    combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenadorActual);
                                    if (combateEntrenadorActual.getIdCombateEntrenador() == combatesDelTorneo.get(0).getCombateEntrenador().getIdCombateEntrenador()) {
                                        contadorV2++;
                                        System.out.println(entrenador2.getNombreEntrenador() + " lleva " + contadorV2 + " victorias");
                                        idEntrenador2 = entrenador2.getIdEntrenador().intValue();
                                    } else if (combateEntrenadorActual.getIdCombateEntrenador() == combatesDelTorneo.get(combatesDelTorneo.size() - 2).getCombateEntrenador().getIdCombateEntrenador()){
                                        contadorV3++;
                                        System.out.println(entrenador2.getNombreEntrenador() + " lleva " + contadorV3 + " victorias");
                                    } else {
                                        contadorV3++;
                                        System.out.println(entrenador2.getNombreEntrenador() + " lleva " + contadorV3 + " victorias");
                                    }
                                }

                                if (combateFinalBoolean) {
                                    if (contadorV3 > contadorV2 && contadorV3 > contadorV1) {
                                        idGanador = entrenador2.getIdEntrenador().intValue();
                                    } else if (contadorV2 > contadorV1 && contadorV2 > contadorV3) {
                                        idGanador = idEntrenador2;
                                    } else if (contadorV1 > contadorV2 && contadorV1 > contadorV3) {
                                        idGanador = entrenador1.getIdEntrenador().intValue();
                                    } else {
                                        idGanador = entrenador2.getIdEntrenador().intValue();
                                    }

                                    //Si es el último combate se busca el combate final
                                    Torneo torneo = torneoServiciosImplementacion.obtenerTorneoPorId(torneoSeleccionado.getIdTorneo());
                                    torneo.setIdGanador(idGanador);
                                    //Se inserta el torneo para actualizar el torneo
                                    torneoServiciosImplementacion.insertarTorneo(torneo);
                                    entrenadorGanador = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(idGanador);
                                    //Anunciamos el ganador
                                    System.out.println(entrenadorGanador.getNombreEntrenador() + " es el ganador del torneo " + torneoSeleccionado.getNombreTorneo());
                                    //Buscamos su carnet y le añadimos los puntos nuevos y le aumentamos el numero de victorias
                                    Carnet carnetEntrenadorGanador = carnetServiciosImplementacion.obtenerCarnetPorId(entrenadorGanador.getIdEntrenador());
                                    carnetEntrenadorGanador.setPuntosCarnet((float) (carnetEntrenadorGanador.getPuntosCarnet() + torneoSeleccionado.getPuntosVictoriaTorneo()));
                                    carnetEntrenadorGanador.setNumeroVictorias(carnetEntrenadorGanador.getNumeroVictorias() + 1);
                                    //Insertamos de nuevo el carnet actualizado
                                    carnetServiciosImplementacion.insertarCarnet(carnetEntrenadorGanador);

                                    try (MongoClient client = MongoDBConectar.conectar()) {
                                        TorneoMongoDAO torneoMongoDAO = new TorneoMongoDAO(client);
                                        torneoMongoDAO.actualizarTorneo(torneo);
                                    }
                                }

                            }
                        } else {
                            System.out.println("Volviendo atrás...");
                            pelear(usuario);
                        }

                    } catch (Exception e) {
                        System.out.println("Volviendo...");
                        pelear(usuario);
                    }
            } catch (Exception e) {
                System.out.println("Dato no reconocido, volviendo...");
                pelear(usuario);
            }
        } else {
            System.out.println("No hay torneos disponibles para realizar combates.");
            System.out.println("Volviendo al menú...");
            menus.menuAdminTorneos((AdminTorneos) usuario);
        }
    }

    public void crearFichero(Torneo torneo) {
        String nombreTorneo = torneo.getNombreTorneo();
        nombreTorneo += ".txt";
        File file = new File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosSalida/", nombreTorneo);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    escribirFichero(torneo, file);
                    System.out.println("Torneo exportado a fichero.");
                } catch (Exception e) {
                    System.out.println("No se ha creado el archivo txt.");
                }
            } else {
                escribirFichero(torneo, file);
                System.out.println("Fichero del torneo actualizado.");
            }
    }

    private void escribirFichero(Torneo torneo, File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            try {
                fileWriter.write("Nombre Torneo: " + torneo.getNombreTorneo() + "\n");
                fileWriter.write("Region Torneo: " + torneo.getCodigoTorneo() + "\n");
                if (torneo.getIdGanador() != 0 && torneo.getIdGanador() != - 1) {
                    Entrenador ganador = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(torneo.getIdGanador());
                    fileWriter.write("Ganador Torneo: " + torneo.getIdGanador() + " | " + ganador.getNombreEntrenador() + "\n");
                }
                fileWriter.write("Combates del torneo" + "\n");
                    if (!torneo.getCombates().isEmpty()) {
                        for (int i = 0; i < torneo.getCombates().size(); i++) {
                            fileWriter.write("Combate " + i + ": " + torneo.getCombates().get(i).getFechaCombate() + " | ID: " + torneo.getCombates().get(i).getIdCombate() + " | " + "Ganador: " + torneo.getCombates().get(i).getCombateEntrenador().getIdGanador() + "\n");
                        }
                    }
                fileWriter.write("Puntos del torneo: " + torneo.getPuntosVictoriaTorneo());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error en la escritura");
            }
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        }
    }
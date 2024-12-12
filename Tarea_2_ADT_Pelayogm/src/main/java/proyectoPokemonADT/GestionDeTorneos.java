package proyectoPokemonADT;

import proyectoPokemonADT.Administradores.AdminTorneos;
import proyectoPokemonADT.DTO.GimnasioDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.FicherosDeTexto.TorneoTXT;
import proyectoPokemonADT.Servicios.TorneosServicio;
import proyectoPokemonADT.ArchivosDelPrograma.ConexionBaseDeDatos;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDeTorneos {
    private static final ConexionBaseDeDatos conexionBaseDeDatos = ConexionBaseDeDatos.getInstancia();
    private static final DataSource dataSource = conexionBaseDeDatos.configurarDataSource();
    private static final TorneosServicio torneosServicio = TorneosServicio.getInstancia(dataSource);

    public static void crearTorneo () {

    }

    public static void apuntarseAUnTorneo (int idUsuario) {
        //NO IMPLEMENTADA AÚN
    }

    public static void exportarTorneo (Usuario usuario) {
        //Si el usuario es un Administrador de Torneos entramos en la condición.
        if (usuario instanceof AdminTorneos) {
            Scanner scanner = new Scanner(System.in);
            AdminTorneos adminTorneos = (AdminTorneos) usuario;
            //Obtenemos todos los torneos que haya disponibles
            List<TorneoDTO> listaDeTorneosDto = torneosServicio.obtenerTodosLosTorneos();
            List<TorneoDTO> torneosDelAdmin = new ArrayList<>();
            int contador = 0;
            //Si hay torneos entramos en la condición y hacemos una comparación entre el idDeAdministradorTorneos que tienen los torneos,
            //con el ID del Administrador de Torneos que ha hecho login.
            if (!listaDeTorneosDto.isEmpty()) {
                for (int i = 0; i < listaDeTorneosDto.size(); i++) {
                    if (listaDeTorneosDto.get(i).getIdAdminTorneos() == adminTorneos.getIdUsuario()) {
                        System.out.println(contador + " " + listaDeTorneosDto.get(i).getNombre());
                        torneosDelAdmin.add(listaDeTorneosDto.get(i));
                        contador++;
                    }
                }

                System.out.println("Indique numero de el torneo que desee exportar");
                int opcionUsuario = scanner.nextInt();
                try {
                    TorneoTXT.exportarTorneo(torneosDelAdmin.get(opcionUsuario));
                    GimnasioDTO gimnasioDTO = torneosServicio.obtenerGimnasioPorId(torneosDelAdmin.get(opcionUsuario).getId());
                    TorneoTXT.exportarGimnasio(gimnasioDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No hay torneos disponibles para exportar");
            }
        }
    }

}

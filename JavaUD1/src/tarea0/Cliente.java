package tarea0;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
    private Documentacion id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private boolean subscripcion;
    private char pagoPref;
    private ArrayList<Notificacion> notificacionesCliente;

    public Cliente() {
    }

    public Cliente(Documentacion id, String nombre, String direccion, String telefono, Date fechaNacimiento, char pagoPref) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.subscripcion = false;
        this.pagoPref = pagoPref;
        notificacionesCliente = new ArrayList<>();
    }

    public static Cliente nuevoCliente() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Documentacion documentacion = new NIF();
        boolean estado = false;

        while (!estado) {
            System.out.println("Tipo de documento: NIF (1) o NIE (2)");
            int i = 0;
            try {
                i = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Introduce un número entre (1) o (2)");
                scanner.nextLine();
            }

            try {
                if (i == 1) {
                    System.out.println("Introduce tu número NIF:");
                    String numeroNif = scanner.next();
                    System.out.println("Introduce tu letra del NIF:");
                    char letra = scanner.next().charAt(0);
                    documentacion = new NIF(numeroNif, letra);
                    if (!documentacion.validar(documentacion)) {
                        throw new Exception("NIF Invalido");
                    } else {
                        estado = true;
                    }
                } else if (i == 2) {
                    System.out.println("Introduce la letra inicial de tu NIE:");
                    char letraInicial = scanner.next().charAt(0);
                    System.out.println("Introduce el numero tu NIE:");
                    String numeroNie = scanner.next();
                    System.out.println("Introduce la letra final de tu NIE:");
                    char letraFinal = scanner.next().charAt(0);
                    documentacion = new NIE(numeroNie, letraInicial, letraFinal);
                    if (!documentacion.validar(documentacion)) {
                        throw new Exception("NIE Invalido");
                    } else {
                        estado = true;
                    }
                }
            } catch (Exception ignored ) {
                System.out.println("Error: Introduce su documentación correctamente");
            }
        }

        System.out.println("Introduce tu nombre:");
        String nombreCliente = scanner.next();

        System.out.println("Introduce tu dirección:");
        String direccionCliente = scanner.next();

        System.out.println("Introduce tu teléfono:");
        String telefonoCliente = scanner.next();

        System.out.println("Introduce tu fecha de nacimiento (dd/mm/yyyy):");
        String fechaNacimiento = "";
        Date dateFormateada = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
           fechaNacimiento = scanner.next();
           dateFormateada = format.parse(fechaNacimiento);
       } catch (Exception e) {
           System.out.println("La Fecha Esta Mal");
           System.out.println("Introduce tu fecha de nacimiento (dd/mm/yyyy):");
           fechaNacimiento = scanner.next();
           dateFormateada = format.parse(fechaNacimiento);
       }

        System.out.println("Tipo de pago preferido: E-Efectivo,  T-Transferencia o C-Credito");
        char inicialPago = 0;

        try {
            inicialPago = scanner.next().charAt(0);
        } catch (Exception e) {
            System.out.println("Dato Introducido de Manera Incorrecta");
            inicialPago = scanner.next().charAt(0);
        }

        enum tipoPago {
            EFECTIVO, TRANSFERENCIA, CREDITO
        }
        tipoPago tipoPagoCliente;
        if (inicialPago == 'C' || inicialPago == 'c') {
            tipoPagoCliente = tipoPago.CREDITO;
        } else if (inicialPago == 'T' || inicialPago == 't') {
            tipoPagoCliente = tipoPago.TRANSFERENCIA;
        } else if (inicialPago == 'E' || inicialPago == 'e' ) {
            tipoPagoCliente = tipoPago.EFECTIVO;
        } else {
            telefonoCliente = null;
        }

        Cliente clienteNuevo = new Cliente(documentacion,nombreCliente, direccionCliente, telefonoCliente, dateFormateada, inicialPago);

        return clienteNuevo;
    }

    public void anadirNotificacion (long id, String date) throws Exception {
        Date dateFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat = format.parse(date);

        Scanner scanner = new Scanner(System.in);
        System.out.println("(1) Notificacion por Telefono / (2) Notificacion por Correo");
        int i = 0;
        try {
            i = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Introduce un número entre (1) o (2)");
            scanner.nextLine();
        }

        try {
            if (i == 1) {
                System.out.println("Telefono a donde se va a enviar");
                String direccion = scanner.next();
                System.out.println("¿Que contiene el mensaje?");
                String contenido = scanner.next();

                Notificacion notificacion = new NTelefonica(id, contenido, direccion, dateFormat);
                System.out.println("Notificacion Enviada");
                notificacionesCliente.add(notificacion);
            } else if (i == 2) {
                System.out.println("Correo a donde se va a enviar");
                String direccion = scanner.next();
                System.out.println("¿Que contiene el mensaje?");
                String contenido = scanner.next();

                Notificacion notificacion = new NEmail(id, contenido, direccion, dateFormat);
                System.out.println("Notificacion Enviada");
                notificacionesCliente.add(notificacion);
            }
        } catch (Exception e){
            System.out.println("Error en el envio de la notificación.");
        }
    }

    public int [] notificacionesEn (int ano) throws Exception {
        ArrayList <Notificacion> notificacionesList = new ArrayList<>();

        for (int i = 0; i < notificacionesCliente.size(); i++) {
            LocalDate localDate = notificacionesCliente.get(i).fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getYear() == ano) {
                notificacionesList.add(notificacionesCliente.get(i));
            }
        }

        int [] notificaciones = new int[12];

        for (int s = 0; s < notificacionesList.size(); s++) {
            LocalDate localDate = notificacionesList.get(s).fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int mesActual = localDate.getMonthValue();
            switch (mesActual) {
                case 1:
                    notificaciones[0]++;
                    break;

                case 2:
                    notificaciones[1]++;
                    break;

                case 3:
                    notificaciones[2]++;
                    break;

                case 4:
                    notificaciones[3]++;
                    break;

                case 5:
                    notificaciones[4]++;
                    break;

                case 6:
                    notificaciones[5]++;
                    break;

                case 7:
                    notificaciones[6]++;
                    break;

                case 8:
                    notificaciones[7]++;
                    break;

                case 9:
                    notificaciones[8]++;
                    break;

                case 10:
                    notificaciones[9]++;
                    break;

                case 11:
                    notificaciones[10]++;
                    break;

                case 12:
                    notificaciones[11]++;
                    break;
            }

        }
        return notificaciones;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", subscripcion=" + subscripcion +
                '}';
    }

    public Documentacion getId() {
        return id;
    }

    public void setId(Documentacion id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isSubscripcion() {
        return subscripcion;
    }

    public void setSubscripcion(boolean subscripcion) {
        this.subscripcion = subscripcion;
    }

}

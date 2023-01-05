// package co.edu.univalle.prueba;

// import co.edu.univalle.modelo.*;
// import java.time.*;

// public class PruebaPersistencia {
//     public static void main(String[] args){
//         LocalDate fechaCumpleanios = LocalDate.parse("1990-10-05");
//         LocalDateTime fechaCita = LocalDateTime.of(2022, 12, 12, 14, 20);
//         LocalDateTime fechaCita2 = LocalDateTime.of(2022, 12, 12, 14, 20);
//         LocalDateTime fechaCita3 = LocalDateTime.of(2022, 12, 13, 14, 20);
//         LocalDateTime fechaCita4 = LocalDateTime.of(2022, 12, 16, 14, 00);
    
//         Afiliado afiliado1 = new Afiliado(
//             "C.C", 1155870302, "Daniel", "Carvajal", fechaCumpleanios, "Cali", "Colombia",
//             "M", "O", "+", "Soltero", "Carrera 13 # 5 - 20", "Palmira",
//             "3183546011" , "Contributivo", "Univalle"
//         );
//         Afiliado afiliado2 = new Afiliado(
//             "C.C", 1144582933, "Alejandra", "Muñoz", fechaCumpleanios, "Cali", "Colombia",
//             "F", "A", "-", "Soltero", "Carrera 13 # 5 - 20", "Palmira",
//             "3183546011" , "Contributivo", "Univalle"
//         );
    
//         ServicioMedico servicioMedico100 = new ServicioMedico(
//             100, "Consulta con medicina general", "Este es el servicio de consulta externa " +
//             "por medicina general, que unicamente se presta en la sede Norte."
//         );
    
//         Medico medico1 = new Medico(
//             "C.C", 1011856700, "Julian", "Torres", fechaCumpleanios, "M", servicioMedico100,
//             "Universidad Nacional", "3202256978"
//         );
    
//         Consultorio consultorioNorte501 = new Consultorio(5011, "Norte", 501, servicioMedico100);
    
//         Cita citaMedica1 = new Cita(21, afiliado1, servicioMedico100, medico1, consultorioNorte501, fechaCita);
       

//         EntidadSalud entidadSalud = null;

//         entidadSalud = ManejadorArchivos.leerArchivoBinario("./src/co/edu/univalle/archivos/entidadSalud.bin");

//         if(entidadSalud == null)
//             entidadSalud = new EntidadSalud("Univalle");

//         System.out.println(entidadSalud.getDaoAfiliados().obtenerLista());
//         System.out.println(entidadSalud.getDaoMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoServiciosMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoConsultorios().obtenerLista());
//         System.out.println(entidadSalud.getDaoCitas().obtenerLista());

//         entidadSalud.getDaoAfiliados().añadir(afiliado1);
//         entidadSalud.getDaoAfiliados().añadir(afiliado2);
//         entidadSalud.getDaoMedicos().añadir(medico1);
//         entidadSalud.getDaoServiciosMedicos().añadir(servicioMedico100);
//         entidadSalud.getDaoConsultorios().añadir(consultorioNorte501);
//         entidadSalud.getDaoCitas().añadir(citaMedica1);

//         entidadSalud.getDaoAfiliados().eliminar(afiliado1.getDocumento());
//         entidadSalud.getDaoAfiliados().eliminar(afiliado2.getDocumento());
//         entidadSalud.getDaoMedicos().eliminar(medico1.getDocumento());
//         entidadSalud.getDaoServiciosMedicos().eliminar(servicioMedico100.getIdServicio());
//         entidadSalud.getDaoConsultorios().eliminar(consultorioNorte501.getIdConsultorio());
//         entidadSalud.getDaoCitas().eliminar(citaMedica1.getIdCita());
        
//         System.out.println(entidadSalud.getDaoAfiliados().obtenerLista());
//         System.out.println(entidadSalud.getDaoMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoServiciosMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoConsultorios().obtenerLista());
//         System.out.println(entidadSalud.getDaoCitas().obtenerLista());


//         System.out.println(ManejadorArchivos.generarBackupCitas(entidadSalud, "./src/co/edu/univalle/archivos/backupCitas"));
//         entidadSalud.setDaoCitas(ManejadorArchivos.restaurarCitasDesdeBackup("./src/co/edu/univalle/archivos/backupCitas/Backup citas 2022-12-13 -- 0h 18m 20s.bin"));
//         System.out.println(entidadSalud.getDaoCitas().obtenerLista().get(0).getAfiliado().getEdadEnAños());
//         System.out.println(entidadSalud.getDaoCitas().obtenerLista().get(0).getAfiliado().getDireccion());

//         System.out.println(entidadSalud.getDaoAfiliados().obtenerLista());
//         System.out.println(entidadSalud.getDaoMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoServiciosMedicos().obtenerLista());
//         System.out.println(entidadSalud.getDaoConsultorios().obtenerLista());
//         System.out.println(entidadSalud.getDaoCitas().obtenerLista());

//         System.out.println(ManejadorArchivos.guardarArchivoBinario(entidadSalud, "./src/co/edu/univalle/archivos/entidadSalud.bin"));
//         System.out.println(ManejadorArchivos.generarArchivoAfiliados(entidadSalud, "./src/co/edu/univalle/archivos/registroAfiliados"));
//     }
// }

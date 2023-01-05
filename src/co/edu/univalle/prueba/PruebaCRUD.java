// package co.edu.univalle.prueba;

// import co.edu.univalle.modelo.*;
// import co.edu.univalle.dao.*;
// import java.util.*;
// import java.time.*;


// public class PruebaCRUD {

//     public static void main(String[] args){
//     AfiliadoDao afiliadoDao = new AfiliadoDaoImpl();
//     MedicoDao medicoDao = new MedicoDaoImpl();
//     ServicioMedicoDao servicioMedicoDao = new ServicioMedicoDaoImpl();
//     ConsultorioDao consultorioDao = new ConsultorioDaoImpl();
//     CitaDao citaDao = new CitaDaoImpl();

//     LocalDate fechaCumpleanios = LocalDate.parse("1990-10-05");
//     LocalDateTime fechaCita = LocalDateTime.of(2022, 12, 12, 14, 20);
//     LocalDateTime fechaCita2 = LocalDateTime.of(2022, 12, 12, 14, 20);
//     LocalDateTime fechaCita3 = LocalDateTime.of(2022, 12, 13, 14, 20);
//     LocalDateTime fechaCita4 = LocalDateTime.of(2022, 12, 16, 14, 00);

//     Afiliado afiliado1 = new Afiliado(
//         "C.C", 1155870302, "Daniel", "Carvajal", fechaCumpleanios, "Cali", "Colombia",
//         "M", "O", "+", "Soltero", "Carrera 13 # 5 - 20", "Palermo", "Palmira",
//         "3183546011" , "Contributivo", "Univalle"
//     );
//     Afiliado afiliado2 = new Afiliado(
//         "C.C", 1144582933, "Alejandra", "Muñoz", fechaCumpleanios, "Cali", "Colombia",
//         "F", "A", "-", "Soltero", "Carrera 13 # 5 - 20", "Palermo", "Palmira",
//         "3183546011" , "Contributivo", "Univalle"
//     );

//     ServicioMedico servicioMedico100 = new ServicioMedico(
//         100, "Consulta con medicina general", "Este es el servicio de consulta externa " +
//         "por medicina general, que unicamente se presta en la sede Norte."
//     );

//     Medico medico1 = new Medico(
//         "C.C", 1011856700, "Julian", "Torres", fechaCumpleanios, "M", servicioMedico100,
//         "Universidad Nacional", "3202256978"
//     );

//     Consultorio consultorioNorte501 = new Consultorio(5011, "Norte", 501, servicioMedico100);

//     Cita citaMedica1 = new Cita(21, afiliado1, servicioMedico100, medico1, consultorioNorte501, fechaCita);
    
//     afiliadoDao.añadir(afiliado1);
//     afiliadoDao.añadir(afiliado2);
//     System.out.println(afiliadoDao.obtenerLista());
//     System.out.println(Arreglo.convertirAArreglo(afiliadoDao.obtenerLista()));
//     }
// }

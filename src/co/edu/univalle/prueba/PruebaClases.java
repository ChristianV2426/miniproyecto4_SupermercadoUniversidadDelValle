// package co.edu.univalle.prueba;

// import co.edu.univalle.modelo.*;
// import java.time.*;

// public class PruebaClases {
//     public static void main(String[] args){
//         LocalDate fechaCumpleanios = LocalDate.parse("1990-10-05");
//         LocalDateTime fechaCita = LocalDateTime.of(2022, 12, 12, 14, 20);
//         LocalDateTime fechaCita2 = LocalDateTime.of(2022, 12, 12, 14, 20);
//         LocalDateTime fechaCita3 = LocalDateTime.of(2022, 12, 13, 14, 20);
//         LocalDateTime fechaCita4 = LocalDateTime.of(2022, 12, 16, 14, 00);

//         Afiliado afiliado1 = new Afiliado(
//             "C.C", 1155870302, "Daniel", "Carvajal", fechaCumpleanios, "Cali", "Colombia",
//             "M", "O", "+", "Soltero", "Carrera 13 # 5 - 20", "Palermo", "Palmira",
//             "3183546011" , "Contributivo", "Univalle"
//         );

//         ServicioMedico servicioMedico100 = new ServicioMedico(
//             100, "Consulta con medicina interna", "Este es el servicio de consulta externa " +
//             "por medicina interna, que unicamente se presta en la sede Sur."
//         );

//         Medico medico1 = new Medico(
//             "C.C", 1011856700, "María", "López", fechaCumpleanios, "F", servicioMedico100,
//             "Universidad del Valle", "3117709854"
//         );

//         Consultorio consultorioSur2002 = new Consultorio(200201, "Sur", 2002, servicioMedico100);
        
//         Cita citaMedica1 = new Cita(2400, afiliado1, servicioMedico100, medico1, consultorioSur2002, fechaCita);
//         Cita citaMedica2 = new Cita(2401, afiliado1, servicioMedico100, medico1, consultorioSur2002, fechaCita2);
        

//         // System.out.println(afiliado1);
//         // System.out.println(medico1);
//         // System.out.println(servicioMedico100);
//         // System.out.println(consultorioSur2002);
//         // System.out.println(citaMedica1);
        
//         // afiliado1.agregarCita(citaMedica1);
//         // System.out.println(afiliado1.getCitasAsignadas());
//         // System.out.println(afiliado1.tieneDisponibilidad(fechaCita2));
//         // System.out.println(afiliado1.tieneEstaCitaAgendada(citaMedica1));
//         // afiliado1.eliminarCita(citaMedica1);
//         // System.out.println(afiliado1.getCitasAsignadas());

//         LocalTime horario1 = LocalTime.of(14, 20);
//         LocalTime horario2 = LocalTime.of(14, 20);
//         LocalTime horario3 = LocalTime.of(14, 40);
//         LocalTime horario4 = LocalTime.of(15, 0);
        
//         medico1.agregarDisponibilidadHoraria(DayOfWeek.MONDAY, horario1);
//         medico1.agregarDisponibilidadHoraria(DayOfWeek.MONDAY, horario3);
//         medico1.agregarDisponibilidadHoraria(DayOfWeek.TUESDAY, horario2);
//         System.out.println(medico1.getDisponibilidadHoraria());
//         System.out.println(medico1.tieneEstaFranjaHoraria(DayOfWeek.TUESDAY, horario1));
//         System.out.println(medico1.tieneEstaFranjaHoraria(DayOfWeek.MONDAY, LocalTime.of(14, 20)));
//         System.out.println(medico1.tieneEstaFranjaHoraria(DayOfWeek.MONDAY, horario4));
//         System.out.println(medico1.tieneDisponibilidadParaCita(fechaCita2));
//         System.out.println(medico1.tieneDisponibilidadParaCita(fechaCita3));

//         medico1.eliminarDisponibilidadHoraria(DayOfWeek.TUESDAY, horario2);
//         System.out.println(medico1.getDisponibilidadHoraria());
//         System.out.println(medico1.tieneEstaFranjaHoraria(DayOfWeek.TUESDAY, horario2));
//         System.out.println(medico1.tieneDisponibilidadParaCita(fechaCita3));

//         System.out.println(medico1.getCitasAsignadas());
//         System.out.println(medico1.tieneDisponibilidadParaCita(fechaCita2));
//         medico1.agregarCita(citaMedica2);
//         System.out.println(medico1.getCitasAsignadas());
//         System.out.println(medico1.tieneDisponibilidadParaCita(fechaCita2));
//     }
// }
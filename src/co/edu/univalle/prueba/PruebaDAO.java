// package co.edu.univalle.prueba;

// import co.edu.univalle.modelo.*;
// import co.edu.univalle.dao.*;
// import java.time.*;

// public class PruebaDAO {
//     public static void main(String[] args){
//         AfiliadoDao afiliadoDao = new AfiliadoDaoImpl();
//         MedicoDao medicoDao = new MedicoDaoImpl();
//         ServicioMedicoDao servicioMedicoDao = new ServicioMedicoDaoImpl();
//         ConsultorioDao consultorioDao = new ConsultorioDaoImpl();
//         CitaDao citaDao = new CitaDaoImpl();

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
//             "C.C", 1155870302, "Mario", "Forero", fechaCumpleanios, "Cali", "Colombia",
//             "M", "O", "+", "Soltero", "Carrera 1 # 25 - 20", "Cali",
//             "3183546011" , "Contributivo", "Sanitas"
//         );

//         ServicioMedico servicioMedico100 = new ServicioMedico(
//             100, "Consulta con medicina general", "Este es el servicio de consulta externa " +
//             "por medicina general, que unicamente se presta en la sede Norte."
//         );

//         ServicioMedico servicioMedico200 = new ServicioMedico(
//             200, "Consulta con medicina interna", "Este es el servicio de consulta externa " +
//             "por medicina interna, que unicamente se presta en la sede Sur."
//         );

//         Medico medico1 = new Medico(
//             "C.C", 1011856700, "Julian", "Torres", fechaCumpleanios, "M", servicioMedico100,
//             "Universidad Nacional", "3202256978"
//         );
//         Medico medico2 = new Medico(
//             "C.C", 66981748, "María", "López", fechaCumpleanios, "F", servicioMedico200,
//             "Universidad del Valle", "3117709854"
//         );
//         Medico medico3 = new Medico(
//             "C.C", 66981748, "María Helena", "López", fechaCumpleanios, "F", servicioMedico200,
//             "Universidad del Valle", "3117709854"
//         );

//         Consultorio consultorioNorte501 = new Consultorio(5011, "Norte", 501, servicioMedico100);
//         Consultorio consultorioSur202 = new Consultorio(2022, "Sur", 202, servicioMedico200);
        
//         Cita citaMedica1 = new Cita(21, afiliado2, servicioMedico100, medico1, consultorioNorte501, fechaCita);
//         Cita citaMedica2 = new Cita(22, afiliado2, servicioMedico200, medico2, consultorioSur202, fechaCita2);
        

//         /*
//         //Prueba afiliados
//         System.out.println(false == afiliadoDao.elementoPresente(afiliado1));
//         System.out.println(true == afiliadoDao.añadir(afiliado1));    
//         System.out.println(false == afiliadoDao.añadir(afiliado1));
//         System.out.println(true == afiliadoDao.elementoPresente(afiliado1));

//         System.out.println(afiliadoDao.obtenerElemento(1155870302));
//         System.out.println("\n" +  afiliadoDao.obtenerElemento(552145) + "\n");

//         System.out.println(afiliadoDao.obtenerLista());
//         System.out.println("\n" + afiliadoDao.actualizar(1155870302, afiliado2) + "\n");
//         System.out.println(afiliadoDao.obtenerLista());
//         System.out.println(false == afiliadoDao.eliminar(4544741));
//         System.out.println(true == afiliadoDao.eliminar(1155870302));
//         System.out.println(afiliadoDao.obtenerLista());

//         afiliadoDao.añadir(afiliado1);
//         afiliadoDao.añadir(afiliado2);
//         afiliado1.agregarCita(citaMedica1);
//         System.out.println(afiliadoDao.obtenerAfiliadosConCita());
//         */

//         /*
//         //Prueba médicos
//         System.out.println(false == medicoDao.elementoPresente(medico1));
//         System.out.println(true == medicoDao.añadir(medico1));
//         System.out.println(false == medicoDao.añadir(medico1));
//         System.out.println(true == medicoDao.elementoPresente(medico1));
        
//         System.out.println(medicoDao.obtenerElemento(1011856700));
//         System.out.println("\n" + medicoDao.obtenerElemento(66981748) + "\n");

//         medicoDao.añadir(medico2);
//         System.out.println(medicoDao.obtenerLista());
//         System.out.println(false == medicoDao.actualizar(66981748, medico1));
//         System.out.println(true == medicoDao.actualizar(66981748, medico3));
//         System.out.println(medicoDao.obtenerLista());
//         System.out.println(false == medicoDao.eliminar(100258));
//         System.out.println(true == medicoDao.eliminar(1011856700));
//         System.out.println(true == medicoDao.eliminar(66981748));
//         System.out.println(medicoDao.obtenerLista());

//         medicoDao.añadir(medico1);
//         medicoDao.añadir(medico2);
//         medico2.agregarCita(citaMedica2);
//         System.out.println(medicoDao.obtenerMedicosConCita());
//         System.out.println(medicoDao.obtenerMedicosConEspecialidad(servicioMedico100));

//         System.out.println(medico1.getDiasDisponibles());
//         System.out.println(medico1.getHorasDisponibles(DayOfWeek.FRIDAY));
//         System.out.println(medico1.getHorasDisponibles(DayOfWeek.MONDAY));        
//         */
        
//         ///*
//         //Prueba servicios médicos
//         System.out.println(false == servicioMedicoDao.elementoPresente(servicioMedico100));
//         System.out.println(true == servicioMedicoDao.añadir(servicioMedico100));
//         System.out.println(false == servicioMedicoDao.añadir(servicioMedico100));
//         System.out.println(true == servicioMedicoDao.elementoPresente(servicioMedico100));
//         System.out.println(servicioMedicoDao.obtenerLista());
//         //*/
        
//         /*
//         //Prueba consultorios
//         System.out.println(false == consultorioDao.elementoPresente(consultorioNorte501));
//         System.out.println(true == consultorioDao.añadir(consultorioNorte501));
//         System.out.println(false == consultorioDao.añadir(consultorioNorte501));
//         System.out.println(true == consultorioDao.elementoPresente(consultorioNorte501));

//         consultorioDao.añadir(consultorioSur202);
//         consultorioNorte501.agregarCita(citaMedica1);
//         System.out.println(consultorioDao.obtenerConsultoriosConCita());
//         System.out.println(consultorioDao.obtenerConsultoriosConEspecialidad(servicioMedico200));
//         */

//         /*
//         //Prueba citas
//         System.out.println(false == citaDao.elementoPresente(citaMedica1));
//         System.out.println(true == citaDao.añadir(citaMedica1));
//         System.out.println(false == citaDao.añadir(citaMedica1));
//         System.out.println(true == citaDao.elementoPresente(citaMedica1));
//         System.out.println(true == citaDao.añadir(citaMedica2));
//         System.out.println(true == citaDao.eliminar(22));
//         System.out.println(citaDao.obtenerLista());
//         // System.out.println(citaDao.obtenerCitasConMedico(medico2));
//         */
//     }
// }
